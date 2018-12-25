package StructuredStreaming

/*
reading smartphone and sensor data from different devices
 */

import org.apache.spark.sql._
import org.apache.log4j._

object Basics extends App{

  Logger.getLogger("org").setLevel(Level.ERROR)

  //creating spark session
  val spark = SparkSession.builder
    .appName("Sensor data")
    .master("local[*]")
    //.config("spark.sql.warehouse.dir", "file:///C:temp")
    .getOrCreate()

  //reading the data as static to get its schema
  val filePath = "C:/Users/Sandeep/Downloads/Data/SparkDefGuide/activity-data/"
  //starting the read stream to read all the data
  val batchDf = spark.read.json(filePath)
  val dataSchema = batchDf.schema

  //maxFilesPerTrigger: how quickly spark will read files in a folder
  val streamingDf = spark.readStream.schema(dataSchema)
    .option("maxFilesPerTrigger", 1).json(filePath)

  //transformations on readstream
  val activityCount = streamingDf.groupBy("gt").count()

  //avoid creation of too many shuffle partitions
  spark.conf.set("spark.sql.shuffle.partitions", 5)

  val outputSink = activityCount.writeStream.queryName("activity_count")
    .format("memory")
    .outputMode("complete")
    .start()

  //prevent driver code from exiting while code is running
  outputSink.awaitTermination()
}
