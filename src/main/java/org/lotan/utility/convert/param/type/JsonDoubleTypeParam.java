package org.lotan.utility.convert.param.type;

import com.fasterxml.jackson.databind.JsonNode;
import org.lotan.utility.convert.param.JsonParams;
import org.lotan.utility.convert.param.constraints.JsonParamValue;
import org.lotan.utility.convert.param.top.JsonNodeValidator;

@JsonParamValue
public class JsonDoubleTypeParam extends JsonTypeParam implements JsonNodeValidator {

    @Override
    public boolean validate(JsonNode node, JsonParams params) {
        return node.isDouble();
    }
}
