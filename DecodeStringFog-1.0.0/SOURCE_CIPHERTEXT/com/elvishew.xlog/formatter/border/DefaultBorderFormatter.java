package com.elvishew.xlog.formatter.border;

import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.internal.SystemCompat;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/formatter/border/DefaultBorderFormatter.class */
public class DefaultBorderFormatter implements BorderFormatter {
    private static final char VERTICAL_BORDER_CHAR = 9553;
    private static final String TOP_HORIZONTAL_BORDER = "╔═══════════════════════════════════════════════════════════════════════════════════════════════════";
    private static final String DIVIDER_HORIZONTAL_BORDER = "╟───────────────────────────────────────────────────────────────────────────────────────────────────";
    private static final String BOTTOM_HORIZONTAL_BORDER = "╚═══════════════════════════════════════════════════════════════════════════════════════════════════";

    private static String appendVerticalBorder(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 10);
        String[] split = str.split(SystemCompat.lineSeparator);
        int length = split.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(SystemCompat.lineSeparator);
            }
            sb.append((char) 9553).append(split[i]);
        }
        return sb.toString();
    }

    @Override // com.elvishew.xlog.formatter.Formatter
    public String format(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return BuildConfig.VERSION_NAME;
        }
        String[] strArr2 = new String[strArr.length];
        int i = 0;
        for (String str : strArr) {
            if (str != null) {
                strArr2[i] = str;
                i++;
            }
        }
        if (i == 0) {
            return BuildConfig.VERSION_NAME;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(TOP_HORIZONTAL_BORDER).append(SystemCompat.lineSeparator);
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2;
            sb.append(appendVerticalBorder(strArr2[i2]));
            if (i3 != i - 1) {
                sb.append(SystemCompat.lineSeparator).append(DIVIDER_HORIZONTAL_BORDER).append(SystemCompat.lineSeparator);
            } else {
                sb.append(SystemCompat.lineSeparator).append(BOTTOM_HORIZONTAL_BORDER);
            }
        }
        return sb.toString();
    }
}