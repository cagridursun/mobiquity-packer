package com.mobiquity.model;

import java.util.List;
import java.util.stream.Collectors;

import static com.mobiquity.util.Constants.*;

public class PackedItemResult {

    private final List<PackItem> packItems;

    public PackedItemResult(List<PackItem> packItems) {
        this.packItems = packItems;
    }

    public List<PackItem> getPackItems() {
        return packItems;
    }

    @Override
    public String toString() {
        return this.packItems.isEmpty() ? DASH : this.packItems.stream()
                                                                .map(p -> p.getIndex().toString())
                                                                .collect(Collectors.joining(PACK_VALUE_DELIMITER));
    }

}
