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

}
