package com.example

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn

object Example {
  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem[Nothing] = akka.actor.typed.ActorSystem(Behaviors.empty, "TheSystem")

    val route: Route =
      get {
        complete("Hello world")
      }

    val bindingFuture = Http().newServerAt("0.0.0.0", 8080).bind(route)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
