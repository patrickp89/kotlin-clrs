package de.netherspace.libs.kotlinclrs.elementarydatastructures

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.hamcrest.Matchers.`is` as Is

class StackTest {

    @Test
    fun testArrayStack() {
        val stack: Stack<String> = ArrayStack()
        assertThat(stack.isEmpty(), Is(true))

        assertThat(stack.push("eins"), Is(true))
        assertThat(stack.isEmpty(), Is(false))

        assertThat(stack.pop(), Is("eins"))
        assertThat(stack.isEmpty(), Is(true))

        for (i in 1..15) {
            assertThat(stack.push("test $i"), Is(true))
        }
        for (i in 15 downTo 1) {
            assertThat(stack.pop(), Is("test $i"))
        }
        assertThat(stack.isEmpty(), Is(true))
    }

}
