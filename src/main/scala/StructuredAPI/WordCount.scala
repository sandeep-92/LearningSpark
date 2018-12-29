package StructuredAPI

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

/*
word count example using structured API
 */
object WordCount extends App {

  //setting log level to print error only
  Logger.getLogger("org").setLevel(Level.ERROR)

  //spark session created
  val spark = SparkSession.builder()
    .appName("word count")
    .master("local[*]")
    .getOrCreate()

  val filepath = "C:\\Users\\Sandeep\\Downloads\\Data\\test.txt"
  //reading textfile using sparkcontext
  val fileDf = spark.sparkContext.textFile(filepath)
  //splitting the words based on comma seprator
  val wordDf = fileDf.flatMap(line => line.split(","))
  //mapping words to their values
  val countDf = wordDf.map(word => (word, 1))
  //aggregating based on the keu
  val resultDf = countDf.reduceByKey(_ + _)
  //showing result
  resultDf.collect.foreach(println)

}
