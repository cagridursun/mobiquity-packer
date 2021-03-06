package com.mobiquity;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.PackItemSet;
import com.mobiquity.packer.PackerTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TestHelper {

    public static final String INPUT_FILE = "example_input";
    public static final String OUTPUT_FILE = "example_output";

    public static String readLinesFromResource(String file) {
        ClassLoader  loader = TestHelper.class.getClassLoader();
        String lines;
        BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(loader.getResourceAsStream(file))
        ));
        lines = br.lines().parallel().map(Object::toString).collect(Collectors.joining("\n"));
        return lines;
    }


}
