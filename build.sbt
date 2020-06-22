import sbt.{ Credentials, Path }

organization := "org.virtuslab"

name := "play-slick-configuration"

version := "2.5.0-SNAPSHOT"

val scala_2_12 = "2.12.11"
val scala_2_13 = "2.13.2"

scalaVersion := scala_2_13

crossScalaVersions := List(scala_2_13, scala_2_12)

resolvers += Resolver.typesafeRepo("releases")

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "com.typesafe.play" %% "play-test" % "2.8.2" % "test",
  "com.h2database" % "h2" % "1.4.199" % "test",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
)

pomExtra := <url>https://github.com/VirtusLab/play-slick-configuration</url>
  <licenses>
    <license>
      <name>Apache-style</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>https://github.com/VirtusLab/play-slick-configuration.git</url>
    <connection>scm:git:git@github.com:VirtusLab/play-slick-configuration.git</connection>
  </scm>
  <developers>
    <developer>
      <id>VirtusLab</id>
      <name>VirtusLab</name>
      <url>http://www.virtuslab.com/</url>
    </developer>
    <developer>
      <id>JerzyMuller</id>
      <name>Jerzy Müller</name>
      <url>https://github.com/Kwestor</url>
    </developer>
  </developers>

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xlint"
)

xerial.sbt.Sonatype.sonatypeSettings

publishTo := sonatypePublishTo.value

fork in Test := true

parallelExecution in Test := false
