package org.virtuslab.config

import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import org.joda.time.{Duration => JDuration}
import scala.concurrent.duration.Duration
import scala.concurrent.duration.FiniteDuration

/**
 * Type class for reading and writing configuration values from/to db.
 *
 * @tparam A type of field to save
 */
trait ConfigurationSerializer[A] {
  def write(a: A): String

  def read(s: String): A
}

/**
 * Common serializers.
 */
object ConfigurationSerializer {

  /** [[java.lang.String]] serializer */
  implicit val stringSerializer = new ConfigurationSerializer[String] {
    def write(a: String) = a

    def read(a: String) = a
  }

  /** [[scala.Int]] serializer */
  implicit val intSerializer = new ConfigurationSerializer[Int] {
    def write(a: Int) = a.toString

    def read(a: String) = Integer.valueOf(a)
  }

  /** [[scala.Long]] serializer */
  implicit val longSerializer = new ConfigurationSerializer[Long] {
    def write(a: Long) = a.toString

    def read(a: String) = java.lang.Long.valueOf(a)
  }

  /** [[org.joda.time.DateTime]] serializer - uses [[org.joda.time.format.ISODateTimeFormat.dateTime]]. */
  implicit val dateTimeSerializer = new ConfigurationSerializer[DateTime] {
    def write(a: DateTime) = ISODateTimeFormat.dateTime.print(a)

    def read(a: String) = ISODateTimeFormat.dateTime.parseDateTime(a)
  }

  /** [[scala.concurrent.duration.FiniteDuration]] serializer */
  implicit val finiteDurationSerializer = new ConfigurationSerializer[FiniteDuration] {
    def write(a: FiniteDuration) = a.toString()

    def read(a: String) = Duration.create(a) match {
      case fd: FiniteDuration => fd
      case inf => throw new IllegalArgumentException(s"Infinite duration: $inf")
    }
  }

  /** [[org.joda.time.Duration]] serializer */
  implicit val durationSerializer = new ConfigurationSerializer[JDuration] {
    def write(a: JDuration) = a.toString

    def read(a: String) = JDuration.parse(a)
  }

  /** [[scala.Boolean]] serializer */
  implicit val booleanSerializer = new ConfigurationSerializer[Boolean] {
    def write(a: Boolean) = a.toString

    def read(a: String) = a.toBoolean
  }
}