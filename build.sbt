name := """maze-runner"""
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  specs2 % Test
)

routesGenerator := InjectedRoutesGenerator

fork in run := true
fork in Test := true