package com.elvishew.xlog.formatter.message.object;

import android.os.Bundle;
import com.elvishew.xlog.internal.util.ObjectToStringUtil;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/formatter/message/object/BundleFormatter.class */
public class BundleFormatter implements ObjectFormatter<Bundle> {
    @Override // com.elvishew.xlog.formatter.Formatter
    public String format(Bundle bundle) {
        return ObjectToStringUtil.bundleToString(bundle);
    }
}