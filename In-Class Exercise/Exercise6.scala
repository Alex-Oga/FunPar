object Exercise6 extends App{
  def unzip[A, B](xs: List[(A, B)]): (List[A], List[B]) = xs match {
    case Nil => (Nil, Nil)
    case xs:: tlxs => {
      val a = unzip(tlxs)._1
      val b = unzip(tlxs)._2
      ((xs._1 :: a), (xs._2 :: b))
    }
  }
  def countWhile[T](xs: List[T], key: T): Int = xs match {
    case Nil => 0
    case xs::tlxs =>
      if xs == key then 1+countWhile(tlxs, key)
      else countWhile(tlxs, key)
  }
  def topK(xs: List[Int], k: Int): List[Int] = xs match {
    case Nil => Nil
    case xs::tlxs => {
      val a = xs::tlxs.filter(_ == xs)
      val b = tlxs.filter(_ != xs)
      if a.length == k then a.head::topK(b, k)
      else topK(b, k)
    }
  }
  //println(unzip(List((3, 6), (2, 1), (5, 9))))
  //println(unzip(List(("nice", "Oof"), ("Mem", "Ento"))))
  //println(countWhile(List(1, 2, 3, 4, 1), 1))
  //println(countWhile(List("nice", "what", "the", "dog", "doin"), "cheese"))
  //println(topK(List(1, 2, 3, 1), 2))
  //println(topK(List(1, 2, 1, 1), 2))
}
