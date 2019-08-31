package de.netherspace.libs.kotlinclrs.elementarydatastructures

interface ArrayOperations {

    /**
     * Swaps the elements at position i and j.
     */
    fun <T> swap(A: Array<T>, j: Int, i: Int) where T : Comparable<T> {
        val d = A[j]
        A[j] = A[i]
        A[i] = d
    }

    /**
     * Doubles the size of an array.
     */
    fun <T> resizeArray(a: Array<T?>): Array<T?> {
        val newSize = a.size * 2
        return a.copyOf(newSize)
    }

    /**
     * Shrinks the size of an array.
     */
    fun <T> shrink(a: Array<T?>, lowerBoundry: Int): Array<T?> {
        val newSize = a.size / 2
        return if (newSize > lowerBoundry) {
            a.copyOf(newSize)
        } else {
            a
        }
    }

}
