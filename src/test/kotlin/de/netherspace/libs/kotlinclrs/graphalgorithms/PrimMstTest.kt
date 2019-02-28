package de.netherspace.libs.kotlinclrs.graphalgorithms

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.hamcrest.Matchers.`is` as Is

class PrimMstTest {

    @Test
    fun testPrimWithSimplyConnectedGraph() {
        val prim = PrimMST()
        val g = Graph<String>(5)

        val v1 = g.addVertex("01", listOf(Pair("02", 22), Pair("03", 77)))
        assertThat(v1.isSuccess, Is(true))

        val v2 = g.addVertex("03", listOf(Pair("04", 21), Pair("05", 12)))
        assertThat(v2.isSuccess, Is(true))

        val mst = prim.findMinimumSpanningTree(g)
        mst.fold({ t ->
            println(" Got a minimum spanning tree with ${t.size} edges: $t")
        }, { e ->
            println("Exception was: $e")
        })
        assertThat(mst.isSuccess, Is(true))
        assertThat(mst.getOrNull()?.size, Is(4))

        TODO("not implemented")
    }

}
