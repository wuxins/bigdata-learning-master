package unit.test

/**
  * Created by think on 2018/3/6.
  */
object EulerDiagramTest {
  def main(args: Array[String]): Unit = {
    val num1 = Seq(1, 2, 3, 4, 5, 6)
    val num2 = Seq(4, 5, 6, 7, 8, 9)

    println(num1.diff(num2)) //List(1, 2, 3)

    println(num1.intersect(num2)) //List(4, 5, 6)

    println(num1.union(num2)) //List(1, 2, 3, 4, 5, 6, 4, 5, 6, 7, 8, 9)

    println(num1.union(num2).distinct) //List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  }
}
