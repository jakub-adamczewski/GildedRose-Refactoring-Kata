package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SulfurasStrategyTest {

    @Test
    fun `sulfuras never decreases in quality`() {
        val item = Item("Sulfuras, Hand of Ragnaros", sellIn = 10, quality = 80)

        SulfurasStrategy.updateItem(item)

        assertEquals(10, item.sellIn)
        assertEquals(80, item.quality)
    }

    @Test
    fun `sulfuras never changes even after sell date`() {
        val item = Item("Sulfuras, Hand of Ragnaros", sellIn = -1, quality = 80)

        SulfurasStrategy.updateItem(item)

        assertEquals(-1, item.sellIn)
        assertEquals(80, item.quality)
    }

    @Test
    fun `sulfuras with quality 0 remains unchanged`() {
        val item = Item("Sulfuras, Hand of Ragnaros", sellIn = 5, quality = 0)

        SulfurasStrategy.updateItem(item)

        assertEquals(5, item.sellIn)
        assertEquals(0, item.quality)
    }

    @Test
    fun `sulfuras sellIn never decrements`() {
        val item = Item("Sulfuras, Hand of Ragnaros", sellIn = 0, quality = 80)

        SulfurasStrategy.updateItem(item)

        assertEquals(0, item.sellIn)
        assertEquals(80, item.quality)
    }
}