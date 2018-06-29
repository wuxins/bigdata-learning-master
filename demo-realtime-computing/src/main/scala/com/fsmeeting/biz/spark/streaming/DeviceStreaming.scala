package com.fsmeeting.biz.spark.streaming

import java.text.SimpleDateFormat

import com.alibaba.fastjson.JSON
import com.fsmeeting.utils.RedisClient
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object DeviceStreaming {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("DeviceStreaming").setMaster("local[2]")
    val ssc = new StreamingContext(sparkConf, Seconds(10)) // 10s per batch
    val topicMap = Map("spark-streaming-test" -> 1)
    val kafkaStream = KafkaUtils.createStream(ssc, "node-1:2181", "device-streaming-group", topicMap)
    val streamData = kafkaStream.flatMap(line => {
      Some(JSON.parseObject(line._2))
    })

    // active devices
    val deviceStats = streamData.map(x => ( {
      val date = new SimpleDateFormat("yyyyMMdd").format(x.getJSONObject("event").getDate("time"))
      val month = new SimpleDateFormat("yyyyMM").format(x.getJSONObject("event").getDate("time"))
      val week = new SimpleDateFormat("yyyyww").format(x.getJSONObject("event").getDate("time"))
      val appkey = x.getString("appkey")
      val version = x.getString("version")
      (date + appkey + version, week + appkey + version, month + appkey + version)
    }, 1)).reduceByKey(_ + _)

    deviceStats.foreachRDD(rdd => {
      rdd.foreachPartition(partitionOfRecords => {
        partitionOfRecords.foreach(pair => {
          val jedis = RedisClient.pool.getResource
          jedis.select(1)
          jedis.incrBy(pair._1._1, pair._2)
          jedis.incrBy(pair._1._2, pair._2)
          jedis.incrBy(pair._1._3, pair._2)
          RedisClient.pool.returnResource(jedis)
        })
      })
    })

    ssc.start()
    ssc.awaitTermination()
  }
}