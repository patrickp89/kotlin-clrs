package de.netherspace.libs.kotlinclrs.graphalgorithms

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Test
import org.hamcrest.Matchers.`is` as Is

class GraphTest {

    @Test
    fun testAddMultipleEdges() {
        val g = Graph<String>(5)

        // the edges differ, therefore these calls should succeed!
        val v1 = g.addVertex("01", listOf(Pair("02", 22), Pair("03", 77)))
        assertThat(v1.isSuccess, Is(true))
        val v2 = g.addVertex("01", listOf(Pair("04", 123), Pair("05", 456)))
        assertThat(v2.isSuccess, Is(true))

        // but the second call will only return the _already existing_ vertex:
        assertThat(v2.getOrNull(), Is(v1.getOrNull()))
    }

    @Test
    fun testAddLoop() {
        val g = Graph<String>(3)

        // vertices must be able to connect an edge with itself (i.e. a loop):
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
    fun testAddDifferentEdgeWeight() {
        val g = Graph<String>(3)

        val v1 = g.addVertex("01", listOf(Pair("02", 22), Pair("03", 77)))
        assertThat(v1.isSuccess, Is(true))

        val v2 = g.addVertex("02", listOf(Pair("01", 123), Pair("02", 456)))
        assertThat(v2.isSuccess, Is(false))
    }

    @Test
    fun testAddVertexThatWasAlreadyAddedAsNeighbour() {
        val g2 = Graph<String>(5)

        val v21 = g2.addVertex("01", listOf(Pair("02", 22), Pair("03", 77)))
        assertThat(v21.isSuccess, Is(true))
        val v2021: Graph.Vertex<String> = g2.vertices()
                .filter { it.value == "02" }
                .first()

        val v22 = g2.addVertex("03", listOf(Pair("04", 21), Pair("05", 12)))
        assertThat(v22.isSuccess, Is(true))

        val v23 = g2.addVertex("02", listOf(Pair("04", 11)))
        assertThat(v23.isSuccess, Is(true))

        // "02" was already added as a neighbour of "01" => addVertex("02") must
        // return the already existing one:
        val v2022 = v23.getOrThrow()
        assertThat(v2022, Is(v2021))
    }

    @Test
    fun testGraphContainsVertex() {
        val g = Graph<String>(3)

        val additionResult = g.addVertex("01", listOf(Pair("02", 22), Pair("03", 77)))
        additionResult.fold({ v ->
            println(" The vertex is: $v")
        }, { e ->
            println("Exception was: $e")
        })
        assertThat(additionResult.isSuccess, Is(true))

        val vertexOne = g.get("01")
        assertThat(vertexOne, Is(not(nullValue())))
        assertThat(vertexOne?.value, Is("01"))
    }

    @Test
    fun testGetAllVertices() {
        val g = Graph<String>(3)
        g.addVertex("01", listOf(Pair("02", 22), Pair("03", 77)))

        val vertices = g.vertices()
        assertThat(vertices.size, Is(3))
    }

    @Test
    fun testGetAllEdges() {
        val g = Graph<String>(3)
        g.addVertex("01", listOf(Pair("02", 22), Pair("03", 77)))

        val edges = g.edges()
        assertThat(edges.size, Is(2))
    }

}
