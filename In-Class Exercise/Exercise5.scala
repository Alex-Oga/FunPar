object Exercise5 extends App{
  def zip(x : List[Int], y: List[Int]) : List[(Int, Int)] = (x, y) match {
    case (Nil, Nil) => Nil
    case (x::xt, Nil) => (x, 0)::zip(xt, List())
    case (Nil, y::yt) => (0, y)::zip(List(), yt)
    case (x::xt, y::yt) => (x, y)::zip(xt, yt)
  }
  def unzip(zipped : List[(Int, Int)]) : (List[Int], List[Int]) = zipped match {
    case Nil => (Nil, Nil)
    case zipped::tlzip => {
      val a = unzip(tlzip)._1
      val b = unzip(tlzip)._2
      ((zipped._1::a), (zipped._2::b))
    }
  }
  def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
    case (Nil, Nil) => Nil
    case (_, Nil) => xs
    case (Nil, _) => ys
    case (xs::tlx, ys::tly) =>
      if xs<ys then xs::merge(tlx, ys::tly) else ys::merge(xs::tlx, tly)
  }
  //println(zip(List(3, 2, 5), List(6, 1, 9)))
  //println(zip(List(3, 2, 5), List(6, 1)))
  //println(zip(List(3, 2), List(6, 1, 9)))
  //println(zip(List(3, 2, 5), List()))
  //println(unzip(List((3, 6), (2, 1), (5, 9))))
  //println(unzip(List((3, 6), (2, 1))))
  //println(unzip(List((3, 6))))
  //println(unzip(List()))
  //println(merge(List(1, 4, 5), List(0, 2, 6)))
  //println(merge(List(1, 4, 5), List(1, 4, 6)))
  //println(merge(List(0, 2, 6), List(1, 4, 5)))
  //println(merge(List(), List()))
}
