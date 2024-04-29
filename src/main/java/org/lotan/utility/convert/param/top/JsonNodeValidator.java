package org.lotan.utility.convert.param.top;

import com.fasterxml.jackson.databind.JsonNode;
import org.lotan.utility.convert.param.JsonParams;

public interface JsonNodeValidator {
    /**
     * Validates a JsonNode in respect to the JsonParams
     */
    boolean validate(JsonNode node, JsonParams params);
}
