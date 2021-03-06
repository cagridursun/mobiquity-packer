package com.mobiquity.util;

import com.mobiquity.TestHelper;
import com.mobiquity.exception.APIException;
import com.mobiquity.model.PackItem;
import com.mobiquity.model.PackItemSet;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PackItemParserTest extends TestHelper {

    @Test
    public void testParsePackageString_whenPackItemSetHasValidValues_shouldReturnPackItemSetList() throws APIException {
        String packagesString = readLinesFromResource("simple_input_file");
        List<PackItemSet> expected = generatePackItemSet();

        List<PackItemSet> result = PackItemParser.parsePackageString(packagesString);

        assertEquals(expected, result);
    }

    @Test
    public void testParsePackageString_whenItemsUnorderedWeight_shouldReturnOrderedPackItemSetList() throws APIException {
        String packagesString = readLinesFromResource("simple_unordered_input_file");
        List<PackItemSet> expected = generateOrderedPackItemSet();

        List<PackItemSet> result = PackItemParser.parsePackageString(packagesString);

        assertEquals(expected, result);
    }

    @Test
    public void testParsePackageString_whenValuesHasNonDigitChars_shouldReturnPackItemSetList() throws APIException {
        String packagesString = readLinesFromResource("simple_non_digit_input_file");
        List<PackItemSet> expected = generatePackItemSet();

        List<PackItemSet> result = PackItemParser.parsePackageString(packagesString);

        assertEquals(expected, result);
    }


    private List<PackItemSet> generatePackItemSet() throws APIException {
        return Collections.singletonList(
                PackItemSet.builder()
                        .weightLimit(81D)
                        .packItems(
                                Arrays.asList(
                                        PackItem.builder().index(1).weight(53.38).cost(45D).build(),
                                        PackItem.builder().index(2).weight(88.62).cost(98D).build()))
                        .build()
        );
    }

    private List<PackItemSet> generateOrderedPackItemSet() throws APIException {
        return Collections.singletonList(
                PackItemSet.builder()
                        .weightLimit(81D)
                        .packItems(
                                Arrays.asList(
                                        PackItem.builder().index(2).weight(53.38).cost(45D).build(),
                                        PackItem.builder().index(1).weight(88.62).cost(98D).build()))
                        .build()
        );
    }
}