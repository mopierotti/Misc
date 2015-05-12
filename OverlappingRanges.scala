import com.google.common.collect.{TreeRangeMap,Range}

def itemsWithoutOverlappingRanges[A,B](items:Seq[A], itemToRange:(A => Range[B])) = {
  val rangeMap:TreeRangeMap[B, A] = TreeRangeMap.create(); //This collection is mutable
  var cleanedItems = Seq.empty[A]
  var overlappedCountLowerBound = 0
  items.foreach { item =>
    val range = itemToRange(item)
    val subRangeMap = rangeMap.subRangeMap(range)
    val overlappingItems = subRangeMap.asMapOfRanges().values()
    if (overlappingItems.isEmpty) {
      // Only add an item if it doesn't overlap with existing items
      rangeMap.put(range, item)
      cleanedItems = cleanedItems :+ item
    } else {
      overlappedCountLowerBound += 1
    }
  }
  Logger.info(s"Found at least $overlappedCountLowerBound overlapped items")
  cleanedItems
}