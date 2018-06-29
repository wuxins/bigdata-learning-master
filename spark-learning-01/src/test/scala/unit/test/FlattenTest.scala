package unit.test

/**
  * Created by think on 2018/3/6.
  */
object FlattenTest {
  def main(args: Array[String]): Unit = {

    // List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z)
    val abcd = Seq('a', 'b', 'c', 'd')
    val efgj = Seq('e', 'f', 'g', 'h')
    val ijkl = Seq('i', 'j', 'k', 'l')
    val mnop = Seq('m', 'n', 'o', 'p')
    val qrst = Seq('q', 'r', 's', 't')
    val uvwx = Seq('u', 'v', 'w', 'x')

    val yz = Seq('y', 'z')

    val alphabet = Seq(abcd, efgj, ijkl, mnop, qrst, uvwx, yz)
    println(alphabet)
    println(alphabet.flatten)

  }
}
