package de.netherspace.libs.kotlinclrs.elementarydatastructures


class MinPriorityQueue<T> : Queue<T> {

    override fun insert(x: T) {
        TODO("not implemented")
    }

    override fun peek(): T {
        TODO("not implemented")
    }

    override fun extract(): T {
        return extractMin()
    }

    override fun changeKey(x: T, k: Long) {
        return decreaseKey(x, k)
    }

    override fun isEmpty(): Boolean {
        TODO("not implemented")
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
     * @param k the element's new key value
     */
    private fun decreaseKey(x: T, k: Long) {
        TODO("not implemented")
    }

}
