package org.lotan.utility.convert.param.params.meta;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonNodeModifier {
    JsonNode modify(JsonNode node);
}
