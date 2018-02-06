package com.spark.ananta

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object FileOperationsFirst {
  def main(args: Array[String]): Unit = {
    val config = new SparkConf().setAppName("File Operations").setMaster("local[*]")
    val sc = new SparkContext(config)
    
    val inputFile = sc.textFile("D:\\Spark/spark_input/test.txt")
    println("No. of lines present in the file : "+inputFile.count())
    println("The first line of the text file is : "+inputFile.first())
    val containsBig = inputFile.filter { line => line.contains("Big") }
    containsBig.foreach { line => println(line) }
    sc.stop()
  }
}