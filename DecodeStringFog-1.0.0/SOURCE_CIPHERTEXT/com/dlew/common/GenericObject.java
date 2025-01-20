package com.dlew.common;

import com.google.gson.internal.$Gson;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/GenericObject.class */
public class GenericObject<T> {
    public Type mType = getSuperclassTypeParameter(getClass());

    private Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        boolean z = genericSuperclass instanceof Class;
        return $Gson.Types.canonicalize(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }
}