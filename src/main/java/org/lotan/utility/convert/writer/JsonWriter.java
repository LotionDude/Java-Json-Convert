package org.lotan.utility.convert.writer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.lotan.utility.convert.param.params.JsonParams;
import org.lotan.utility.convert.reader.referencevalue.ReferenceValue;

@RequiredArgsConstructor
public class JsonWriter {
    private final JsonParams jsonParams;

    public JsonNode write(ReferenceValue reference) {
        return this.get(reference, jsonParams.getReqNode(), jsonParams);
    }

    public JsonNode get(ReferenceValue reference, JsonNode response, JsonParams jsonParams) {
        if (response.isObject()) {
            return this.getObject(reference, response, jsonParams);
        }

        return null;
    }

    public JsonNode getObject(ReferenceValue reference, JsonNode response, JsonParams jsonParams) {
        ObjectNode builderNode = JsonNodeFactory.instance.objectNode();

        response.fields().forEachRemaining(field -> {
            String fieldName = field.getKey();
            JsonNode fieldNode = field.getValue();

            JsonParams innerJsonParams = jsonParams.getSubJsonParams().get(fieldName);
            String referenceName = innerJsonParams.getParamValue("ref").orElse(fieldName);

            if (fieldNode.isTextual()) {
                builderNode.set(fieldName, reference.getReference(referenceName).getValue());
            } else if (fieldNode.isObject()) {
                builderNode.set(fieldName, this.getObject(reference, fieldNode, innerJsonParams));
            } else if (fieldNode.isArray()) {
                builderNode.set(fieldName, this.getArray(reference.getReference(referenceName), fieldNode, innerJsonParams));
            }
        });

        return builderNode;
    }

    public JsonNode getArray(ReferenceValue reference, JsonNode response, JsonParams jsonParams) {
        ArrayNode builderNode = JsonNodeFactory.instance.arrayNode();

        reference.getChildReferences().forEach(referenceValue -> {
            builderNode.add(this.get(referenceValue, response.get(0), jsonParams.getSubJsonParams().get("0")));
        });

        return builderNode;
    }
}
