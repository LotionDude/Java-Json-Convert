package org.lotan.utility.convert.param.params.type;

import com.fasterxml.jackson.databind.JsonNode;
import org.lotan.utility.convert.param.constraints.JsonParamObject;
import org.lotan.utility.convert.param.params.JsonParams;
import org.lotan.utility.convert.param.params.meta.JsonNodeValidator;

@JsonParamObject
public class JsonObjectTypeParam extends JsonTypeParam implements JsonNodeValidator {

    @Override
    public boolean validate(JsonNode node, JsonParams params) {
        return node.isObject();
    }

    @Override
    public String paramValue() {
        return JsonParamType.ObjType.getValue();
    }
}
