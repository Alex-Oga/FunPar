object Aloud extends App {
  def readAloud(xs: List[Int]): List[Int] = {
    def read(xs: List[Int], count: Int, cur: Int): List[Int] = {
      if xs.tail.isEmpty then count+1::xs.head::List()
      else if xs.tail.head != xs.head then count+1::xs.head::read(xs.tail, 0, xs.tail.head)
      else {
        read(xs.tail, count+1, xs.head)
      }
    }
    if xs.isEmpty then List()
    else read(xs, 0, xs.head)
  }
  println(readAloud(List(1, 1, 1)))
  println(readAloud(List(-1, 2, 7)))
  println(readAloud(List(3, 3, 8, -10, -10, -10)))
  println(readAloud(List(3, 3, 1, 1, 3, 1, 1)))
  println(readAloud(List(1, 1, 2, 2)))
}
