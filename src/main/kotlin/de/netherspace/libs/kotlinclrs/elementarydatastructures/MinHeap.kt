package de.netherspace.libs.kotlinclrs.elementarydatastructures

import java.util.concurrent.atomic.AtomicInteger

class MinHeap<T> : Heap<T>  where T : Comparable<T> {

    private val initialArraySize = 10
    private val h = arrayOfNulls<Heap.HeapElement<T>>(initialArraySize)
    private var heapSize = AtomicInteger(0)

    override fun insert(x: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun peek(): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun extract(): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun buildHeap(a: Array<T>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun heapify(i: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun increaseHeapSize(): Int {
        return heapSize.incrementAndGet()
    }

}
