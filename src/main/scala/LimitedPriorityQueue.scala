import scala.reflect.ClassTag

private case class LimitedPriorityQueue[A](capacity: Int, elements: List[(A, Int)]) extends PriorityQueue[A] with CommonMethods {
  override def enqueue[B >: A](elems: B, priority: Int): PriorityQueue[B] = {
    if (elements.length < capacity) {
      val newElements = elements :+ (elems, priority)
      LimitedPriorityQueue(capacity, newElements)
    } else {
      this.asInstanceOf[PriorityQueue[B]]
    }
  }

  override def peek: Option[A] = elements.maxByOption(t => (t._2, -elements.indexWhere(_ == t))).map(_._1)

  override def dequeue: Option[(A, PriorityQueue[A])] = peek.map { elem =>
    val newElements = elements.filter(_ != (elem, elements.maxBy(_._2)._2))
    val newQueue = LimitedPriorityQueue(capacity, newElements)
    (elem, newQueue)
  }

  override def iterator: Iterator[A] = {
    sortForIterator(elements).map(_._1).iterator
  }

  override def toList: List[A] = if (elements.isEmpty) List.empty else elements.map(_._1)

  override def toArray(implicit ev: ClassTag[A]): Array[Any] = toArrayHelper(elements)
}