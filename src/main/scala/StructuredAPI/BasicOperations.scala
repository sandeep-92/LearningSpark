package StructuredAPI

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object BasicOperations extends App{

  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder()
    .appName("basic app")
    .master("local[*]")
    .getOrCreate()

  //creates a dataframe df with column names numbers
  val df = spark.range(100).toDF("numbers")
  //will show the first 5 rows of dataframe
  df.show(5)
  //performing a transformation filter on df
  //will only show even numbers
  val transDf = df.filter("numbers % 2 = 0")
  transDf.show(5)
}
