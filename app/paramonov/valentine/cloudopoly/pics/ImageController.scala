package paramonov.valentine.cloudopoly.pics

import play.api.mvc._

object ImageController extends Controller {
  def processPics = Action { implicit request =>
    val urls: Option[String] = request.getQueryString("images")
    urls match {
      case Some(x) => {
        val urls = x.split(' ')
        if (urls.length == 10) {
          Ok(ImageDownloader.download(urls))
        } else {
          BadRequest("There should be exactly 10 image urls!")
        }
      }
      case None => BadRequest("No images parameter!")
    }
  }
}
