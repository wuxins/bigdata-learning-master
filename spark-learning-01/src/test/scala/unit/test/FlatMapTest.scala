package unit.test

/**
  * map & flatten
  * Created by think on 2018/3/6.
  */
object FlatMapTest {
  def main(args: Array[String]): Unit = {
    val abcd = Seq('a', 'b', 'c', 'd')

    println(abcd.flatMap(ch => List(ch.toUpper, ch))) //List(A, a, B, b, C, c, D, d)
    //abcd.flatMap(_)
    //https://www.iteblog.com/archives/1946.html
  }
}
