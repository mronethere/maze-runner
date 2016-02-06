lazy val rootSettings = Seq(
  name := "maze-runner",
  scalaVersion := "2.11.7",
  version := "1.0-SNAPSHOT",
  routesGenerator := InjectedRoutesGenerator,
  fork in Test := true,
  libraryDependencies ++= Seq(
    specs2 % Test,
    "com.typesafe.play" %% "play-slick" % "1.1.1",
    "mysql" % "mysql-connector-java" % "5.1.16",
    "com.h2database" % "h2" % "1.4.191"
  )
)

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(rootSettings: _*)

lazy val dbUtils = (project in file("db-utils"))
  .dependsOn(root)
  .settings(
    name := "database utils",
    scalaVersion := "2.11.7",
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.1.1"
    )
  ).disablePlugins(BackgroundRunPlugin)
