package de.netherspace.libs.kotlinclrs.graphalgorithms

interface MinimumSpanningTreeAlgorithm {

    /**
     * Computes a MST for the given graph G.
     *
     * @return the MST
     */
    fun <T> findMinimumSpanningTree(G: Graph<T>) : Graph<T>

}
