package paramonov.valentine.cloudopoly.pics

import akka.util.Timeout

import scala.concurrent.duration._

object ImageSystem {

  sealed trait ImageMessage

  case class ProcessLinks(links: Seq[String]) extends ImageMessage

  case class Finished(urls: Seq[String]) extends ImageMessage
}

trait ImageSystem {
  implicit val timeout = Timeout(5 seconds)
}