package de.netherspace.libs.kotlinclrs.elementarydatastructures

interface Stack<T> {

    fun push(x: T): Boolean

    fun pop(): T?

    fun isEmpty(): Boolean

}