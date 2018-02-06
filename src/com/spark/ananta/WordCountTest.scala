package com.spark.ananta

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object WordCountTest {
  def main(args: Array[String]): Unit = {
    val config = new SparkConf().setAppName("Word Count").setMaster("local[*]")
    val sc = new SparkContext(config)
    
    val inputText = sc.textFile("D:\\Spark/spark_input/test.txt", 3)
    val words = inputText.flatMap { local => local.split(" ") }
    val wordCount = words.map( word => (word,1)).reduceByKey{case(x,y)=>x+y}
    wordCount.saveAsTextFile("D:\\Spark/spark_output/word_count.txt")
    sc.stop()
  }
}