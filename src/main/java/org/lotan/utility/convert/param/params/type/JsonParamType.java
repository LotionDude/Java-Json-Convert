package org.lotan.utility.convert.param.params.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JsonParamType {
    ArrayType("arr"),
    DoubleType("dbl"),
    IntType("int"),
    NumberType("num"),
    StringType("str"),
    ObjType("obj"),
    ValueType("val");

    private final String value;
}
