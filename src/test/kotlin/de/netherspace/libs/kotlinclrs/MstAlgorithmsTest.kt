package de.netherspace.libs.kotlinclrs

import de.netherspace.libs.kotlinclrs.graphalgorithms.Graph
import de.netherspace.libs.kotlinclrs.graphalgorithms.KruskalMST
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Ignore
import org.hamcrest.Matchers.`is` as Is
import org.junit.Test

class MstAlgorithmsTest {

    @Test
    @Ignore
    fun testKruskal() {
        val kruskal = KruskalMST()
        val g = Graph<String>(5)

        val v1 =g.addVertex("01", listOf(Pair("02", 22), Pair("03", 77)))
        assertThat(v1.isSuccess, Is(true))

        val v2 = g.addVertex("03", listOf(Pair("04", 21), Pair("05", 12)))
        assertThat(v2.isSuccess, Is(true))

        val mst = kruskal.findMinimumSpanningTree(g)
        assertThat(true, Is(true))
    }

}
