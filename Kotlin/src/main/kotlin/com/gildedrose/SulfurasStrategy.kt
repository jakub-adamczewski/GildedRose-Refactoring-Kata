package com.gildedrose

/**
 * Strategy for Sulfuras, a legendary item that never changes.
 *
 * Rules:
 * - Quality never changes
 * - SellIn never changes
 * - Being a legendary item, it has no degradation or expiration
 */
data object SulfurasStrategy : ItemUpdateStrategy {
    override fun updateItem(item: Item) {
        // Legendary items never change
    }
}