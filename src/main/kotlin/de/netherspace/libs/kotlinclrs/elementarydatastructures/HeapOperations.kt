package de.netherspace.libs.kotlinclrs.elementarydatastructures

import java.util.concurrent.atomic.AtomicInteger

interface HeapOperations<T> {
    /**
     * Returns the node's left child.
     *
     * @param i the node's index
     * @return it's left child
     */
    fun left(i: Int): Int {
        return 2 * i + 1
    }

    /**
     * Returns the node's right child.
     *
     * @param i the node's index
     * @return it's right child
     */
    fun right(i: Int): Int {
        return 2 * i + 2
    }

    /**
     * Returns the node's parent.
     *
     * @param i the node's index
     * @return it's parent
     */
    fun parent(i: Int): Int {
        return (i - 1) / 2
    }

    fun increaseHeapSize(heapSize: AtomicInteger): Int {
        return heapSize.incrementAndGet()
    }

    fun decreaseHeapSize(heapSize: AtomicInteger): Int {
        return heapSize.decrementAndGet()
    }

}
