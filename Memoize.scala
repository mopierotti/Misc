/*Simple memoize implementation
    Caveats:
        - Supports single-argument functions only
        - Does not support concurrent threads (For that, switch to a concurrent mutable map)
        - Does not support recursive functions directly
    Notes:
        - "If a tree falls in the woods, does it make a sound? If a pure function mutates local data to return an immutable value, is that ok?" 
        Yes, yes it is.
*/
def memoize[A,B](func:(A => B)):(A => B) = {
  val mutableMap = scala.collection.mutable.Map.empty[A,B]
    (input:A) => {
      mutableMap.getOrElseUpdate(input, func(input))
    }
}