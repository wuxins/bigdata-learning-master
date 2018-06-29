package unit.test

import com.fsmeeting.RedisClient
import org.junit.Test

/**
  * Created by think on 2018/4/1.
  */
object JedisTest {


  @Test
  def testJedis: Unit = {
    val jedis = RedisClient.pool.getResource
    jedis.select(1)
    jedis.incrBy("a", 1)
  }

}
