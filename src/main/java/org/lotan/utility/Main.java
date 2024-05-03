package org.lotan.utility;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lotan.utility.convert.param.params.JsonParams;
import org.lotan.utility.convert.param.params.factory.JsonParamsFactory;
import org.lotan.utility.convert.reader.JsonReader;
import org.lotan.utility.convert.reader.referencevalue.ReferenceValue;
import org.lotan.utility.convert.writer.JsonWriter;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        File request = new File("./src/main/resources/request.json");
        File target = new File("./src/main/resources/target.json");
        File response = new File("./src/main/resources/response.json");

        JsonNode reqNode = objectMapper.readTree(request);
        JsonNode targetNode = objectMapper.readTree(target);
        JsonNode resNode = objectMapper.readTree(response);

        JsonParamsFactory jsonParamsFactory = new JsonParamsFactory();

        JsonParams requestParams = jsonParamsFactory.generateJsonParams(reqNode.deepCopy());
        JsonParams responseParams = jsonParamsFactory.generateJsonParams(resNode.deepCopy());

        JsonReader jsonReader = new JsonReader(requestParams);
        JsonWriter jsonWriter = new JsonWriter(responseParams);


        ReferenceValue read = jsonReader.read(targetNode);
        ReferenceValue flattened = read.flatten();
        JsonNode write = jsonWriter.write(flattened);
//
//        Lotion lotion = objectMapper.treeToValue(write, Lotion.class);

        System.out.println();
        System.out.println();
    }
}