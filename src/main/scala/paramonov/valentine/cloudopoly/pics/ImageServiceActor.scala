package paramonov.valentine.cloudopoly.pics

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import paramonov.valentine.cloudopoly.pics.ImageSystem.{Finished, ProcessLinks}
import spray.http.{HttpResponse, StatusCodes}
import spray.json.DefaultJsonProtocol._
import spray.json._
import spray.routing.HttpService

import scala.concurrent.ExecutionContext.Implicits.global

class ImageServiceActor extends Actor with ImageService {

  def actorRefFactory = context

  def receive = runRoute(processing)
}

trait ImageService extends HttpService with ImageSystem {
  val master = ActorSystem("imageSystem").actorOf(Props[ImageMaster])
  val processing = path("process") {
    get {
      parameter("images") { images =>
        complete {
          val links = images.split(' ')
          if (links.length != 10) {
            HttpResponse(StatusCodes.BadRequest, "The number of images must be exactly 10")
          } else {
            processLinks(links)
          }
        }
      }
    }
  }

  def processLinks(links: Seq[String]) = {
    val future = master ? ProcessLinks(links)
    future.mapTo[Finished].map({
      case Finished(links) => links.toJson.prettyPrint
    }).recover {
      case _ => "Processing Failed"
    }
  }
}
