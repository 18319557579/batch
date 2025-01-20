package com.elvishew.xlog.formatter.message.json;

import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.internal.Platform;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/formatter/message/json/DefaultJsonFormatter.class */
public class DefaultJsonFormatter implements JsonFormatter {
    private static final int JSON_INDENT = 4;

    @Override // com.elvishew.xlog.formatter.Formatter
    public String format(String str) {
        String jSONArray;
        if (str == null || str.trim().length() == 0) {
            Platform.get().warn("JSON empty.");
            return BuildConfig.VERSION_NAME;
        }
        try {
            if (str.startsWith("{")) {
                jSONArray = new JSONObject(str).toString(4);
            } else {
                if (!str.startsWith("[")) {
                    Platform.get().warn("JSON should start with { or [");
                    return str;
                }
                jSONArray = new JSONArray(str).toString(4);
            }
            return jSONArray;
        } catch (Exception e) {
            Platform.get().warn(e.getMessage());
            return str;
        }
    }
}