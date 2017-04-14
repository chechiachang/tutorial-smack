package lecture;

/**
  * Created by davidchang on 4/12/17.
  */
object ListFunction extends App{
  def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
    case Nil => xs
    case y :: yx => y * factor :: scaleList(xs, factor)
  }

  def squareList(xs: List[Int]): List[Int] = xs match {
    case Nil => xs
    case y :: ys => y * y :: squareList(ys)
  }

  def squareListMap(xs: List[Int]): List[Int] =
    xs map (x => x * x)

  def posElems(xs: List[Int]): List[Int] = xs match {
    case Nil => xs
    case y :: ys => if (y > 0) y :: posElems(ys) else posElems(ys)
  }

  val nums = List(1, 7, 2, 6, 78)
  println(nums filter (x => x/2 == 0))
  println(nums filterNot (x => x > 0))
  println(nums partition (x => x/2 == 0))
  println(nums takeWhile (x => x/2 == 0))

  println(nums span (x => x >0))

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case y :: ys =>
      val (first, rest) = xs span (x => x == y)
      first :: pack(rest)
  }

  val chars = List("a", "a", "a", "a", "b", "b", "b", "c", "d")
  println(pack(chars))

  def encode[T](xs: List[T]): List[(T, Int)] = xs match {
    case Nil => Nil
    case y :: ys =>
      val (first, rest) = xs span (x => x == y)
      (y, first.length) :: encode(rest)
  }

  def sum(xs: List[Int]): Int = xs match {
    case Nil => 0
    case y :: ys => y + sum(ys)
  }

  def product(xs: List[Int]): Int = (1 :: xs) reduceLeft (_ * _)
  def product1(xs: List[Int]): Int = (xs foldLeft 1) (_ * _)

  def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    (xs foldRight List[U]())( ??? )

  def lengthFun[T](xs: List[T]): Int =
    (xs foldRight 0)( ??? )
}
