package com.example

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

object Example {
  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem[Nothing] = akka.actor.typed.ActorSystem(Behaviors.empty, "TheSystem")

    val route: Route =
      get {
        complete("Hello world")
      }

    Http().newServerAt("0.0.0.0", 8080).bind(route)
  }
}
