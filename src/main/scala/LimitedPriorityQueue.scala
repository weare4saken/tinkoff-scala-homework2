import scala.reflect.ClassTag

private case class LimitedPriorityQueue[A](capacity: Int, elements: List[(A, Int)]) extends PriorityQueue[A] {
  override def enqueue(elems: A, priority: Int): PriorityQueue[A] = {
    if (elements.length < capacity) {
      val newElements = elements :+ (elems, priority)
      LimitedPriorityQueue(capacity, newElements)
    } else {
      this.asInstanceOf[PriorityQueue[A]]
    }
  }

  override def peek: Option[A] = elements.maxByOption(t => (t._2, -elements.indexWhere(_ == t))).map(_._1)

  override def dequeue: Option[(A, PriorityQueue[A])] = getHighPriorityEl.map { elem =>
    val newEls = elements.filterNot(_ == elem)
    val newQueue = LimitedPriorityQueue(capacity, newEls)
    (elem._1, newQueue)
  }

  private def getHighPriorityEl: Option[(A, Int)] = elements.maxByOption(_._2)

  override def iterator: Iterator[A] = new Iterator[A] {
    private val iter = elements.iterator

    override def hasNext: Boolean = iter.hasNext

    override def next(): A = iter.next()._1
  }

  override def toList: List[A] = elements.map(_._1)

  override def toArray(implicit ev: ClassTag[A]): Array[Any] = elements.map(_._1).toArray
}