package de.netherspace.libs.kotlinclrs.advanceddatastructures

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Test
import org.hamcrest.Matchers.`is` as Is

class DisjointSetTest {

    @Test
    fun testMakeSet() {
        val ds = DisjointSet<String>()
        val items = listOf("one", "two", "three")
        items.forEach {
            val s = ds.makeSet(it)
            assertThat(s, Is(not(nullValue())))
        }
    }

    @Test
    fun testFindSet() {
        val ds = DisjointSet<String>()
        val items = listOf("one", "two", "three")
        items.forEach {
            ds.makeSet(it)
        }
        items.forEach {
            val r = ds.findSet(it)
            assertThat(r, Is(not(nullValue())))
        }
    }

    @Test
    fun testUnionAttachLargeToSmallSet() {
        val ds = DisjointSet<String>()
        val items = listOf("one", "two", "three", "four", "five", "six")
        items.forEach {
            ds.makeSet(it)
        }

        assertThat(ds.union(items[0], items[1]).isSuccess, Is(true))
        assertThat(ds.union(items[1], items[2]).isSuccess, Is(true))
        assertThat(ds.union(items[2], items[3]).isSuccess, Is(true))
        assertThat(ds.union(items[3], items[4]).isSuccess, Is(true))
        assertThat(ds.union(items[4], items[5]).isSuccess, Is(true))
    }

    @Test
    fun testUnionAttachSmallToLargeSet() {
        val ds = DisjointSet<String>()
        val items = listOf("one", "two", "three", "four", "five", "six")
        items.forEach {
            ds.makeSet(it)
        }

        assertThat(ds.union(items[0], items[1]).isSuccess, Is(true))
        assertThat(ds.union(items[2], items[1]).isSuccess, Is(true))
        assertThat(ds.union(items[2], items[3]).isSuccess, Is(true))
        assertThat(ds.union(items[3], items[4]).isSuccess, Is(true))
        assertThat(ds.union(items[4], items[5]).isSuccess, Is(true))
    }

    @Test
    fun testUniteSameSet() {
        val ds = DisjointSet<String>()
        val items = listOf("one", "two")
        items.forEach {
            ds.makeSet(it)
        }

        assertThat(ds.union(items[1], items[1]).isSuccess, Is(false))
    }

}
