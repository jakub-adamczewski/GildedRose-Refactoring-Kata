package com.gildedrose

/**
 * The Gilded Rose inventory management system.
 * Processes a collection of items, updating their quality and sellIn values
 * according to business rules specific to each item type.
 *
 * @property items The list of items to manage. Items are modified in place during updates.
 */
class GildedRose(val items: List<Item>) {

    /**
     * Updates all items in the inventory for one day.
     * Each item is updated according to its specific business rules.
     */
    fun updateQuality() {
        items.forEach { item ->
            val strategy = ItemUpdateStrategy.from(item.name)
            strategy.updateItem(item)
        }
    }
}