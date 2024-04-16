package org.lotan.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lotan.utility.convert.ValueConvert;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        JsonReader jsonReader = new JsonReader();
        JsonWriter jsonWriter = new JsonWriter();

        File ref = new File("./src/main/resources/ref.json");
        File base = new File("./src/main/resources/base.json");
        File target = new File("./src/main/resources/target.json");

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode refNode = objectMapper.readTree(ref);
        JsonNode baseNode = objectMapper.readTree(base);
        JsonNode targetNode = objectMapper.readTree(target);

        Map<String, ValueConvert> read = jsonReader.read(baseNode, refNode);
        JsonNode write = jsonWriter.write(read, targetNode);

        System.out.println();
    }
}