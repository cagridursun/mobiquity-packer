package com.mobiquity.model;

import com.mobiquity.exception.APIException;

import java.util.List;
import java.util.Objects;

import static com.mobiquity.util.Constants.*;

public class PackItemSet {

    private final Double weightLimit;
    private final List<PackItem> packItems;

    /*For immutability, the builder design pattern used*/
    public static PackItemSetBuilder builder(){
        return new PackItemSetBuilder();
    }

    private PackItemSet(PackItemSetBuilder packItemSetBuilder){
        this.weightLimit = packItemSetBuilder.weightLimit;
        this.packItems = packItemSetBuilder.packItems;
    }

    public Double getWeightLimit() {
        return weightLimit;
    }

    public List<PackItem> getPackItems() {
        return packItems;
    }

    @Override
    public String toString() {
        return "PackItemSet{" +
                "weightLimit=" + weightLimit +
                ", packItems=" + packItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackItemSet that = (PackItemSet) o;
        return Objects.equals(weightLimit, that.weightLimit) && Objects.equals(packItems, that.packItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weightLimit, packItems);
    }

    public static class PackItemSetBuilder{
        private Double weightLimit;
        private List<PackItem> packItems;

        private PackItemSetBuilder() {
        }

        public PackItemSetBuilder weightLimit(Double weightLimit){
            this.weightLimit = weightLimit;
            return this;
        }

        public PackItemSetBuilder packItems(List<PackItem> packItems){
            this.packItems = packItems;
            return this;
        }

        public PackItemSet build() throws APIException {
            PackItemSet packItemSet = new PackItemSet(this);
            validatePackItemSet(packItemSet);
            return packItemSet;
        }

        /*Validations are done while the object creating*/
        private void validatePackItemSet(PackItemSet packItemSet) throws APIException {
            if (packItemSet.getWeightLimit() == null){
                throw new APIException("Package weight limit must not be null");
            }
            if ( packItemSet.getWeightLimit() <= MIN_WEIGHT){
                throw new APIException("Package weight limit must be greater than zero");
            }
            if (packItemSet.getWeightLimit() > MAX_WEIGHT){
                throw new APIException("Package weight limit must not be greater than 100");
            }
            if (packItemSet.getPackItems().isEmpty()){
                throw new APIException("Package item size must not be empty");
            }
            if (packItemSet.getPackItems().size() > MAX_ITEM_SIZE){
                throw new APIException("Package item size must not be greater than 15");
            }
        }

    }

}
