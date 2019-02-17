package de.netherspace.libs.kotlinclrs.graphalgorithms

interface MinimumSpanningTreeAlgorithm {

    /**
     * Computes a MST for the given graph g.
     *
     * @param g the given graph
     * @return a Result containing the MST
     */
    fun <T> findMinimumSpanningTree(g: Graph<T>) : Result<Graph<T>>

}
