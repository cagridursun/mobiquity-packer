package com.mobiquity.util;

import com.mobiquity.TestHelper;
import com.mobiquity.exception.APIException;
import org.junit.Assert;
import org.junit.Test;

public class PackFileReaderTest extends TestHelper {

    @Test
    public void testGetFileFromResourceAsStream_whenFileIsValid_shouldReturnInputFileString() throws APIException {
        String fileContext = PackFileReader.readPackFile(INPUT_FILE);
        Assert.assertEquals(TestHelper.readLinesFromResource(INPUT_FILE), fileContext);
    }

    @Test(expected = APIException.class)
    public void testGetFileFromResourceAsStream_whenFileDoesNotExist_shouldThrowAPIException() throws APIException {
        PackFileReader.readPackFile("not_exist_input_file");
    }

    @Test(expected = APIException.class)
    public void testGetFileFromResourceAsStream_whenFileIsEmpty_shouldThrowAPIException() throws APIException {
        PackFileReader.readPackFile("empty_input_file");
    }

}