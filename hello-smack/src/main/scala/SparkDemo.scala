import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by davidchang on 4/2/17.
  */
object SparkDemo extends App{
  val conf = new SparkConf().setMaster("local").setAppName("mySparkApp")

  val sc = new SparkContext(conf)

  val myFile = sc.textFile("file.txt")

  val lowerCase = myFile.map(line => line.toLowerCase)

  val tinytStopWords = Set("what", "of", "and", "the", "to", "it", "in", "or", "no", "that", "is", "with", "by", "those", "which", "its", "his", "her", "me", "him", "on", "an", "if", "more", "I", "you", "my", "your", "for" )

  val words = lowerCase.flatMap(line => line.split("\\s+")).filter(! tinytStopWords.contains(_))

  val counts = words.map(word => (word, 1))

  val frequency = counts.reduceByKey(_ + _)

  val invFrequency = frequency.map(_.swap)

  invFrequency.top(20).foreach(println)
}
