package com.gildedrose

/**
 * Strategy for Backstage passes, which increase in value as the concert approaches
 * but become worthless after the concert.
 *
 * Rules:
 * - Quality increases by 1 when more than 10 days remain
 * - Quality increases by 2 when 10 days or less remain
 * - Quality increases by 3 when 5 days or less remain
 * - Quality drops to 0 after the concert (sellIn < 0)
 * - Quality never exceeds MAX_QUALITY (50)
 */
data object BackstagePassStrategy : ItemUpdateStrategy {
    override fun updateItem(item: Item) {
        item.sellIn--
        if (item.sellIn < 0) {
            item.quality = 0
        } else {
            val qualityIncrease = when {
                item.sellIn < 5 -> 3
                item.sellIn < 10 -> 2
                else -> 1
            }
            item.quality = (item.quality + qualityIncrease).coerceAtMost(ItemUpdateStrategy.MAX_QUALITY)
        }
    }
}