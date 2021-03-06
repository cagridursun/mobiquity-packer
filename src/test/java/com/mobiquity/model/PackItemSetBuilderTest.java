package com.mobiquity.model;

import com.mobiquity.exception.APIException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PackItemSetBuilderTest {

    @Test
    public void testBuild_whenValidValuesPassed_shouldCreatePackItemSetObject() throws APIException {
        List<PackItem> packItemList = Collections.singletonList(PackItem.builder().index(1).weight(10D).cost(10D).build());
        PackItemSet expected = PackItemSet.builder().packItems(packItemList).weightLimit(10D).build();

        assertNotNull(expected);
    }

    @Test(expected = APIException.class)
    public void testBuild_whenWeightLimitIsNull_shouldThrowAPIException() throws APIException {
        List<PackItem> packItemList = Collections.singletonList(PackItem.builder().index(1).weight(10D).cost(10D).build());
        PackItemSet.builder().packItems(packItemList).weightLimit(null).build();
    }

    @Test(expected = APIException.class)
    public void testBuild_whenWeightLimitIsZero_shouldThrowAPIException() throws APIException {
        List<PackItem> packItemList = Collections.singletonList(PackItem.builder().index(1).weight(10D).cost(10D).build());
        PackItemSet.builder().packItems(packItemList).weightLimit(0D).build();
    }

    @Test(expected = APIException.class)
    public void testBuild_whenWeightLimitMoreThan100_shouldThrowAPIException() throws APIException {
        List<PackItem> packItemList = Collections.singletonList(PackItem.builder().index(1).weight(10D).cost(10D).build());
        PackItemSet.builder().packItems(packItemList).weightLimit(150D).build();
    }

    @Test(expected = APIException.class)
    public void testBuild_whenPackItemListIsEmpty_shouldThrowAPIException() throws APIException {
        List<PackItem> packItemList = Collections.emptyList();
        PackItemSet.builder().packItems(packItemList).weightLimit(10D).build();
    }

    @Test(expected = APIException.class)
    public void testBuild_whenPackItemListHasMoreThan15Item_shouldThrowAPIException() throws APIException {
        List<PackItem> packItemList = generateOverLimitPackItem();
        PackItemSet.builder().packItems(packItemList).weightLimit(10D).build();
    }


    private static List<PackItem> generateOverLimitPackItem() throws APIException{
        return Arrays.asList(
                PackItem.builder().index(1).weight(10D).cost(10D).build(),
                PackItem.builder().index(2).weight(10D).cost(10D).build(),
                PackItem.builder().index(3).weight(10D).cost(10D).build(),
                PackItem.builder().index(4).weight(10D).cost(10D).build(),
                PackItem.builder().index(5).weight(10D).cost(10D).build(),
                PackItem.builder().index(6).weight(10D).cost(10D).build(),
                PackItem.builder().index(7).weight(10D).cost(10D).build(),
                PackItem.builder().index(8).weight(10D).cost(10D).build(),
                PackItem.builder().index(9).weight(10D).cost(10D).build(),
                PackItem.builder().index(10).weight(10D).cost(10D).build(),
                PackItem.builder().index(11).weight(10D).cost(10D).build(),
                PackItem.builder().index(12).weight(10D).cost(10D).build(),
                PackItem.builder().index(13).weight(10D).cost(10D).build(),
                PackItem.builder().index(14).weight(10D).cost(10D).build(),
                PackItem.builder().index(15).weight(10D).cost(10D).build(),
                PackItem.builder().index(16).weight(10D).cost(10D).build());
    }
}