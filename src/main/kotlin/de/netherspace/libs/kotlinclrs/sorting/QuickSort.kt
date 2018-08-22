package de.netherspace.libs.kotlinclrs.sorting

class QuickSort : SortingAlgorithm {

    override fun sort(A: Array<Int>) {
        val p = 0
        val r = A.size - 1
        quickSort(A, p ,r)
    }

    private fun quickSort(A: Array<Int>, p: Int, r: Int) {
        if (p < r) {
            val q = partition(A, p, r)
            quickSort(A, p, q-1)
            quickSort(A, q+1, r)
        }
    }

    private fun partition(A: Array<Int>, p: Int, r: Int): Int {
        val pivot = A[r]
        var i = p-1

        for (j in p until r) {
            if (A[j] <= pivot) {
                i++
                swap(A, i, j)
            }
        }

        swap(A, i+1, r)
        return i+1
    }

}