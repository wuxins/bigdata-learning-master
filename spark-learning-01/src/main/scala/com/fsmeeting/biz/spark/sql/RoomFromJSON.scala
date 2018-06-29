package com.fsmeeting.biz.spark.sql

import java.util.Properties

import org.apache.spark.sql.{DataFrame, SparkSession}

object RoomFromJSON {

  def main(args: Array[String]): Unit = {

    val sparkSession: SparkSession = SparkSession
      .builder()
      .appName(getClass.getSimpleName)
      //.config("spark.some.config.option", "some-value")
      .getOrCreate()
    val json: DataFrame = sparkSession.read.json(args(0))
    json.createOrReplaceTempView("v_room_runtime")

    val sql = "select " +
      "bpartnerId,roomId,from_unixtime(floor(time/1000),'yyyyMMddHHmm') as time,max(num) num " +
      "from v_room_runtime " +
      "group by bpartnerId,roomId,from_unixtime(floor(time/1000),'yyyyMMddHHmm')"

    val result: DataFrame = sparkSession.sql(sql)

    //将结果写入数据库中
    val properties = new Properties()
    properties.setProperty("user", "root")
    properties.setProperty("password", "fsmeeting")
    result.write.mode("append").jdbc("jdbc:mysql://192.168.5.44:3306/test?useUnicode=true&characterEncoding=UTF-8",
      "t_spark_test", properties)

   result.write.format("jdbc")
     .options(Map("url" -> "jdbc:mysql://192.168.5.44:3306/test", "driver" -> "com.mysql.jdbc.Driver", "dbtable" -> "person", "user" -> "root", "password" -> "123456"))
     //.load()

    sparkSession.stop()
  }

}
