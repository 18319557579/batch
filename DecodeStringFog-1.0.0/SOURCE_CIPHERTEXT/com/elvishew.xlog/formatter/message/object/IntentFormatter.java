package com.elvishew.xlog.formatter.message.object;

import android.content.Intent;
import com.elvishew.xlog.internal.util.ObjectToStringUtil;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/formatter/message/object/IntentFormatter.class */
public class IntentFormatter implements ObjectFormatter<Intent> {
    @Override // com.elvishew.xlog.formatter.Formatter
    public String format(Intent intent) {
        return ObjectToStringUtil.intentToString(intent);
    }
}