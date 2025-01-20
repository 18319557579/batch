package com.dlew.common;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Random;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/NumberUtil.class */
public class NumberUtil {
    public static int getRandomInt(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    public static double getRandomDouble(double d, double d2) {
        return d + (Math.random() * d2);
    }

    public static double round(double d, int i) {
        return Math.round(d * Math.pow(10.0d, i)) / d;
    }

    public static String fractionDigits(int i, double d) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMaximumFractionDigits(i);
        return numberInstance.format(d);
    }

    public static String getBestFitString(double d) {
        return fractionDigits(getNumberOfDecimalPlaces(d), d);
    }

    public static int getNumberOfDecimalPlaces(double d) {
        return getNumberOfDecimalPlaces(BigDecimal.valueOf(d));
    }

    public static int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
        return Math.max(0, bigDecimal.stripTrailingZeros().scale());
    }
}