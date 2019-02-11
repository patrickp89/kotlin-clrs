package de.netherspace.libs.kotlinclrs.graphalgorithms

import de.netherspace.libs.kotlinclrs.advanceddatastructures.DisjointSet

class KruskalMST : MinimumSpanningTreeAlgorithm {

    override fun <T> findMinimumSpanningTree(g: Graph<T>): Graph<T> {
        val a = mutableListOf<T>()

        val ds = DisjointSet<Graph.Vertex<T>>()
        g.vertices().forEach {
            ds.makeSet(it)
        }

        val sortedEdges = g.edges() // TODO: sort by weight!

        sortedEdges.forEach {
            if (ds.findSet(it.endpoint1) != ds.findSet(it.endpoint2)) {
                println("The endpoints are not inside the same set")
                TODO("not implemented")
            }
        }
        // TODO: ...

        return g // TODO: Graph(a) instead!
    }

}
