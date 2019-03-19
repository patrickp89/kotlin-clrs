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

    fun getHeapSize(): Int

    /**
     * An element of a heap, wrapping the actual (generic) value.
     */
    class HeapElement<T>(val element: T?) where T : Comparable<T>

}
