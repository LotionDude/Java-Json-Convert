package org.lotan.utility.convert.param.type;

import com.fasterxml.jackson.databind.JsonNode;
import org.lotan.utility.convert.param.JsonParams;
import org.lotan.utility.convert.param.constraints.JsonParamObject;
import org.lotan.utility.convert.param.top.JsonNodeValidator;

@JsonParamObject
public class JsonObjectTypeParam extends JsonTypeParam implements JsonNodeValidator {

    @Override
    public boolean validate(JsonNode node, JsonParams params) {
        return node.isObject();
    }
}
