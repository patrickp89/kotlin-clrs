package de.netherspace.libs.kotlinclrs.graphalgorithms

import java.util.concurrent.atomic.AtomicInteger

/**
 * A naive adjacency matrix implementation for weighted undirected graphs.
 */
class Graph<T>(private val initialVertexCount: Int) { // TODO: inherit from "Graph", refactor as "UndirectedGraph"

    // the graph's adjacency matrix:
    private var g: Array<Array<Edge<T>?>> = Array(initialVertexCount) { arrayOfNulls<Edge<T>?>(initialVertexCount) }

    // the vertices' indices and a vertex counter:
    private val vertexCounter = AtomicInteger(0)
    private val indices = mutableMapOf<T, Int>()

    // vertices and edges are also stored in redundant collections to allow O(n) enumeration:
    private val vertices = mutableMapOf<T, Vertex<T>>()
    private val edges = mutableListOf<Edge<T>>()

    // A supplier for unique array indices:
    private val indexGenerator: () -> Int = {
        vertexCounter.getAndIncrement()
    }


    /**
     * Returns all vertices of this graph (in O(n) time).
     *
     * @return the vertices
     */
    fun vertices(): List<Vertex<T>> {
        return vertices
                .values
                .toList()
    }


    /**
     * Returns all edges of this graph (in O(n) time).
     *
     * @return the edges
     */
    fun edges(): List<Edge<T>> {
        return edges
                .toList()
    }


    /**
     * Convenience method that adds a new vertex to the graph.
     *
     * @param x             the vertex' value
     * @param neighbours    a list of neighbouring vertices and the corresponding edge weights
     * @return a Result wrapping either the created vertex or an appropriate exception
     */
    fun addVertex(x: T, neighbours: List<Pair<T, Int>>): Result<Vertex<T>> {
        println("adding vertex: '$x' ...")

        // get old vertex or create new one:
        getOrCreateVertex(x).fold({ v ->

            // check whether an edge to one of the neighbouring vertices already exists and its edge weight differs:
            checkForDifferingEdgeWeights(v, neighbours).fold({ differingEdgeWeights ->
                if (differingEdgeWeights) {
                    // at least one edge weight differs, return a Failure:
                    return Result.failure(Exception("Some edge weights differ for given neighbouring vertices!"))
                }
            }, { e ->
                return Result.failure(e)
            })

            // filter duplicate neighbours:
            val distinctNeighbours = neighbours
                    .distinctBy { listOf(it.first, it.second) }
                    .toList()

            // create edge objects for all distinct neighbours:
            val distinctEdges = distinctNeighbours
                    .map { Edge(v, vertices[it.first] ?: Vertex(it.first), it.second) }
                    .toList()

            // place each edge in our matrix:
            distinctEdges.forEach {
                persistEdge(it).fold({ v ->
                    println("The edge's indices are: ${v.first}, ${v.second}")
                }, { e ->
                    return Result.failure(e)
                })
            }

            return Result.success(v)
        }, { e ->
            return Result.failure(e)
        })
    }


    /**
     * Looks up a vertex for a given value.
     *
     * @param x the value
     * @return the vertex or null
     */
    fun get(x: T): Vertex<T>? {
        return if (!g.containsKey(x) && vertices[x] == null) {
            null
        } else {
            vertices[x]
        }
    }


    /**
     * Checks whether a vertex with the same value already exists (the hash map allows for
     * lookups in O(1)).
     *
     * @param x the vertex' value
     * @return the (new or already existing) vertex
     */
    private fun getOrCreateVertex(x: T): Result<Vertex<T>> {
        // does the vertex already exist?
        return if (vertices.contains(x)) {
            val v = vertices[x] ?: return Result.failure(Exception("The vertex was null!"))
            Result.success(v)
        } else {
            // no! create and persist the new vertex:
            val v = Vertex(x)
            vertices[x] = v
            Result.success(v)
        }
    }


    /**
     * Checks whether a neighbouring vertex already exists and its edge weight differs.
     *
     * @param v the new vertex
     * @param neighbours its neighbours
     * @return a Result containing true if a differing edge weight was found, false otherwise
     */
    private fun checkForDifferingEdgeWeights(v: Vertex<T>, neighbours: List<Pair<T, Int>>): Result<Boolean> {
        val i: Int = indices[v.value] ?: return Result.success(false)

        val neighbourIndices = neighbours
                .filter { g.containsKey(it.first) }
                // mapTo: Pair<Pair<NeighbouringVertex, IndexOfNeighbouringVertex> , EdgeWeight>
                .map {
                    Pair(Pair(it.first, indices[it.first]
                            ?: return Result.failure(Exception("Could not find index '${it.first}'!")))
                            , it.second)
                }
                .toList()

        val edgesWithDifferingWeights = neighbourIndices
                .filter { g[i][it.first.second] != null }
                .filter { g[i][it.first.second]?.weight != it.second }
                .toList()

        return if (!edgesWithDifferingWeights.isEmpty()) {
            println("Some edge weights differ for given neighbouring vertices!")
            edgesWithDifferingWeights
                    .forEach {
                        println("  -> the edge weight differs for ${v.value} -> ${it.first.first}, weight ${it.second}!")
                    }
            Result.success(true)
        } else {
            Result.success(false)
        }
    }


