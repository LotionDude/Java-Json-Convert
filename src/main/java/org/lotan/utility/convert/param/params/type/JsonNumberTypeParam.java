package org.lotan.utility.convert.param.params.type;

import com.fasterxml.jackson.databind.JsonNode;
import org.lotan.utility.convert.param.constraints.JsonParamValue;
import org.lotan.utility.convert.param.params.JsonParams;
import org.lotan.utility.convert.param.params.meta.JsonNodeValidator;

@JsonParamValue
public class JsonNumberTypeParam extends JsonTypeParam implements JsonNodeValidator {

    @Override
    public boolean validate(JsonNode node, JsonParams params) {
        return node.isNumber();
    }

    @Override
    public String paramValue() {
        return JsonParamType.NumberType.getValue();
    }
}
