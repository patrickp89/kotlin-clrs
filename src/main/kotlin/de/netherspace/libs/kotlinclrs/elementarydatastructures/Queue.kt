package de.netherspace.libs.kotlinclrs.elementarydatastructures

interface Queue<T> {

    /**
     * Inserts a given element into the queue.
     *
     * @param x the element that will be inserted
     */
    fun insert(x: T)

    /**
     * Returns the min/max (depending on the implementation).
     *
     * @return the min/max
     */
    fun peek() : T

    /**
     * Returns the min/max (depending on the implementation) and
     * deletes it from the queue.
     *
     * @return a Result containing the min/max or an exception
     */
    fun extract() : Result<T>

    /**
     * Increases/decreases (depending on the implementation) the
     * key for a given element.
     *
     * @param i index of the the element who's key will be increased/decreased
     * @param k the element's new key value
     * @return a Result containing the newly set key or an exception
     */
    fun changeKey(i: Int, k: Long): Result<Long>

    /**
     * Checks whether the queue is empty.
     *
     * @return true if empty, false otherwise
     */
    fun isEmpty() : Boolean

    /**
     * An element of a queue, wrapping the actual (generic) value.
     */
    class QueueElement<T>(val element: T?, var key: Long) : Comparable<QueueElement<T>> {
        override fun compareTo(other: QueueElement<T>): Int {
            return this.key.compareTo(other.key)
        }
    }

}
