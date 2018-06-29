package unit.test

/**
  * Created by think on 2018/3/6.
  */
object FilterTest {

  def main(args: Array[String]): Unit = {
    val numbers = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(numbers.filter(_ % 2 == 0))

    case class Book(title: String, pages: Int)

    val books = Seq(
      Book("Future of Scala developers", 85),
      Book("Parallel algorithms", 240),
      Book("Object Oriented Programming", 130),
      Book("Mobile Development", 495)
    )

    println(books.filter(_.pages > 120))
  }
}
