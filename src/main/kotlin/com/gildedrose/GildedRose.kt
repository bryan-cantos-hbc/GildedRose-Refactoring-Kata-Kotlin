package com.gildedrose

fun updateItem(item: Item) {
    when (item.itemType) {
        ItemType.GENERAL -> updateGeneralItem(item)
        ItemType.AGE_BRIE -> updateAgedBrieItem(item)
        ItemType.BACKSTAGE_PASS -> updateBackStagePassItem(item)
        ItemType.CONJURED -> updateConjuredItem(item)
        else -> {
        }
    }
}

fun updateConjuredItem(item: Item) {
    if (item.sellIn < 0) {
        updateQuality(item, -4)
    } else {
        updateQuality(item, -2)
    }
    item.sellIn = item.sellIn - 1
}

fun updateAgedBrieItem(item: Item) {
    updateQuality(item, 1)
    item.sellIn = item.sellIn - 1
}

fun updateBackStagePassItem(item: Item) {
    if (item.sellIn <= 5) {
        updateQuality(item, 3)
    } else if (item.sellIn <= 10) {
        updateQuality(item, 2)
    } else {
        updateQuality(item, 1)
    }

    item.sellIn = item.sellIn - 1
}

fun updateGeneralItem(item: Item) {
    if (item.sellIn < 0) {
        updateQuality(item, -2)
    } else {
        updateQuality(item, -1)
    }
    item.sellIn = item.sellIn - 1
}

fun updateQuality(item: Item, count: Int) {
    if (item.quality + count < 0) {
        item.quality = 0
    } else if (item.quality + count > 50) {
        item.quality = 50
    } else {
        item.quality = item.quality + count
    }
}

fun oldUpdateQuality(items: Array<Item>) {
    for (i in items.indices) {
        if (items[i].name != "Aged Brie" && items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
            if (items[i].quality > 0) {
                if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                    items[i].quality = items[i].quality - 1
                }
            }
        } else {
            if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1

                if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                    if (items[i].sellIn < 11) {
                        if (items[i].quality < 50) {
                            items[i].quality = items[i].quality + 1
                        }
                    }

                    if (items[i].sellIn < 6) {
                        if (items[i].quality < 50) {
                            items[i].quality = items[i].quality + 1
                        }
                    }
                }
            }
        }

        if (items[i].name != "Sulfuras, Hand of Ragnaros") {
            items[i].sellIn = items[i].sellIn - 1
        }

        if (items[i].sellIn < 0) {
            if (items[i].name != "Aged Brie") {
                if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                    if (items[i].quality > 0) {
                        if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                            items[i].quality = items[i].quality - 1
                        }
                    }
                } else {
                    items[i].quality = items[i].quality - items[i].quality
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1
                }
            }
        }
    }
}

