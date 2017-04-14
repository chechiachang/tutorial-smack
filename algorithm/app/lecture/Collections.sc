val xs = Array(1, 2, 3, 44)
xs map (x => x * 2)

val r: Range = 1 until 5
val ss: Range = 1 to 5 by 1

val str = "Hello World"
str filter (c => c.isUpper)

val pair = str zip List(1, 2, 3)
pair.unzip

List(1, 2, 3) zip str

str flatMap(c => List('.', c))

(1 to 10) flatMap(x => (1 to 10) map (y => (x, y)))

def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
  (xs zip ys).map(xy => xy._1 * xy._2).sum

def isPrime(x: Int): Boolean = (2 until x) forall (y => x % y != 0)


(1 until 10) map(x => (1 until 10) map (y => (x, y)))

case class Person(name: String, age: Int)

val persons = List(Person("d", 100))
for(p <- persons if p.age > 20) yield p.name

for{
  i <- 1 until 10
  j <- 1 until 10
  if isPrime(i + j)
} yield (i, j)


