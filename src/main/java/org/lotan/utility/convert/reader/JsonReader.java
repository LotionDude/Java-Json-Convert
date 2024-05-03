package org.lotan.utility.convert.reader;

import com.fasterxml.jackson.databind.JsonNode;
import org.lotan.utility.convert.param.params.JsonParams;
import org.lotan.utility.convert.reader.referencevalue.ArrayReferenceValue;
import org.lotan.utility.convert.reader.referencevalue.BundleReferenceValue;
import org.lotan.utility.convert.reader.referencevalue.LeafReferenceValue;
import org.lotan.utility.convert.reader.referencevalue.ReferenceValue;

public class JsonReader {
    private final JsonParams jsonParams;

    public JsonReader(JsonParams jsonParams) {
        this.jsonParams = jsonParams;
    }

    public ReferenceValue read(JsonNode target) {
        return this.get(this.jsonParams.getReqNode(), target, this.jsonParams);
    }

    private ReferenceValue get(JsonNode request, JsonNode target, JsonParams jsonParams) {
        if (request.isTextual()) {
            return new LeafReferenceValue(target);
        } else if (request.isObject() && target.isObject()) {
            return this.getObject(request, target, jsonParams);
        } else if (request.isArray() && request.isArray()) {
            return this.getArray(request, target, jsonParams);
        }

        return null;
    }

    private BundleReferenceValue getObject(JsonNode request, JsonNode target, JsonParams jsonParams) {
        BundleReferenceValue bundleValueConvert = new BundleReferenceValue();

        request.fields().forEachRemaining(entry -> {
            String fieldName = entry.getKey();

            JsonNode reqFieldNode = entry.getValue();
            JsonNode targetFieldNode = target.get(fieldName);

            JsonParams innerJsonParams = jsonParams.getSubJsonParams().get(fieldName);

            String referenceName = innerJsonParams.getParamValue("ref").orElse(fieldName);
            bundleValueConvert.putValueConvert(referenceName, this.get(reqFieldNode, targetFieldNode, innerJsonParams));
        });

        return bundleValueConvert;
    }


    private ArrayReferenceValue getArray(JsonNode request, JsonNode target, JsonParams jsonParams) {
        ArrayReferenceValue arrayValueConvert = new ArrayReferenceValue();

        JsonNode currentRequest = request.get(0);
        JsonParams innerJsonParams = jsonParams.getSubJsonParams().get("0");

        target.elements().forEachRemaining(currentTarget -> {
            ReferenceValue referenceValue = this.get(currentRequest, currentTarget, innerJsonParams);
            arrayValueConvert.putValueConvert(referenceValue);
        });

        return arrayValueConvert;
    }
}
