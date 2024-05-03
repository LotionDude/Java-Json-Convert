package org.lotan.utility.convert.param.params.type;

import com.fasterxml.jackson.databind.JsonNode;
import org.lotan.utility.convert.param.constraints.JsonParamArray;
import org.lotan.utility.convert.param.params.JsonParams;
import org.lotan.utility.convert.param.params.meta.JsonNodeValidator;

@JsonParamArray
public class JsonArrayTypeParam extends JsonTypeParam implements JsonNodeValidator {
    @Override
    public boolean validate(JsonNode node, JsonParams params) {
        return node.isArray();
    }

    @Override
    public String paramValue() {
        return JsonParamType.ArrayType.getValue();
    }
}
