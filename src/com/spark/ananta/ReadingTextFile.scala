package com.spark.ananta

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object ReadingTextFile {
  def main(args: Array[String]): Unit = {
    val config = new SparkConf().setAppName("ReadingTextFile").setMaster("local[*]")
    val sc = new SparkContext(config)
    
    val inputFile = sc.textFile("D:\\Spark/spark_input/player_info.tsv")
    val lines = inputFile.flatMap ( line => line.split(" "))  
    lines.foreach { line => print(line) }
    sc.stop()
  }
}