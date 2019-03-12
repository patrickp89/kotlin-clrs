package de.netherspace.libs.kotlinclrs.elementarydatastructures

interface Heap<T> {

    /**
     * Inserts a given element into the heap.
     *
     * @param x the element that will be inserted
     */
    fun insert(x: T)

    /**
     * Returns the min/max (depending on the implementation).
     *
     * @return the min/max
     */
    fun peek(): T

    /**
     * Returns the min/max (depending on the implementation) and
     * deletes it from the heap.
     *
     * @return the min/max
     */
    fun extract(): T

    fun buildHeap(a: Array<T>)

    fun heapify(i: Int)

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

    /**
     * An element of a heap, wrapping the actual (generic) value.
     */
    class HeapElement<T>(e: T?) where T : Comparable<T> {
        val element = e
    }
}
