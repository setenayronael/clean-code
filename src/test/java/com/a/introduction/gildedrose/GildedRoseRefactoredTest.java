package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseRefactoredTest {

    private static final int MAX_QUALITY = 50;
    private static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final int DEFAULT_INITIAL_QUALITY = 3;
    private static final int DEFAULT_UNEXPIRED_SELL_IN = 15;
    private static final int DEFAULT_EXPIRED_SELL_IN = -1;
    private static final int UNEXPIRED_SELL_IN_BETWEEN_5_AND_10 = 7;
    private static final int UNEXPIRED_SELL_IN_LESS_THAN_5 = 4;

    @Test
    void unexpiredDefaultItem_qualityDecreasesBy1() {

        GildedRose app = initGildedRoseWithOneItem(
                DEFAULT_ITEM,
                DEFAULT_UNEXPIRED_SELL_IN,
                DEFAULT_INITIAL_QUALITY);

        app.updateQuality();

        Item expected = new Item(
                DEFAULT_ITEM,
                DEFAULT_UNEXPIRED_SELL_IN - 1,
                DEFAULT_INITIAL_QUALITY - 1);
        assertItem(expected, app.items[0]);
    }

    @Test
    void expiredDefaultItem_qualityDecreasesBy2() {

        GildedRose app = initGildedRoseWithOneItem(
                DEFAULT_ITEM,
                DEFAULT_EXPIRED_SELL_IN,
                DEFAULT_INITIAL_QUALITY);

        app.updateQuality();

        Item expected = new Item(
                DEFAULT_ITEM,
                DEFAULT_EXPIRED_SELL_IN - 1,
                DEFAULT_INITIAL_QUALITY - 2);
        assertItem(expected, app.items[0]);
    }

    @Test
    void unexpiredAgedBrie_qualityIncreasesBy1() {

        GildedRose app = initGildedRoseWithOneItem(
                AGED_BRIE,
                DEFAULT_UNEXPIRED_SELL_IN,
                DEFAULT_INITIAL_QUALITY);

        app.updateQuality();

        Item expected = new Item(
                AGED_BRIE,
                DEFAULT_UNEXPIRED_SELL_IN - 1,
                DEFAULT_INITIAL_QUALITY + 1);
        assertItem(expected, app.items[0]);
    }

    @Test
    void expiredAgedBrie_qualityIncreasesBy2() {

        GildedRose app = initGildedRoseWithOneItem(
                AGED_BRIE,
                DEFAULT_EXPIRED_SELL_IN,
                DEFAULT_INITIAL_QUALITY);

        app.updateQuality();

        Item expected = new Item(
                AGED_BRIE,
                DEFAULT_EXPIRED_SELL_IN - 1,
                DEFAULT_INITIAL_QUALITY + 2);
        assertItem(expected, app.items[0]);
    }

    @Test
    void unexpiredAgedBrie_qualityFixAtMaxValue() {

        GildedRose app = initGildedRoseWithOneItem(
                AGED_BRIE,
                DEFAULT_UNEXPIRED_SELL_IN,
                MAX_QUALITY);

        app.updateQuality();

        Item expected = new Item(
                AGED_BRIE,
                DEFAULT_UNEXPIRED_SELL_IN - 1,
                MAX_QUALITY);
        assertItem(expected, app.items[0]);
    }

    @Test
    void unexpiredBackstagePasses_moreThan10Days_qualityIncreasesBy1() {

        GildedRose app = initGildedRoseWithOneItem(
                BACKSTAGE_PASSES,
                DEFAULT_UNEXPIRED_SELL_IN,
                DEFAULT_INITIAL_QUALITY);

        app.updateQuality();

        Item expected = new Item(
                BACKSTAGE_PASSES,
                DEFAULT_UNEXPIRED_SELL_IN - 1,
                DEFAULT_INITIAL_QUALITY + 1);
        assertItem(expected, app.items[0]);
    }

    @Test
    void unexpiredBackstagePasses_between5and10Days_qualityIncreasesBy2() {

        GildedRose app = initGildedRoseWithOneItem(
                BACKSTAGE_PASSES,
                UNEXPIRED_SELL_IN_BETWEEN_5_AND_10,
                DEFAULT_INITIAL_QUALITY);

        app.updateQuality();

        Item expected = new Item(
                BACKSTAGE_PASSES,
                UNEXPIRED_SELL_IN_BETWEEN_5_AND_10 - 1,
                DEFAULT_INITIAL_QUALITY + 2);
        assertItem(expected, app.items[0]);
    }

    @Test
    void unexpiredBackstagePasses_lessThan5Days_qualityIncreasesBy3() {

        GildedRose app = initGildedRoseWithOneItem(
                BACKSTAGE_PASSES,
                UNEXPIRED_SELL_IN_LESS_THAN_5,
                DEFAULT_INITIAL_QUALITY);

        app.updateQuality();

        Item expected = new Item(
                BACKSTAGE_PASSES,
                UNEXPIRED_SELL_IN_LESS_THAN_5 - 1,
                DEFAULT_INITIAL_QUALITY + 3);
        assertItem(expected, app.items[0]);
    }

    private GildedRose initGildedRoseWithOneItem(String itemType, int sellIn, int quality) {
        Item item = new Item(itemType, sellIn, quality);
        Item[] items = new Item[]{item};
        return new GildedRose(items);
    }

    private void assertItem(Item expectedItem, Item firstItem) {
        assertEquals(expectedItem.name, firstItem.name);
        assertEquals(expectedItem.sellIn, firstItem.sellIn);
        assertEquals(expectedItem.quality, firstItem.quality);
    }

}