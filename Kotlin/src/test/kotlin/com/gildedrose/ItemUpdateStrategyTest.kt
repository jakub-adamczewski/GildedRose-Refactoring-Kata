package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested

/**
 * Unit tests for ItemUpdateStrategy factory method.
 * Tests the mapping logic from item names to their corresponding strategies.
 */
internal class ItemUpdateStrategyTest {

    @Nested
    inner class FactoryMethod {

        @Test
        fun `from returns AgedBrieStrategy for Aged Brie`() {
            val strategy = ItemUpdateStrategy.from("Aged Brie")

            assertInstanceOf(AgedBrieStrategy::class.java, strategy)
        }

        @Test
        fun `from returns SulfurasStrategy for Sulfuras, Hand of Ragnaros`() {
            val strategy = ItemUpdateStrategy.from("Sulfuras, Hand of Ragnaros")

            assertInstanceOf(SulfurasStrategy::class.java, strategy)
        }

        @Test
        fun `from returns BackstagePassStrategy for Backstage passes to a TAFKAL80ETC concert`() {
            val strategy = ItemUpdateStrategy.from("Backstage passes to a TAFKAL80ETC concert")

            assertInstanceOf(BackstagePassStrategy::class.java, strategy)
        }

        @Test
        fun `from returns ConjuredItemStrategy for Conjured Mana Cake`() {
            val strategy = ItemUpdateStrategy.from("Conjured Mana Cake")

            assertInstanceOf(ConjuredItemStrategy::class.java, strategy)
        }

        @Test
        fun `from returns ConjuredItemStrategy for any item starting with Conjured`() {
            val strategies = listOf(
                ItemUpdateStrategy.from("Conjured Mana Cake"),
                ItemUpdateStrategy.from("Conjured Sword"),
                ItemUpdateStrategy.from("Conjured Health Potion"),
                ItemUpdateStrategy.from("Conjured item")
            )

            strategies.forEach { strategy ->
                assertInstanceOf(ConjuredItemStrategy::class.java, strategy)
            }
        }

        @Test
        fun `from returns NormalItemStrategy for unknown items`() {
            val strategies = listOf(
                ItemUpdateStrategy.from("Unknown Item"),
                ItemUpdateStrategy.from("Regular Sword"),
                ItemUpdateStrategy.from("Health Potion"),
                ItemUpdateStrategy.from("")
            )

            strategies.forEach { strategy ->
                assertInstanceOf(NormalItemStrategy::class.java, strategy)
            }
        }

        @Test
        fun `from returns NormalItemStrategy for item with Conjured in middle of name`() {
            val strategy = ItemUpdateStrategy.from("Mystical Conjured Artifact")

            assertInstanceOf(NormalItemStrategy::class.java, strategy)
        }

        @Test
        fun `from is case sensitive for special items`() {
            val strategies = listOf(
                ItemUpdateStrategy.from("aged brie"),
                ItemUpdateStrategy.from("AGED BRIE"),
                ItemUpdateStrategy.from("backstage passes to a TAFKAL80ETC concert"),
                ItemUpdateStrategy.from("sulfuras, hand of ragnaros")
            )

            strategies.forEach { strategy ->
                assertInstanceOf(NormalItemStrategy::class.java, strategy)
            }
        }
    }

    @Nested
    inner class Constants {

        @Test
        fun `MAX_QUALITY is 50`() {
            assertEquals(50, ItemUpdateStrategy.MAX_QUALITY)
        }
    }
}