package com.mobiquity.model;

import com.mobiquity.exception.APIException;
import org.junit.Test;

import static org.junit.Assert.*;

public class PackItemBuilderTest {

    @Test
    public void testBuild_whenValidValuesPassed_shouldCreatePackItemObject() throws APIException {
        PackItem expected = PackItem.builder().index(1).weight(10D).cost(10D).build();
        assertNotNull(expected);
    }

    @Test(expected = APIException.class)
    public void testBuild_whenIndexIsNull_shouldThrowAPIException() throws APIException {
        PackItem.builder().index(null).weight(10D).cost(10D).build();
    }

    @Test(expected = APIException.class)
    public void testBuild_whenWeightIsNull_shouldThrowAPIException() throws APIException {
        PackItem.builder().index(1).weight(null).cost(10D).build();
    }

    @Test(expected = APIException.class)
    public void testBuild_whenWeightIsZero_shouldThrowAPIException() throws APIException {
        PackItem.builder().index(1).weight(0D).cost(10D).build();
    }

    @Test(expected = APIException.class)
    public void testBuild_whenWeightIsMoreThan100_shouldThrowAPIException() throws APIException {
        PackItem.builder().index(1).weight(150D).cost(10D).build();
    }

    @Test(expected = APIException.class)
    public void testBuild_whenCostIsNull_shouldThrowAPIException() throws APIException {
        PackItem.builder().index(1).weight(10D).cost(null).build();
    }

    @Test(expected = APIException.class)
    public void testBuild_whenCostIsZero_shouldThrowAPIException() throws APIException {
        PackItem.builder().index(1).weight(10D).cost(0D).build();
    }

    @Test(expected = APIException.class)
    public void testBuild_whenCostIsMoreThan100_shouldThrowAPIException() throws APIException {
        PackItem.builder().index(1).weight(10D).cost(150D).build();
    }
}