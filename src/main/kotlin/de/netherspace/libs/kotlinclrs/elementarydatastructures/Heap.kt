package de.netherspace.libs.kotlinclrs.elementarydatastructures

interface Heap<T> {

    /**
     * Creates a heap from a given array.
     *
     * @param A the array that will be turned into a heap
     */
    fun buildHeap(A: Array<T>)

    /**
     * Corrects a subtree of the heap. Afterwards all heap
     * properties will hold for this particular subtree.
     *
     * @param i the index of the subtree's root
     */
    fun heapify(i: Int)

    /**
     * Returns the element at position i.
     *
     * @param i the position in the underlying array
     * @return the element at the given position
     */
    fun get(i: Int): T

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
