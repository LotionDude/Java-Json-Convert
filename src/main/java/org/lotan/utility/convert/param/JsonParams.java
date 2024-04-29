package org.lotan.utility.convert.param;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import org.lotan.utility.convert.param.top.JsonNodeValidator;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record JsonParams(Set<JsonParam> jsonParams, JsonNode refNode, Map<String, JsonParams> subJsonParams) {
    //TODO: Don't make this so ugly wtf
    public Set<JsonNodeValidator> nodeValidators() {
        return this.jsonParams
                .stream()
                .filter(jsonParam -> jsonParam instanceof JsonNodeValidator)
                .map(jsonParam -> (JsonNodeValidator) jsonParam)
                .collect(Collectors.toSet());
    }
}
