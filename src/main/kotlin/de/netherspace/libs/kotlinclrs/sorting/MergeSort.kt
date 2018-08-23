package de.netherspace.libs.kotlinclrs.sorting

class MergeSort : SortingAlgorithm {

    override fun sort(A: Array<Int>) {
        var p = 0
        var r = A.size-1
        mergeSort(A, p ,r)
    }

    private fun mergeSort(A: Array<Int>, p: Int, r: Int) {
        if (p < r) {
            var q = (p+r)/2
            mergeSort(A, p, q)
            mergeSort(A, q+1, r)
            merge(A,p, q, r)
        }
    }

    private fun merge(A: Array<Int>, p: Int, q: Int, r: Int) {
        val n1 = q-p+1
        val n2 = r-q

        var L = Array(n1+1) {_ -> 0}
        var R = Array(n2+1) {_ -> 0}

        for (i in 0 until n1) {
            L[i] = A[p+i]
        }
        for (i in 0 until n2) {
            R[i] = A[q+i+1]
        }

        L[n1] = Int.MAX_VALUE
        R[n2] = Int.MAX_VALUE

        var i = 0
        var j = 0

        for (k in p until r+1) {
            if (L[i] <= R[j]) {
                A[k] = L[i++]
            } else {
                A[k] = R[j++]
            }
        }
    }

}
