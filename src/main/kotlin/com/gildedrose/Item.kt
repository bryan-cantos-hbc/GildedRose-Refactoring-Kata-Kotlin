package com.gildedrose

data class Item(val name: String, var sellIn: Int, var quality: Int, val itemType: ItemType = ItemType.GENERAL)

enum class ItemType {
    GENERAL, BACKSTAGE_PASS, AGE_BRIE, SULFURAS, CONJURED
}