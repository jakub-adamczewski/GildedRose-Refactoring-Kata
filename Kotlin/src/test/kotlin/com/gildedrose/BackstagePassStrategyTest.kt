package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BackstagePassStrategyTest {

    @Test
    fun `backstage pass increases quality by 1 when more than 10 days remain`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", sellIn = 15, quality = 20)

        BackstagePassStrategy.updateItem(item)

        assertEquals(14, item.sellIn)
        assertEquals(21, item.quality)
    }

    @Test
    fun `backstage pass increases quality by 2 when 10 days or less remain`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 20)

        BackstagePassStrategy.updateItem(item)

        assertEquals(9, item.sellIn)
        assertEquals(22, item.quality)
    }

    @Test
    fun `backstage pass increases quality by 3 when 5 days or less remain`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", sellIn = 5, quality = 20)

        BackstagePassStrategy.updateItem(item)

        assertEquals(4, item.sellIn)
        assertEquals(23, item.quality)
    }

    @Test
    fun `backstage pass quality drops to 0 after concert`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", sellIn = 0, quality = 20)

        BackstagePassStrategy.updateItem(item)

        assertEquals(-1, item.sellIn)
        assertEquals(0, item.quality)
    }

    @Test
    fun `backstage pass quality never exceeds 50 with 5 days remaining`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", sellIn = 5, quality = 49)

        BackstagePassStrategy.updateItem(item)

        assertEquals(4, item.sellIn)
        assertEquals(50, item.quality)
    }

    @Test
    fun `backstage pass quality capped at 50 when 10 days remain`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 49)

        BackstagePassStrategy.updateItem(item)

        assertEquals(9, item.sellIn)
        assertEquals(50, item.quality)
    }

    @Test
    fun `backstage pass quality remains at 50 when already at max`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", sellIn = 15, quality = 50)

        BackstagePassStrategy.updateItem(item)

        assertEquals(14, item.sellIn)
        assertEquals(50, item.quality)
    }

    @Test
    fun `backstage pass with 11 days increases by 1`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 20)

        BackstagePassStrategy.updateItem(item)

        assertEquals(10, item.sellIn)
        assertEquals(21, item.quality)
    }

    @Test
    fun `backstage pass with 6 days increases by 2`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", sellIn = 6, quality = 20)

        BackstagePassStrategy.updateItem(item)

        assertEquals(5, item.sellIn)
        assertEquals(22, item.quality)
    }
}