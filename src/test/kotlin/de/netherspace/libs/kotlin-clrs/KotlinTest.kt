package de.pp.test.kotlin

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.emptyOrNullString
import org.hamcrest.Matchers.`is` as Is
import org.junit.Test

class KotlinTest {

    @Test
    fun testKotlinSinglyLinkedList() {
        assertThat(true, Is(true))
    }
}
