package com.gildedrose

/**
 * Strategy for Aged Brie, which increases in quality as it ages.
 *
 * Rules:
 * - Quality increases by 1 per day before the sell-by date
 * - Quality increases by 2 per day after the sell-by date
 * - Quality never exceeds MAX_QUALITY (50)
 */
data object AgedBrieStrategy : ItemUpdateStrategy {
    override fun updateItem(item: Item) {
        item.sellIn--
        val appreciationRate = if (item.sellIn < 0) 2 else 1
        item.quality = (item.quality + appreciationRate).coerceAtMost(ItemUpdateStrategy.MAX_QUALITY)
    }
}