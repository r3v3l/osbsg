name := """com.osbsg"""

version := "0.1.3-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean).aggregate(core).dependsOn(core);

lazy val core = project.in(file("modules/core"))

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  filters,
  "com.typesafe.play" %% "play-mailer" % "5.0.0",
  "org.apache.commons" % "commons-io" % "1.3.2",
  "org.postgresql" % "postgresql" % "9.4.1212"
)

playEbeanModels in Compile := Seq("models.*")
playEbeanDebugLevel := 4
playEbeanAgentArgs += ("detect" -> "false")
inConfig(Test)(PlayEbean.scopedSettings)
playEbeanModels in Test := Seq("models.*")


javaOptions in Test ++= Seq(
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9998",
  "-Xms512M",
  "-Xmx1536M",
  "-Xss1M",
  "-XX:MaxPermSize=384M"
)