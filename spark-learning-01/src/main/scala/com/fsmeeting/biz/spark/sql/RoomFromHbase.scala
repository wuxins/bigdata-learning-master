package com.fsmeeting.biz.spark.sql

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{Result, Scan}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.{TableInputFormat, TableMapReduceUtil}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object RoomFromHbase {

  def main(args: Array[String]): Unit = {

    val conf: Configuration = HBaseConfiguration.create()
    //设置zooKeeper集群地址，也可以通过将hbase-site.xml导入classpath，但是建议在程序里这样设置
    conf.set("hbase.zookeeper.quorum", "master,node1,node2")
    //设置zookeeper连接端口，默认2181
    conf.set("hbase.zookeeper.property.clientPort", "2181")
    conf.set(TableInputFormat.INPUT_TABLE, "T_FMAnalysis_INFO")

    //组装scan语句
    val scan = new Scan(Bytes.toBytes("ErrorCrashInfo20180101"), Bytes.toBytes("ErrorCrashInfo20180501"))
    //开始rowkey和结束一样代表精确查询某条数据
    scan.setCacheBlocks(false)

    //将scan类转化成string类型
    val scan_str = TableMapReduceUtil.convertScanToString(scan)
    conf.set(TableInputFormat.SCAN, scan_str)

    val sparkSession: SparkSession = SparkSession.builder().appName(getClass.getSimpleName).getOrCreate()

    val hBaseRDD: RDD[(ImmutableBytesWritable, Result)] = sparkSession.sparkContext.newAPIHadoopRDD(
      conf,
      classOf[TableInputFormat],
      classOf[ImmutableBytesWritable],
      classOf[Result]
    )

    val count: Long = hBaseRDD.count()
    println(count)
    sparkSession.stop()
  }

}
