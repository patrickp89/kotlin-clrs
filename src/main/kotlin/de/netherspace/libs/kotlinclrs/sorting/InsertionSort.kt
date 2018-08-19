package de.netherspace.libs.kotlinclrs.sorting

class InsertionSort : SortingAlgorithm {

    override fun sort(A: Array<Int>) {
        val l = A.size
        for (j in 1 until l) {
            val k = A[j]
            var i = j
            while (i > 0 && A[i-1] > k) {
                A[i] = A[i-1]
                i--
            }
            A[i] = k
        }
    }

}
