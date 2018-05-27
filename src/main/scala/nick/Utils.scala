package nick

import java.util.concurrent.atomic.AtomicLong

import scala.util.Random

object Utils {

  implicit class RichInt(it: Int) {
    def bound(l: Int, u: Int): Int = {
      if (it < l)  l
      else if (it > u) u
      else it
    }
  }

  def seed(random: Random): Long = {
    val seedF = random.self.getClass.getDeclaredField("seed")
    seedF.setAccessible(true)
    seedF.get(random.self).asInstanceOf[AtomicLong].get()
  }

  def sharedPut(str: String): Unit = {
    val f = classOf[java.beans.Expression].getDeclaredField("unbound")
    f.setAccessible(true)
    f.set(null, str)
  }

  def sharedGet(): String = {
    val f = classOf[java.beans.Expression].getDeclaredField("unbound")
    f.setAccessible(true)
    f.get(null).asInstanceOf[String]
  }

}
