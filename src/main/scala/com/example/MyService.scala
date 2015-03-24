package com.example

import java.net.URI
import java.sql.{Connection, DriverManager}

import akka.actor.Actor
import spray.http.MediaTypes._
import spray.routing._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with MyService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}

// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

  val myRoute =
    path("db") {
      get {
        complete {
          getTicks()
        }
      }
    } ~
    pathEndOrSingleSlash {
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
              </body>
            </html>
          }
        }
      }
    }

  private def getConnection(): Connection = {
    val dbUri = new URI(System.getenv("DATABASE_URL"))
    val username = dbUri.getUserInfo.split(":")(0)
    val password = dbUri.getUserInfo.split(":")(1)
    val dbUrl = s"jdbc:postgresql://${dbUri.getHost}:${dbUri.getPort}${dbUri.getPath}"
    DriverManager.getConnection(dbUrl, username, password)
  }

  private def getTicks(): String = {
    val connection = getConnection()
    try {
      val stmt = connection.createStatement
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)")
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())")
      val rs = stmt.executeQuery("SELECT tick FROM ticks")
      var out = ""
      while (rs.next) {
        out += "Read from DB: " + rs.getTimestamp("tick") + "\n"
      }
      out
    } finally {
      connection.close()
    }
  }
}
