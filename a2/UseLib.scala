object UseLib extends App {
  def onlyBeginsWithLower(xs: Vector[String]): Vector[String] = xs.filter(_.head.isLower)

  def longestString(xs: Vector[String]): Option[String] = {
    if xs.isEmpty then None
    else {
      Some(xs.maxBy(_.length))
    }
  }

  def longestLowercase(xs: Vector[String]): Option[String] = {
    val a = onlyBeginsWithLower(xs)
    longestString(a)
  }

  //println(onlyBeginsWithLower(Vector("Nice", "meme", "Gamer")))
  //println(onlyBeginsWithLower(Vector("Nice", "Gamer")))
  //println(onlyBeginsWithLower(Vector()))
  //println(longestString(Vector("Nice", "Memes", "Gamer")))
  //println(longestLowercase(Vector("Nice", "memes", "ga", "Mer")))
}
