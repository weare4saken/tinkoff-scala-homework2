import scala.reflect.ClassTag

trait PriorityQueue[A] extends Iterable[A] {
  def enqueue[B >: A](elem: B, priority: Int): PriorityQueue[B]

  //в данной реализации peek == headOption у List'a
  def peek: Option[A]

  def dequeue: Option[(A, PriorityQueue[A])]

  def toList: List[A]

  def toArray(implicit ev: ClassTag[A]): Array[Any]

  //некоторые методы Iterable
  override def iterator: Iterator[A]

  override def isEmpty: Boolean = toList.isEmpty

  override def size: Int = toList.size

  override def head: A = if (isEmpty) throw new NoSuchElementException("head of empty list") else toList.head

  override def tail: List[A] = if (isEmpty) throw new NoSuchElementException("tail of empty list") else toList.tail.reverse
  //так же можно переопределить и другие методы Iterable
}

object PriorityQueue {
  def unlimitedQueue[A]: PriorityQueue[A] = UnlimitedPriorityQueue(Nil)

  def limitedQueue[A](capacity: Int): PriorityQueue[A] = LimitedPriorityQueue(capacity, Nil)

  def apply[A](elems: (A, Int)*): PriorityQueue[A] = {
    var priorityQueue: PriorityQueue[A] = UnlimitedPriorityQueue(Nil)
    for ((elem, priority) <- elems) {
      priorityQueue = priorityQueue.enqueue(elem, priority)
    }
    priorityQueue
  }
}