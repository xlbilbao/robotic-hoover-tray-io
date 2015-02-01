organization := "me.laiseca"
name := "robotic-hoover"
version := "1.0"
scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
)

mainClass in (Compile, packageBin) := Some("robotichoover.application.App")