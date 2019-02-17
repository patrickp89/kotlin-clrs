package de.netherspace.libs.kotlinclrs.elementarydatastructures

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.emptyOrNullString
import org.junit.Test
import org.hamcrest.Matchers.`is` as Is

class LinkedListTest {

    @Test
    fun testSinglyLinkedList() {
        val list = SinglyLinkedList<String>()
        assertThatListInsertSucceeded(list, "eins", 1L)
        assertThatListInsertSucceeded(list, "zwei", 2L)
        assertThatListInsertSucceeded(list, "drei", 3L)
        assertThatListInsertSucceeded(list, "zwei-zwei", 2L)
        assertThat(testListImplementation(list), Is(true))
    }

    @Test
    fun testDoublyLinkedList() {
        val list = DoublyLinkedList<String>()
        assertThatListInsertSucceeded(list, "eins", 1L)
        assertThatListInsertSucceeded(list, "zwei", 2L)
        assertThatListInsertSucceeded(list, "drei", 3L)
        assertThatListInsertSucceeded(list, "zwei-zwei", 2L)
        assertThat(testListImplementation(list), Is(true))
    }

    private fun assertThatListInsertSucceeded(list: SinglyLinkedList<String>, x: String, k: Long) {
        assertThat(list.insert(x, k).element, Is(x))
    }

    private fun assertThatListInsertSucceeded(list: DoublyLinkedList<String>, x: String, k: Long) {
        assertThat(list.insert(x, k).element, Is(x))
    }

    private fun testListImplementation(list: List<String>): Boolean {
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

}
