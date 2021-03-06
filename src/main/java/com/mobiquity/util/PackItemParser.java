package com.mobiquity.util;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.PackItem;
import com.mobiquity.model.PackItemSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.mobiquity.util.Constants.*;
import static com.mobiquity.util.Constants.PACK_VALUE_DELIMITER;

public class PackItemParser {

    public static List<PackItemSet> parsePackageString(String packagesString) throws APIException {
        List<PackItemSet> packs = new ArrayList<>();
        String[] lines = packagesString.split(NEW_LINE);
        for (String line : lines) {
            String[] packageSet = line.split(PACKAGE_DELIMITER);
            try {
                /*Parsing the index 1 as pack items */
                List<PackItem> packItemList = parsePackItem(packageSet[1].split(PACK_ITEM_DELIMITER));
                packs.add(PackItemSet.builder()
                        .weightLimit(Double.parseDouble(removeNonDigit(packageSet[0]))) //First value of the line is weightLimit
                        .packItems(packItemList)
                        .build());
            } catch (RuntimeException e) {
                throw new APIException(e.getMessage());
            }
        }

        return packs;
    }

    private static List<PackItem> parsePackItem(String[] packItems) {
        return Arrays.stream(packItems)
                .filter(packItem -> !packItem.isEmpty())
                .flatMap(packItem -> {
                    try {
                        return Stream.ofNullable(buildPackItem(packItem));
                    } catch (APIException e) {
                        //Flapping the stream chunks for exception handling
                        throw new RuntimeException(e.getMessage());
                    }
                })
                .sorted() //Sorting by weight value via compareTo implementation in PackItem object
                .collect(Collectors.toList());
    }

    private static PackItem buildPackItem(String packItemStr) throws APIException {
        String[] packItemValues = packItemStr.split(PACK_VALUE_DELIMITER);
        Integer index = Integer.parseInt(removeNonDigit(packItemValues[0]));
        Double weight = Double.parseDouble(removeNonDigit(packItemValues[1]));
        Double cost = Double.parseDouble(removeNonDigit(packItemValues[2]));

        return PackItem.builder()
                .index(index)
                .weight(weight)
                .cost(cost)
                .build();
    }

    private static String removeNonDigit(String value) {
        return value.replaceAll(NON_DIGIT_REGEX, EMPTY_STRING);
    }
}
