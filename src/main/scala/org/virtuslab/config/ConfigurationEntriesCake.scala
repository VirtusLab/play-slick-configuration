package org.virtuslab.config

import org.virtuslab.config.util.HasJdbcDriver

trait ConfigurationEntriesCake {
  self: HasJdbcDriver =>

  import profile.api._

  /**
   * Entity for keeping basic configuration in DB.
   *
   */
  private[config] case class ConfigurationEntry(key: String, value: String)

  /**
   * DB table for configuration.
   */
  class ConfigurationEntries(tag: Tag) extends Table[ConfigurationEntry](tag, "configuration") {

    def key = column[String]("key", O.PrimaryKey)

    def value = column[String]("value")

    def * = (key, value) <> (ConfigurationEntry.tupled, ConfigurationEntry.unapply)

  }

}