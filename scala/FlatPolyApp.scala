object FlatPolyApp extends App {
  sealed case class Term(coeff: Int, expo: Int)

  class FlatPoly(val terms: List[Term]) { // val makes "terms" a member variable

    def eval(x: Double): Double = {
      def loop(a: List[Term]): Double = a match {
        case Nil => 0
        case h::t => Math.pow(x, h.expo)*h.coeff+loop(t)
      }
      loop(FlatPoly.this.terms)
    }

    def diff: FlatPoly = {
      def loop(a: List[Term]): List[Term] = a match {
        case Nil => Nil
        case h :: t => {
          if h.expo <= 0 then loop(t)
          else Term(h.coeff * h.expo, h.expo - 1) :: loop(t)
        }
      }
      val a = loop(FlatPoly.this.terms)
      FlatPoly(a)
    }

    def +(that: FlatPoly) = {
      FlatPoly(this.terms:::that.terms)
    }

    def *(that: FlatPoly) = {
      def loop(a: List[Term], b: List[Term]): List[Term] = a match {
        case Nil => Nil
        case h::t => {
          val x = b.map(c => Term(c.coeff*h.coeff, c.expo+h.expo))
          x:::loop(t, b)
        }
      }
      /*
      def add(a: List[Term], b: Term): List[Term] = {
        if b == null then null
        else if a.isEmpty then List(b)
        else {
          if a.head.expo == b.expo then add(a.tail, Term(a.head.coeff+b.coeff, b.expo))
          else List(b):::add(a.tail, a.head)
        }
      }
       */
      val a = loop(this.terms, that.terms)
      val b = a.sortBy(_.expo)
      val c = FlatPoly(b).normalize
      c
    }

    def normalize: FlatPoly = {
      def loop(a: List[Term], b: Term): List[Term] = {
        if b == null then null
        else if a.isEmpty then List(b)
        else {
          if a.head.expo == b.expo then loop(a.tail, Term(a.head.coeff+b.coeff, b.expo))
          else b::loop(a.tail, a.head)
        }
      }
      val a = FlatPoly.this.terms.sortBy(_._2).filter(_.coeff >= 1)
      val b = loop(a.tail, a.head)
      FlatPoly(b)
    }

    def unary_- : FlatPoly = FlatPoly(terms.map { case Term(coeff, power) => Term(-coeff, power) })

    override def toString: String = {
      val renderedTerms = terms.map {
        case Term(1, 1) => "x"
        case Term(c, 0) => s"$c"
        case Term(c, 1) => s"${c}x"
        case Term(1, p) => s"x^$p"
        case Term(c, p) => s"${c}x^$p"
      }

      if terms.isEmpty then "<empty>" else renderedTerms.mkString(" + ")
    }
  }

  val a = FlatPoly(List(Term(2, 3), Term(3, 2), Term(5, 3)))
  val b = FlatPoly(List(Term(1, 1), Term(1, 2), Term(2, 0)))
}