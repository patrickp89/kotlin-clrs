package de.netherspace.libs.kotlinclrs.elementarydatastructures

import java.util.concurrent.atomic.AtomicInteger

class MinHeap<T> : Heap<T>, HeapOperations, ArrayOperations where T : Comparable<T> {

    private val initialArraySize = 10
    //    private val h = arrayOfNulls<Heap.HeapElement<T>>(initialArraySize)
    private lateinit var a: Array<T>
    private var heapSize = AtomicInteger(0)

    override fun buildHeap(a: Array<T>) {
        TODO("not implemented")
    }

    override fun heapify(i: Int) {
        TODO("not implemented")
    }

    override fun get(i: Int): T {
        return a[i]
    }

    override fun getHeapSize(): Int {
        return heapSize.get()
    }

    fun increaseHeapSize(): Int {
        return heapSize.incrementAndGet()
    }

}
