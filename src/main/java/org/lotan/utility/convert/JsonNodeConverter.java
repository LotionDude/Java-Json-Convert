package org.lotan.utility.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lotan.utility.convert.param.params.JsonParams;
import org.lotan.utility.convert.param.params.factory.JsonParamsFactory;
import org.lotan.utility.convert.reader.JsonReader;
import org.lotan.utility.convert.reader.referencevalue.ReferenceValue;
import org.lotan.utility.convert.writer.JsonWriter;

public class JsonNodeConverter {
    private final JsonReader jsonReader;
    private final JsonWriter jsonWriter;
    private final ObjectMapper objectMapper;

    public JsonNodeConverter(JsonNode request, JsonNode response) {
        this(request, response, new ObjectMapper());
    }

    public JsonNodeConverter(JsonNode request, JsonNode response, ObjectMapper objectMapper) {
        JsonParamsFactory jsonParamsFactory = new JsonParamsFactory();

        JsonParams requestParams = jsonParamsFactory.generateJsonParams(request.deepCopy());
        JsonParams responseParams = jsonParamsFactory.generateJsonParams(response.deepCopy());

        this.jsonReader = new JsonReader(requestParams);
        this.jsonWriter = new JsonWriter(responseParams);

        this.objectMapper = objectMapper;
    }

    public <REQ, RES> RES convert(REQ request, Class<RES> responseClass) throws JsonProcessingException {
        JsonNode targetNode = this.objectMapper.valueToTree(request);

        ReferenceValue referenceValue = this.jsonReader.read(targetNode).flatten();
        JsonNode responseValue = this.jsonWriter.write(referenceValue);

        return this.objectMapper.treeToValue(responseValue, responseClass);
    }
}
