package de.netherspace.libs.kotlinclrs.elementarydatastructures

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Ignore
import org.junit.Test
import org.hamcrest.Matchers.`is` as Is

class QueueTest {

    @Test
    @Ignore
    fun testMinPriorityQueue() {
        val one = "one"
        val two = "two"
        val three = "three"
        val a = arrayOf(one, two, three)
                .map { Queue.QueueElement(it, 10) }
                .toTypedArray()
        val q = MinPriorityQueue(a)
        assertThat(q.isEmpty(), Is(true))
    }

    @Test
    fun testMaxPriorityQueue() {
        val one = "one"
        val two = "two"
        val three = "three"
        val a = arrayOf(one, two, three)
                .map { Queue.QueueElement(it, 10) }
                .toTypedArray()
        val q = MaxPriorityQueue(a)
        assertThat(q.isEmpty(), Is(false))

        val k1 = q.changeKey(1, 222) // a[1] is "two"
        assertThat(k1.isSuccess, Is(true))
        assertThat(a[0].element, Is(two))
        assertThat(a[1].element, Is(one))
        assertThat(a[2].element, Is(three))

        val k2 = q.changeKey(1, 3)
        assertThat(k2.isSuccess, Is(false))
        assertThat(a[0].element, Is(two))
        assertThat(a[1].element, Is(one))
        assertThat(a[2].element, Is(three))

        val k3 = q.changeKey(1, 15) // a[1] is now "one"!
        assertThat(k3.isSuccess, Is(true))
        val k4 = q.changeKey(2, 25) // a[2] is "three"
        assertThat(k4.isSuccess, Is(true))
        assertThat(a[0].element, Is(two))
        assertThat(a[1].element, Is(one))
        assertThat(a[2].element, Is(three))

        val e1 = q.extract()
        e1.fold({ max ->
            println(" Extracted max: $max")
        }, { e ->
            println("Exception was: $e")
        })
        assertThat(e1.isSuccess, Is(true))
        assertThat(a[0].element, Is(three))
        assertThat(a[1].element, Is(one))

        assertThat(q.peek(), Is(three))
    }

}
