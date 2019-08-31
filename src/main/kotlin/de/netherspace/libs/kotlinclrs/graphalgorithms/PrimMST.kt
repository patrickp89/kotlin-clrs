package de.netherspace.libs.kotlinclrs.graphalgorithms

import de.netherspace.libs.kotlinclrs.elementarydatastructures.MinPriorityQueue
import de.netherspace.libs.kotlinclrs.elementarydatastructures.Queue

class PrimMST : MinimumSpanningTreeAlgorithm {

    override fun <T> findMinimumSpanningTree(g: Graph<T>): Result<List<Graph.Edge<T>>> {
        if (g.vertices().isEmpty()) {
            return Result.failure(Exception("The graph does not contain any vertices!"))
        }

        // pick an arbitrary root:
        val r = g.vertices()[0]
        val V: Array<Graph.Vertex<T>> = g.vertices()
                .toTypedArray()
                .clone()

        V.forEach {
            // TODO: lambda[it] = null
        }

        // TODO: schluessel[r] = 0

        val queueElements: Sequence<Queue.QueueElement<Graph.Vertex<T>>> = g.vertices()
                .asSequence()
                .map { Queue.QueueElement(it, Long.MAX_VALUE) } // schluessel[it] = infinity

        val A: Array<Queue.QueueElement<Graph.Vertex<T>>> = queueElements
                .toList()
                .toTypedArray() // TODO: erase, the underlying Array is refactored to be a Sequence!

        // Q <- V[G]:
        val q = MinPriorityQueue(A)
        g.vertices().forEach {
            q.insert(it, 666L) // TODO: what's the proper key??
        }

//        while (!q.isEmpty()) {
//            val u = q.extract()
//            TODO("not implemented")
//        }

        TODO("not implemented")
    }

}
