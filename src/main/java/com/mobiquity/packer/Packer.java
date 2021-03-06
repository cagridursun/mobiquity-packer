package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.PackItem;
import com.mobiquity.model.PackItemSet;
import com.mobiquity.model.PackedItemResult;
import com.mobiquity.util.PackItemParser;
import com.mobiquity.util.PackFileReader;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.mobiquity.util.Constants.*;

public class Packer {

    private Packer() {
    }

    public static String pack(final String filePath) throws APIException {
        final String packagesString = PackFileReader.readPackFile(filePath);
        final List<PackItemSet> packItemSetList = PackItemParser.parsePackageString(packagesString);

        final List<PackedItemResult> result = getPackedItemResult(packItemSetList);

        return preparePackedResult(result);
    }

    private static List<PackedItemResult> getPackedItemResult(List<PackItemSet> packItemSetList) {
        return packItemSetList.stream()
                .map(Packer::calculatePackedItems)
                .collect(Collectors.toList());
    }

    private static PackedItemResult calculatePackedItems(PackItemSet packItemSet){
        return new PackedItemResult(PackerCalculator.calculate(packItemSet.getPackItems(),
                packItemSet.getWeightLimit()));
    }

    private static String preparePackedResult(List<PackedItemResult> packedItems) {
        /* The result list sorting by index */
        packedItems.forEach(packedItemResult -> packedItemResult.getPackItems().sort(Comparator.comparing(PackItem::getIndex)));
        return packedItems.stream()
                .map(PackedItemResult::toString)
                .collect(Collectors.joining(NEW_LINE));
    }

}
