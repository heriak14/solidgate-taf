package com.solidgate.core.testdata.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.IOException;
import java.util.List;

public class JsonReader<T> implements FileReader<T> {

    private static final String JSON_READ_ERROR = "Failed to read JSON data from '%s' file.";
    private final ObjectMapper objectMapper;
    private final String filePath;
    private final Class<T> type;

    public JsonReader(String filePath, Class<T> type) {
        this.objectMapper = new ObjectMapper();
        this.filePath = filePath;
        this.type = type;
    }

    @Override
    public T readOne() {
        try {
            return objectMapper.readValue(ClassLoader.getSystemClassLoader().getResourceAsStream(filePath), type);
        } catch (IOException e) {
            throw new RuntimeException(JSON_READ_ERROR.formatted(filePath), e);
        }
    }

    @Override
    public List<T> readAll() {
        try {
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
            return objectMapper.readValue(ClassLoader.getSystemClassLoader().getResourceAsStream(filePath), collectionType);
        } catch (IOException e) {
            throw new RuntimeException(JSON_READ_ERROR.formatted(filePath), e);
        }
    }
}
