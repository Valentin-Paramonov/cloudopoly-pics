package paramonov.valentine.cloudopoly.pics

object ImageDownloader {
  def download(urls: Seq[String]) : String = {
    urls.map({ s => "http://bitly.com/" + s}).toString()
  }
}
