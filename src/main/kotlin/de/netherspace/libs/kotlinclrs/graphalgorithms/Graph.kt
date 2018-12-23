package de.netherspace.libs.kotlinclrs.graphalgorithms

/**
 * A naive adjacency matrix implementation for weighted undirected graphs.
 */
class Graph<T> {

//    TODO:
//    private val vertices: Vertex[]
//    private val edges: Edge[]
//    private val weights: Int[]

    private var g: Array<Array<Vertex<T>>> = emptyArray()

    class Vertex<T>(e: T?) {
        val element = e
    }
}
