package org.lotan.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lotan.utility.convert.ValueConvert;

import java.util.Map;
import java.util.Objects;

public class JsonWriter {
    public JsonNode write(Map<String, ValueConvert> reference, JsonNode target) {
        return this.get(reference, target);
    }

    public JsonNode get(Map<String, ValueConvert> reference, JsonNode target) {
        target.fieldNames().forEachRemaining(refKey -> {
            ValueConvert convert = reference.get(refKey);

            if (target.get(refKey).isTextual() && Objects.nonNull(convert)) {
                ((ObjectNode) target).set(refKey, convert.getValue());
            } else if (target.get(refKey).isObject()) {
                this.get(reference, target.get(refKey));
            } else {
                ((ObjectNode) target).set(refKey, JsonNodeFactory.instance.missingNode());
            }
        });

        return target;
    }
}
