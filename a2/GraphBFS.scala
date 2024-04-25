object GraphBFS {

  def bfs[V](nbrs: V => Set[V], src: V) = {

    def expand(frontier: Set[V], parent: Map[V, V]): (Set[V], Map[V, V])  = frontier match {
      case null => null
      case frontier => {
        val a = front(frontier)
        val b = nbrsMap(frontier)
        (a, parent++b)
      }
    }

    def front(a: Set[V]): Set[V]= {
      if a.isEmpty then null
      else nbrs(a.head)++front(a.tail)
    }

    def nbrsMap(a: Set[V]): Map[V, V]= {
      if a.isEmpty then null
      else nbrs(a.head).map(_ -> a.head).toMap++nbrsMap(a.tail)
    }

    def iterate(frontier: Set[V], 
                parent: Map[V, V], 
                distance: Map[V, Int], d: Int
                ): (Map[V, V], Map[V, Int]) =
      if frontier.isEmpty then
        (parent, distance)
      else {
        val (frontier_, parent_) = expand(frontier, parent)
        val distance_ = frontier.map(_ -> d).toMap

        iterate(frontier_, parent_, distance_, d + 1)
      }

    iterate(Set(src), Map(src -> src), Map(), 0)
  }
}