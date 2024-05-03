package org.lotan.utility.convert.param.params.meta;

import com.fasterxml.jackson.databind.JsonNode;
import org.lotan.utility.convert.param.params.JsonParams;

public interface JsonNodeValidator {
    /**
     * Validates a JsonNode in respect to the JsonParams
     */
    boolean validate(JsonNode node, JsonParams params);
}
