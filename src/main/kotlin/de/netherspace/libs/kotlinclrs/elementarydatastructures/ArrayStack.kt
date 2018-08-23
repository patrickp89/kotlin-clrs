package de.netherspace.libs.kotlinclrs.elementarydatastructures

import java.util.concurrent.atomic.AtomicInteger

class ArrayStack<T> : Stack<T> {

    private val A = emptyArray<StackElement<T>>()
    private var top = AtomicInteger(0)

    override fun push(x: T): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pop(): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class StackElement<T>(e: T?) {
        val element = e
    }
}
