package de.netherspace.libs.kotlinclrs.elementarydatastructures

import java.util.concurrent.atomic.AtomicInteger

class MaxHeap<T> : ArrayOperations, Heap<T> where T : Comparable<T> {

    private val initialArraySize = 10
    //    private val a2 = arrayOfNulls<Heap.HeapElement<T>>(initialArraySize)
    private lateinit var a: Array<T>
    private var heapSize = AtomicInteger(0)

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

//        val leftChild: Heap.HeapElement<T> = a2[l]
//                ?: throw Exception("Could not find left child (index $l)!")
//        val iNode : Heap.HeapElement<T> = a2[i]
//                ?: throw Exception("Could not find the element at index i ($i)!")

//        val leftChildsElement : T = leftChild.element
//                ?: throw Exception("The left child heap element (index $l) does not contain a value!")
//        val iElement : T = iNode.element
//                ?: throw Exception("The heap element at index i ($i) does not contain a value!")

//        var max = if ( (l <= heapSize.get()) && (leftChildsElement > iElement) ) {
        var max = if ((l <= heapSize.get()) && (a[l] > a[i])) {
            l
        } else {
            i
        }

//        val rightChild: Heap.HeapElement<T> = a2[r]
//                ?: throw Exception("Could not find right child (index $r)!")
//
//        val rightChildsElement : T = rightChild.element
//                ?: throw Exception("The right child heap element (index $r) does not contain a value!")

//        val maxNode: Heap.HeapElement<T> = a2[max]
//                ?: throw Exception("Could not find the max node (index $max)!")
//
//        val maxNodeElement : T = maxNode.element
//                ?: throw Exception("The max heap element (index $max) does not contain a value!")

        if ((r <= heapSize.get()) && (a[r] > a[max])) {
            max = r
        }

        if (max != i) {
            // TODO: use swap(a2, i, max)
            val d = a[max]
            a[max] = a[i]
            a[i] = d

            heapify(max)
        }
    }

    override fun get(i: Int): T {
        return a[i]
    }

    fun decreaseHeapSize(): Int {
        return heapSize.decrementAndGet()
    }

}
