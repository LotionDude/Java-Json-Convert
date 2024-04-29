package org.lotan.utility.convert.param.constraints;

public @interface JsonParamArray {
    JsonFieldType type() default JsonFieldType.ANY;
}
