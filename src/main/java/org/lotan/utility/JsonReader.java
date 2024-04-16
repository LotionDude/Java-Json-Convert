package org.lotan.utility;

import com.fasterxml.jackson.databind.JsonNode;
import org.lotan.utility.convert.ValueConvert;

import java.util.HashMap;
import java.util.Map;

public class JsonReader {
    public Map<String, ValueConvert> read(JsonNode base, JsonNode reference) {
        return this.get(base, reference);
    }

    private Map<String, ValueConvert> get(JsonNode base, JsonNode reference) {
        final Map<String, ValueConvert> fields = new HashMap<>();

        reference.fields().forEachRemaining(field -> {
            String refKey = field.getKey();
            JsonNode refValue = field.getValue();

            if (refValue.isTextual()) {
                ValueConvert valueConvert = new ValueConvert(refValue.asText(), base.get(refKey));
                fields.put(refValue.asText(), valueConvert);
            } else if (refValue.isObject() && base.has(refKey)) {
                fields.putAll(get(base.get(refKey), reference.get(refKey)));
            }
        });

        return fields;
    }
}
