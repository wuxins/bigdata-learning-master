package com.fsmeeting.biz.spark.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{IntegerType, LongType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object RoomFromAnyType {

  //case class RoomRuntime(bpartnerId: Int, roomId: Int, time: Long, num: Int)

  def main(args: Array[String]): Unit = {

    val sparkSession: SparkSession = SparkSession
      .builder()
      .appName(getClass.getSimpleName)
      //.config("spark.some.config.option", "some-value")
      .getOrCreate()

    val lines: RDD[String] = sparkSession.sparkContext.textFile(args(0))

    var rowsRdd: RDD[Row] = lines.map(line => {
      val fields = line.split(" ")
      val bpartnerId = fields(0).toInt
      val roomId = fields(1).toInt
      val time = fields(2).toLong
      val num = fields(3).toInt
      Row(bpartnerId, roomId, time, num)
    })

    val schema = StructType(List(
      StructField("bpartnerId", IntegerType, true),
      StructField("roomId", IntegerType, true),
      StructField("time", LongType, true),
      StructField("num", IntegerType, true)
    ))

    val pdf: DataFrame = sparkSession.createDataFrame(rowsRdd, schema)

    pdf.createOrReplaceTempView("t_room_runtime")
    val result: DataFrame = sparkSession.sql("select bpartnerId,roomId,from_unixtime(time, '%Y%m%d%H%I'),max" +
      "(num) from t_room_runtime group by " +
      "bpartnerId,roomId,from_unixtime(time, '%Y%m%d%H%I')")
    result.show()
    sparkSession.stop()

  }
}
