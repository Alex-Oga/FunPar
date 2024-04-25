object StreamApp extends App {
    def onlyOddOdds(s: LazyList[Long]): LazyList[Long] = {
        val a = s.head.toString
        def num(str: String, count: Int): Int = {
            if str.isEmpty then count
            else {
                if str.head.toInt % 2 == 1 then num(str.tail, count+1)
                else num(str.tail, count)
            }
        }
        val b = num(a, 0)
        if b % 2 == 0 then onlyOddOdds(s.tail)
        else s.head #:: onlyOddOdds(s.tail)
    }

    // TODO: Fix me
    val p: List[Int] = (1 to 150).toList
    val q: List[Long] = p.map(a => a.toLong)
    val z = q.to(LazyList)

    val allOddOdds: LazyList[Long] = onlyOddOdds(z)

    def recurrence(r: Vector[(Int, Int)]): LazyList[Long] = {
        val (coeffs, baseCases) = r.unzip
        val cases = baseCases.map(_.toLong)
        val a = cases.to(LazyList)

        def next(s: LazyList[Long]): LazyList[Long] = {
            val x = coeffs zip s.reverse
            val y = x.map((a, b) => a*b).sum
            val z = s.drop(1)
            val l = z#:::LazyList(y)
            y#::next(l)
        }

        a #::: next(a)
    }

    val one = Vector((1, 0), (1, 1))
    val two = recurrence(one)
    
    val fibOddOdds: LazyList[Long] = onlyOddOdds(two) // TODO: FIX ME
}
