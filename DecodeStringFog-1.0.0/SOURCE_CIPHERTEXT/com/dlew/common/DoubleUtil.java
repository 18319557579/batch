package com.dlew.common;

import com.dlew.StringFog;
import java.math.BigDecimal;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/DoubleUtil.class */
public class DoubleUtil {
    private static final int DEF_DIV_SCALE = 10;

    private DoubleUtil() {
    }

    public static double add(double d, double d2) {
        return new BigDecimal(Double.toString(d)).add(new BigDecimal(Double.toString(d2))).doubleValue();
    }

    public static double sub(double d, double d2) {
        return new BigDecimal(Double.toString(d)).subtract(new BigDecimal(Double.toString(d2))).doubleValue();
    }

    public static double mul(double d, double d2) {
        return new BigDecimal(Double.toString(d)).multiply(new BigDecimal(Double.toString(d2))).doubleValue();
    }

    public static double div(double d, double d2) {
        return div(d, d2, DEF_DIV_SCALE);
    }

    public static double round(double d, int i) {
        if (i >= 0) {
            return new BigDecimal(Double.toString(d)).divide(new BigDecimal(StringFog.decrypt("Rg==\n", "d2G/P2nKfRc=\n")), i, 4).doubleValue();
        }
        throw new IllegalArgumentException(StringFog.decrypt("4Xv7S/3lcfzQM/Me/fIw8tAz/0v+6WP5wXroDq7vfuTQdPsZrulisM927AQ=\n", "tROea46GEJA=\n"));
    }

    public static double div(double d, double d2, int i) {
        if (i >= 0) {
            return new BigDecimal(Double.toString(d)).divide(new BigDecimal(Double.toString(d2)), i, 4).doubleValue();
        }
        throw new IllegalArgumentException(StringFog.decrypt("mAiZoEM08lCpQJH1QyOzXqlAnaBAOOBVuAmK5RA+/UipB5nyEDjhHLYFju8=\n", "zGD8gDBXkzw=\n"));
    }
}