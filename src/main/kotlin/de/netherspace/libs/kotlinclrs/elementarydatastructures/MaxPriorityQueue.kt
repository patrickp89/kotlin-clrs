package de.netherspace.libs.kotlinclrs.elementarydatastructures

class MaxPriorityQueue<T> : Queue<T> where T : Comparable<T> {

    val a: Array<T>
    val maxHeap = MaxHeap<T>()

    constructor(A: Array<T>) {
        a = A
        maxHeap.buildHeap(a)
    }

    override fun insert(x: T) {
        TODO("not implemented")
    }

    override fun peek(): T {
        return maxHeap.get(0)
    }

    override fun extract(): T {
        return extractMax()
    }

    override fun changeKey(x: T, k: Long) {
        increaseKey(x, k)
    }

    override fun isEmpty(): Boolean {
        return a.isEmpty()
    }

    /**
     * Will return the element with the maximum key.
     *
     * @return the element with the maximum key
     */
    private fun extractMax(): T {
        TODO("not implemented")
    }

    /**
     * Increases the key of a given element
     *
     * @param x the element who's key will be increased
     * @param k the key's new value
     */
    private fun increaseKey(x: T, k: Long) {
        TODO("not implemented")
    }

}
