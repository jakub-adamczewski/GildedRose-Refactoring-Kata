package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AgedBrieStrategyTest {

    @Test
    fun `aged brie increases in quality before sell date`() {
        val item = Item("Aged Brie", sellIn = 10, quality = 20)

        AgedBrieStrategy.updateItem(item)

        assertEquals(9, item.sellIn)
        assertEquals(21, item.quality)
    }

    @Test
    fun `aged brie increases in quality by 2 after sell date`() {
        val item = Item("Aged Brie", sellIn = 0, quality = 20)

        AgedBrieStrategy.updateItem(item)

        assertEquals(-1, item.sellIn)
        assertEquals(22, item.quality)
    }

    @Test
    fun `aged brie quality never exceeds 50`() {
        val item = Item("Aged Brie", sellIn = 5, quality = 50)

        AgedBrieStrategy.updateItem(item)

        assertEquals(4, item.sellIn)
        assertEquals(50, item.quality)
    }

    @Test
    fun `aged brie quality does not exceed 50 even after sell date`() {
        val item = Item("Aged Brie", sellIn = -1, quality = 49)

        AgedBrieStrategy.updateItem(item)

        assertEquals(-2, item.sellIn)
        assertEquals(50, item.quality)
    }

    @Test
    fun `aged brie at quality 49 caps at 50 before sell date`() {
        val item = Item("Aged Brie", sellIn = 5, quality = 49)

        AgedBrieStrategy.updateItem(item)

        assertEquals(4, item.sellIn)
        assertEquals(50, item.quality)
    }
}