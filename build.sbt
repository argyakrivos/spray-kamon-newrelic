name := "spray-kamon-newrelic"

version := "1.2"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-Xfatal-warnings", "-feature", "-Xfuture")

libraryDependencies ++= {
  val akkaV = "2.3.14"
  val sprayV = "1.3.3"
  val kamonV = "0.5.1"
  Seq(
    "com.typesafe.akka"       %% "akka-actor"      % akkaV,
    "io.spray"                %% "spray-can"       % sprayV,
    "io.spray"                %% "spray-routing"   % sprayV,
    "ch.qos.logback"          %  "logback-classic" % "1.1.3",
    "io.kamon"                %% "kamon-core"      % kamonV,
    "io.kamon"                %% "kamon-spray"     % kamonV,
    "io.kamon"                %% "kamon-newrelic"  % kamonV,
    "org.postgresql"          %  "postgresql"      % "9.4-1203-jdbc42",
    "org.aspectj"             %  "aspectjweaver"   % "1.8.7",
    "com.newrelic.agent.java" %  "newrelic-agent"  % "3.20.0"
  )
}

enablePlugins(JavaAppPackaging)
