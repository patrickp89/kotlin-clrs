package de.netherspace.libs.kotlinclrs

import de.netherspace.libs.kotlinclrs.graphalgorithms.Graph
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Ignore
import org.junit.Test
import org.hamcrest.Matchers.`is` as Is

class GraphTest {

    @Test
    @Ignore
    fun testInsertDuplicate() {
        println("\n\n\n=== testInsertDuplicate ===") // TODO: erase!
        val g = Graph<String>(3)

        val v1 = g.addVertex("01", listOf(Pair("02", 22), Pair("03", 77)))
        assertThat(v1.isSuccess, Is(true))

        val v2 = g.addVertex("01", listOf(Pair("04", 123), Pair("05", 456)))
        assertThat(v2.isSuccess, Is(false))
    }

    @Test
    @Ignore
    fun testInsertLoop() {
        println("\n\n\n=== testInsertLoop ===")
        val g = Graph<String>(3)

        // edges must be able to connect an edge with itself (i.e. a loop):
        val v1 = g.addVertex("01", listOf(Pair("01", 22), Pair("03", 77)))
        v1.fold({ v ->
            println(" The vertex is: $v")
        }, { e ->
            println("Exception was: $e")
        })
        assertThat(v1.isSuccess, Is(true))

        val v2 = g.addVertex("02", listOf(Pair("03", 123), Pair("02", 456)))
        v2.fold({ v ->
            println(" The vertex is: $v")
        }, { e ->
            println("Exception was: $e")
        })
        assertThat(v2.isSuccess, Is(true))
    }

    @Test
    fun testInsertDifferentEdgeWeight() {
        println("\n\n\n=== testInsertDifferentEdgeWeight ===")
        val g = Graph<String>(3)

        val v1 = g.addVertex("01", listOf(Pair("02", 22), Pair("03", 77)))
        assertThat(v1.isSuccess, Is(true))

        val v2 = g.addVertex("02", listOf(Pair("01", 123), Pair("02", 456)))
        assertThat(v2.isSuccess, Is(false))
    }

    @Test
    @Ignore
    fun testGraphContainsVertex() {
        println("\n\n\n=== testGraphContainsVertex ===")
        val g = Graph<String>(3)

        val additionResult = g.addVertex("01", listOf(Pair("02", 22), Pair("03", 77)))
        additionResult.fold({ v ->
            println(" The vertex is: $v")
        }, { e ->
            println("Exception was: $e")
        })
        assertThat(additionResult.isSuccess, Is(true))

        val contains = g.contains("01")
        assertThat(contains.isSuccess, Is(true))

        val v = contains.getOrNull()
        assertThat(v, Is(not(nullValue())))
        assertThat(v?.value, Is("01"))
    }

}
