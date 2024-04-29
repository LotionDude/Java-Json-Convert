package org.lotan.utility;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValueConvert {
    private final String field;
    private final JsonNode value;
}
