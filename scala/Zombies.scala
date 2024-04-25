object Zombies extends App {
  def countBad(hs: List[Int]): Int = {
    def split(A: Int, B: List[Int]): Int = {
      if B.isEmpty then 0
      else {
        if A < B.head then 1 + split(A, B.tail)
        else 0 + split(A, B.tail)
      }
    }
    if hs.isEmpty then 0
    else {
      split(hs.head, hs.tail) + countBad(hs.tail)
    }
  }
  println(countBad(List(35, 22, 10)))
  println(countBad(List(3, 1, 4, 2)))
  println(countBad(List(5, 4, 11, 7)))
  println(countBad(List(1, 7, 22, 13, 25, 4, 10, 34, 16, 28, 19, 31)))
}
