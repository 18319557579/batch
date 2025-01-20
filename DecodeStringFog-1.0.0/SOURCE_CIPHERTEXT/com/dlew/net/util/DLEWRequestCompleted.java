package com.dlew.net.util;

import com.google.gson.internal.$Gson;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/DLEWRequestCompleted.class */
public abstract class DLEWRequestCompleted<T> {
    public Type mType = getSuperclassTypeParameter(getClass());

    public static Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        boolean z = genericSuperclass instanceof Class;
        return $Gson.Types.canonicalize(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public abstract void OnCompleted(boolean z, T t);
}