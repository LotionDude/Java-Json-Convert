package org.lotan.utility.convert.param.top;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonNodeModifier {
    JsonNode modify(JsonNode node);
}
