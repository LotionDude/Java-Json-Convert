package org.lotan.utility;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lotan.utility.convert.JsonReader;
import org.lotan.utility.convert.param.JsonParams;
import org.lotan.utility.convert.param.factory.JsonParamsFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        File ref = new File("./src/main/resources/ref.json");
        File base = new File("./src/main/resources/base.json");
        File target = new File("./src/main/resources/target.json");

        JsonNode refNode = objectMapper.readTree(ref);
        JsonNode baseNode = objectMapper.readTree(base);
        JsonNode targetNode = objectMapper.readTree(target);

        JsonParamsFactory jsonParamsFactory = new JsonParamsFactory();
        JsonParams jsonParams = jsonParamsFactory.generateJsonParams(refNode.deepCopy());

        JsonReader jsonReader = new JsonReader(jsonParams);
        JsonWriter jsonWriter = new JsonWriter();


        Map<String, ValueConvert> read = jsonReader.read(baseNode);
        JsonNode write = jsonWriter.write(read, targetNode);

        Lotion lotion = objectMapper.treeToValue(write, Lotion.class);

        System.out.println();

        System.out.println();
    }
}