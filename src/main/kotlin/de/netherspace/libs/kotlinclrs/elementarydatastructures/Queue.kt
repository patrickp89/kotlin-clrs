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
     * @return the min/max
     */
    fun extract() : T

    /**
     * Increases/decreases (depending on the implementation) the
     * key for a given element.
     *
     * @param x the element who's key will be increased/decreased
     * @param k the element's new key value
     */
    fun changeKey(x: T, k: Long)

    /**
     * Checks whether the queue is empty.
     *
     * @return true if empty, false otherwise
     */
    fun isEmpty() : Boolean

}
