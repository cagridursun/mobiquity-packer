package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.PackItem;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PackerCalculatorTest {

    @Test
    public void testCalculate_whenAnItemIsMostValued_shouldReturnBestValuePackedItem() throws APIException {
        List<PackItem> items = Arrays.asList(
                generatePackItem(1, 53.38, 45D),
                generatePackItem(2, 88.62, 98D),
                generatePackItem(3, 78.48, 3D),
                generatePackItem(4, 72.30, 76D),
                generatePackItem(5, 30.18, 9D),
                generatePackItem(6, 46.34, 48D)
        );
        Collections.sort(items);
        List<PackItem> result = PackerCalculator.calculate(items, 81D);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getIndex().intValue(), 4);
    }

    @Test
    public void testCalculate_whenNotExistAnItemHasLessThanWeightLimit_shouldReturnAnEmptyList() throws APIException {
        List<PackItem> items = Collections.singletonList(
                generatePackItem(1, 15.3, 34D)
        );
        List<PackItem> result = PackerCalculator.calculate(items, 8D);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void testCalculate_whenTwoItemHaveMostValue_shouldReturnBestTwoValueItems() throws APIException {
        List<PackItem> items = Arrays.asList(
                generatePackItem(1, 85.31, 29D),
                generatePackItem(2, 14.55, 74D),
                generatePackItem(3, 3.98, 16D),
                generatePackItem(4, 26.24, 55D),
                generatePackItem(5, 63.69, 52D),
                generatePackItem(6, 76.25, 75D),
                generatePackItem(7, 60.02, 74D),
                generatePackItem(8, 93.18, 35D),
                generatePackItem(9, 89.95, 78D)
        );
        Collections.sort(items);
        List<PackItem> result = PackerCalculator.calculate(items, 75D);
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(1).getIndex().intValue(), 2);
        Assert.assertEquals(result.get(0).getIndex().intValue(), 7);
    }

    @Test
    public void testCalculate_whenTwoItemHaveMostValueLessWeight_shouldReturnBestTwoValueAndWeightItems() throws APIException {
        List<PackItem> items = Arrays.asList(
                generatePackItem(1, 90.72, 13D),
                generatePackItem(2, 33.80, 40D),
                generatePackItem(3, 43.15, 10D),
                generatePackItem(4, 37.97, 16D),
                generatePackItem(5, 46.81, 36D),
                generatePackItem(6, 48.77, 79D),
                generatePackItem(7, 81.80, 45D),
                generatePackItem(8, 19.36, 79D),
                generatePackItem(9, 6.76, 64D)

        );
        Collections.sort(items);
        List<PackItem> result = PackerCalculator.calculate(items, 56D);
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0).getIndex().intValue(), 8);
        Assert.assertEquals(result.get(1).getIndex().intValue(), 9);
    }

    @Test
    public void testCalculate_whenItemListDoesNotHaveItem_shouldReturnEmptyResult() {
        List<PackItem> items = Collections.emptyList();
        List<PackItem> result = PackerCalculator.calculate(items, 56D);
        Assert.assertEquals(result.size(), 0);
    }

    private PackItem generatePackItem(Integer index, Double weight, Double cost) throws APIException {
        return PackItem.builder().index(index).weight(weight).cost(cost).build();
    }
}