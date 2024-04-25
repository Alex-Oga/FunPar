import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.matching.Regex

object TopK {

  def words(strGrp: Vector[String]): Vector[String] = {
    strGrp.flatMap(_.toLowerCase().split("\\s+").filter(_.nonEmpty)).filter(_ matches("^[a-z]+(-[a-z]+){0,2}$"))
  }

  def topKWords(fileSpec: String): Vector[(String, Int)] = {
    val lines = io.Source.fromFile(fileSpec).getLines.toVector
    val total = io.Source.fromFile(fileSpec).getLines.size
    val NUM_GROUPS = 4
    val groups = lines.grouped(total / NUM_GROUPS)
    val a = groups.map(rng => Future { words(rng) }).toVector
    val b = Future.sequence(a)

    Await.result(b, Duration.Inf)

    val c = b.value.get.get.flatten
    val d = c.groupBy(identity).maxBy(_._2.size)._2.size
    val e = c.groupBy(identity).filter(_._2.size == d).map(x => x._1).toVector.sorted
    val f = e.map(x => (x, d))
    f
  }

  def main(args: Array[String]) = {
    val a = topKWords("D:\\Mahidol\\ICCS 311 Functional and Parallel Programming\\Assignment3\\a3-starter\\topk\\src\\main\\scala\\text.txt")
    println(a)
  }
}
