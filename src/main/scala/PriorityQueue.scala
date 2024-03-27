import scala.reflect.ClassTag

trait PriorityQueue[A] extends Iterable[A] {
  def enqueue(elem: A, priority: Int): PriorityQueue[A]

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
  def apply[A](elems: (A, Int)*): PriorityQueue[A] = {
    var priorityQueue: PriorityQueue[A] = UnlimitedPriorityQueue(Nil)
    val filledQueue = elems.foldLeft(UnlimitedPriorityQueue[A](Nil): PriorityQueue[A]) {
      case (acc, (el, prior)) => acc.enqueue(el, prior)
    }

    filledQueue
  }
}