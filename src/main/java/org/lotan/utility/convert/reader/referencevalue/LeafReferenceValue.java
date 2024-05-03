package org.lotan.utility.convert.reader.referencevalue;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;
import java.util.Set;

@Getter
@ToString
public class LeafReferenceValue extends ReferenceValue {
    private final JsonNode value;

    public LeafReferenceValue(JsonNode value) {
        this.value = value;
    }

    @Override
    public boolean isValue() {
        return true;
    }

    @Override
    public JsonNode getValue() {
        return this.value;
    }

    @Override
    public ReferenceValue getReference(int index) {
        return null;
    }

    @Override
    public ReferenceValue getReference(String value) {
        return null;
    }

    @Override
    public Collection<ReferenceValue> getChildReferences() {
        return Set.of();
    }
}
