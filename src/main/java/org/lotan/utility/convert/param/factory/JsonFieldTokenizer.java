package org.lotan.utility.convert.param.factory;

import lombok.AllArgsConstructor;
import org.lotan.utility.convert.param.JsonParam;
import org.lotan.utility.convert.param.mapper.JsonParamMapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class JsonFieldTokenizer {
    private static final Pattern PARAM_REGEX = Pattern.compile("--(\\w*)=(('?)([\\w ]*)\\3)( |$)");
    private static final Pattern FIELD_REGEX = Pattern.compile("^(\\w*)(.*| )$");

    private final JsonParamMapper jsonParamMapper;

    public Set<JsonParam> tokenize(String fieldConfig) {
        Map<String, String> rawTokens = this.getRawTokens(fieldConfig);
        return this.getTokens(rawTokens);
    }

    public String getFieldName(String fieldConfig) {
        Matcher matcher = FIELD_REGEX.matcher(fieldConfig);

        if (matcher.find()) {
            return matcher.group(1);
        }

        throw new RuntimeException();
    }

    private Map<String, String> getRawTokens(String fieldConfig) {
        Matcher matcher = PARAM_REGEX.matcher(fieldConfig);

        Map<String, String> rawTokens = new HashMap<>();

        while (matcher.find()) {
            rawTokens.put(matcher.group(1), matcher.group(4));
        }

        return rawTokens;
    }

    private Set<JsonParam> getTokens(Map<String, String> rawTokens) {
        Set<JsonParam> tokens = new HashSet<>();

        rawTokens.forEach((k, v) -> tokens.add(this.jsonParamMapper.get(k, v)));

        return tokens;
    }
}


