package service

import service.MergeSort.msort

/**
  * Created by davidchang on 4/12/17.
  */
object MergeSort extends App{
  def msort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
    val n = xs.length/2
    if (n == 0) xs
    else{
      def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if(lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(ys1, xs)
      }
      val (fst, snd) = xs splitAt n
      merge(msort(fst)(lt), msort(snd)(lt))
    }
  }

  val nums = List(2, -4, 5, 7, 1)
  println(msort(nums)((x: Int, y: Int) => x < y))

  val fruits = List("ab", "cdf", "werweewt", "asdasfaf", "zvpoipj[dfij[sdkif[")
  println(msort(fruits)((x:String, y: String) => x.compareTo(y) > 0))
}

object MergeSortOrdering extends App{
  def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length/2
    if (n == 0) xs
    else{
      def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if(ord.lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(ys1, xs)
      }
      val (fst, snd) = xs splitAt n
      merge(msort(fst), msort(snd))
    }
  }

  val nums = List(2, -4, 5, 7, 1)
  val fruits = List("ab", "cdf", "werweewt", "asdasfaf", "zvpoipj[dfij[sdkif[")

  println(msort(nums))
  println(msort(fruits))
}