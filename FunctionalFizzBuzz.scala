
//Functional FizzBuzz Solution
Range(1,101).map{ 
	case n if n%3 == 0 && n%5 == 0 => s"FizzBuzz"
	case n if n%3 == 0 => s"Fizz"
	case n if n%5 == 0 => s"Buzz"
	case n => s"$n"
}.mkString("\n")

//Excessively Generic Functional FizzBuzz Solution
def applyTestsToSequence[A](tests:Seq[(A => Option[String])], items:Seq[A]) = {
	items.map { item =>
		val appliedTests = tests.flatMap(_(item))
		if (appliedTests.size >= 1) appliedTests.mkString("") else item.toString
	}.mkString("\n")
}

val tests:Seq[(Int => Option[String])] = Seq( //Order defined is order applied
	((n:Int) => if (n % 3 == 0) Some("Fizz") else None),
	((n:Int) => if (n % 5 == 0) Some("Buzz") else None)
)

print(applyTestsToSequence(tests, Range(1,101)))