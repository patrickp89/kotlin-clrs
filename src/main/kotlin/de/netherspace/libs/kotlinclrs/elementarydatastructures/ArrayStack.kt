package de.netherspace.libs.kotlinclrs.elementarydatastructures

import java.util.concurrent.atomic.AtomicInteger

class ArrayStack<T> : Stack<T> {

    private val initialArraySize = 10

    private var a = Array<StackElement<T>>(initialArraySize) { StackElement(null) }
    private val top = AtomicInteger(0)

    override fun push(x: T): Boolean {
        if (top.get() == a.size - 1) {
            a = resizeArray(a)
        }
        a[top.incrementAndGet()] = StackElement(x)
        return true
    }

    override fun pop(): T? {
        if (top.get() < a.size / 2) {
            a = shrink(a)
        }
        return if (!isEmpty()) {
            a[top.getAndDecrement()].element
        } else {
            null
        }
    }

    override fun isEmpty(): Boolean {
        return top.get() == 0
    }

    private fun resizeArray(A: Array<StackElement<T>>): Array<StackElement<T>> {
        return copy(A) { s: Int -> s * 2 }
    }

    private fun shrink(A: Array<StackElement<T>>): Array<StackElement<T>> {
        //check for min. threshold:
        return if (A.size >= initialArraySize * 2) {
            copy(A) { s: Int -> s / 2 }
        } else {
            A
        }
    }

    private fun copy(A: Array<StackElement<T>>, sizeOperator: (Int) -> Int): Array<StackElement<T>> {
        val newSize = sizeOperator.invoke(A.size)
        val b = Array<StackElement<T>>(newSize) { StackElement(null) }
        for (i in 0 until minimum(A.size, newSize)) {
            b[i] = A[i]
        }
        return b
    }

    private fun minimum(i: Int, j: Int): Int {
        return if (i < j) {
            i
        } else {
            j
        }
    }

    class StackElement<T>(e: T?) {
        val element = e
    }
}
