package com.fsmeeting.biz.spark.streaming

import java.text.SimpleDateFormat

import com.alibaba.fastjson.JSON
import com.fsmeeting.RedisClient
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object DeviceDirectStreaming {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("DeviceDirectStreaming").setMaster("local[2]")
    val ssc = new StreamingContext(sparkConf, Seconds(10)) // 10s per batch

    val kafkaParams = Map[String, String](
      "metadata.broker.list" -> "node-1:9092",
      "group.id" -> "device-direct-streaming")

    val topics = Set("spark-streaming-test")
    val stream = KafkaUtils.createDirectStream(ssc, kafkaParams, topics)

    val streamData = stream.flatMap(line => {
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
      /*val offsetsList = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      //val cluster = new KafkaCluster(kafkaParams)
      for (offsets <- offsetsList) {
        println(s"${offsets.topic} ${offsets.partition} ${offsets.fromOffset} ${offsets.untilOffset}")
        /*val topicAndPartition = TopicAndPartition("spark-streaming-test", offsets.partition)
        val o = cluster.setConsumerOffsets(args(0), Map((topicAndPartition, offsets.untilOffset)))
        if (o.isLeft) {
          println(s"Error updating the offset to Kafka cluster: ${o.left.get}")
        }*/
      }*/

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