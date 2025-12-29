package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested

/**
 * Integration tests for the GildedRose system.
 * Individual strategy tests are in separate test files for each strategy.
 */
internal class GildedRoseTest {

    @Nested
    inner class MultipleItems {

        @Test
        fun `multiple items are updated correctly`() {
            val items = listOf(
                Item("Normal Item", sellIn = 10, quality = 20),
                Item("Aged Brie", sellIn = 5, quality = 10),
                Item("Sulfuras, Hand of Ragnaros", sellIn = 0, quality = 80),
                Item("Backstage passes to a TAFKAL80ETC concert", sellIn = 15, quality = 20),
                Item("Conjured Mana Cake", sellIn = 3, quality = 6)
            )
            val app = GildedRose(items)

            app.updateQuality()

            // Normal Item
            assertEquals(9, items[0].sellIn)
            assertEquals(19, items[0].quality)

            // Aged Brie
            assertEquals(4, items[1].sellIn)
            assertEquals(11, items[1].quality)

            // Sulfuras
            assertEquals(0, items[2].sellIn)
            assertEquals(80, items[2].quality)

            // Backstage passes
            assertEquals(14, items[3].sellIn)
            assertEquals(21, items[3].quality)

            // Conjured
            assertEquals(2, items[4].sellIn)
            assertEquals(4, items[4].quality)
        }
    }

    @Nested
    inner class EdgeCases {

        @Test
        fun `quality boundaries are respected across multiple updates`() {
            val items = listOf(
                Item("Aged Brie", sellIn = 2, quality = 48),
                Item("Normal Item", sellIn = 2, quality = 2)
            )
            val app = GildedRose(items)

            // Day 1
            app.updateQuality()
            assertEquals(49, items[0].quality)
            assertEquals(1, items[1].quality)

            // Day 2
            app.updateQuality()
            assertEquals(50, items[0].quality)
            assertEquals(0, items[1].quality)

            // Day 3 - quality boundaries maintained
            app.updateQuality()
            assertEquals(50, items[0].quality)
            assertEquals(0, items[1].quality)
        }

        @Test
        fun `backstage pass progression through all stages`() {
            val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 10))
            val app = GildedRose(items)

            // 11 days: +1
            app.updateQuality()
            assertEquals(10, items[0].sellIn)
            assertEquals(11, items[0].quality)

            // 10 days: +2
            app.updateQuality()
            assertEquals(9, items[0].sellIn)
            assertEquals(13, items[0].quality)

            // Continue to 5 days
            repeat(4) { app.updateQuality() }
            assertEquals(5, items[0].sellIn)
            assertEquals(21, items[0].quality)

            // 5 days: +3
            app.updateQuality()
            assertEquals(4, items[0].sellIn)
            assertEquals(24, items[0].quality)

            // Continue to after concert
            repeat(5) { app.updateQuality() }
            assertEquals(-1, items[0].sellIn)
            assertEquals(0, items[0].quality)
        }
    }
}