package de.netherspace.libs.kotlinclrs.sorting

interface SortingAlgorithm {

    fun sort(A: Array<Int>)

    fun swap(A: Array<Int>, j: Int, i: Int) {
        val d = A[j]
        A[j] = A[i]
        A[i] = d
    }

}
