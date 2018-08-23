package de.netherspace.libs.kotlinclrs.elementarydatastructures

import java.util.concurrent.atomic.AtomicInteger

class ArrayStack<T> : Stack<T> {

    private val a = Array<StackElement<T>>(10) {_ -> StackElement(null)}
    private var top = AtomicInteger(0)

    override fun push(x: T): Boolean {
        a[top.incrementAndGet()] = StackElement(x)
        //TODO: resize!
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

    class StackElement<T>(e: T?) {
        val element = e
    }
}
