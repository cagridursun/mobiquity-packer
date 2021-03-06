package com.mobiquity.model;

import com.mobiquity.exception.APIException;

import java.util.Objects;

import static com.mobiquity.util.Constants.*;

public class PackItem implements Comparable<PackItem>{

    private final Integer index;
    private final Double weight;
    private final Double cost;

    /*For immutability, the builder design pattern used*/
    public static PackItemBuilder builder(){
        return new PackItemBuilder();
    }

    private PackItem(PackItemBuilder packItemBuilder){
        this.index = packItemBuilder.index;
        this.weight = packItemBuilder.weight;
        this.cost = packItemBuilder.cost;
    }

    public Integer getIndex() {
        return index;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "PackItem{" +
                "index=" + index +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackItem packItem = (PackItem) o;
        return Objects.equals(index, packItem.index) && Objects.equals(weight, packItem.weight) && Objects.equals(cost, packItem.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, weight, cost);
    }

    @Override
    public int compareTo(PackItem o) {
        return this.getWeight().compareTo(o.getWeight());
    }

    public static class PackItemBuilder{

        private Integer index;
        private Double weight;
        private Double cost;

        private PackItemBuilder() {
        }

        public PackItemBuilder index(Integer index){
            this.index = index;
            return this;
        }

        public PackItemBuilder weight(Double weight){
            this.weight = weight;
            return this;
        }

        public PackItemBuilder cost(Double cost){
            this.cost = cost;
            return this;
        }

        public PackItem build() throws APIException {
            PackItem packItem = new PackItem(this);
            validatePackItemSet(packItem);
            return packItem;
        }

        /*Validations are done while the object creating*/
        private void validatePackItemSet(PackItem packItem) throws APIException {
            if (packItem.getIndex() == null){
                throw new APIException("Pack index must not be null");
            }
            if (packItem.getWeight() == null){
                throw new APIException("Pack weight must not be null " + packItem);
            }
            if (packItem.getWeight() <= MIN_WEIGHT){
                throw new APIException("Pack weight must be greater than zero " + packItem);
            }
            if (packItem.getWeight() > MAX_WEIGHT){
                throw new APIException("Pack weight must not be greater than 100 " + packItem);
            }
            if (packItem.getCost() == null){
                throw new APIException("Pack cost must not be null " + packItem);
            }
            if (packItem.getCost() <= MIN_COST){
                throw new APIException("Pack cost must be greater than zero " + packItem);
            }
            if (packItem.getCost() > MAX_COST){
                throw new APIException("Pack cost must not be greater than 100 " + packItem);
            }
        }

    }
}
