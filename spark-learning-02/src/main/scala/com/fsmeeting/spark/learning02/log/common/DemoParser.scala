package com.offline.job.babel.log.common

import com.offline.job.babel.log.{LogParser, ParseParams}
import org.apache.spark.sql.SparkSession
import org.springframework.stereotype.Component

@Component
class DemoParser extends LogParser {
    override def run(params: ParseParams, sparkSession: SparkSession): Unit = {
        sparkSession.sparkContext.textFile("")
            .filter(e => e != null)
            .map(e => (e, 1))
            .reduceByKey((x, y) => x + y)
            .saveAsTextFile("")
    }
}

