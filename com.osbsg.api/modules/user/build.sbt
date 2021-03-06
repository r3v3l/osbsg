name := "user"

version := "0.0.3-BUILD"

lazy val user = (project in file(".")).enablePlugins(PlayJava, PlayEbean).dependsOn(core).aggregate(core)
lazy val core = (project in file("modules/core")).enablePlugins(PlayJava, PlayEbean)
scalaVersion := "2.11.7"
libraryDependencies ++= Seq (
  javaJdbc,
  cache,
  javaWs,
  filters,
  "mysql" % "mysql-connector-java" % "6.0.5",
  "com.typesafe.play" % "play-mailer_2.11" % "5.0.0"
)

playEbeanModels in Compile := Seq("models.core.*")
playEbeanDebugLevel := 4
playEbeanAgentArgs += ("detect" -> "false")
inConfig(Test)(PlayEbean.scopedSettings)
playEbeanModels in Test := Seq("models.*")