import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class ImageControllerSpec extends Specification {
  "ImageController" should {
    "send 400 when no images parameter provided" in new WithApplication {
      val process = route(FakeRequest(GET, "/process")).get

      status(process) must equalTo(BAD_REQUEST)
      contentAsString(process) must equalTo("No images parameter!")
    }

    "send 400 when the number of urls is not 10" in new WithApplication {
      val process = route(FakeRequest(GET, "/process?images=a")).get

      status(process) must equalTo(BAD_REQUEST)
      contentAsString(process) must equalTo("There should be exactly 10 image urls!")
    }

    "send 200 when the number of urls is 10" in new WithApplication {
      val process = route(FakeRequest(GET, "/process?images=a+b+c+d+e+f+g+h+i+j")).get

      status(process) must equalTo(OK)
    }
  }
}
