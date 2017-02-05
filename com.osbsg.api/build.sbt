name := """com.osbsg.api"""

version := "1.1-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean).dependsOn(core, user).aggregate(core, user)
lazy val core = (project in file("modules/core")).enablePlugins(PlayJava, PlayEbean)
lazy val user = (project in file("modules/user")).enablePlugins(PlayJava, PlayEbean).dependsOn(core)


scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  filters,
  "mysql" % "mysql-connector-java" % "6.0.5",
  "com.typesafe.play" % "play-mailer_2.11" % "5.0.0",
  "org.seleniumhq.selenium" % "selenium-firefox-driver" % "3.0.1",
  "org.seleniumhq.selenium" % "selenium-server" % "3.0.1"
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
