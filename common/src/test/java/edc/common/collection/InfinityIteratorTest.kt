package edc.common.collection

import org.junit.Assert
import org.junit.Test

class InfinityIteratorTest {

    @Test
    fun test() {
        val infinityIterator = InfinityIterator(
            listOf("a","b","c")
        )

        repeat(8){
            println(infinityIterator.next())
        }
    }

    @Test
    fun test2() {
        val list = listOf("a","b","c")
        val pal = Palette(list)

        Assert.assertEquals(pal[3], pal[9])
        Assert.assertEquals(pal[2], pal[8])
        Assert.assertEquals(pal[0], pal[3])
    }
}
