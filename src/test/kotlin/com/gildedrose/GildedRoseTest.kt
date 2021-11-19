package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun generalItemShouldDegrade() {
        val item = Item("+5 Dexterity Vest", 10, 20)
        updateItem(item)
        assertEquals(9, item.sellIn)
        assertEquals(19, item.quality)
    }

    @Test
    fun generalItemShouldDegradeTwiceAsFastWhenPassedSellByDate() {
        val item = Item("+5 Dexterity Vest", -1, 20)
        updateItem(item)
        assertEquals(-2, item.sellIn)
        assertEquals(18, item.quality)
    }

    @Test
    fun allItemQualityShouldNeverBeNegative() {
        val item1 = Item("+5 Dexterity Vest", 10, 0)
        val item2 = Item("+5 Dexterity Vest", -1, 1)
        updateItem(item1)
        updateItem(item2)
        assertEquals(0, item1.quality)
        assertEquals(9, item1.sellIn)
        assertEquals(0, item2.quality)
        assertEquals(-2, item2.sellIn)
    }

    @Test
    fun ageBrie() {
        val item = Item("Aged Brie", 2, 0, ItemType.AGE_BRIE)
        updateItem(item)
        assertEquals(1, item.quality)
        assertEquals(1, item.sellIn)
    }

    @Test
    fun qualityOfItemShouldNeverBeOver50() {
        val item = Item("Age Brie", 10, 50, ItemType.AGE_BRIE)
        updateItem(item)
        assertEquals(50, item.quality)
        assertEquals(9, item.sellIn)
    }

    @Test
    fun sulfuraQualityAndSellInDoesNotChange() {
        val item1 = Item("Sulfuras, Hand of Ragnaros", 0, 80, ItemType.SULFURAS) //
        val item2 = Item("Sulfuras, Hand of Ragnaros", -1, 80, ItemType.SULFURAS)
        updateItem(item1)
        updateItem(item2)
        assertEquals(80, item1.quality)
        assertEquals(80, item2.quality)
        assertEquals(0, item1.sellIn)
        assertEquals(-1, item2.sellIn)
    }

    @Test
    fun backstagePass() {
        val item1 = Item("Backstage passes to a TAFKAL80ETC concert", 15, 20, ItemType.BACKSTAGE_PASS)
        val item2 = Item("Backstage passes to a TAFKAL80ETC concert", 10, 45, ItemType.BACKSTAGE_PASS)
        val item3 = Item("Backstage passes to a TAFKAL80ETC concert", 5, 45, ItemType.BACKSTAGE_PASS)
        val item4 = Item("Backstage passes to a TAFKAL80ETC concert", 5, 49, ItemType.BACKSTAGE_PASS)
        updateItem(item1)
        updateItem(item2)
        updateItem(item3)
        updateItem(item4)
        assertEquals(21, item1.quality)
        assertEquals(14, item1.sellIn)
        assertEquals(47, item2.quality)
        assertEquals(9, item2.sellIn)
        assertEquals(48, item3.quality)
        assertEquals(4, item3.sellIn)
        assertEquals(50, item4.quality)
        assertEquals(4, item4.sellIn)
    }

    @Test
    fun conjuredItemShouldDegradeTwice() {
        val item1 = Item("Conjured Mana Cake", 3, 6, ItemType.CONJURED)
        val item2 = Item("Conjured Mana Cake", -1, 6, ItemType.CONJURED)
        updateItem(item1)
        updateItem(item2)

        assertEquals(2, item1.sellIn)
        assertEquals(4, item1.quality)

        assertEquals(-2, item2.sellIn)
        assertEquals(2, item2.quality)
    }
}


