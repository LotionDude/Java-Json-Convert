package org.lotan.utility.convert.param.params.name;

import lombok.RequiredArgsConstructor;
import org.lotan.utility.convert.param.params.JsonParam;

@RequiredArgsConstructor
public class JsonReferenceNameParam implements JsonParam {
    private final String name;

    @Override
    public String paramValue() {
        return this.name;
    }
}
