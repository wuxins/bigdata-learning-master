package com.fsmeeting.scala.test

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by think on 2018/4/15.
  */
object SparkTest {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName(getClass.getSimpleName)
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(List(1, 2, 3, 4, 5, 6)).map(_ * 10)
    val mappedRDD = rdd.filter(_ > 10).collect()
    println(rdd.reduce(_ + _))
    println(mappedRDD)
  }
}
