package unit.test

import org.junit.Test

/**
  * Created by think on 2018/3/6.
  */
class MaxMinTest {

  @Test
  def test: Unit = {

    val numbers = Seq(11, 2, 5, 1, 6, 3, 9)
    println(numbers.max + "/" + numbers.min)

    val books = Seq(
      Book("Future of Scala developers", 85),
      Book("Parallel algorithms", 240),
      Book("Object Oriented Programming", 130),
      Book("Mobile Development", 495)
    )
    println(books.maxBy(book => book.pages) + "/" + books.minBy(_.pages))

  }

  case class Book(title: String, pages: Int)


}
