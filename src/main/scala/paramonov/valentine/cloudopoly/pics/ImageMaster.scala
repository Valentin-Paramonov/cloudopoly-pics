package paramonov.valentine.cloudopoly.pics

import akka.actor.Actor
import paramonov.valentine.cloudopoly.pics.ImageSystem.{Finished, ProcessLinks}

class ImageMaster extends Actor {
  def receive = {
    case ProcessLinks(links) =>
      //      links.foldLeft(0)((acc, link) => {
      //        "http://bitly.com/" + link
      //        acc + 1
      //      })
      sender ! Finished(links.map(link => "http://bitly.com/" + link))
  }
}
