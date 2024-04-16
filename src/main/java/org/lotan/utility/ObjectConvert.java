package org.lotan.utility;

public interface ObjectConvert<T, C> {
    C convert(Class<C> conversionClass, T type1, T type2);
}
