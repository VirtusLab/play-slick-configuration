organization := "org.virtuslab"

name := "play-slick-configuration"

version := "1.1.2-SNAPSHOT"

scalaVersion := "2.11.6"

crossScalaVersions := Seq("2.10.4", scalaVersion.value)

resolvers += Resolver.typesafeRepo("releases")

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "com.typesafe.play" %% "play-slick" % "0.9.0-M4",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.typesafe.play" %% "play-test" % "2.4.0-M3" % "test",
  "com.h2database" % "h2" % "1.4.186" % "test"
)

incOptions := incOptions.value.withNameHashing(true)

pomExtra := <url>https://github.com/VirtusLab/unicorn</url>
  <licenses>
    <license>
      <name>Apache-style</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>https://github.com/VirtusLab/unicorn.git</url>
    <connection>scm:git:git@github.com:VirtusLab/unicorn.git</connection>
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

xerial.sbt.Sonatype.sonatypeSettings