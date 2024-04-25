object Aggregate extends App{
  def myMin(p: Double, q: Double, r: Double): Double = {
    if p <= q && p <= r then p
    else if q <= p && q <= r then q
    else r
  }
  def myMean(p: Double, q: Double, r: Double): Double = (p+q+r)/3
  def myMed(p: Double, q: Double, r: Double): Double = {
    if p < q && p > r then p
    else if p < r && p > q then p
    else if q < p && q > r then q
    else if q < r && q > p then q
    else if r < p && r > q then r
    else r
  }
  println(myMin(3.0, 1.0, 9.0))
  println(myMean(3, 7, 4))
  println(myMed(4, 1, 5))
  println(myMed(13, 5.0, 12))
}
