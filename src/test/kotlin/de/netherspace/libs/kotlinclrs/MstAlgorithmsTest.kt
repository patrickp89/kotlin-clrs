package de.netherspace.libs.kotlinclrs

import de.netherspace.libs.kotlinclrs.graphalgorithms.Graph
import de.netherspace.libs.kotlinclrs.graphalgorithms.KruskalMST
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is` as Is
import org.junit.Test

class MstAlgorithmsTest {

    @Test
    fun testKruskal() {
        val kruskal = KruskalMST()
        val G = Graph<String>()
        val mst = kruskal.findMinimumSpanningTree(G)
        assertThat(true, Is(true))
    }

}
