package de.netherspace.libs.kotlinclrs.elementarydatastructures

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.hamcrest.Matchers.`is` as Is

class QueueTest {

    @Test
    fun testMinPriorityQueue() {
        val a = arrayOf("one", "two", "three")
        val q = MinPriorityQueue<String>(a)
        assertThat(q.isEmpty(), Is(true))
    }

    @Test
    fun testMaxPriorityQueue() {
        val a = arrayOf("one", "two", "three")
        val q = MaxPriorityQueue<String>(a)
        assertThat(q.isEmpty(), Is(true))
    }

}
