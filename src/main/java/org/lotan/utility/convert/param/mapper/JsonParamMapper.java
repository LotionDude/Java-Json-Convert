package org.lotan.utility.convert.param.mapper;

import lombok.AllArgsConstructor;
import org.lotan.utility.convert.param.JsonParam;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class JsonParamMapper {
    private Map<String, IJsonParamMapper> jsonParamMap;

    //TODO: Make this not so inefficient
    public JsonParamMapper() {
        this.jsonParamMap = new HashMap<>() {{
            this.put("type", (value -> new JsonTypeParamMapper().get(value)));
        }};
    }

    public JsonParam get(String param, String value) {
        return this.jsonParamMap.get(param).get(value);
    }
}
