name := "LearningSpark"

version := "0.1"

scalaVersion := "2.11.0"


// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.3.0",
  "org.apache.spark" %% "spark-sql" % "2.3.0",
  "org.apache.spark" %% "spark-mllib" % "2.3.0" % "runtime",
  "org.apache.spark" %% "spark-streaming" % "2.3.0" % "provided",
  "org.apache.spark" %% "spark-hive" % "2.3.0" % "provided",
  "org.apache.spark" %% "spark-catalyst" % "2.3.0" % Test,
  "org.apache.spark" %% "spark-graphx" % "2.3.0"
)






