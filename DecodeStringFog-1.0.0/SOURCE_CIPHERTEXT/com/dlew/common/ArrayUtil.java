package com.dlew.common;

import java.util.ArrayList;
import java.util.List;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/ArrayUtil.class */
public class ArrayUtil {
    public static <T> List<T> intersection(List<T> list, List<T> list2) {
        ArrayList arrayList = new ArrayList();
        for (T t : list) {
            if (list2.contains(t)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }
}