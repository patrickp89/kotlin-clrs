package de.netherspace.libs.kotlinclrs

import de.netherspace.libs.kotlinclrs.sorting.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is` as Is
import org.junit.Test

class SortingAlgorithmsTests {

    @Test
    fun testInsertionSort() {
        val a = getTestArray()
        testSortingAlgorithm(a, InsertionSort())
    }

    @Test
    fun testMergeSort() {
        val a = getTestArray()
        testSortingAlgorithm(a, MergeSort())
    }

    @Test
    fun testBubbleSort() {
        val a = getTestArray()
        testSortingAlgorithm(a, BubbleSort())
    }

    @Test
    fun testQuickSort() {
        val a = getTestArray()
        testSortingAlgorithm(a, QuickSort())
    }

    private fun getTestArray(): Array<Int> {
        return arrayOf(9, 1 ,8, 2, 7, 3, 6, 4, 5)
    }

    private fun getSortedTestArray(): Array<Int> {
        return arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    private fun testSortingAlgorithm(a: Array<Int>, algo: SortingAlgorithm) {
        val exp = getSortedTestArray()
        algo.sort(a)

        for (i in a.indices) {
            assertThat(a[i], Is(exp[i]))
        }
    }
}
