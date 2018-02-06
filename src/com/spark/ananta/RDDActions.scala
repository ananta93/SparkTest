package com.spark.ananta

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import scala.collection.immutable.List

object RDDActions {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("RDD Actions").setMaster("local[*]"))
    
    val inputRDD = sc.parallelize(List(1,2,3,3,4,4,4,8,6))
    
    println("The elements to be processed -> Calling \"collect()\" : "+inputRDD.collect().mkString(","))
    
    println("Total No. of elements -> Calling \"count()\" : "+inputRDD.count())
    
    println("First 3 elements -> Calling \"take(n)\" : "+inputRDD.take(3).mkString(","))
    println("Taking top elements -> Calling \"top(n)\" : "+inputRDD.top(2).mkString(","))
    println("Taking top element in provided order -> Calling \"takeOrdered(n)\" : "+inputRDD.takeOrdered(2).mkString(","))
    println("Return random output -> Calling \"takeSample()\" : "+inputRDD.takeSample(false, 3).mkString(","))
    println("Combine the elements of the RDD in parallel : -> Calling \"reduce(func)\" : "+inputRDD.reduce((x,y)=>x+y))
    println("Same as reduce()with provided 0 value -> Calling \"fold(0)(func)\" : "+inputRDD.fold(0)((x,y)=>x+y))
    println("Using aggregate "+inputRDD.aggregate(0,0)((x,y)=>(x._1+y,x._2+1),(x,y)=>(x._1+y._1,x._2+y._2)))
    print("Applying for each : ")
    inputRDD.foreach { x => println(x) }
    println("Applying mean : "+inputRDD.mean())
  }
}