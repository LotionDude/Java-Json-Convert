package org.lotan.utility.convert.reader.referencevalue;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.util.Collection;

@Getter
public abstract class ReferenceValue {

    public boolean isArray() {
        return false;
    }

    public boolean isValue() {
        return false;
    }

    public boolean isBundle() {
        return false;
    }

    public ReferenceValue flatten() {
        return this;
    }

    public abstract JsonNode getValue();

    public abstract ReferenceValue getReference(int index);

    public abstract ReferenceValue getReference(String key);

    public abstract Collection<ReferenceValue> getChildReferences();

}
