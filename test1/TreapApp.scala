object TreapApp extends App {
    sealed trait Treap[+K, +V]
    case object Empty extends Treap[Nothing, Nothing]
    case class Node[K, V](
        left: Treap[K, V], key: K, value: V, right: Treap[K, V]
    ) extends Treap[K, V]
    
    def getByValue[K, V](rt: Treap[K, V], v: V)(implicit ord: Ordering[V]): Option[(K, V)] =  rt match {
        case Empty => None
        case Node(left, key, value, right) => {
            val cmp = ord.compare(v, value)
            if cmp == 0 then Some(key, value)
            else {
                if cmp > 0 then getByValue(right, v)
                else getByValue(left, v)
            }
        }
    }

    def avgDepth[K, V](rt: Treap[K, V]): Double = {
        def go(a: Treap[K, V], b: Int): (Int, Int) = a match {
            case Empty => (0, 0)
            case Node(left, key, value, right) => {
                val x = go(left, b+1)
                val y = go(right, b+1)
                (1 + x._1 + y._1, b + x._2 + y._2)
            }
        }
        val z = go(rt, 0)
        z._1/z._2
    }

    def h[K](k: K): Int = {
        // h implements linear congruential compression on the hashCode
        // of the object. Both a and p are (decently large) prime numbers, and
        // b is arbitrarily chosen.

        val (a, b) = (879_190_747L, 32_452_843L)
        val p = 236_887_699L
        ((k.hashCode.toLong*a + b) % p).toInt
    }
    
    /*
     *       y                   x
     *      / \                 / \
     *     x   c    <=====>    a   y
     *    / \                     / \
     *   a   b                   b   c 
    */
    def rebalance[K, V](rt: Treap[K, V]): Treap[K, V] = rt match {
        case _ => rt
    }

    def insert[K, V](rt: Treap[K, V], k: K, v: V)(implicit ord: Ordering[K]): Treap[K, V] = 
        rt match {
            case Empty => Node(Empty, k, v, Empty)
            case Node(l, key, value, r) => {
                val cmp = ord.compare(k, key)
                if cmp < 0 then rebalance(Node(insert(l, k, v), key, value, r))
                else if cmp == 0 then Node(l, key, v, r) 
                else rebalance(Node(l, key, value, insert(r, k, v)))
            }
        }
    
    def satisfiesMHIV[K, V](rt: Treap[K, V]): Boolean = ???

    
}
