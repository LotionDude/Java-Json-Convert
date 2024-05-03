package org.lotan.utility.convert.param.params.mapper;

import org.lotan.utility.convert.param.params.JsonParam;
import org.lotan.utility.convert.param.params.type.*;

import java.util.HashMap;
import java.util.Map;


public class JsonTypeParamMapper implements IJsonParamMapper {
    private final Map<String, JsonTypeParamCreator> jsonParamMap;

    public JsonTypeParamMapper() {
        this.jsonParamMap = new HashMap<>() {{
            this.put(JsonParamType.ValueType.getValue(), JsonValueTypeParam::new);
            this.put(JsonParamType.ObjType.getValue(), JsonObjectTypeParam::new);
            this.put(JsonParamType.ArrayType.getValue(), JsonArrayTypeParam::new);
            this.put(JsonParamType.StringType.getValue(), JsonStringTypeParam::new);
            this.put(JsonParamType.NumberType.getValue(), JsonNumberTypeParam::new);
            this.put(JsonParamType.IntType.getValue(), JsonIntegerTypeParam::new);
            this.put(JsonParamType.DoubleType.getValue(), JsonDoubleTypeParam::new);
        }};
    }

    public JsonParam get(String paramType) {
        return this.jsonParamMap.get(paramType).createJsonParam();
    }
}
