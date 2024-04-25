object Exercise7 extends App {
  def countNot[T](p: T => Boolean, xs: List[T]): Int = xs match {
    case Nil => 0
    case xs::tlxs => {
      if p(xs) == false then 1+countNot(p, tlxs)
      else countNot(p, tlxs)
    }
  }
  def check(x: String) = x.isEmpty
  def isPrime(n: Int): Boolean = {
    def factor(a: Int, b: Int): Boolean = {
      if b <= 0 then false
      else if a%b == 0 && b != 1 then true
      else factor(a, b-1)
    }
    factor(n, n-1)
  }
  //println(countNot(check, List("Nice", "Memes", "", "mer")))
  //println(isPrime(1))
  //println(isPrime(2))
  //println(isPrime(3))
  //println(isPrime(4))
  //println(isPrime(10))

}
