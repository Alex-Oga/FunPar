object Happy extends App {
  def sumOfDigitsSquared(n: Int): Int = {
    if n <= 9 then n*n
    else {
      val a = n%10
      a*a+sumOfDigitsSquared(n/10)
    }
  }
  def isHappy(n: Int): Boolean = {
    val a = sumOfDigitsSquared(n)
    if a==1 then true
    else if a==4 then false
    else isHappy(a)
  }
  def kThHappy(k: Int): Int = {
    def check(n: Int, x: Int, con: Int): Int = {
      if n != x then {
        if isHappy(con) != true then check(n, x, con+1)
        else check(n+1, x, con+1)
      }
      else if isHappy(con) != true then check(n, x, con+1)
      else con
    }
    check(1, k, 1)
  }
  println(sumOfDigitsSquared(7))
  println(sumOfDigitsSquared(145))
  println(sumOfDigitsSquared(199))
  println(isHappy(100))
  println(isHappy(111))
  println(isHappy(1234))
  println(isHappy(989))
  println(kThHappy(1))
  println(kThHappy(3))
  println(kThHappy(11))
  println(kThHappy(19))
}
