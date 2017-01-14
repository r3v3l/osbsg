name := """core"""

version := "0.0.2-SNAPSHOT"

lazy val core = project.in(file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  filters,
  "com.typesafe.play" %% "play-mailer" % "5.0.0",
  "org.apache.commons" % "commons-io" % "1.3.2"
)