package de.netherspace.libs.kotlinclrs.elementarydatastructures

import java.util.concurrent.atomic.AtomicInteger

class SinglyLinkedList<T> : List<T> {

    private val head: Node<T> = Node(null, -1)
    private val tail: Node<T> = Node(null, -1)
    private val size = AtomicInteger(0)

    constructor() {
        head.next = tail
    }

    override fun search(k: Long): T? {
        val p = searchPredecessor(k) ?: return null
        return p.next.element
    }

    override fun insert(x: T, k: Long): Boolean {
        val newNode = Node(x, k)
        val y = head.next
        head.next = newNode
        newNode.next = y
        size.incrementAndGet()
        return true
    }

    override fun delete(k: Long): T? {
        val p = searchPredecessor(k) ?: return null
        val x = p.next.element
        val pnn = p.next.next
        p.next = pnn
        size.decrementAndGet()
        return x
    }

    override fun size(): Int {
        return size.get()
    }

    private fun searchPredecessor(k: Long): Node<T>? {
        var p = head
        var x = head.next
        while (x !== tail && x.key != k) {
            p = x
            x = x.next
        }
        return if (x === tail) {
            null
        } else {
            p
        }
    }

    class Node<T>(e: T?, k: Long) {
        val element = e
        val key = k
        var next = this
    }

}
