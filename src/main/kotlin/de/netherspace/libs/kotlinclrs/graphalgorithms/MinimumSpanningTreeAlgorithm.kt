package de.netherspace.libs.kotlinclrs.graphalgorithms

/**
 * Encapsulates the computation of a Minimum Spanning Tree
 * for an (undirected) graph.
 */
interface MinimumSpanningTreeAlgorithm {

    /**
     * Computes a MST for the given graph g.
     *
     * @param g the graph
     * @return a Result containing the MST
     */
    fun <T> findMinimumSpanningTree(g: Graph<T>): Result<List<Graph.Edge<T>>>

}
