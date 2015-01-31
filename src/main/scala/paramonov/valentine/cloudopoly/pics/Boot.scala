package paramonov.valentine.cloudopoly.pics

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import spray.can.Http

object Boot extends App with ImageSystem {
  implicit val imageSystem = ActorSystem("imageSystem")
  val imageService = imageSystem.actorOf(Props[ImageServiceActor], "imageService")
  IO(Http) ? Http.Bind(imageService, interface = "localhost", port = 8080)
}
