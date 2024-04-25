import scala.concurrent.Future
import scala.concurrent.Promise
import scala.concurrent.ExecutionContext.Implicits.global
import org.jsoup._
import java.net._
import collection.JavaConverters._

object Crawler {

  sealed case class WebStats(
    // the total number of (unique) files found
    numFiles: Int,
    // the total number of (unique) file extensions (.jpg is different from .jpeg)
    numExts: Int,
    // a map storing the total number of files for each extension.
    extCounts: Map[String, Int],
    // the total number of words in all html files combined, excluding
    // all html tags, attributes and html comments.
    totalWordCount: Long
  )

  class Checked {
    private var lst: Set[String] = Set()

    def increment(a: Set[String]) = {
      this.lst ++= a
    }
    def get: Set[String] = lst
  }

  def crawlForStats(basePath: String): WebStats = {

    val overall = new WebStats(0, 0, Map(), 0)
    val checked = new Checked

    def pageCrawler(p: String): Int = {
      if checked.get.contains(p) == false then {
        val doc = Jsoup.connect(p).get
        val a = doc.select("a[href~=^[^#?:]*$]").asScala.map(_.attr("abs:href")).filter(_.contains(basePath))
        checked.increment(a.toSet)
        val b = doc.text().split("\\s+").filter(_.nonEmpty)
        val c = b.map(_.toLowerCase())
        println(c.toList)
        b.size
      }
      else 0
    }

    pageCrawler(basePath)

    println(checked.get)
    println(overall.totalWordCount)

    overall
  }

  def main(args: Array[String]) = {
    val sampleBasePath = "https://cs.muic.mahidol.ac.th/courses/ooc/api/"
    val ws = crawlForStats(sampleBasePath)

    println(ws)
  }
}
