import scala.reflect.ClassTag

private case class UnlimitedPriorityQueue[A](elements: List[(A, Int)]) extends PriorityQueue[A] with CommonMethods {
  override def enqueue[B >: A](elem: B, priority: Int): PriorityQueue[B] = {
    val newElements = elements :+ (elem, priority)
    UnlimitedPriorityQueue(newElements)
  }

  override def peek: Option[A] = elements.maxByOption(t => (t._2, -elements.indexWhere(_ == t))).map(_._1)

  override def dequeue: Option[(A, PriorityQueue[A])] = peek.map { elem =>
    val newElems = elements.filter(_ != (elem, elements.maxBy(_._2)._2))
    (elem, UnlimitedPriorityQueue(newElems))
  }

  override def iterator: Iterator[A] = {
    sortForIterator(elements).map(_._1).iterator
  }

  override def toList: List[A] = if (elements.isEmpty) List.empty else elements.map(_._1)

  override def toArray(implicit ev: ClassTag[A]): Array[Any] = toArrayHelper(elements)
}