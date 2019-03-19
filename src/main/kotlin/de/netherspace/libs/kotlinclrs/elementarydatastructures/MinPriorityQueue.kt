package de.netherspace.libs.kotlinclrs.elementarydatastructures

class MinPriorityQueue<T> : Queue<T>, HeapOperations, ArrayOperations where T : Comparable<T> {

    private val a: Array<Queue.QueueElement<T>>
    private val heap = MinHeap<Queue.QueueElement<T>>()

    constructor(A: Array<Queue.QueueElement<T>>) {
        a = A
        heap.buildHeap(a)
    }

    override fun insert(x: T) {
        TODO("not implemented")
    }

    override fun peek(): T {
        return heap.get(0).element
                ?: throw Exception("No element present for maxHeap[0]!")
    }

    override fun extract(): Result<T> {
        return extractMin()
    }

    override fun changeKey(i: Int, k: Long): Result<Long> {
        return decreaseKey(i, k)
    }

    override fun isEmpty(): Boolean {
        return a.isEmpty()
    }

    /**
     * Return the element with the minimum key.
     *
     * @return a Result containing the element with the minimum key or an exception
     */
    private fun extractMin(): Result<T> {
        TODO("not implemented")
    }

    /**
     * Decreases the key of a given element
     *
     * @param i index of the element who's key will be increased
     * @param k the key's new value
     * @return a Result containing the newly set key or an exception
     */
    private fun decreaseKey(i: Int, k: Long): Result<Long> {
        TODO("not implemented")
    }

}
