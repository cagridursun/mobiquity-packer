package com.mobiquity.packer;

import com.mobiquity.model.PackItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PackerCalculator {

    protected static List<PackItem> calculate(final List<PackItem> items, final Double capacity) {
        final int itemLength = items.size();
        final int intCapacity = (int) Math.round(capacity);
        // we use a matrix to store the max value at each n-th item
        final List<List<Double>> itemMatrix = new ArrayList<>();
        //initialize the first row
        itemMatrix.add(new ArrayList<>() {{
            IntStream.range(0, intCapacity + 1).forEach(i -> add(0.0));
        }});

        /* traverse the item list to be able to find best value item */
        IntStream.range(1, itemLength + 1)
                .forEach(i -> itemMatrix.add(new ArrayList<>() {{
                    IntStream.range(0, intCapacity + 1).forEach(j -> {

                        PackItem visitedItem = items.get(i - 1);
                        List<Double> previousMatrixRow = itemMatrix.get(i - 1);

                        if (visitedItem.getWeight() > j) {
                            add(previousMatrixRow.get(j));
                        } else {
                            int previousIndex = j - (int) Math.round(visitedItem.getWeight());
                            double currentCost = previousMatrixRow.get(previousIndex) + visitedItem.getCost();
                            double maxCost = Math.max(previousMatrixRow.get(j), currentCost);

                            add(maxCost);
                        }
                    });
                }}));

        Double maxCost = itemMatrix.get(itemLength).get(intCapacity);
        Double weight = capacity;
        List<PackItem> itemsSolution = new ArrayList<>();

        /*Finding the best packed items*/
        for (int i = itemLength; i > 0 && maxCost > 0; i--) {
            if (!maxCost.equals(itemMatrix.get(i - 1).get((int) Math.round(weight)))) {
                PackItem currentItem = items.get(i - 1);
                itemsSolution.add(currentItem);
                // we remove items value and weight
                maxCost -= currentItem.getCost();
                weight -= currentItem.getWeight();
            }
        }

        return itemsSolution;
    }
}
