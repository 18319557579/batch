package com.dlew.common;

import com.google.gson.Gson;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/StringUtil.class */
public class StringUtil {
    public static int[] splitToIntArray(String str, String str2) {
        Gson gson = new Gson();
        return (int[]) gson.fromJson(gson.toJson(str.split(str2)), int[].class);
    }

    public static double[] splitToDoubleArray(String str, String str2) {
        Gson gson = new Gson();
        return (double[]) gson.fromJson(gson.toJson(str.split(str2)), double[].class);
    }
}