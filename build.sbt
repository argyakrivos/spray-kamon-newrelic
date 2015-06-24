name := "spray-kamon-newrelic"

version := "1.1"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.11"
  val sprayV = "1.3.3"
  val kamonV = "0.4.0"
  Seq(
    "com.typesafe.akka"       %% "akka-actor"     % akkaV,
    "io.spray"                %% "spray-can"      % sprayV,
    "io.spray"                %% "spray-routing"  % sprayV,
    "io.kamon"                %% "kamon-core"     % kamonV,
    "io.kamon"                %% "kamon-spray"    % kamonV,
    "io.kamon"                %% "kamon-newrelic" % kamonV,
    "org.postgresql"          %  "postgresql"     % "9.4-1201-jdbc41",
    "org.aspectj"             %  "aspectjweaver"  % "1.8.6",
    "com.newrelic.agent.java" %  "newrelic-agent" % "3.17.0"
  )
}

enablePlugins(JavaAppPackaging)
