object TurnIt extends App {
  def transpose(A: List[List[Int]]): List[List[Int]] = {
    def first(B: List[Int]): List[List[Int]] = {
      if B.isEmpty then List()
      else List(B.head)::first(B.tail)
    }
    def second(C: List[List[Int]], D: List[Int]): List[List[Int]] = {
      if C.isEmpty then List()
      else {
        List(C.head:::List(D.head)):::second(C.tail, D.tail)
      }
    }
    def third(E: List[List[Int]], F: List[List[Int]]): List[List[Int]] = {
      if F.isEmpty then E
      else {
        third(second(E, F.head), F.tail)
      }
    }
    third(first(A.head), A.tail)
  }
  println(transpose(List(List(1,2,3))))
  println(transpose(List(List(1,2,3), List(4, 5, 6))))
  println(transpose(List(List(1,2,3), List(4, 5, 6), List(7, 8, 9))))
}