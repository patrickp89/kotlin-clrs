package de.netherspace.libs.kotlinclrs.elementarydatastructures

class MaxPriorityQueue<T> : Queue<T>, HeapOperations, ArrayOperations where T : Comparable<T> {

    private val a: Array<Queue.QueueElement<T>>
    private val heap = MaxHeap<Queue.QueueElement<T>>()

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
        return extractMax()
    }

    override fun changeKey(i: Int, k: Long): Result<Long> {
        return increaseKey(i, k)
    }

    override fun isEmpty(): Boolean {
        return a.isEmpty()
    }

    /**
     * Return the element with the maximum key.
     *
     * @return a Result containing the element with the maximum key or an exception
     */
    private fun extractMax(): Result<T> {
        val heapSize = heap.getHeapSize()
        if (heapSize < 1) {
            return Result.failure(Exception("Heap underflow!"))
        }

        val max = a[0]
        a[0] = a[heapSize]
        heap.decreaseHeapSize()
        heap.heapify(0)

        val element: T = max.element
                ?: throw Exception("No element present for maxHeap[0]!")
        return Result.success(element)
    }

    /**
     * Increases the key of a given element
     *
     * @param i index of the element who's key will be increased
     * @param k the key's new value
     * @return a Result containing the newly set key or an exception
     */
    private fun increaseKey(i: Int, k: Long): Result<Long> {
        var j = i
        val oldKey = a[j].key
        if (k < oldKey) {
            return Result.failure(Exception("New key ($k) is less than the old key ($oldKey)!"))
        }
        a[j].key = k

        while ((j > 0) && (a[parent(j)].key < a[j].key)) {
            swap(a, j, parent(j))
            j = parent(j)
        }
        return Result.success(k)
    }

}
