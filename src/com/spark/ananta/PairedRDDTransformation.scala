package com.spark.ananta

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object PairedRDDTransformation {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("Spark Paired RDD").setMaster("local[*]"))
    val lines = sc.parallelize(List("1","2","3","4","3","6"))
    
    
    val pairs = lines.map {  x  => (x.split(" ")(0),x) }
    println("==============================")
    pairs.foreach(x => print(x))
    
    println("================ Reduce by key ================")
    pairs.reduceByKey((x,y) => x+y ).foreach(x=>print(x))
    
    println("================ Group by key ================")
    pairs.groupByKey().foreach(x=>print(x))
    
    
    
    
//    val inputText = sc.textFile("D:\\Spark/spark_input/test.txt", 3)
    
    
//    val words = inputText.flatMap { local => local.split(" ") }
//    val wordCount = words.map( word => (word,1)).reduceByKey{case(x,y)=>x+y}
    
    

  }
}