package de.netherspace.libs.kotlinclrs.elementarydatastructures

class MinPriorityQueue<T>(
        private var a: Array<Queue.QueueElement<T>>
) : Queue<T>, QueueOperations<T>, HeapOperations<T>, ArrayOperations where T : Comparable<T> {
    private val heap = MinHeap<Queue.QueueElement<T>>()

    init {
        heap.buildHeap(a)
    }

    override fun insert(x: T, k: Long) {
        increaseHeapSize(heap.getHeapSizeCounter())
        a = insertInfinityElement(a, x)
        decreaseKey(heap.getHeapSize(), k)
    }

    override fun peek(): T {
        return heap.get(0).element
                ?: throw Exception("No element present for maxHeap[0]!")
    }

    override fun extract(): Result<T> {
        return extractHead(a, heap)
    }

    override fun changeKey(i: Int, k: Long): Result<Long> {
        return decreaseKey(i, k)
    }

    override fun isEmpty(): Boolean {
        return a.isEmpty()
    }

    override fun getArray(): Array<Queue.QueueElement<T>> {
        return a
    }

    /**
     * Decreases the key of a given element
     *
     * @param i index of the element who's key will be decreased
     * @param k the key's new value
     * @return a Result containing the newly set key or an exception
     */
    private fun decreaseKey(i: Int, k: Long): Result<Long> {
        var j = i
        val oldKey = a[j].key
        if (k > oldKey) { // TODO: parameterize this comparison and extract this function!
            return Result.failure(Exception("New key ($k) is greater than the old key ($oldKey)!"))
        }
        a[j].key = k

        while ((j > 0) && (a[parent(j)].key > a[j].key)) { // TODO: parameterize this comparison and extract this function!
            swap(a, j, parent(j))
            j = parent(j)
        }
        return Result.success(k)
    }

}
