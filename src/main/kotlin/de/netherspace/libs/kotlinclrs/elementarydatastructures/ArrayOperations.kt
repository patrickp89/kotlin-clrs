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

}
