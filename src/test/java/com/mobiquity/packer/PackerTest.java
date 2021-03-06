package com.mobiquity.packer;

import com.mobiquity.TestHelper;
import com.mobiquity.exception.APIException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PackerTest extends TestHelper {

    private String outputLines;

    @Before
    public void setup() {
        outputLines = readLinesFromResource(OUTPUT_FILE);
    }


    @Test
    public void testPack_whenInputFileIsExist_shouldReturnCalculatedItemListAsString() throws APIException {
        String r = Packer.pack(INPUT_FILE);
        Assert.assertEquals(outputLines, r);
    }

    @Test(expected = APIException.class)
    public void testPack_whenInputFileDoesNotExist_shouldThrowAPIException() throws APIException {
        Packer.pack("not_exist_input_file");
    }

    @Test(expected = APIException.class)
    public void testPack_whenFileHasMoreThan100WeightLimit_shouldThrowAPIException() throws APIException {
        Packer.pack("weight_limit_exceed_input");
    }

    @Test(expected = APIException.class)
    public void testPack_whenFileHasAnItemMoreThan100Weight_shouldThrowAPIException() throws APIException {
        Packer.pack("item_weight_limit_exceed_input");
    }

    @Test(expected = APIException.class)
    public void testPack_whenFileHasMoreThan15ItemsForASet_shouldThrowAPIException() throws APIException {
        Packer.pack("item_size_limit_exceed_input");
    }

}