    /**
     * Persists a given edge.
     *
     * @param edge the edge
     * @return the endpoint's indices
     */
    private fun persistEdge(edge: Edge<T>): Result<Pair<Int, Int>> {
        val endpointValue1 = edge.endpoint1.value
        val endpointValue2 = edge.endpoint2.value

        // do we already have indices for these endpoints?
        val indicesExist = indices.containsKey(endpointValue1) && indices.containsKey(endpointValue2)

        // does our graph already contain these endpoints?
        val graphContainsEndpoints = g.containsKey(endpointValue1) && g.containsKey(endpointValue2)

        if (!indicesExist && graphContainsEndpoints) {
            return Result.failure(Exception("Something went wrong: the graph contains these values"
                    + " but I can not find their indices!"))
        }

        if (indicesExist && !graphContainsEndpoints) {
            return Result.failure(Exception("Something went wrong: the graph does not contain these values"
                    + " but I found their indices!"))
        }

        // get or create indices for the two vertices:
        val i = storeOrGetVertexValueToIndexMapping(endpointValue1)
                .getOrElse { e -> return Result.failure(e) }
        val k = storeOrGetVertexValueToIndexMapping(endpointValue2)
                .getOrElse { e -> return Result.failure(e) }

        // store both endpoints (if necessary):
        if (!vertices.containsKey(endpointValue1)) {
            vertices[endpointValue1] = edge.endpoint1
        }
        if (!vertices.containsKey(endpointValue2)) {
            vertices[endpointValue2] = edge.endpoint2
        }

        // persist the edge:
        return persistEdgeSymmetrically(i, k, edge)
    }


    /**
     * Persists an edge symmetrically, i.e. in the upper and lower
     * triangle of the adjacency matrix.
     *
     * @param i the first endpoints index
     * @param k the second endpoints index
     * @param edge the edge to store
     * @return the endpoint's indices
     */
    private fun persistEdgeSymmetrically(i: Int, k: Int, edge: Edge<T>): Result<Pair<Int, Int>> {
        // store the edge symmetrically, as the graph is undirected:
        g[i][k] = edge
        g[k][i] = edge

        // persist the edge in a separate list for faster lookups:
        edges.add(edge)

        // return its indices:
        return Result.success(Pair(i, k))
    }


    /**
     * Generates a new index for a given value and stores the mapping. If the value is
     * already present, it will return its index.
     *
     * @param x the (vertex') value
     * @return a Result containing the (new) index or a Failure
     */
    private fun storeOrGetVertexValueToIndexMapping(x: T): Result<Int> {
        return if (indices.contains(x)) {
            // return the already existing index:
            val i: Int = indices[x]
                    ?: return Result.failure(Exception("There should have been an index for the value ($x)!"))
            Result.success(i)

        } else {
            // generate a new index:
            val i = indexGenerator.invoke()
            indices[x] = i
            Result.success(i)
        }
    }


    /**
     * An extension function that looks up a given edge in a two-dimensional array.
     *
     * @param k the key
     * @return true if a corresponding value is in the array, false otherwise
     */
    private fun Array<Array<Edge<T>?>>.containsKey(k: T): Boolean {
        val matchingEdges = this
                .flatMap { it.toList() }
                .filter { it?.endpoint1?.value == k || it?.endpoint2?.value == k }
                .toList()
        val size = matchingEdges
                .size

        return size > 0
    }


    /**
     * A simple vertex class that stores a vertex' value.
     */
    class Vertex<T>(val value: T) : Comparable<Vertex<T>> {
        override fun compareTo(other: Vertex<T>): Int {
            TODO("not implemented")
        }
    }


    /**
     * A simple edge class that stores both endpoints and the
     * edge's weight.
     */
    class Edge<T>(ep1: Vertex<T>, ep2: Vertex<T>, w: Int?) {
        val endpoint1: Vertex<T> = ep1
        val endpoint2: Vertex<T> = ep2
        val weight: Int = w ?: 0

    }

}
