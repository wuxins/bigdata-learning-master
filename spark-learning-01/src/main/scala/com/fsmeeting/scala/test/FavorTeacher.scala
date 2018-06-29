package com.fsmeeting.scala.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 最受欢迎的老师
  * Created by think on 2018/4/15.
  */
object FavorTeacher {

  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setAppName(getClass.getSimpleName)

    val sparkCxt: SparkContext = new SparkContext(sparkConf)

    val lines: RDD[String] = sparkCxt.textFile(args(0))

    //Array[(String, String)] =
    // Array((数据结构,老张), (数据结构,老张), (数据结构,老张), (数据结构,老张), (数据结构,老李), (数据结构,老李), (算法分析,老李), (算法分析,老李), (算法分析,老孙), (算法分析,老孙), (java,老王), (java,老王), (java,老猪), (java,老猪), (java,老猪), (java,老猪))
    val courseAndTeacher: RDD[(String, String)] = lines.map(line => {
      val arr = line.split("/")
      (arr(0), arr(1))
    })

    //聚合
    //Array[((String, String), Int)] =
    // Array(((java,老猪),4), ((java,老王),2), ((算法分析,老孙),2), ((数据结构,老张),4), ((数据结构,老李),2), ((算法分析,老李),2))
    val reduced: RDD[((String, String), Int)] = courseAndTeacher.map((_, 1)).reduceByKey(_ + _)


    //Array[(String, Iterable[((String, String), Int)])] =
    // Array(
    //  (算法分析,CompactBuffer(((算法分析,老孙),2), ((算法分析,老李),2))),
    //  (java,CompactBuffer(((java,老猪),4), ((java,老王),2))),
    //  (数据结构,CompactBuffer(((数据结构,老张),4), ((数据结构,老李),2))))
    val grouped: RDD[(String, Iterable[((String, String), Int)])] = reduced.groupBy(_._1._1)

    //二次排序
    //Array[(String, List[((String, String), Int)])] =
    // Array((算法分析,List(((算法分析,老李),2))), (java,List(((java,老猪),4))), (数据结构,List(((数据结构,老张),4))))

    val groupedResult: RDD[(String, ((String, String), Int))] = grouped.mapValues(_.toList.sortBy(_._2).reverse.head)

    //收集结果
    //Array[(String, String, Int)] =
    // Array((算法分析,老李,2), (java,老猪,4), (数据结构,老张,4))
    val result: RDD[(String, String, Int)] = groupedResult.map(item => {
      (item._2._1._1, item._2._1._2, item._2._2)
    })

    println(result.collect().toBuffer)

    sparkCxt.stop()
  }
}
