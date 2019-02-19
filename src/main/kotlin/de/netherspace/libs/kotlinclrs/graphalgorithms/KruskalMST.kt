package de.netherspace.libs.kotlinclrs.graphalgorithms

import de.netherspace.libs.kotlinclrs.advanceddatastructures.DisjointSet

class KruskalMST : MinimumSpanningTreeAlgorithm {

    override fun <T> findMinimumSpanningTree(g: Graph<T>): Result<Graph<T>> {
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
                println("The endpoints are not inside the same set! --> union(u,v)") // TODO: erase!
                a.add(it)
                ds.union(it.endpoint1, it.endpoint2).fold({ u ->
                    println("United ${it.endpoint1} and ${it.endpoint2}, the new representative is $u")
                }, { e ->
                    // an error occurred, return a Failure:
                    return Result.failure(e)
                })
            }
        }
        // TODO: ...

        return Result.success(g) // TODO: Graph(a) instead!
    }

}
