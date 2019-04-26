package de.netherspace.libs.kotlinclrs.elementarydatastructures

import java.util.concurrent.atomic.AtomicInteger

class MinHeap<T> : Heap<T>, HeapOperations<T>, ArrayOperations where T : Comparable<T> {

    private lateinit var a: Array<T>
    private lateinit var heapSize: AtomicInteger

    override fun buildHeap(A: Array<T>) {
        a = A
        heapSize = AtomicInteger(a.size - 1)
        for (i in (a.size / 2) downTo 0) {
            heapify(i)
        }
    }

    override fun heapify(i: Int) {
        val l = left(i)
        val r = right(i)

        var min = if ((l <= heapSize.get()) && (a[l] < a[i])) { // TODO: parameterize this comparison and extract this function!
            l
        } else {
            i
        }

        if ((r <= heapSize.get()) && (a[r] < a[min])) { // TODO: parameterize this comparison and extract this function!
            min = r
        }

        if (min != i) {
            // TODO: use swap(a2, i, max)
            val d = a[min]
            a[min] = a[i]
            a[i] = d

            heapify(min)
        }
    }

    override fun get(i: Int): T {
        return a[i]
    }

    override fun getHeapSize(): Int {
        return heapSize.get()
    }

    override fun getHeapSizeCounter(): AtomicInteger {
        return heapSize
    }

}
