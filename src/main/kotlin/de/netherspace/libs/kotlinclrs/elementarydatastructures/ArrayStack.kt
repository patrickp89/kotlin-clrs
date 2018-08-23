package de.netherspace.libs.kotlinclrs.elementarydatastructures

import java.util.concurrent.atomic.AtomicInteger

class ArrayStack<T> : Stack<T> {

    private var a = Array<StackElement<T>>(10) {_ -> StackElement(null)}
    private val top = AtomicInteger(0)

    override fun push(x: T): Boolean {
        if (top.get() == a.size - 1) {
            resizeArray()
        }
        a[top.incrementAndGet()] = StackElement(x)
        return true
    }

    override fun pop(): T? {
        return if (!isEmpty()) {
            a[top.getAndDecrement()].element
        } else {
            null
        }
    }

    override fun isEmpty(): Boolean {
        return top.get() == 0
    }

    private fun resizeArray() {
        val newSize = a.size * 2
        var b = Array<StackElement<T>>(newSize) {_ -> StackElement(null)}
        for (i in 0 until a.size) {
            b[i] = a[i]
        }
        a = b
    }

    class StackElement<T>(e: T?) {
        val element = e
    }
}
