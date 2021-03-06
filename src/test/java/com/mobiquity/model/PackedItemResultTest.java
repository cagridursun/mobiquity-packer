package com.mobiquity.model;

import com.mobiquity.exception.APIException;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PackedItemResultTest {

    @Test
    public void testToString_whenListIsNotEmpty_shouldCreateStringWithCommaDelimiter() throws APIException {
        List<PackItem> packItemList = Arrays.asList(
                PackItem.builder().index(1).weight(10D).cost(10D).build(),
                PackItem.builder().index(2).weight(10D).cost(10D).build());
        PackedItemResult packedItemResult = new PackedItemResult(packItemList);

        String result = packedItemResult.toString();

        assertEquals(result, "1,2");
    }

    @Test
    public void testToString_whenListIsEmpty_shouldCreateDashString() {
        List<PackItem> packItemList = Collections.emptyList();
        PackedItemResult packedItemResult = new PackedItemResult(packItemList);

        String result = packedItemResult.toString();

        assertEquals(result, "-");
    }

}