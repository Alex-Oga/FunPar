object DateUtil extends App {
  type Date = (Int, Int, Int)

  def isOlder(x: Date, y: Date): Boolean = {
    if x._3 < y._3 then true
    else if x._3 > y._3 then false
    else {
      if x._2 < y._2 then true
      else if x._2 > y._2 then false
      else {
        if x._1 < y._1 then true
        else if x._1 > y._1 then false
        else false
      }
    }
  }

  def numberInMonth(xs: List[Date], month: Int): Int = xs match {
    case Nil => 0
    case xs::tlxs => {
      if xs._2==month then 1+numberInMonth(tlxs, month)
      else numberInMonth(tlxs, month)
    }
  }

  def numberInMonths(xs: List[Date], months: List[Int]): Int = months match {
    case Nil => 0
    case months::tlmonths => numberInMonth(xs, months)+numberInMonths(xs, tlmonths)
  }

  def datesInMonth(xs: List[Date], month: Int): List[Date] = xs match {
    case Nil => Nil
    case xs::tlxs => {
      if xs._2==month then xs::datesInMonth(tlxs, month)
      else datesInMonth(tlxs, month)
    }
  }

  def datesInMonths(xs: List[Date], months: List[Int]): List[Date] = {
    def desc(a: Date, b: List[Int]): Date = {
      if b.isEmpty then null
      else {
        if a._2==b.head then a
        else desc(a, b.tail)
      }
    }
    if xs.isEmpty then Nil
    else desc(xs.head, months)::datesInMonths(xs.tail, months)
  }

  def dateToString(d: Date): String = {
    def month(a: List[String], b: Int, c: Int): String = a match {
      case Nil => ""
      case a::tla => {
        if b == c then a
        else month(tla, b, c+1)
      }
    }
    month(List("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"), d._2, 1)+"-"+d._1+"-"+d._3
  }

  def whatMonth(n: Int, yr: Int): Int = {
    def LeapOrNot(a: Int): Boolean = {
      if a%400==0 then true
      else if a%100==0 then false
      else if a%4==0 then true
      else false
    }
    def day(a: List[Int], b: Int, c: Int): Int = {
      if b <= 0 then c
      else day(a.tail, b-a.head, c+1)
    }
    val a = LeapOrNot(yr)
    if n==366 && a==false then 1
    else if a then day(List(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31), n, 0)
    else day(List(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31), n, 0)
  }

  def oldest(dates: List[Date]): Option[Date] = {
    def old(a: List[Date], b: Date): Date = {
      if a.isEmpty then b
      else {
        if isOlder(b, a.head) then old(a.tail, b)
        else old(a.tail, a.head)
      }
    }
    if dates.isEmpty then None
    else {
      val a = old(dates, dates.head)
      if a == null then None
      else Some(a)
    }
  }

  def isReasonableDate(d: Date): Boolean = {
    def Leap(a: Int): Boolean = {
      if a % 400 == 0 then true
      else if a % 100 == 0 then false
      else if a % 4 == 0 then true
      else false
    }

    def days(a: List[Int], b: Int, c: Int): Int = {
      if b == c then a.head
      else days(a.tail, b, c + 1)
    }
    if d._3<1 then false
    else if d._2<1 || d._2>12 then false
    else if d._1<1 || d._1>31 then false
    else {
      val a = Leap(d._3)
      val b = days(List(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31), d._2, 1)
      val c = days(List(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31), d._2, 1)
      if a then {
        if d._1 > b then false
        else true
      }
      else {
        if d._1 > c then false
        else true
      }
    }
  }

  //println(isOlder((1, 3, 2002), (3, 2, 2001)))
  //println(isOlder((3, 2, 2001), (3, 2, 2001)))
  //println(isOlder((3, 2, 2001), (3, 2, 2002)))
  //println(numberInMonth(List((2, 3, 2002), (1, 3, 2001), (3, 1, 2000)), 3))
  //println(numberInMonth(List((2, 3, 2002), (1, 3, 2001), (3, 1, 2000)), 4))
  //println(numberInMonths(List((2, 3, 2002), (1, 3, 2001), (3, 1, 2000)), List(1, 2, 3)))
  //println(datesInMonth(List((2, 3, 2002), (1, 3, 2001), (3, 1, 2000)), 3))
  //println(datesInMonths(List((2, 3, 2002), (1, 3, 2001), (3, 1, 2000)), List(1, 2, 3)))
  //println(dateToString((1, 3, 2002)))
  //println(whatMonth(366, 2004))
  //println(whatMonth(60, 2104))
  //println(whatMonth(60, 2100))
  //println(whatMonth(366, 2001))
  //println(whatMonth(365, 2001))
  //println(whatMonth(2, 2100))
  //println(whatMonth(2, 2104))
  //println(oldest(List((1, 2, 2002), (1, 2, 2004), (1, 2, 2003))))
  //println(oldest(List()))
  //println(isReasonableDate((1, 2, 3)))
  //println(isReasonableDate((29, 2, 4)))
  //println(isReasonableDate((29, 2, 3)))
  //println(isReasonableDate((3, 2, -10)))
  //println(isReasonableDate((3, 124, 3)))
}
