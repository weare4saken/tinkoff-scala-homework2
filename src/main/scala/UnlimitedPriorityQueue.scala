import scala.reflect.ClassTag

private case class UnlimitedPriorityQueue[A](elements: List[(A, Int)]) extends PriorityQueue[A] {
  override def enqueue(elem: A, priority: Int): PriorityQueue[A] = {
    val newElements = elements :+ (elem, priority)
    UnlimitedPriorityQueue(newElements)
  }

  override def peek: Option[A] = elements.maxByOption(t => (t._2, -elements.indexWhere(_ == t))).map(_._1)

  override def dequeue: Option[(A, PriorityQueue[A])] = peek.map { elem =>
    val newElems = elements.filter(_ != (elem, elements.maxBy(_._2)._2))
    (elem, UnlimitedPriorityQueue(newElems))
  }

  override def iterator: Iterator[A] = new Iterator[A] {
    private val iter = elements.iterator

    override def hasNext: Boolean = iter.hasNext

    override def next(): A = iter.next()._1
  }

  override def toList: List[A] = elements.map(_._1)

  override def toArray(implicit ev: ClassTag[A]): Array[Any] = elements.map(_._1).toArray
}