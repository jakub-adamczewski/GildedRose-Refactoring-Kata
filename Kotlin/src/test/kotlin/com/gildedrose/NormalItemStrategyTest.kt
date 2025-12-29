package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class NormalItemStrategyTest {

    @Test
    fun `normal item degrades quality by 1 before sell date`() {
        val item = Item("Normal Item", sellIn = 10, quality = 20)

        NormalItemStrategy.updateItem(item)

        assertEquals(9, item.sellIn)
        assertEquals(19, item.quality)
    }

    @Test
    fun `normal item degrades quality by 2 after sell date`() {
        val item = Item("Normal Item", sellIn = 0, quality = 20)

        NormalItemStrategy.updateItem(item)

        assertEquals(-1, item.sellIn)
        assertEquals(18, item.quality)
    }

    @Test
    fun `normal item quality never goes negative`() {
        val item = Item("Normal Item", sellIn = 5, quality = 0)

        NormalItemStrategy.updateItem(item)

        assertEquals(4, item.sellIn)
        assertEquals(0, item.quality)
    }

    @Test
    fun `normal item quality does not go below zero even after sell date`() {
        val item = Item("Normal Item", sellIn = -1, quality = 1)

        NormalItemStrategy.updateItem(item)

        assertEquals(-2, item.sellIn)
        assertEquals(0, item.quality)
    }

    @Test
    fun `normal item with quality 1 goes to 0 after sell date`() {
        val item = Item("Normal Item", sellIn = 0, quality = 1)

        NormalItemStrategy.updateItem(item)

        assertEquals(-1, item.sellIn)
        assertEquals(0, item.quality)
    }
}