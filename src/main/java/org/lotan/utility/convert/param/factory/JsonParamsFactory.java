package org.lotan.utility.convert.param.factory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.lotan.utility.convert.param.JsonParam;
import org.lotan.utility.convert.param.JsonParams;
import org.lotan.utility.convert.param.mapper.JsonParamMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Builder
@AllArgsConstructor
public class JsonParamsFactory {
    @Builder.Default
    private JsonFieldTokenizer jsonFieldTokenizer = new JsonFieldTokenizer(new JsonParamMapper());

    public JsonParamsFactory() {
        this.jsonFieldTokenizer = new JsonFieldTokenizer(new JsonParamMapper());
    }

    public JsonParams generateJsonParams(JsonNode ref) {
        return JsonParams.builder()
                .refNode(ref)
                .jsonParams(Set.of())
                .subJsonParams(this.getInnerJsonParams(ref))
                .build();
    }

    private JsonParams getJsonParams(JsonNode ref, String fieldConfig) {
        Set<JsonParam> jsonParamsSet = this.jsonFieldTokenizer.tokenize(fieldConfig);

        return JsonParams.builder()
                .refNode(ref)
                .jsonParams(jsonParamsSet)
                .subJsonParams(this.getInnerJsonParams(ref))
                .build();
    }

    private Map<String, JsonParams> getInnerJsonParams(JsonNode ref) {
//        if (ref.isArray()) {
//            return this.getArrayJsonParams(ref);
        if (ref.isObject()) {
            return this.getObjectJsonParams(ref);
        }

        return Map.of();
    }

    private Map<String, JsonParams> getObjectJsonParams(JsonNode ref) {
        Map<String, JsonParams> jsonParamsMap = new HashMap<>();
        Map<String, Map.Entry<String, JsonNode>> innerJsonParams = new HashMap<>();

        ref.fieldNames().forEachRemaining(fieldConfig -> {
            JsonParams jsonParams = this.getJsonParams(ref.get(fieldConfig), fieldConfig);

            JsonNode innerRef = ref.get(fieldConfig);

            String fieldName = this.jsonFieldTokenizer.getFieldName(fieldConfig);
            jsonParamsMap.put(fieldName, jsonParams);

            innerJsonParams.put(fieldConfig, Map.entry(fieldName, innerRef));
        });

        ObjectNode objectRef = (ObjectNode) ref;

        innerJsonParams.forEach((k, v) -> {
            objectRef.remove(k);
            objectRef.set(v.getKey(), v.getValue());
        });

        return jsonParamsMap;
    }

//    private Map<String, JsonParams> getArrayJsonParams(JsonNode ref) {
//        final Map<String, JsonParams> jsonParamMap = new HashMap<>();
//
//        ref.elements().forEachRemaining(node -> jsonParamMap.putAll(this.getJsonParams(node).getParams()));
//
//        return new JsonParamsBuilder(jsonParamMap, ref);
//    }
}
