name := """maze-runner"""
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  specs2 % Test,
  "com.typesafe.play" %% "play-slick" % "1.1.1",
  "mysql" % "mysql-connector-java" % "5.1.16",
  "com.h2database" % "h2" % "1.4.191"
)

routesGenerator := InjectedRoutesGenerator

fork in Test := true
