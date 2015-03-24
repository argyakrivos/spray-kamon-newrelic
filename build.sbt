enablePlugins(JavaAppPackaging)

name := "scala-getting-started"

version := "1.0"

scalaVersion := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"                %% "spray-can"      % sprayV,
    "io.spray"                %% "spray-routing"  % sprayV,
    "com.typesafe.akka"       %% "akka-actor"     % akkaV,
    "org.postgresql"          %  "postgresql"     % "9.4-1201-jdbc41",
    "com.newrelic.agent.java" %  "newrelic-agent" % "3.14.0"
  )
}

Revolver.settings
