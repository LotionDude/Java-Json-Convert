package org.lotan.utility.convert.param.params.mapper;


import org.lotan.utility.convert.param.params.JsonParam;

@FunctionalInterface
public interface JsonParamCreator {
    JsonParam createJsonParam(String value);
}
