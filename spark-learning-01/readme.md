./bin/spark-submit --class MySpark --master spark://Master:7077 /fsmeeting/task/spark-test.jar  

./spark-submit --class spark.wordcount.WordCount  /opt/spark-wordcount-in-scala.jar 

bin/kafka-topics.sh --create --zookeeper Master:2181 --replication-factor 1 --partitions 3 --topic spark-streaming-test
bin/kafka-console-producer.sh --broker-list Master:9092,Slave1:9092,Slave2:9092 --topic spark-streaming-test
bin/kafka-console-consumer.sh --zookeeper Master:2181 --from-beginning --topic spark-streaming-test

./bin/spark-submit --class com.fsmeeting.DeviceStreaming --master spark://Master:7077 --jars /fsmeeting/jars/spark-streaming-kafka-0-8-assembly_2.11-2.3.0.jar,/fsmeeting/jars/commons-pool2-2.4.2.jar,/fsmeeting/jars/jedis-2.9.0.jar,/fsmeeting/jars/fastjson-1.2.28.jar /fsmeeting/task/spark-demo-1.0-SNAPSHOT.jar
sc.textFile("hdfs://Master:9000/...").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).sortBy(_._2, false).collect


./bin/spark-submit \
--class com.fsmeeting.biz.spark.streaming.LivePCUStreaming2Redis \
--master yarn \
--deploy-mode cluster \
--driver-cores 2 \
--executor-cores 2 \
--executor-memory 1G \
/fsmeeting/task/spark-learning-01-1.0-SNAPSHOT-jar-with-dependencies.jar

./bin/spark-submit \
--class com.fsmeeting.biz.spark.sql.RoomFromJSON \
--master local[*] \
--driver-class-path /fsmeeting/jars/mysql-connector-java-5.1.40.jar \
/fsmeeting/task/spark-learning-01-1.0-SNAPSHOT-jar-with-dependencies.jar \
hdfs://node-1:9000/room.json

-- 本地模式
./bin/spark-submit \
--class com.fsmeeting.scala.test.FavorTeacher \
--master local[*] \
--jars /home/jars/fastjson-1.2.28.jar \
/home/task/spark-learning-01-1.0-SNAPSHOT.jar \
/home/txt/fav-teacher.txt

-- 集群单机模式
./bin/spark-submit \
--class com.fsmeeting.scala.test.FavorTeacher \
--master spark://node-1.example.com:7077 \
--jars /home/jars/fastjson-1.2.28.jar \
/home/task/spark-learning-01-1.0-SNAPSHOT.jar \
/home/txt/fav-teacher.txt

-- 集群yarn-client模式
./bin/spark-submit \
--class com.fsmeeting.scala.test.FavorTeacher \
--master yarn \
--deploy-mode client \
--jars /home/jars/fastjson-1.2.28.jar \
/home/task/spark-learning-01-1.0-SNAPSHOT.jar \
/home/txt/fav-teacher.txt

-- 集群yarn-cluster模式
./bin/spark-submit \
--class com.fsmeeting.scala.test.FavorTeacher \
--master yarn \
--deploy-mode cluster \
--jars /home/jars/fastjson-1.2.28.jar \
/home/task/spark-learning-01-1.0-SNAPSHOT.jar \
/home/txt/fav-teacher.txt