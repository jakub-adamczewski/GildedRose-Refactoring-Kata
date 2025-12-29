package com.gildedrose

open class Item(
    var name: String,
    var sellIn: Int,
    var quality: Int,
) {
    override fun toString(): String = "$name, $sellIn, $quality"
}

/*
According to the requirements, the Item class should not be modified, but its implementation could be much simpler.

The `name` property could be an enum, or Item could have a `type` enum property to pick strategies
for each item in a cleaner way than relying on String-based `name` matching.

Note: The properties must remain mutable (var) because the GildedRose logic updates sellIn and quality.
If immutability was possible, this could be simplified to:

data class Item(
    val name: String,
    val sellIn: Int,
    val quality: Int,
)
*/

