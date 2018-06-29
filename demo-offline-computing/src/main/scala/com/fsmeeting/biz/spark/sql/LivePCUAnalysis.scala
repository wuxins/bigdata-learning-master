package com.fsmeeting.biz.spark.sql

import com.google.common.base.Charsets
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{Result, Scan}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.{TableInputFormat, TableMapReduceUtil}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object LivePCUAnalysis {

  def main(args: Array[String]): Unit = {

    val conf: Configuration = HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", "node-1,node-2,node-3")
    conf.set("hbase.zookeeper.property.clientPort", "2181")
    conf.set(TableInputFormat.INPUT_TABLE, "t_client_logs")

    val scan = new Scan(Bytes.toBytes("live-pcu/201805251100"), Bytes.toBytes("live-pcu/201805252155"))

    scan.setCacheBlocks(false)

    val scan_str = TableMapReduceUtil.convertScanToString(scan)
    conf.set(TableInputFormat.SCAN, scan_str)

    val sparkSession: SparkSession = SparkSession.builder().appName(getClass.getSimpleName).getOrCreate()

    val hBaseRDD: RDD[(ImmutableBytesWritable, Result)] = sparkSession.sparkContext.newAPIHadoopRDD(
      conf,
      classOf[TableInputFormat],
      classOf[ImmutableBytesWritable],
      classOf[Result]
    )

    val result: RDD[Result] = hBaseRDD.map(_._2)

    val statMap: RDD[(String, Long)] = result.map(item => {
      val time = Bytes.toLong(item.getValue("info".getBytes, "event_time".getBytes)).toString
      val companyId = Bytes.toLong(item.getValue("info".getBytes, "meeting_companyId".getBytes))
      val pcu = Bytes.toLong(item.getValue("info".getBytes, "meeting_pcu".getBytes(Charsets.UTF_8)))
      val roomId = Bytes.toLong(item.getValue("info".getBytes, "meeting_roomId".getBytes(Charsets.UTF_8)))
      (companyId + "/" + roomId + "/" + time.substring(0, time.length - 2), pcu)
    })

    println(statMap.count())
    val peakPCU: RDD[(String, Long)] = statMap.groupByKey().mapValues(_.toList.max)
    println(peakPCU.count())

    sparkSession.stop()
  }

}
