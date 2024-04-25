object OptionFriends extends App {
  def lookup(xs: List[(String, String)], key: String): Option[String] = xs match {
    case Nil => None
    case xs::tlxs => {
      if xs._1==key then Some(xs._2)
      else lookup(tlxs, key)
    }
  }

  def resolve(userIdFromLoginName: String => Option[String],
              majorFromUserId: String => Option[String],
              divisionFromMajor: String => Option[String],
              averageScoreFromDivision: String => Option[Double],
              loginName: String): Double = loginName match {
    case null => 0.0
    case loginName => {
      val a = userIdFromLoginName(loginName)
      if a==None then 0.0
      else {
        val b = majorFromUserId(a.get)
        if b==None then 0.0
        else {
          val c = divisionFromMajor(b.get)
          if c==None then 0.0
          else {
            val d = averageScoreFromDivision(c.get)
            if d==None then 0.0
            else d.get
          }
        }
      }
    }
  }
  /*
  def userIdFromLoginName(s: String):Option[String] = {
    if s.isEmpty then None
    else Some(s)
  }
  def majorFromUserId(s: String):Option[String] = {
    if s.isEmpty then None
    else Some(s)
  }
  def divisionFromMajor(s: String):Option[String] = {
    if s.isEmpty then None
    else Some(s)
  }
  def averageScoreFromDivision(s: String):Option[Double] = {
    if s.isEmpty then None
    else Some(2.2)
  }
   */




  //println(lookup(List(("a", "xy"), ("c", "pq"), ("a", "je")), "a"))
  //println(lookup(List(("a", "xy"), ("c", "pq"), ("a", "je")), "b"))
  //println(lookup(List(("a", "xy"), ("c", "pq"), ("a", "je")), "c"))
  //println(resolve(userIdFromLoginName, majorFromUserId, divisionFromMajor, averageScoreFromDivision, "Nice"));
  //println(resolve(userIdFromLoginName, majorFromUserId, divisionFromMajor, averageScoreFromDivision, ""));
}
