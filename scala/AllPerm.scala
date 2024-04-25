object AllPerm extends App{
  def allPerm(n: Int): List[List[Int]] = {
    def first(A: Int): List[Int] = {
      if A == 0 then List()
      else A :: first(A - 1)
    }

    def second(A: List[Int], B: Int): Int = {
      if B == 0 then A.head
      else second(A.tail, B - 1)
    }

    def search(A: List[Int], B: Int, C: Int): List[Int] = {
      if A.head == B then C::A.tail
      else {
        A.head::search(A.tail, B, C)
      }
    }

    def third(A: List[Int], B: Int, C: Int, D: List[Int], E: Int): List[Int] = {
      if C == 0 && A.head > B then B :: search(A.tail, B, A.head)
      else if C == 0 then B :: A.tail
      else if A.head == B then second(D, E) :: third(A.tail, B, C - 1, D, E)
      else {
        A.head :: third(A.tail, B, C - 1, D, E)
      }
    }

    def fourth(A: List[Int], B: Int, C: Int, D: List[Int], E: Int, F: Int): List[List[Int]] = {
      if C == F then List()
      else {
        third(A, B, C, D, E) :: fourth(A, B, C + 1, D, E + 1, F)
      }
    }

    def fifth(A: List[Int], B: Int): List[List[Int]] = {
      if B == 0 then List()
      else {
        fourth(A, B, 0, A, 0, A.length) ::: fifth(A, B - 1)
      }
    }
    if n == 0 then List()
    else {
      val a = first(n)
      //List(a)
      //println(second(a, 0))
      //println(third(a, a.head, 1, a, 1))
      //println(fourth(a, 2, 0, a, 0, n))
      //List(a)
      //fifth(a, n)
      //println(fourth(a, n-1, 0, a, 0))
      //println(clean(b.head, b))
      //fourth(a, n, 0, a, 0, n)
      fifth(a, n)
    }
  }
  println(allPerm(1))
  println(allPerm(2))
  println(allPerm(3))
  println(allPerm(4))
}
