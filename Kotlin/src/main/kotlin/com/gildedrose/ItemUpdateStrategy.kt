package com.gildedrose

/**
 * Sealed interface representing different update strategies for item types.
 * Each strategy encapsulates the specific business rules for how an item's quality and sellIn evolve.
 *
 * Implementations are singleton objects (data objects) that define type-specific behavior.
 * The factory method [Companion.from] determines the appropriate strategy based on item name.
 */
sealed interface ItemUpdateStrategy {

    /**
     * Updates the given item according to this strategy's business rules.
     * Modifies the item's sellIn and quality properties in place.
     *
     * @param item The item to update
     */
    fun updateItem(item: Item)

    companion object {
        /**
         * Maximum quality value for items (except legendary items like Sulfuras).
         */
        const val MAX_QUALITY = 50

        /**
         * Factory method to determine the appropriate update strategy based on item name.
         *
         * @param itemName The name of the item to create a strategy for
         * @return The appropriate [ItemUpdateStrategy] for the given item
         */
        fun from(itemName: String): ItemUpdateStrategy = when {
            itemName == "Aged Brie" -> AgedBrieStrategy
            itemName == "Sulfuras, Hand of Ragnaros" -> SulfurasStrategy
            itemName == "Backstage passes to a TAFKAL80ETC concert" -> BackstagePassStrategy
            itemName.startsWith("Conjured") -> ConjuredItemStrategy
            else -> NormalItemStrategy
        }
    }
}