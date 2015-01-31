package paramonov.valentine.cloudopoly.pics

import org.specs2.mutable.Specification
import spray.http.StatusCodes._
import spray.routing.{MissingQueryParamRejection, HttpService}
import spray.testkit.Specs2RouteTest

class ImageServiceSpec extends Specification with Specs2RouteTest with ImageService {
  def actorRefFactory = system

  "ImageService" should {
    "send 404 when no images parameter provided" in {
      Get("/process") ~> processing ~> check {
        rejection === MissingQueryParamRejection("images")
      }
    }

    "send 400 when the number of urls is not 10" in {
      Get("/process?images=a") ~> processing ~> check {
        status === BadRequest
        responseAs[String] === "The number of images must be exactly 10"
      }
    }

    "send 200 when the number of urls is 10" in {
      Get("/process?images=a+b+c+d+e+f+g+h+i+j") ~> processing ~> check {
        status === OK
      }
    }
  }
}
