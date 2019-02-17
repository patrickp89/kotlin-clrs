package de.netherspace.libs.kotlinclrs.elementarydatastructures

import java.util.concurrent.atomic.AtomicInteger

class DoublyLinkedList<T> : List<T> {

    private val head = DoublyLinkedNode<T>(null, -1)
    private val tail = DoublyLinkedNode<T>(null, -1)
    private val size = AtomicInteger(0)

    constructor() {
        head.next = tail
        tail.pred = head
    }

    override fun search(k: Long): T? {
        val x = searchNode(k) ?: return null
        return x.element
    }

    override fun insert(x: T, k: Long): DoublyLinkedNode<T> {
        val newNode = DoublyLinkedNode(x, k)
        val y = head.next
        head.next = newNode
        newNode.next = y
        y.pred = newNode
        newNode.pred = head
        size.incrementAndGet()
        return newNode
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

    private fun searchNode(k: Long): DoublyLinkedNode<T>? {
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

    class DoublyLinkedNode<U>(e: U?, k: Long) : Node() {
        val element = e
        val key = k
        var next = this
        var pred = this
    }

}
