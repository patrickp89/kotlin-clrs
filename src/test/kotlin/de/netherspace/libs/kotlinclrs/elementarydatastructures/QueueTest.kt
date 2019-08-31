package de.netherspace.libs.kotlinclrs.elementarydatastructures

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.hamcrest.Matchers.`is` as Is

class QueueTest {

    @Test
    fun testMinPriorityQueue() {
        val one = "one"
        val two = "two"
        val three = "three"

        // all elements have the initial key 450:
        val a = arrayOf(one, two, three)
                .map { Queue.QueueElement(it, 450) }
                .toTypedArray()
        val q = MinPriorityQueue(a)
        assertThat(q.isEmpty(), Is(false))

        // a[1] is "two", change its key:
        val k1 = q.changeKey(1, 31)
        assertThat(k1.isSuccess, Is(true))
        assertThat(a[0].element, Is(two))
        assertThat(a[1].element, Is(one))
        assertThat(a[2].element, Is(three))

        // changing the old key (of a[1]="one") from 450 to a greater value (600) should fail:
        val k2 = q.changeKey(1, 600)
        assertThat(k2.isSuccess, Is(false))
        assertThat(a[0].element, Is(two))
        assertThat(a[1].element, Is(one))
        assertThat(a[2].element, Is(three))

        // changing a[1]'s ("one") key to 125 shouldn't change the queue order, as a[0]'s key is lesser (31):
        val k3 = q.changeKey(1, 125)
        assertThat(k3.isSuccess, Is(true))
        assertThat(a[0].element, Is(two))
        assertThat(a[1].element, Is(one))
        assertThat(a[2].element, Is(three))

        // changing a[2]'s ("three") key to 55 shouldn't change the queue order, as a[0]'s key is lesser (31):
        val k4 = q.changeKey(2, 55)
        assertThat(k4.isSuccess, Is(true))
        assertThat(a[0].element, Is(two))
        assertThat(a[1].element, Is(one))
        assertThat(a[2].element, Is(three))

        // extracting the min should yield "two" and a change in queue order:
        val e1 = q.extract()
        e1.fold({ max ->
            println(" Extracted max: $max")
        }, { e ->
            println("Exception was: $e")
        })
        assertThat(e1.isSuccess, Is(true))
        assertThat(a[0].element, Is(three))
        assertThat(a[1].element, Is(one))

        // a peek should yield "three", without changing the queue order:
        assertThat(q.peek(), Is(three))
        assertThat(a[0].element, Is(three))
        assertThat(a[1].element, Is(one))
    }


    @Test
    fun testMaxPriorityQueue() {
        val one = "one"
        val two = "two"
        val three = "three"

        // all elements have the initial key 10:
        val a = arrayOf(one, two, three)
                .map { Queue.QueueElement(it, 10) }
                .toTypedArray()
        val q = MaxPriorityQueue(a)
        assertThat(q.isEmpty(), Is(false))

        // a[1] is "two", change its key:
        val k1 = q.changeKey(1, 222)
        assertThat(k1.isSuccess, Is(true))
        assertThat(a[0].element, Is(two))
        assertThat(a[1].element, Is(one))
        assertThat(a[2].element, Is(three))

        // changing the old key (of a[1]="one") from 10 to a lesser value (3) should fail:
        val k2 = q.changeKey(1, 3)
        assertThat(k2.isSuccess, Is(false))
        assertThat(a[0].element, Is(two))
        assertThat(a[1].element, Is(one))
        assertThat(a[2].element, Is(three))

        // changing a[1]'s ("one") key to 15 shouldn't change the queue order, as a[0]'s key is greater (222):
        val k3 = q.changeKey(1, 15)
        assertThat(k3.isSuccess, Is(true))
        assertThat(a[0].element, Is(two))
        assertThat(a[1].element, Is(one))
        assertThat(a[2].element, Is(three))

        // changing a[2]'s ("three") key to 25 shouldn't change the queue order, as a[0]'s key is greater (222):
        val k4 = q.changeKey(2, 25)
        assertThat(k4.isSuccess, Is(true))
        assertThat(a[0].element, Is(two))
        assertThat(a[1].element, Is(one))
        assertThat(a[2].element, Is(three))

        // extracting the max should yield "two" and a change in queue order:
        val e1 = q.extract()
        e1.fold({ max ->
            println(" Extracted max: $max")
        }, { e ->
            println("Exception was: $e")
        })
        assertThat(e1.isSuccess, Is(true))
        assertThat(a[0].element, Is(three))
        assertThat(a[1].element, Is(one))

        // a peek should yield "three", without changing the queue order:
        assertThat(q.peek(), Is(three))
        assertThat(a[0].element, Is(three))
        assertThat(a[1].element, Is(one))
    }


    @Test
    fun testMinPriorityQueueInsert() {
        val one = "one"
        val two = "two"
        val three = "three"

        // all elements have the initial key 450:
        var a = arrayOf(one, two, three)
                .map { Queue.QueueElement(it, 450) }
                .toTypedArray()
        val q = MinPriorityQueue(a)
        assertThat(q.isEmpty(), Is(false))

        // the elements and their order before inserting a new one:
        assertThat(a[0].element, Is(one))
        assertThat(a[1].element, Is(two))
        assertThat(a[2].element, Is(three))

        val four = "four"
        q.insert(four, 3)
        a = q.getArray()
        assertThat(a[0].element, Is(four))
        assertThat(a[1].element, Is(one))
        assertThat(a[2].element, Is(three))
        assertThat(a[3].element, Is(two))
    }


    @Test
    fun testMaxPriorityQueueInsert() {
        val one = "one"
        val two = "two"
        val three = "three"

        // all elements have the initial key 10:
        var a = arrayOf(one, two, three)
                .map { Queue.QueueElement(it, 10) }
                .toTypedArray()
        val q = MaxPriorityQueue(a)
        assertThat(q.isEmpty(), Is(false))

        // the elements and their order before inserting a new one:
        assertThat(a[0].element, Is(one))
        assertThat(a[1].element, Is(two))
        assertThat(a[2].element, Is(three))

        val four = "four"
        q.insert(four, 3)
        a = q.getArray()
        assertThat(a[0].element, Is(one))
        assertThat(a[1].element, Is(two))
        assertThat(a[2].element, Is(three))
        assertThat(a[3].element, Is(four))
    }

}
