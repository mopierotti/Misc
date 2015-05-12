def memoize[A,B](func:(A => B)):(A => B) = {
  val mutableMap = scala.collection.mutable.Map.empty[A,B]
    (input:A) => {
      mutableMap.getOrElseUpdate(input, func(input))
    }
}