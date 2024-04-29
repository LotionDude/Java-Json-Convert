package org.lotan.utility.convert;

import com.fasterxml.jackson.databind.JsonNode;
import org.lotan.utility.ValueConvert;
import org.lotan.utility.convert.param.JsonParams;
import org.lotan.utility.convert.param.top.JsonNodeValidator;

import java.util.HashMap;
import java.util.Map;

public class JsonReader {
    private final JsonParams jsonParams;

    public JsonReader(JsonParams jsonParams) {
        this.jsonParams = jsonParams;
    }

    public Map<String, ValueConvert> read(JsonNode base) {
        return this.get(base, this.jsonParams);
    }

    private Map<String, ValueConvert> get(JsonNode base, JsonParams jsonParams) {
        final Map<String, ValueConvert> fields = new HashMap<>();

        jsonParams.refNode().fields().forEachRemaining(field -> {
            String refKey = field.getKey();
            JsonNode refValue = field.getValue();
            JsonParams subJsonParams = jsonParams.subJsonParams().get(refKey);

            if (base.has(refKey)) {
                this.validate(base.get(refKey), subJsonParams);
            }

            if (refValue.isTextual()) {
                ValueConvert valueConvert = new ValueConvert(refValue.asText(), base.get(refKey));
                fields.put(refValue.asText(), valueConvert);
            } else if (refValue.isObject() && base.has(refKey)) {
                fields.putAll(get(base.get(refKey), subJsonParams));
            }
        });

        return fields;
    }

    //TODO: Don't make this so ugly
    private void validate(JsonNode base, JsonParams jsonParams) {
        for (JsonNodeValidator nodeValidator : jsonParams.nodeValidators()) {
            if (!nodeValidator.validate(base, jsonParams)) {
                throw new RuntimeException("FAILED JSON NODE VALIDATION: " + base);
            }
        }
    }
}
