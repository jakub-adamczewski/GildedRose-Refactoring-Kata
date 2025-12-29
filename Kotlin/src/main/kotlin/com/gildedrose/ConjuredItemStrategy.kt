package com.gildedrose

/**
 * Strategy for Conjured items, which degrade twice as fast as normal items.
 *
 * Rules:
 * - Quality degrades by 2 per day before the sell-by date
 * - Quality degrades by 4 per day after the sell-by date
 * - Quality never goes below 0
 */
data object ConjuredItemStrategy : ItemUpdateStrategy {
    override fun updateItem(item: Item) {
        item.sellIn--
        val degradationRate = if (item.sellIn < 0) 4 else 2
        item.quality = (item.quality - degradationRate).coerceAtLeast(0)
    }
}