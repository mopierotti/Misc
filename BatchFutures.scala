import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

//Execute arbitrarily many Futures in such a way that the number of concurrently executing Futures is never greater than the batchSize.
//TODO: Thorough testing

def batchFutures[A,B](seedData:Seq[A], futureCreator:(A => Future[B]), batchSize:Int):Future[Seq[B]] = {
  def helper(seedData:Seq[A], results:Seq[B]):Future[Seq[B]] = {
    if(seedData.isEmpty) Future{results} else {
      val futures = seedData.take(batchSize).map(futureCreator(_))
      val composedFutures = Future.sequence(futures)
      composedFutures.flatMap{ completedFutures =>
        helper(seedData.drop(batchSize), results++completedFutures)
      }
    }
  }
  helper(seedData, Seq.empty)
}

