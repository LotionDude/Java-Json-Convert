package org.lotan.utility.convert.param.mapper;

import org.lotan.utility.convert.param.JsonParam;
import org.lotan.utility.convert.param.JsonTypeParamCreator;
import org.lotan.utility.convert.param.type.*;

import java.util.HashMap;
import java.util.Map;

public class JsonTypeParamMapper implements IJsonParamMapper {
    private final Map<String, JsonTypeParamCreator> jsonParamMap;

    public JsonTypeParamMapper() {
        this.jsonParamMap = new HashMap<>() {{
            this.put("multi", JsonValueTypeParam::new);
            this.put("obj", JsonObjectTypeParam::new);
            this.put("arr", JsonArrayTypeParam::new);
            this.put("val", JsonValueTypeParam::new);
            this.put("text", JsonTextTypeParam::new);
            this.put("num", JsonNumberTypeParam::new);
            this.put("int", JsonIntegerTypeParam::new);
            this.put("double", JsonDoubleTypeParam::new);
        }};
    }

    public JsonParam get(String paramType) {
        return this.jsonParamMap.get(paramType).createJsonParam();
    }
}
