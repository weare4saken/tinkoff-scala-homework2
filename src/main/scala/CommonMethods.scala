import scala.reflect.ClassTag

trait CommonMethods {
  protected def sortForIterator[A](elements: List[(A, Int)]): List[(A, Int)] =
    elements.sortBy { case (_, priority) => (-priority, elements.indexWhere(_ == (_, priority))) }

  protected def toArrayHelper[A](elements: List[(A, Int)])(implicit ev: ClassTag[A]): Array[Any] = {
    val arr = new Array[Any](elements.length)
    elements.zipWithIndex.foreach { case ((elem, _), idx) =>
      arr(idx) = elem.asInstanceOf[Any]
    }
    arr
  }
}