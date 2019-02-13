package de.netherspace.libs.kotlinclrs.advanceddatastructures

import de.netherspace.libs.kotlinclrs.elementarydatastructures.List
import java.util.concurrent.atomic.AtomicInteger

class DisjointSet<T> {

    private val sets = mutableListOf<DisjointSetLinkedList<T>>()

    fun makeSet(x: T) : DisjointSetLinkedList<T> {
        val set = DisjointSetLinkedList<T>()
        set.insert(x, 999) // TODO: key is not necessary for the DS impl.?!
        sets.add(set)
        return set
    }

    fun union(x: T, y: T) {
        TODO("not implemented")
    }

    fun findSet(x: T): T {
        return x
    }

}

class DisjointSetLinkedList<T> : List<T> {

    private val head = DisjointSetdNode<T> (null, -1)
    private val tail = DisjointSetdNode<T> (null, -1)
    private val size = AtomicInteger(0)

    constructor() {
        head.next = tail
        tail.pred = head
    }

    override fun search(k: Long): T? {
        val x = searchNode(k) ?: return null
        return x.element
    }

    override fun insert(x: T, k: Long): Boolean {
        val newNode = DisjointSetdNode(x, k)
        val y = head.next
        head.next = newNode
        newNode.next = y
        y.pred = newNode
        newNode.pred = head
        size.incrementAndGet()
        newNode.representative = head
        return true
    }

    override fun delete(k: Long): T? {
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

    private fun searchNode(k: Long): DisjointSetdNode<T>? {
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

    class DisjointSetdNode<T>(e: T?, k: Long) {
        val element = e
        val key = k
        var next = this
        var pred = this
        var representative = this
    }

}
