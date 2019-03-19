package de.netherspace.libs.kotlinclrs.graphalgorithms

import de.netherspace.libs.kotlinclrs.elementarydatastructures.MinPriorityQueue

class PrimMST : MinimumSpanningTreeAlgorithm {

    override fun <T> findMinimumSpanningTree(g: Graph<T>): Result<List<Graph.Edge<T>>> {
        if (g.vertices().isEmpty()) {
            return Result.failure(Exception("The graph does not contain any vertices!"))
        }

        // pick an arbitrary root:
        val r = g.vertices()[0]
        val V : Array<Graph.Vertex<T>> = g.vertices()
                .toTypedArray()
                .clone()

        V.forEach {
            // TODO: schluessel[it] = infinity
            // TODO: lambda[it] = null
        }

        // TODO: schluessel[r] = 0

//        val q = MinPriorityQueue<Graph.Vertex<T>>(V)
//        g.vertices().forEach {
//            q.insert(it)
//        }

//        while (!q.isEmpty()) {
//            val u = q.extract()
//            TODO("not implemented")
//        }

        TODO("not implemented")
    }

}
