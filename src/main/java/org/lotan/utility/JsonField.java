package org.lotan.utility;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class JsonField {
    private final boolean isArray;
    private final Map<String, JsonField> subFields;
}
