object Roman extends App {
  def toRoman(n: Int): String = {
    if n / 1000 > 0 || (n+100)/1000 > 0 then {
      if n / 1000 > 0 then "M" + toRoman(n - 1000)
      else "CM" + toRoman(n - 900)
    }
    else if n / 500 > 0 || (n+100)/500 > 0 then {
      if n / 500 > 0 then "D" + toRoman(n - 500)
      else "CD" + toRoman(n - 400)
    }
    else if n / 100 > 0 || (n+10)/100 > 0 then {
      if n / 100 > 0 then "C" + toRoman(n - 100)
      else "XC" + toRoman(n - 90)
    }
    else if n / 50 > 0 || (n+10)/50 > 0 then {
      if n / 50 > 0 then "L" + toRoman(n - 50)
      else "XL" + toRoman(n - 40)
    }
    else if n / 10 > 0 || (n+1)/10 > 0 then {
      if n / 10 > 0 then "X" + toRoman(n - 10)
      else "IX" + toRoman(n - 9)
    }
    else if n / 5 > 0 || (n+1)/5 > 0 then {
      if n/5 > 0 then "V" + toRoman(n - 5)
      else "IV" + toRoman(n - 4)
    }
    else if n / 1 > 0 then {
      "I" + toRoman(n - 1)
    }
    else ""
  }
  println(toRoman(1))
  println(toRoman(5))
  println(toRoman(10))
  println(toRoman(50))
  println(toRoman(100))
  println(toRoman(500))
  println(toRoman(1000))
  println(toRoman(2))
  println(toRoman(3))
  println(toRoman(4))
  println(toRoman(6))
  println(toRoman(7))
  println(toRoman(8))
  println(toRoman(40))
  println(toRoman(42))
  println(toRoman(43))
  println(toRoman(44))
  println(toRoman(45))
  println(toRoman(90))
  println(toRoman(99))
  println(toRoman(900))
}
