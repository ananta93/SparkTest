package com.spark.ananta

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object AgreegateTest {
  def main(args: Array[String]): Unit = {
     var config = new SparkConf().setAppName("Hello").setMaster("local[2]")
    var sc = new SparkContext(config)
    
    var input = sc.parallelize(List(1,2,3,4))
    
    /*val avgResult = input.aggregate((0,0))(
        (x, y) => (x._1+1,x._1+2), 
       combOp)*/
    
    val result = input.aggregate((0, 0))(
      (acc, value) => (acc._1 + value, acc._2 + 1),
      (acc1, acc2) => (acc1._1 + acc2._1, acc1._2 + acc2._2))
    val avg = result._1 / result._2.toDouble
    println("=============================="+avg)
  }
}