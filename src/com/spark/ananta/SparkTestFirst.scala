package com.spark.ananta

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkConf
import scala.collection.immutable.List

object SparkTestFirst {
  def main(args: Array[String]): Unit = {
    var config = new SparkConf().setAppName("Hello").setMaster("local[2]")
    var sc = new SparkContext(config)
    
    var inputRDD = sc.parallelize(List(1,2,3,4))
    var result = inputRDD.map { x => x*x}
    println("The output is : "+result.collect().mkString(","))
    sc.stop()
  }
}