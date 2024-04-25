object Spaghetti extends App {
  def spaghetti: LazyList[String] = {
    def lop(a: String): LazyList[String] = {
      val nextStr = counts(a, a.head, 0)
      LazyList.cons(nextStr, lop(nextStr))
    }
    def counts(a: String, b: Char, c: Int): String = {
      if a.isEmpty then c.toString+b
      else {
        if a.head != b then c.toString+b+counts(a.tail, a.head, 1)
        else counts(a.tail, b, c+1)
      }
    }
    "1" #:: lop("1")
  }

  def ham: LazyList[String] = ???

  /*
  println(spaghetti)
  println(spaghetti.head)
  println(spaghetti.tail.head)
  println(spaghetti.tail.tail.head)
  println(spaghetti.tail.tail.tail.head)
  println(spaghetti.tail.tail.tail.tail.head)
  println(spaghetti.tail.tail.tail.tail.tail.head)
   */
}
