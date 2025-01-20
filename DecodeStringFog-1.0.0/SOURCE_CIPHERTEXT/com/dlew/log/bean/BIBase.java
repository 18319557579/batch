package com.dlew.log.bean;

import com.dlew.StringFog;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/bean/BIBase.class */
public class BIBase {
    static final String CHAR_DELIMITER = null;
    static final String NULL = StringFog.decrypt("MTl+MA==\n", "X0wSXDs4DCI=\n");

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/bean/BIBase$EvLveE1.class */
    public class EvLveE1 implements Comparator<Field> {
        public EvLveE1() {
        }

        @Override // java.util.Comparator
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public int compare(Field field, Field field2) {
            return ((BeanFieldAnnotation) field.getAnnotation(BeanFieldAnnotation.class)).order() > ((BeanFieldAnnotation) field2.getAnnotation(BeanFieldAnnotation.class)).order() ? 1 : -1;
        }
    }

    private List<Field> getOrderedField(Field[] fieldArr) {
        ArrayList arrayList = new ArrayList();
        for (Field field : fieldArr) {
            if (field.getAnnotation(BeanFieldAnnotation.class) != null) {
                arrayList.add(field);
            }
        }
        Collections.sort(arrayList, new EvLveE1());
        return arrayList;
    }

    public String getFormatString() {
        StringBuilder sb = new StringBuilder();
        String str = null;
        try {
            for (Field field : getOrderedField(getClass().getDeclaredFields())) {
                field.setAccessible(true);
                Object obj = field.get(this);
                if (obj == null || obj.equals(NULL)) {
                    sb.append(NULL).append("\u0001");
                } else {
                    sb.append(obj).append("\u0001");
                }
            }
            String sb2 = sb.toString();
            str = sb2;
            return sb2.substring(0, sb2.lastIndexOf("\u0001"));
        } catch (Exception unused) {
            return str;
        }
    }
}