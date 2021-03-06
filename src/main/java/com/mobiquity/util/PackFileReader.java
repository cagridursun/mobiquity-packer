package com.mobiquity.util;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.mobiquity.util.Constants.NEW_LINE;

public class PackFileReader {

    public static String readPackFile(String fileName) throws APIException {

        // The class loader that loaded the class
        ClassLoader classLoader = PackFileReader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new APIException("File could not found! " + fileName, new IllegalArgumentException());
        } else {
            return getInputStream(inputStream);
        }

    }

    private static String getInputStream(InputStream inputStream) throws APIException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStreamReader streamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(NEW_LINE);
            }

        } catch (IOException e) {
            throw new APIException(e.getMessage(), e);
        }

        if (stringBuilder.toString().isBlank()) {
            throw new APIException("Input file must not be empty");
        }
        return stringBuilder.toString().trim();

    }
}
