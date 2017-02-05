name := "core"

version := "0.0.4-BUILD"

lazy val core = (project in file(".")).enablePlugins(PlayJava, PlayEbean)
scalaVersion := "2.11.7"
libraryDependencies ++= Seq (
  javaJdbc,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "6.0.5"
)

playEbeanModels in Compile := Seq("models.core.*")
playEbeanDebugLevel := 4
playEbeanAgentArgs += ("detect" -> "false")
inConfig(Test)(PlayEbean.scopedSettings)
playEbeanModels in Test := Seq("models.*")