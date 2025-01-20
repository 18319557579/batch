package com.dlew.net.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/ParameterizedTypeImpl.class */
public class ParameterizedTypeImpl implements ParameterizedType {
    private final Class raw;
    private final Type[] args;

    public ParameterizedTypeImpl(Class cls, Type[] typeArr) {
        this.raw = cls;
        this.args = typeArr == null ? new Type[0] : typeArr;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return this.args;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getRawType() {
        return this.raw;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return null;
    }
}