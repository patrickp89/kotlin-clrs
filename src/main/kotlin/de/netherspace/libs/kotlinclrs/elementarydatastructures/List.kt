package de.netherspace.libs.kotlinclrs.elementarydatastructures

interface List<T> {

    fun search(k: Long) : T?

    fun insert(x: T, k: Long) : Boolean

    fun delete(k: Long) : T?

    fun size() : Int

}
