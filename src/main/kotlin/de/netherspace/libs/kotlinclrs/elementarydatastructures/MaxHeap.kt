package de.netherspace.libs.kotlinclrs.elementarydatastructures

import java.util.concurrent.atomic.AtomicInteger

class MaxHeap<T> : ArrayOperations, Heap<T> where T : Comparable<T> {

    private val initialArraySize = 10
    //    private val a2 = arrayOfNulls<Heap.HeapElement<T>>(initialArraySize)
    private lateinit var a2: Array<T>
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
        a2 = a
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
        var max = if ((l <= heapSize.get()) && (a2[l] > a2[i])) {
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

        if ((r <= heapSize.get()) && (a2[r] > a2[max])) {
            max = r
        }

        if (max != i) {
            // TODO: use swap(a2, i, max)
            val d = a2[max]
            a2[max] = a2[i]
            a2[i] = d

            heapify(max)
        }
    }

    fun decreaseHeapSize(): Int {
        return heapSize.decrementAndGet()
    }

}
