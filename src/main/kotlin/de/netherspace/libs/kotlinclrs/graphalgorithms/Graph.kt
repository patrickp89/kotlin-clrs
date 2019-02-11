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
                .distinct()
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
                // an error occurred, return a Failure:
                return Result.failure(e)
            })

            // filter duplicate neighbours:
            val distinctNeighbours = neighbours
                    .distinctBy { listOf(it.first, it.second) }
                    .toList()

            val distinctEdges = distinctNeighbours
                    .map { p -> Edge(v, Vertex(p.first), p.second) }
                    .toList()

            // place each edge in our matrix:
            distinctEdges.forEach {
//                println("   --> endpoint1 = " + it.endpoint1.value + "  endpoint2 = " + it.endpoint2.value) // TODO: erase!
                persistEdge(it).fold({ v ->
                    println("The edge's indices are: ${v.first}, ${v.second}")
                }, { e ->
                    // an error occurred, return a Failure:
                    return Result.failure(e)
                })

                // keep the symmetry:
                // distinctEdges.forEach { g[it.endpoint2.value][it.endpoint1.value] = it }

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
        val differingEdges = neighbours
                .filter { g.containsKey(it.first) }
        //###################################################
                .toList()
        differingEdges
                .forEach { println(" ********>> $it") } // TODO: erase!
        differingEdges
        //###################################################
                .map { it ->
                    Pair<Int, Int>(indices[it.first]
                            ?: return Result.failure(Exception("Could not find index '${it.first}'!"))
                            , it.second)
                }
                //.map { it -> Pair<Array<Edge<T>?>, Int>(g[it.first], it.second) }
                // .filter { it.second != g[it.first][x].weight }
                .filter { g[i][it.first] != null }
                .filter { g[i][it.first]?.weight != it.second }
                .toList()

        return if (!differingEdges.isEmpty()) {
            val m = "Some edge weights differ for given neighbouring vertices!"
            println(m)
            differingEdges.forEach { println("The edge weight differs for ${v.value} -> ${it.first}!") }
            Result.success(true)
        } else {
            Result.success(false)
        }
    }


    /**
     * Persists a given edge.
     *
     * @param edge the edge
     * @return a Result containing the endpoints indices or a Failure
     */
    private fun persistEdge(edge: Edge<T>): Result<Pair<Int, Int>> {
        // do we already have indices for these endpoints?
        val indicesExist = indices.containsKey(edge.endpoint1.value) && indices.containsKey(edge.endpoint2.value)
//        println("  indicesExist = $indicesExist") // TODO erase!

        // does our graph already contain these endpoints?
        val graphContainsEndpoints = g.containsKey(edge.endpoint1.value) && g.containsKey(edge.endpoint2.value)
//        println("  graphContainsEndpoints = $graphContainsEndpoints") // TODO erase!

        if (!indicesExist && graphContainsEndpoints) {
            return Result.failure(Exception("Something went wrong: the graph contains these values"
                    + " but I can not find their indices!"))
        }

        if (indicesExist && !graphContainsEndpoints) {
            return Result.failure(Exception("Something went wrong: the graph does not contain these values"
                    + " but I found their indices!"))
        }

        // does our graph already contain these endpoints?
        if (graphContainsEndpoints) {
//            println("  if (graphContainsEndpoints) { ... }") // TODO erase!

            // yes! then there must also exist an index for it:
            if (!indices.contains(edge.endpoint1.value)) { // TODO: was "containsKey" - which is correct?
//                println("    there is NO index for key '${edge.endpoint1.value}'") // TODO: erase!

                // if there is no index, something went wrong somewhere:
                val m = "Something went wrong: I couldn't find the array index for '${edge.endpoint1.value}'!"
                println(m)
                return Result.failure(Exception(m))

            } else if (!indices.contains(edge.endpoint2.value)) { // TODO: was "containsKey" - which is correct?
//                println("    there is NO index for key '${edge.endpoint2.value}'") // TODO: erase!

                // if there is no index, something went wrong somewhere:
                val m = "Something went wrong: I couldn't find the array index for '${edge.endpoint2.value}'!"
                println(m)
                return Result.failure(Exception(m))

            } else {
//                println("    there IS an index for key '${edge.endpoint1.value}': " + indices[edge.endpoint1.value]) // TODO: erase!
                // we have an index, so store our edge in that column/row:
                val i = indices[edge.endpoint1.value]
                        ?: return Result.failure(Exception("Something went wrong: an index was null!"))

                val j = indices[edge.endpoint1.value]
                        ?: return Result.failure(Exception("Something went wrong: an index was null!"))

                // TODO: where is the "g[i][k] = edge" ?? Not needed here??

                // persist the edge in a separate list for faster lookups:
                edges.add(edge)

                // return the existing indices:
                return Result.success(Pair(i, j))
            }

        } else {
//            println("  if (graphContainsEndpoints) {} else { ... }") // TODO erase!

            // no, the edge is not part of our graph yet => persist it!
//            println("   the edge '${edge.endpoint1.value}' / '${edge.endpoint2.value}'"
//                    + " (${edge.endpoint1} / ${edge.endpoint2}) is not part of our graph yet!") // TODO: erase!

            // get an index for its first endpoint:
            val i = storeOrGetVertexValueToIndexMapping(edge.endpoint1.value)
                    .getOrElse { e -> return Result.failure(e) }

            val j = indices[edge.endpoint2.value]
            return if (j == null) {
                // the adjacent vertex is not yet persisted!
//                println("   the adjacent vertex is NOT yet persisted!") // TODO: erase!

                // get an index for this (adjacent) vertex:
                val k = storeOrGetVertexValueToIndexMapping(edge.endpoint2.value)
                        .getOrElse { e -> return Result.failure(e) }

                // store both endpoints (if necessary):
                if (!vertices.containsKey(edge.endpoint1.value)) {
                    vertices[edge.endpoint1.value] = edge.endpoint2
                }
                if (!vertices.containsKey(edge.endpoint2.value)) {
                    vertices[edge.endpoint2.value] = edge.endpoint2
                }

                // persist the actual edge:
                println("   g[$i][$k] = $edge")
                g[i][k] = edge

                // persist the edge in a separate list for faster lookups:
                edges.add(edge)

                // return the existing indices:
                Result.success(Pair(i, k)) // TODO: this line and the above 6 (the whole "else" branch) should be extracted for reuse!
            } else {
//                println("   the adjacent vertex is already persisted") // TODO: erase!
                // persist the actual edge:
                println("   g[$i][$j] = $edge")
                g[i][j] = edge

                // persist the edge in a separate list for faster lookups:
                edges.add(edge)

                // return the existing indices:
                Result.success(Pair(i, j)) // TODO: this line and the above 6 (the whole "else" branch) should be extracted for reuse!
            }
        }
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
//        println("containsKey($k)") // TODO: erase!
        val matchingEdges = this
                .flatMap { it.toList() }
                .filter { it?.endpoint1?.value == k || it?.endpoint2?.value == k }
                .toList()
//        matchingEdges.forEach { println(" ~> $it") } // TODO: erase!
        val size = matchingEdges
                .size

//        println(" ~~>" + (size > 0)) // TODO: erase!
        return size > 0
    }


    /**
     * A simple vertex class that stores a vertex' value.
     */
    class Vertex<T>(val value: T)


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
