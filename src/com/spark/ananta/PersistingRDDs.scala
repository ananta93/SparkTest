package com.spark.ananta

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import scala.collection.immutable.List
import org.apache.spark.storage.StorageLevel

object PersistingRDDs {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("Persting").setMaster("local[*]"))
    val inputRDD = sc.parallelize(List(1,2,3,4))
    
    var resultMap = inputRDD.map { x => x*x }
    var resultFlatMap = inputRDD.flatMap { x => x.to(3)}
    
    resultMap.persist(StorageLevel.DISK_ONLY)
    println("The count after persisting : "+resultMap.count())
    println("After persisting result : "+resultMap.collect().mkString(","))
    println("Using flatmap : "+resultFlatMap.collect().mkString(","))
  }
}