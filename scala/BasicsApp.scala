object BasicsApp extends App {
    def flatMap[A, B](f: A => List[B])(xs: List[A]): List[B] = {
        def tailFlatMap(a: List[A]): List[B]= a match {
            case Nil => Nil
            case h::t => f(h):::tailFlatMap(t)
        }
        tailFlatMap(xs)
    }

    def negNegPos(n: Int): List[Int] = {
        if n <= 0 then Nil
        else {
            val a = (x: Int) => List(-x, -x, x)
            val xs = (x: Int) => 1 to x
            flatMap(a)(xs(n).toList)
        }
    }

    def winnerOf(xs: Vector[String]): (String, Int) = {
        val a = xs.groupMapReduce(l => l)(_ => 1)(_ + _)
        a.maxBy(_._2)
    }

    def nonTrailingZeros(n: BigInt): Int = {
        val digits = n.toString
        // apply foldLeft or foldRight on digits
        // what should be in the accumulator? (it can be a tuple)
        digits.foldLeft((0, String(), 'a', 0)) {(a, curr) => curr match {
            case h => {
                val check = a._2+curr.toString
                if check==digits then {
                    if curr=='0' then (0, a._2, h, a._4)
                    else (a._1, a._2, h, a._4+a._1+1)
                }
                else if h == '0' then (a._1+1, a._2+h.toString, h, a._4)
                else (0, a._2, h, a._4+a._1)
            }
        }}._4
    }
}
