package de.netherspace.libs.kotlinclrs.graphalgorithms

import de.netherspace.libs.kotlinclrs.advanceddatastructures.DisjointSet

class KruskalMST : MinimumSpanningTreeAlgorithm {

    override fun <T> findMinimumSpanningTree(g: Graph<T>): Result<Graph<T>> {
        val a = mutableListOf<Graph.Edge<T>>()

        val ds = DisjointSet<Graph.Vertex<T>>()
        g.vertices().forEach {
            ds.makeSet(it)
        }
//        val disjointSetElements: List<DisjointSetNode<Graph.Vertex<T>>> = g.vertices()
//                .map { ds.makeSet(it) }
//                .toList()
//        val dsElements: Map<Graph.Vertex<T>, DisjointSetNode<Graph.Vertex<T>>> = g.vertices()
//                .associateBy({ it }, { ds.makeSet(it) })

        val sortedEdges = g.edges()
                .asSequence()
                .sortedWith(compareBy { it.weight })
                .toList()

        sortedEdges.forEach {
//            val endpoint1: DisjointSetNode<Graph.Vertex<T>> = dsElements[it.endpoint1]
//                    ?: return Result.failure(Exception("Error: did not call makeSet() for '${it.endpoint1}'!"))
//            val endpoint2: DisjointSetNode<Graph.Vertex<T>> = dsElements[it.endpoint2]
//                    ?: return Result.failure(Exception("Error: did not call makeSet() for '${it.endpoint2}'!"))

            if (ds.findSet(it.endpoint1) != ds.findSet(it.endpoint2)) {
                println("The endpoints are not inside the same set! --> union(u,v)") // TODO: erase!
                a.add(it)
                ds.union(it.endpoint1, it.endpoint2)
            }
        }
        // TODO: ...

        return Result.success(g) // TODO: Graph(a) instead!
    }

}
