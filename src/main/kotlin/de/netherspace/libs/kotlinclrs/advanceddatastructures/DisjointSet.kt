package de.netherspace.libs.kotlinclrs.advanceddatastructures

import de.netherspace.libs.kotlinclrs.elementarydatastructures.List
import de.netherspace.libs.kotlinclrs.elementarydatastructures.Node
import java.util.concurrent.atomic.AtomicInteger

class DisjointSet<T> {

    private val sets = mutableMapOf<T, DisjointSetNode<T>>()

    fun makeSet(x: T): DisjointSetNode<T> {
        val set = DisjointSetLinkedList<T>()
        val node = set.insert(x, 0L)
        sets[x] = node
        return node
    }

    fun union(x: T, y: T) {
        TODO("not implemented")
    }

    /**
     * Returns the representative of this set.
     *
     * @param x the element of the set
     * @return its representative
     */
    fun findSet(x: T): DisjointSetNode<T>? {
        val set = sets[x]
        return set?.representative
    }

}

class DisjointSetLinkedList<U> : List<U> {

    private val head = DisjointSetNode<U>(null, -1)
    private val tail = DisjointSetNode<U>(null, -1)
    private val size = AtomicInteger(0)

    constructor() {
        head.next = tail
        tail.pred = head
    }

    override fun search(k: Long): U? {
        val x = searchNode(k) ?: return null
        return x.element
    }

    override fun insert(x: U, k: Long): DisjointSetNode<U> {
        val newNode = DisjointSetNode(x, k)
        val y = head.next
        head.next = newNode
        newNode.next = y
        y.pred = newNode
        newNode.pred = head
        size.incrementAndGet()
        newNode.representative = head
        return newNode
    }

    override fun delete(k: Long): U? {
        val x = searchNode(k) ?: return null
        val p = x.pred
        p.next = x.next
        p.next.pred = p
        size.decrementAndGet()
        return x.element
    }

    override fun size(): Int {
        return size.get()
    }

    private fun searchNode(k: Long): DisjointSetNode<U>? {
        var x = head.next
        while (x !== tail && x.key != k) {
            x = x.next
        }
        return if (x === tail) {
            null
        } else {
            x
        }
    }

}

class DisjointSetNode<V>(e: V?, k: Long) : Node() {
    val element = e
    val key = k
    var next = this
    var pred = this
    var representative = this
}
