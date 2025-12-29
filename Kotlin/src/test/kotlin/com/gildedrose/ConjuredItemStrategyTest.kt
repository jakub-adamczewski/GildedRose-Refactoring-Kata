package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ConjuredItemStrategyTest {

    @Test
    fun `conjured item degrades quality by 2 before sell date`() {
        val item = Item("Conjured Mana Cake", sellIn = 10, quality = 20)

        ConjuredItemStrategy.updateItem(item)

        assertEquals(9, item.sellIn)
        assertEquals(18, item.quality)
    }

    @Test
    fun `conjured item degrades quality by 4 after sell date`() {
        val item = Item("Conjured Mana Cake", sellIn = 0, quality = 20)

        ConjuredItemStrategy.updateItem(item)

        assertEquals(-1, item.sellIn)
        assertEquals(16, item.quality)
    }

    @Test
    fun `conjured item quality never goes negative`() {
        val item = Item("Conjured Mana Cake", sellIn = 5, quality = 1)

        ConjuredItemStrategy.updateItem(item)

        assertEquals(4, item.sellIn)
        assertEquals(0, item.quality)
    }

    @Test
    fun `conjured item quality does not go below zero even after sell date`() {
        val item = Item("Conjured Mana Cake", sellIn = -1, quality = 3)

        ConjuredItemStrategy.updateItem(item)

        assertEquals(-2, item.sellIn)
        assertEquals(0, item.quality)
    }

    @Test
    fun `conjured item with quality 0 remains at 0`() {
        val item = Item("Conjured Mana Cake", sellIn = 5, quality = 0)

        ConjuredItemStrategy.updateItem(item)

        assertEquals(4, item.sellIn)
        assertEquals(0, item.quality)
    }

    @Test
    fun `conjured item with quality 2 goes to 0 before sell date`() {
        val item = Item("Conjured Mana Cake", sellIn = 5, quality = 2)

        ConjuredItemStrategy.updateItem(item)

        assertEquals(4, item.sellIn)
        assertEquals(0, item.quality)
    }
}