package com.elvishew.xlog.interceptor;

import com.elvishew.xlog.LogItem;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/interceptor/WhitelistTagsFilterInterceptor.class */
public class WhitelistTagsFilterInterceptor extends AbstractFilterInterceptor {
    private Iterable<String> whitelistTags;

    public WhitelistTagsFilterInterceptor(String... strArr) {
        this(Arrays.asList(strArr));
    }

    @Override // com.elvishew.xlog.interceptor.AbstractFilterInterceptor
    public boolean reject(LogItem logItem) {
        Iterable<String> iterable = this.whitelistTags;
        if (iterable == null) {
            return true;
        }
        Iterator<String> it = iterable.iterator();
        while (it.hasNext()) {
            if (logItem.tag.equals(it.next())) {
                return false;
            }
        }
        return true;
    }

    public WhitelistTagsFilterInterceptor(Iterable<String> iterable) {
        iterable.getClass();
        this.whitelistTags = iterable;
    }
}