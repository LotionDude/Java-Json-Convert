package org.lotan.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class FileJsonConvert<C> implements ObjectConvert<File, C> {
    private final ObjectMapper objectMapper;

    public FileJsonConvert(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public FileJsonConvert() {
        this(new ObjectMapper());
    }

    @Override
    public C convert(Class<C> conversionClass, File type1, File type2) {
        return null;
    }
}
