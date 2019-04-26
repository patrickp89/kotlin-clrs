package de.netherspace.libs.kotlinclrs.sorting

import de.netherspace.libs.kotlinclrs.elementarydatastructures.MaxHeap

class HeapSort : SortingAlgorithm {

    override fun sort(A: Array<Int>) {
        val heap = MaxHeap<Int>()
        heap.buildHeap(A)
        for (i in (A.size - 1) downTo 1) {
            swap(A, 0, i)
            heap.decreaseHeapSize(heap.getHeapSizeCounter())
            heap.heapify(0)
        }
    }

}