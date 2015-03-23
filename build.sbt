enablePlugins(JavaAppPackaging)

name := "scala-getting-started"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-http" % "6.24.0",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"
)
