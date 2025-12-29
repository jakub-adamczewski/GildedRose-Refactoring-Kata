package com.gildedrose

/**
 * Strategy for normal items that degrade in quality over time.
 *
 * Rules:
 * - Quality degrades by 1 per day before the sell-by date
 * - Quality degrades by 2 per day after the sell-by date
 * - Quality never goes below 0
 */
data object NormalItemStrategy : ItemUpdateStrategy {
    override fun updateItem(item: Item) {
        item.sellIn--
        val degradationRate = if (item.sellIn < 0) 2 else 1
        item.quality = (item.quality - degradationRate).coerceAtLeast(0)
    }
}