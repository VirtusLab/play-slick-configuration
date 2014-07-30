organization := "org.virtuslab"

name := "play-slick-configuration"

version := "1.0.1-SNAPSHOT"

scalaVersion := "2.10.4"

resolvers += Resolver.typesafeRepo("releases")

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "2.0.2",
  "com.typesafe.play" %% "play-slick" % "0.6.1",
  "org.scalatest" %% "scalatest" % "2.2.0" % "test",
  "com.typesafe.play" %% "play-test" % "2.2.4" % "test",
  "com.h2database" % "h2" % "1.4.180" % "test"
)

incOptions := incOptions.value.withNameHashing(true)
