import munit.FunSuite

class LimitedPriorityQueueTest extends FunSuite {
  test("enqueue") {
    var pq = PriorityQueue.limitedQueue[String](1)
    pq = pq.enqueue("abc", 1)
    assertEquals(pq.peek, Some("abc"))
  }

  test("peek") {
    var pq = PriorityQueue.limitedQueue[Int](7)
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
    var pq = PriorityQueue.limitedQueue[Int](5)
    pq = pq.enqueue(1, 3)
      .enqueue(2, 1)
      .enqueue(3, 2)
      .enqueue(4, 3)
      .enqueue(5, 1)
    val (elem, pq2) = pq.dequeue.get
    assertEquals(elem, 1)
    assertEquals(pq2.peek, Some(4))
  }

  test("iterator") {
    var pq = PriorityQueue.limitedQueue[Char](7)
    pq = pq.enqueue('b', 6)
      .enqueue('d', 3)
      .enqueue('o', 2)
      .enqueue('a', 7)
      .enqueue('n', 1)
      .enqueue('n', 4)
      .enqueue('a', 5)

    val iterator = pq.iterator
    val expectedElements = List('a', 'b', 'a', 'n', 'd', 'o', 'n')
    val actualElements = iterator.toList

    assertEquals(actualElements, expectedElements)
  }

  test("toList") {
    var pq = PriorityQueue.limitedQueue[Int](7)
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
    var pq = PriorityQueue.limitedQueue[Int](7)
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
    val pq = PriorityQueue.limitedQueue[Int](0)
    assertEquals(pq.isEmpty, true)
  }

  test("size") {
    var pq = PriorityQueue.limitedQueue[Int](3)
    pq = pq.enqueue(1, 3)
      .enqueue(2, 1)
      .enqueue(3, 2)

    assertEquals(pq.size, 3)
  }

  test("head") {
    var pq = PriorityQueue.limitedQueue[Int](3)
    pq = pq.enqueue(1, 3)
      .enqueue(2, 1)
      .enqueue(3, 2)

    assertEquals(pq.head, 1)
  }

  test("tail") {
    var pq = PriorityQueue.limitedQueue[Int](3)
    pq = pq.enqueue(1, 3)
      .enqueue(2, 1)
      .enqueue(3, 2)

    assertEquals(pq.tail, List(3, 2))
  }

  test("tail with exception") {
    var pq = PriorityQueue.limitedQueue[Int](6)

    intercept[NoSuchElementException] {
      pq.tail
    }
  }
}