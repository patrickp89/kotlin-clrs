package de.netherspace.libs.kotlinclrs.elementarydatastructures

interface QueueOperations<T> : HeapOperations<T> {

    fun extractHead(a: Array<Queue.QueueElement<T>>, heap: Heap<Queue.QueueElement<T>>): Result<T> {
        val heapSize = heap.getHeapSize()
        if (heapSize < 1) {
            return Result.failure(Exception("Heap underflow!"))
        }

        val max = a[0]
        a[0] = a[heapSize]
        decreaseHeapSize(heap.getHeapSizeCounter())
        heap.heapify(0)

        return if (max.element == null) {
            Result.failure(Exception("No element present for maxHeap[0]!"))
        } else {
            Result.success(max.element)
        }
    }

    enum class Comparison {
        GREATER, LESSER
    }
}
