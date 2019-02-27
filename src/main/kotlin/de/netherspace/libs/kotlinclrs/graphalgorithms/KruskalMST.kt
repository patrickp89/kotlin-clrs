package de.netherspace.libs.kotlinclrs.graphalgorithms

import de.netherspace.libs.kotlinclrs.advanceddatastructures.DisjointSet

/**
 * Kruskal's algorithm for Minimum Spanning Trees.
 */
class KruskalMST : MinimumSpanningTreeAlgorithm {

    override fun <T> findMinimumSpanningTree(g: Graph<T>): Result<List<Graph.Edge<T>>> {
        val a = mutableListOf<Graph.Edge<T>>()

        val ds = DisjointSet<Graph.Vertex<T>>()
        g.vertices().forEach {
            ds.makeSet(it)
        }

        val sortedEdges = g.edges()
                .asSequence()
                .sortedWith(compareBy { it.weight })
                .toList()

        sortedEdges.forEach {
            if (ds.findSet(it.endpoint1) != ds.findSet(it.endpoint2)) {
                ds.union(it.endpoint1, it.endpoint2).fold({ u ->
                    a.add(it)
                }, { e ->
                    return Result.failure(e)
                })
            }
        }

        return Result.success(a)
    }

}
