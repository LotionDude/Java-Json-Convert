package org.lotan.utility.convert.reader.referencevalue;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@ToString
public class ArrayReferenceValue extends ReferenceValue {
    private final List<ReferenceValue> children;

    public ArrayReferenceValue(List<ReferenceValue> children) {
        this.children = children;
    }

    public ArrayReferenceValue() {
        this.children = new ArrayList<>();
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public JsonNode getValue() {
        return null;
    }

    @Override
    public ReferenceValue getReference(int index) {
        return this.children.get(index);
    }

    @Override
    public ReferenceValue getReference(String value) {
        return null;
    }

    @Override
    public Collection<ReferenceValue> getChildReferences() {
        return this.children;
    }

    public void putValueConvert(ReferenceValue value) {
        this.children.add(value);
    }

    public void putAllValueConvert(Collection<ReferenceValue> values) {
        this.children.addAll(values);
    }

    @Override
    public ReferenceValue flatten() {
        List<ReferenceValue> flattened = this.children.stream()
                .map(ReferenceValue::flatten)
                .toList();

        return new ArrayReferenceValue(flattened);
    }
}
