package de.netherspace.libs.kotlin-clrs

interface List<T> {

    fun search(k: Long) : T?

    fun insert(x: T, k: Long) : Boolean

    fun delete(k: Long) : T?

    fun size() : Int

}
