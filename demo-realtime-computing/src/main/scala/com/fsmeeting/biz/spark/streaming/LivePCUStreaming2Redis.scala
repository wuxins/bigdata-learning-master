package com.fsmeeting.biz.spark.streaming

import java.text.SimpleDateFormat
import java.util.Date

import com.alibaba.fastjson.{JSON, JSONObject}
import com.fsmeeting.utils.RedisClient
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object LivePCUStreaming2Redis {

  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setAppName(getClass.getSimpleName)
    val ssc: StreamingContext = new StreamingContext(sparkConf, Seconds(10)) // 10s per batch
    val topicMap: Map[String, Int] = Map("live-online-users" -> 1)
    val kafkaStream: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc, "node-1:2181",
      "online-streaming-group", topicMap)
    val lines: DStream[JSONObject] = kafkaStream.flatMap(line => {
      Some(JSON.parseObject(line._2))
    })

    //{"event":{"code":"live_login","time":1524749266600},"meeting":{"companyId":3,"pcu":546,"roomId":1002}}
    val line: DStream[(String, Int)] = lines.map(x => {
      val sep: String = ":";
      val companyId = x.getJSONObject("meeting").getIntValue("companyId")
      val roomId = x.getJSONObject("meeting").getIntValue("roomId")
      val pcu = x.getJSONObject("meeting").getIntValue("pcu")
      val time = x.getJSONObject("event").getLongValue("time")
      val timeMin: String = new SimpleDateFormat("yyyyMMddHHmm").format(new Date(time))
      (companyId + sep + roomId + sep + timeMin, pcu)
    })

    val grouped: DStream[(String, Iterable[Int])] = line.groupByKey()

    val maxOfGrouped: DStream[(String, Int)] = grouped.mapValues(_.max)

    maxOfGrouped.foreachRDD(rdd => {
      rdd.foreachPartition(partitionOfRecords => {
        //to redis
        val jedis = RedisClient.pool.getResource
        val pipeline = jedis.pipelined
        partitionOfRecords.foreach(pair => {
          jedis.select(1)
          val pre = jedis.get(pair._1)
          val cur = pair._2
          if (pre == null || cur > pre.toInt) {
            jedis.set(pair._1, pair._2.toString)
          }
        })
        pipeline.sync()
        RedisClient.pool.returnResource(jedis)
      })
    })

    ssc.start()
    ssc.awaitTermination()
  }
}