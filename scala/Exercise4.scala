object Exercise4 extends App{
  def find(xs: List[(Int, String)], key: Int): Option[String] = {
    if xs.isEmpty then None else {
      val tlAns = find(xs.tail, key)
      if xs.head._1 == key then Some(xs.head._2)
      else tlAns
    }
  }
  def rev(xs: List[Int]): List[Int] = {
    if xs.isEmpty then List()
    else {
      rev(xs.tail) ::: List(xs.head)
    }
  }

  def fib(n: Int): Long = {
    if n <= 1 then 0
    else if n == 2 then 1
    else fib(n - 1) + fib(n - 2)
  }

  // println(find(List((1, "One"), (2, "Two"), (3, "Three")), 1))
  // println(find(List((1, "One"), (2, "Two"), (3, "Three")), 4))
  // println(find(List(), 1))
  // println(rev(List(1, 2, 3, 4, 5)))
  // println(rev(List()))
  // println(fib(1))
  // println(fib(2))
  // println(fib(3))
  // println(fib(4))
  // println(fib(5))
  // println(fib(6))
  // println(fib(7))
  // println(fib(8))
  // println(fib(9))
  // println(fib(10))
  // println(fib(11))
}
