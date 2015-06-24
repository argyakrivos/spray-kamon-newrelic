package com.example

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import kamon.Kamon
import spray.can.Http

import scala.concurrent.duration._
import scala.util.Properties

object Boot extends App {
  // start Kamon
  Kamon.start()

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("on-spray-can")

  // create and start our service actor
  val service = system.actorOf(Props[MyServiceActor], "demo-service")

  implicit val timeout = Timeout(5.seconds)
  // start a new HTTP server on port with our service actor as the handler
  val port = Properties.envOrElse("PORT", "8080").toInt
  IO(Http) ? Http.Bind(service, interface = "0.0.0.0", port = port)
}
