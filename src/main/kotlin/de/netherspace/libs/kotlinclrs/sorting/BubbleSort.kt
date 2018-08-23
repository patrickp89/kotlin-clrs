package de.netherspace.libs.kotlinclrs.sorting

class BubbleSort : SortingAlgorithm {

    override fun sort(A: Array<Int>) {
        val l = A.size - 1
        for (i in 0 until l) {
            for (j in l downTo i+1) {
                if (A[j] < A[j-1]) {
                    swap(A, j, j-1)
                }
            }
        }
    }

}
