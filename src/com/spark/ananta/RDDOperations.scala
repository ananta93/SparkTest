package com.spark.ananta

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import java.io.File

object RDDOperations {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("RDD Operations").setMaster("local[*]"))
    val inputRDD = sc.textFile("D:\\Spark/spark_input/player_info.tsv")
    
    val brazilOutputPath = new File("D:\\Spark/spark_output/brazil_player_info.txt")
    val mexicoOutputPath = new File("D:\\Spark/spark_output/mexico_player_info.txt")
    val groupAOutputPath = new File("D:\\Spark/spark_output/mexico_player_info.txt")
    
    val brazilRDD = inputRDD.filter { line => line.contains("Brazil") }
    val mexicoRDD = inputRDD.filter { line => line.contains("Mexico") }
    val groupARDD = brazilRDD.union(mexicoRDD)
    
    println("No. of players participated in the world cup : "+inputRDD.count())  //Actions
    println("No. of players from Brazilian team : "+brazilRDD.count())
    println("10 players from Brazilian team : ")
    brazilRDD.take(10).foreach { line => println(line)}
    
    println("No. of players from Mexican team : "+mexicoRDD.count())
    println("10 players from Mexican team : ")
    mexicoRDD.take(10).foreach { line => println(line)}
    
    if(brazilOutputPath.exists()||mexicoOutputPath.exists()||groupAOutputPath.exists()){
      brazilOutputPath.delete()
      mexicoOutputPath.delete()      
      groupAOutputPath.delete()
    }
    else{
      brazilRDD.saveAsTextFile("D:\\Spark/spark_output/brazil_player_info.txt")
      mexicoRDD.saveAsTextFile("D:\\Spark/spark_output/mexico_player_info.txt")
      groupARDD.saveAsTextFile("D:\\Spark/spark_output/groupA_player_info.txt")
    }
    sc.stop()
  }
}