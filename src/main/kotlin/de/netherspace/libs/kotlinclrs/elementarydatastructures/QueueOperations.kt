package de.netherspace.libs.kotlinclrs.elementarydatastructures

interface QueueOperations<T> : HeapOperations<T> {

    fun insertInfinityElement(a: Array<Queue.QueueElement<T>>, x: T): Array<Queue.QueueElement<T>> {
        println("Resizing array of size ${a.size}")
        val e = Queue.QueueElement(x, Long.MAX_VALUE)
        val resizedArray = a.copyOf(a.size + 1)
                .map { it ?: e }
                .toTypedArray()

        println("Its new size is ${resizedArray.size}")
        return resizedArray
    }

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
