package de.netherspace.libs.kotlinclrs

import de.netherspace.libs.kotlinclrs.elementarydatastructures.DoublyLinkedList
import de.netherspace.libs.kotlinclrs.elementarydatastructures.List
import de.netherspace.libs.kotlinclrs.elementarydatastructures.SinglyLinkedList
import de.netherspace.libs.kotlinclrs.sorting.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.emptyOrNullString
import org.junit.Test
import org.hamcrest.Matchers.`is` as Is

class KotlinTest {

    @Test
    fun testSinglyLinkedList() {
        val list = SinglyLinkedList<String>()
        assertThat(testListImplementation(list), Is(true))
    }

    @Test
    fun testDoublyLinkedList() {
        val list = DoublyLinkedList<String>()
        assertThat(testListImplementation(list), Is(true))
    }

    private fun testListImplementation(list: List<String>): Boolean {
        assertThat(list.size(), Is(0))

        assertThat(list.insert("eins", 1L), Is(true))
        assertThat(list.insert("zwei", 2L), Is(true))
        assertThat(list.insert("drei", 3L), Is(true))
        assertThat(list.insert("zwei-zwei", 2L), Is(true))
        assertThat(list.size(), Is(4))

        assertThat(list.search(3L), Is("drei"))
        assertThat(list.search(2L), Is("zwei-zwei")) //the 2nd element with key 2 was inserted BEFORE "zwei"!
        assertThat(list.search(1L), Is("eins"))
        assertThat(list.search(5L), Is(emptyOrNullString()))

        assertThat(list.delete(3L), Is("drei"))
        assertThat(list.search(3L), Is(emptyOrNullString()))
        assertThat(list.search(1L), Is("eins"))
        assertThat(list.size(), Is(3))

        return true
    }

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
