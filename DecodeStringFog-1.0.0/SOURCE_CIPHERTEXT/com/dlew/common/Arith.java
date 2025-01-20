package com.dlew.common;

import com.dlew.StringFog;
import java.math.BigDecimal;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/Arith.class */
public class Arith {
    private static final int DEF_DIV_SCALE = 10;

    private Arith() {
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
            return new BigDecimal(Double.toString(d)).divide(new BigDecimal(StringFog.decrypt("6Q==\n", "2ImUq4fBFEk=\n")), i, 4).doubleValue();
        }
        throw new IllegalArgumentException(StringFog.decrypt("z/rfq/z6IpD+stf+/O1jnv6y26v/9jCV7/vM7q/wLYj+9d/5r/Yx3OH3yOQ=\n", "m5K6i4+ZQ/w=\n"));
    }

    public static double div(double d, double d2, int i) {
        if (i >= 0) {
            return new BigDecimal(Double.toString(d)).divide(new BigDecimal(Double.toString(d2)), i, 4).doubleValue();
        }
        throw new IllegalArgumentException(StringFog.decrypt("tMYHVTbq3MmFjg8ANv2dx4WOA1U15s7MlMcUEGXg09GFyQcHZebPhZrLEBo=\n", "4K5idUWJvaU=\n"));
    }
}