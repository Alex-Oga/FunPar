object Exercise3 extends App{
  def sumPairList(xs: List[(Int, Int)]): Int = if xs.isEmpty then 0 else xs.head._1+xs.head._2+sumPairList(xs.tail)

  def firsts(xs: List[(Int, Int)]): List[Int] = if xs.isEmpty then List() else xs.head._1::firsts(xs.tail)

  def seconds(xs: List[(Int, Int)]): List[Int] = if xs.isEmpty then List() else xs.head._2::seconds(xs.tail)

  def  pairSumList(xs: List[(Int, Int)]): (Int, Int) = {

    def first(xs: List[(Int, Int)]): Int = {
      if xs.isEmpty then 0
      else xs.head._1 + first(xs.tail)
    }

    def second(xs: List[(Int, Int)]): Int = {
      if xs.isEmpty then 0
      else xs.head._2 + second(xs.tail)
    }

    if xs.isEmpty then (0, 0)
    else (first(xs), second(xs))
  }


  println(sumPairList(List((5,2), (1,4), (2, 7))))
  println(firsts(List((5,2), (1,4), (2, 7))))
  println(seconds(List((5,2), (1,4), (2, 7))))
  println(pairSumList(List((5,2), (1,4), (2, 7))))
}
