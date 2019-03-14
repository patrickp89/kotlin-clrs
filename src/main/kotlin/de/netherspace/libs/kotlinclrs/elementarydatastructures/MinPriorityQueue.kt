package de.netherspace.libs.kotlinclrs.elementarydatastructures

class MinPriorityQueue<T> : Queue<T> where T : Comparable<T> {

    val a: Array<T>
    val minHeap = MinHeap<T>()

    constructor(A: Array<T>) {
        a = A
        minHeap.buildHeap(a)
    }

    override fun insert(x: T) {
        TODO("not implemented")
    }

    override fun peek(): T {
        return minHeap.get(0)
    }

    override fun extract(): T {
        return extractMin()
    }

    override fun changeKey(x: T, k: Long) {
        decreaseKey(x, k)
    }

    override fun isEmpty(): Boolean {
        return a.isEmpty()
    }

    /**
     * Will return the element with the minimum key.
     *
     * @return the element with the minimum key
     */
    private fun extractMin(): T {
        TODO("not implemented")
    }

    /**
     * Decreases the key of a given element
     *
     * @param x the element who's key will be decreased
     * @param k the key's new value
     */
    private fun decreaseKey(x: T, k: Long) {
        TODO("not implemented")
    }

}
