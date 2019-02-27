package de.netherspace.libs.kotlinclrs

import de.netherspace.libs.kotlinclrs.graphalgorithms.Graph
import de.netherspace.libs.kotlinclrs.graphalgorithms.KruskalMST
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.hamcrest.Matchers.`is` as Is

class MstAlgorithmsTest {

    @Test
    @SuppressWarnings("Duplicates")
    fun testKruskalWithSimplyConnectedGraph() {
        val kruskal = KruskalMST()
        val g = Graph<String>(5)

        val v1 = g.addVertex("01", listOf(Pair("02", 22), Pair("03", 77)))
        assertThat(v1.isSuccess, Is(true))

        val v2 = g.addVertex("03", listOf(Pair("04", 21), Pair("05", 12)))
        assertThat(v2.isSuccess, Is(true))

        val mst = kruskal.findMinimumSpanningTree(g)
        mst.fold({ t ->
            println(" Got a minimum spanning tree with ${t.size} edges: $t")
        }, { e ->
            println("Exception was: $e")
        })
        assertThat(mst.isSuccess, Is(true))
        assertThat(mst.getOrNull()?.size, Is(4))

        val mstContainsEdge3To5 = mst.getOrThrow()
                .filter { it.endpoint1.value == "03" && it.endpoint2.value == "05" && it.weight == 12 }
                .isNotEmpty()
        assertThat(mstContainsEdge3To5, Is(true))

        val mstContainsEdge3To4 = mst.getOrThrow()
                .filter { it.endpoint1.value == "03" && it.endpoint2.value == "04" && it.weight == 21 }
                .isNotEmpty()
        assertThat(mstContainsEdge3To4, Is(true))

        val mstContainsEdge1To2 = mst.getOrThrow()
                .filter { it.endpoint1.value == "01" && it.endpoint2.value == "02" && it.weight == 22 }
                .isNotEmpty()
        assertThat(mstContainsEdge1To2, Is(true))

        val mstContainsEdge1To3 = mst.getOrThrow()
                .filter { it.endpoint1.value == "01" && it.endpoint2.value == "03" && it.weight == 77 }
                .isNotEmpty()
        assertThat(mstContainsEdge1To3, Is(true))
    }

    @Test
    @SuppressWarnings("Duplicates")
    fun testKruskalWithMultiplePaths() {
        val kruskal = KruskalMST()
        val g = Graph<String>(5)

        val v1 = g.addVertex("01", listOf(Pair("02", 22), Pair("03", 77)))
        assertThat(v1.isSuccess, Is(true))

        val v2 = g.addVertex("03", listOf(Pair("04", 21), Pair("05", 12)))
        assertThat(v2.isSuccess, Is(true))

        val v3 = g.addVertex("02", listOf(Pair("04", 11)))
        assertThat(v3.isSuccess, Is(true))

        val mst = kruskal.findMinimumSpanningTree(g)
        mst.fold({ t ->
            println(" Got a minimum spanning tree with ${t.size} edges: $t")
        }, { e ->
            println("Exception was: $e")
        })
        assertThat(mst.isSuccess, Is(true))
        assertThat(mst.getOrNull()?.size, Is(4))

        val mstContainsEdge3To5 = mst.getOrThrow()
                .filter { it.endpoint1.value == "03" && it.endpoint2.value == "05" && it.weight == 12 }
                .isNotEmpty()
        assertThat(mstContainsEdge3To5, Is(true))

        val mstContainsEdge3To4 = mst.getOrThrow()
                .filter { it.endpoint1.value == "03" && it.endpoint2.value == "04" && it.weight == 21 }
                .isNotEmpty()
        assertThat(mstContainsEdge3To4, Is(true))

        val mstContainsEdge1To2 = mst.getOrThrow()
                .filter { it.endpoint1.value == "01" && it.endpoint2.value == "02" && it.weight == 22 }
                .isNotEmpty()
        assertThat(mstContainsEdge1To2, Is(true))

        val mstContainsEdge1To3 = mst.getOrThrow()
                .filter { it.endpoint1.value == "01" && it.endpoint2.value == "03" && it.weight == 77 }
                .isNotEmpty()
        assertThat(mstContainsEdge1To3, Is(false))

        val mstContainsEdge2To4 = mst.getOrThrow()
                .filter { it.endpoint1.value == "02" && it.endpoint2.value == "04" && it.weight == 11 }
                .isNotEmpty()
        assertThat(mstContainsEdge2To4, Is(true))
    }

}
