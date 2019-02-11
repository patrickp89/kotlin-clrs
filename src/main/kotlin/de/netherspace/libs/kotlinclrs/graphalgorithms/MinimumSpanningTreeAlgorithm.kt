package de.netherspace.libs.kotlinclrs.graphalgorithms

interface MinimumSpanningTreeAlgorithm {

    /**
     * Computes a MST for the given graph g.
     *
     * @param g the given graph
     * @return the MST
     */
    fun <T> findMinimumSpanningTree(g: Graph<T>) : Graph<T>

}
