package unit.test

/**
  * Created by think on 2018/3/6.
  */
object MapTest {

  def main(args: Array[String]): Unit = {
    val numbers = Seq(1, 2, 3, 4, 5, 6)
    println(numbers.map(_ * 3.14))
    val chars = Seq('a', 'b', 'c', 'd')
    println(chars.map(_.toUpper)) //chars.map(ch => ch.toUpper) //List(A, B, C, D)
  }
}
