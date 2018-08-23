package de.netherspace.libs.kotlinclrs

import de.netherspace.libs.kotlinclrs.elementarydatastructures.DoublyLinkedList
import de.netherspace.libs.kotlinclrs.elementarydatastructures.List
import de.netherspace.libs.kotlinclrs.elementarydatastructures.SinglyLinkedList
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class ElementaryDataStructuresTests {
    
    @Test
    fun testSinglyLinkedList() {
        val list = SinglyLinkedList<String>()
        MatcherAssert.assertThat(testListImplementation(list), Matchers.`is`(true))
    }

    @Test
    fun testDoublyLinkedList() {
        val list = DoublyLinkedList<String>()
        MatcherAssert.assertThat(testListImplementation(list), Matchers.`is`(true))
    }

    private fun testListImplementation(list: List<String>): Boolean {
        MatcherAssert.assertThat(list.size(), Matchers.`is`(0))

        MatcherAssert.assertThat(list.insert("eins", 1L), Matchers.`is`(true))
        MatcherAssert.assertThat(list.insert("zwei", 2L), Matchers.`is`(true))
        MatcherAssert.assertThat(list.insert("drei", 3L), Matchers.`is`(true))
        MatcherAssert.assertThat(list.insert("zwei-zwei", 2L), Matchers.`is`(true))
        MatcherAssert.assertThat(list.size(), Matchers.`is`(4))

        MatcherAssert.assertThat(list.search(3L), Matchers.`is`("drei"))
        MatcherAssert.assertThat(list.search(2L), Matchers.`is`("zwei-zwei")) //the 2nd element with key 2 was inserted BEFORE "zwei"!
        MatcherAssert.assertThat(list.search(1L), Matchers.`is`("eins"))
        MatcherAssert.assertThat(list.search(5L), Matchers.`is`(Matchers.emptyOrNullString()))

        MatcherAssert.assertThat(list.delete(3L), Matchers.`is`("drei"))
        MatcherAssert.assertThat(list.search(3L), Matchers.`is`(Matchers.emptyOrNullString()))
        MatcherAssert.assertThat(list.search(1L), Matchers.`is`("eins"))
        MatcherAssert.assertThat(list.size(), Matchers.`is`(3))

        return true
    }
}
