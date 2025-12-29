package com.gildedrose

open class Item(
    var name: String,
    var sellIn: Int,
    var quality: Int,
) {
    override fun toString(): String = "$name, $sellIn, $quality"
}

/*
According to the requirements, Item class should not be updated, but its implementation could be much simpler, like below.
name property could also be an enum

data class Item(
    val name: String,
    val sellIn: Int,
    val quality: Int,
)
*/

