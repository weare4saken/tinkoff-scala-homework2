import munit.FunSuite

class UnlimitedPriorityQueueTest extends FunSuite {
  test("enqueue") {
    var pq: PriorityQueue[String] = UnlimitedPriorityQueue(Nil)
    pq = pq.enqueue("abc", 1)

    assertEquals(pq.peek, Some("abc"))
  }

  test("peek") {
    var pq: PriorityQueue[Int] = UnlimitedPriorityQueue(Nil)
    pq = pq.enqueue(1, 1)
      .enqueue(2, 3)
      .enqueue(3, 2)
      .enqueue(4, 7)
      .enqueue(5, 6)
      .enqueue(6, 5)
      .enqueue(7, 7)

    assertEquals(pq.peek, Some(4))
  }

  test("dequeue") {
    var pq: PriorityQueue[Int] = UnlimitedPriorityQueue(Nil)
    pq = pq.enqueue(1, 3)
      .enqueue(2, 1)
      .enqueue(3, 2)
      .enqueue(4, 3)
      .enqueue(5, 1)

    val (elem, pq2) = pq.dequeue.get

    assertEquals(elem, 1)
    assertEquals(pq2.peek, Some(4))
  }

  test("iterator is empty") {
    val pq: PriorityQueue[Int] = UnlimitedPriorityQueue(Nil)

    assertEquals(pq.iterator.hasNext, false)
  }

  test("iterator is not empty") {
    val pq: PriorityQueue[Int] = UnlimitedPriorityQueue(List((1, 1), (2, 2), (3, 3)))
    val it = pq.iterator

    assertEquals(it.hasNext, true)
    assertEquals(it.next(), 1)
    assertEquals(it.hasNext, true)
    assertEquals(it.next(), 2)
    assertEquals(it.hasNext, true)
    assertEquals(it.next(), 3)
    assertEquals(it.hasNext, false)
  }

  test("toList") {
    var pq: PriorityQueue[Int] = UnlimitedPriorityQueue(Nil)
    pq = pq.enqueue(1, 1)
      .enqueue(2, 3)
      .enqueue(3, 2)
      .enqueue(4, 7)
      .enqueue(5, 6)
      .enqueue(6, 5)
      .enqueue(7, 7)

    val expectedList = List(1, 2, 3, 4, 5, 6, 7)
    val actualList = pq.toList

    assertEquals(actualList, expectedList)
  }

  test("toArray") {
    var pq: PriorityQueue[Int] = UnlimitedPriorityQueue(Nil)
    pq = pq.enqueue(1, 1)
      .enqueue(2, 3)
      .enqueue(3, 2)
      .enqueue(4, 7)
      .enqueue(5, 6)
      .enqueue(6, 5)
      .enqueue(7, 7)

    val expectedArray = Array(1, 2, 3, 4, 5, 6, 7)
    val actualArray = pq.toArray

    assertEquals(actualArray.toList, expectedArray.toList)
  }

  test("isEmpty") {
    val pq: PriorityQueue[Int] = UnlimitedPriorityQueue(Nil)

    assertEquals(pq.isEmpty, true)
  }

  test("size") {
    var pq: PriorityQueue[Int] = UnlimitedPriorityQueue(Nil)
    pq = pq.enqueue(1, 3)
      .enqueue(2, 1)
      .enqueue(3, 2)

    assertEquals(pq.size, 3)
  }

  test("head") {
    var pq: PriorityQueue[Int] = UnlimitedPriorityQueue(Nil)
    pq = pq.enqueue(1, 3)
      .enqueue(2, 1)
      .enqueue(3, 2)

    assertEquals(pq.head, 1)
  }

  test("head with exception") {
    var pq: PriorityQueue[Int] = UnlimitedPriorityQueue(Nil)

    intercept[NoSuchElementException] {
      pq.head
    }
  }

  test("tail") {
    var pq: PriorityQueue[Int] = UnlimitedPriorityQueue(Nil)
    pq = pq.enqueue(1, 3)
      .enqueue(2, 1)
      .enqueue(3, 2)

    assertEquals(pq.tail, List(3, 2))
  }
}