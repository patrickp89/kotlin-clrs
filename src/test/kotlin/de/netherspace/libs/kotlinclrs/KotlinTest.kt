package de.netherspace.libs.kotlinclrs

import de.netherspace.libs.kotlinclrs.elementarydatastructures.DoublyLinkedList
import de.netherspace.libs.kotlinclrs.elementarydatastructures.List
import de.netherspace.libs.kotlinclrs.elementarydatastructures.SinglyLinkedList
import de.netherspace.libs.kotlinclrs.sorting.InsertionSort
import de.netherspace.libs.kotlinclrs.sorting.MergeSort
import de.netherspace.libs.kotlinclrs.sorting.SortingAlgorithm
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.emptyOrNullString
import org.hamcrest.Matchers.`is` as Is
import org.junit.Test

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
        val A = arrayOf(9, 1 ,8, 2, 7, 3, 6, 4, 5)
        val inss = InsertionSort()
        testSortingAlgorithm(A, inss)
    }

    @Test
    fun testMergeSort() {
        val A = arrayOf(9, 1 ,8, 2, 7, 3, 6, 4, 5)
        val ms = MergeSort()
        testSortingAlgorithm(A, ms)
    }

    private fun testSortingAlgorithm(A: Array<Int>, algo: SortingAlgorithm) {
        var exp = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

        algo.sort(A)

        for (i in A.indices) {
            assertThat(A[i], Is(exp[i]))
        }
    }
}
