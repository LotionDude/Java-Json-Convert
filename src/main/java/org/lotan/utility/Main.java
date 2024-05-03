package org.lotan.utility;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lotan.utility.convert.JsonNodeConverter;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode request = objectMapper.readTree(new File("./src/main/resources/request.json"));
        JsonNode response = objectMapper.readTree(new File("./src/main/resources/response.json"));

        JsonNodeConverter jsonNodeConverter = new JsonNodeConverter(request, response, objectMapper);

        JsonNode target = objectMapper.readTree(new File("./src/main/resources/target.json"));

        Lotion lotion = jsonNodeConverter.convert(target, Lotion.class);

        System.out.println();
    }
}