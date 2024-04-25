import scala.concurrent.Future
import scala.concurrent.Promise
import scala.concurrent.ExecutionContext.Implicits.global

object DataCrunch {

  trait DataProvider {
    def get(onSuccess: Seq[String] => Unit,
            onFailure: () => Unit): Unit
  };


  object LoremIpsum extends DataProvider {
    override def get(onSuccess: Seq[String] => Unit,
            onFailure: () => Unit): Unit = {
      val lorem =
        """Lorem ipsum dolor sit amet, consectetur adipiscing elit.
        Cras nec sagittis justo. Nullam dignissim ultricies velit a tempus.
        Aenean pharetra semper elit eu luctus. Fusce maximus lacus eget magna
        ultricies, ac suscipit justo lobortis. Nullam pellentesque lectus
        at tellus gravida rhoncus. Nam augue tortor, rutrum et eleifend id,
        luctus ut turpis. Vivamus nec sodales augue.

        Suspendisse non erat diam. Mauris hendrerit neque at sem laoreet
          vehicula. Sed aliquam urna a efficitur tincidunt. In non purus
        tincidunt, molestie velit vulputate, mollis nisl. Pellentesque
        rhoncus molestie bibendum. Etiam sit amet felis a orci fermentum
        tempor. Duis ante lacus, luctus ac scelerisque eget, viverra ut felis."""
      onSuccess(lorem.split("\n"))
    }
  }

  object FailedSample extends DataProvider {
    override def get(onSuccess: Seq[String] => Unit,
                     onFailure: () => Unit): Unit = {
      onFailure()
    }
  }

  // This returns a DataProvider that feeds the "consumer" all the lines from a
  // file indicated by filename.
  def FileSource(filename: String): DataProvider = new DataProvider {
    override def get(onSuccess: Seq[String] => Unit, onFailure: () => Unit): Unit = {
      try {
        val lines = io.Source.fromFile(filename)
          .getLines
          .toVector
        onSuccess(lines)
      }
      catch {
        case _: Throwable => onFailure()
      }
    }
  }

  def dataProviderFuture(dp: DataProvider): Future[Seq[String]] = {
    val a = Promise[Seq[String]]
    def funcOnSuccess(lines: Seq[String]) = a.success(lines)
    def funcOnFailure() = a.failure(Exception("failed"))

    dp.get(funcOnSuccess, funcOnFailure)

    a.future
  }

  def highestFreq(linesFut: Future[Seq[String]]): Future[(String, Double)] = {
    val b = Promise[(String, Double)]
    // flatmap or map
    try {
      val test = linesFut.value.get.get.flatMap(_.split("\\s+").filter(_.nonEmpty))
      val c = test.groupBy(identity).maxBy(_._2.size)._1
      val d = test.groupBy(identity).maxBy(_._2.size)._2.size
      val total = test.size
      b.success(c, d.toDouble / total)
    } catch {
      case _: Throwable => b.failure(Exception("failed"))
    }
    b.future
  }

  def main(args: Array[String]) = {
    // Example of how DataProvider is typically used. Comment out the block of code
    // below so it doesn't print some random garbage.
    /*
    def funcOnSuccess(lines: Seq[String]) = lines.foreach(println(_))
    def funcOnFailure() = println("Failed")
    val sampleProvider = LoremIpsum
    sampleProvider.get(funcOnSuccess, funcOnFailure)
     */
    val fileProvider: DataProvider = FileSource("D:\\Mahidol\\ICCS 311 Functional and Parallel Programming\\Assignment3\\a3-starter\\crunch\\src\\main\\scala\\text.txt")
    val a = dataProviderFuture(fileProvider)
    println(a)
    val b = highestFreq(a)
    println(b)
    val fileProvider2: DataProvider = FileSource("D:\\Mahidol\\ICCS 311 Functional and Parallel Programming\\Assignment3\\a3-starter\\crunch\\src\\main\\scala")
    val c = dataProviderFuture(fileProvider2)
    println(c)
    val d = highestFreq(c)
    println(d)
  }

}
