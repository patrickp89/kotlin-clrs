package de.netherspace.libs.kotlinclrs.advanceddatastructures

import de.netherspace.libs.kotlinclrs.elementarydatastructures.List
import de.netherspace.libs.kotlinclrs.elementarydatastructures.Node

/**
 * Tracks the membership of elements to a set of disjoint (sub)sets.
 */
class DisjointSet<T> {

    // maps an element to its wrapped counterpart:
    private val setElements = mutableMapOf<T, DisjointSetNode<T>>()

    // maps an element to its set:
    private val sets = mutableMapOf<T, DisjointSetLinkedList<T>>()


    /**
     * Creates a new set, having x as its only element.
     *
     * @param x the element contained in the new set
     * @return the newly created node
     */
    fun makeSet(x: T): DisjointSetNode<T> {
        val set = DisjointSetLinkedList<T>()
        val node = set.insert(x, 0L)
        sets[x] = set
        setElements[x] = node
        return node
    }


    /**
     * Naively unites the two sets, x and y belong to.
     *
     * @param x the element of the first set
     * @param y the element of the second set
     * @return the representative of the united set
     */
    fun union(x: T, y: T): Result<DisjointSetNode<T>> {
        val firstSet: DisjointSetLinkedList<T> = sets[x]
                ?: return Result.failure(Exception("Could not find a set for '$x'!"))
        val secondSet: DisjointSetLinkedList<T> = sets[y]
                ?: return Result.failure(Exception("Could not find a set for '$x'!"))

        if (firstSet == secondSet) {
            return Result.failure(Exception("x and y already belong to the same set!"))
        }

        // attach the second set's list to end of the first one's:
        val lastElementOfFirstSet = firstSet.tail.pred // tail itself is a dummy!
        val firstElementOfSecondSet = secondSet.head.next// head itself is a dummy!
        lastElementOfFirstSet.next = firstElementOfSecondSet
        firstElementOfSecondSet.pred = lastElementOfFirstSet

        // change the representative for all elements in the second set:
        var z = secondSet.head
        while (z !== secondSet.tail) {
            z.representative = lastElementOfFirstSet.representative
            z = z.next
        }

        // get rid of the obsolete dummies:
        firstSet.tail = secondSet.tail
        secondSet.head.next = secondSet.head

        // adjust the global mapping:
        sets[y] = firstSet

        return Result.success(firstElementOfSecondSet.representative)
    }


    /**
     * Returns the representative of the set which x belongs to.
     *
     * @param x some element
     * @return its representative or null (if no set has been created for x)
     */
    fun findSet(x: T): DisjointSetNode<T>? { // TODO: Result instead of null!
        return setElements[x]?.representative
    }

}


class DisjointSetLinkedList<U> : List<U> {

    var head = DisjointSetNode<U>(null, -1)
    var tail = DisjointSetNode<U>(null, -1)

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
        newNode.representative = head
        return newNode
    }

    override fun delete(k: Long): U? {
        val x = searchNode(k) ?: return null
        val p = x.pred
        p.next = x.next
        p.next.pred = p
        return x.element
    }

    override fun size(): Int {
        var x = head.next
        var i = 0
        while (x !== tail) {
            x = x.next
            i++
        }
        return i
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
