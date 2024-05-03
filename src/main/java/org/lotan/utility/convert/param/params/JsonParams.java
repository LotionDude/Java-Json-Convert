package org.lotan.utility.convert.param.params;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;

@Getter
@Builder
public class JsonParams {
    private final Map<String, JsonParam> jsonParams;
    private final JsonNode reqNode;
    private final Map<String, JsonParams> subJsonParams;

//    //TODO: Don't make this so ugly wtf
//    public Set<JsonNodeValidator> nodeValidators() {
//        return this.jsonParams
//                .stream()
//                .filter(jsonParam -> jsonParam instanceof JsonNodeValidator)
//                .map(jsonParam -> (JsonNodeValidator) jsonParam)
//                .collect(Collectors.toSet());
//    }

    public Optional<String> getParamValue(String paramName) {
        if (this.jsonParams.containsKey(paramName)) {
            return Optional.ofNullable(this.jsonParams.get(paramName).paramValue());
        } else {
            return Optional.empty();
        }
    }
}
