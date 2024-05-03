package org.lotan.utility.convert.reader.referencevalue;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@ToString
@Getter(AccessLevel.PROTECTED)
public class BundleReferenceValue extends ReferenceValue {
    private final Map<String, ReferenceValue> children;

    public BundleReferenceValue() {
        this.children = new HashMap<>();
    }

    public BundleReferenceValue(Map<String, ReferenceValue> children) {
        this.children = children;
    }

    @Override
    public boolean isBundle() {
        return true;
    }

    @Override
    public JsonNode getValue() {
        return null;
    }

    @Override
    public ReferenceValue getReference(int index) {
        return null;
    }

    @Override
    public ReferenceValue getReference(String key) {
        return this.children.get(key);
    }

    @Override
    public Collection<ReferenceValue> getChildReferences() {
        return children.values();
    }

    public void putValueConvert(String field, ReferenceValue referenceValue) {
        this.children.put(field, referenceValue);
    }

    public void putAll(BundleReferenceValue bundleValueConvert) {
        this.children.putAll(bundleValueConvert.getChildren());
    }

    @Override
    public BundleReferenceValue flatten() {
        BundleReferenceValue bundleValueConvert = new BundleReferenceValue();

        this.children.forEach((field, valueConvert) -> {
            ReferenceValue flattened = valueConvert.flatten();

            if (flattened.isBundle()) {
                bundleValueConvert.putAll((BundleReferenceValue) flattened);
            } else {
                bundleValueConvert.putValueConvert(field, flattened);
            }
        });

        return bundleValueConvert;
    }
}
