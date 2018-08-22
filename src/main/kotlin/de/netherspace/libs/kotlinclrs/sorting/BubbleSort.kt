package de.netherspace.libs.kotlinclrs.sorting

class BubbleSort : SortingAlgorithm {

    override fun sort(A: Array<Int>) {
        val l = A.size
        for (i in 0 until l) {
            for (j in l downTo i+1) {
                if (A[j] < A[j-1]) {
                    val d = A[j]
                    A[j] = A[j-i]
                    A[j-1] = d
                }
            }
        }
    }

}