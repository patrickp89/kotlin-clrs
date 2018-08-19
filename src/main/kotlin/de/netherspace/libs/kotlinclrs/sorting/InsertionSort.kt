package de.netherspace.libs.kotlinclrs.sorting

class InsertionSort : SortingAlgorithm {

    override fun sort(A: Array<Int>) {
        val l = A.size
        for (j in 1 until l) {
            val k = A.get(j)
            var i = j
            while (i > 0 && A.get(i-1) > k) {
                A.set(i, A.get(i-1)) //TODO: indexing operator?
                i--
            }
            A.set(i, k)
        }
    }

}
