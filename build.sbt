organization := "org.virtuslab"

name := "play-slick-configuration"

version := "1.1.2"

scalaVersion := "2.11.6"

resolvers += Resolver.typesafeRepo("releases")

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "com.typesafe.play" %% "play-slick" % "0.9.0-M4",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.typesafe.play" %% "play-test" % "2.4.0-M3" % "test",
  "com.h2database" % "h2" % "1.4.186" % "test"
)

incOptions := incOptions.value.withNameHashing(true)