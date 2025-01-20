package com.elvishew.xlog.interceptor;

import com.elvishew.xlog.LogItem;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/interceptor/BlacklistTagsFilterInterceptor.class */
public class BlacklistTagsFilterInterceptor extends AbstractFilterInterceptor {
    private Iterable<String> blacklistTags;

    public BlacklistTagsFilterInterceptor(String... strArr) {
        this(Arrays.asList(strArr));
    }

    @Override // com.elvishew.xlog.interceptor.AbstractFilterInterceptor
    public boolean reject(LogItem logItem) {
        Iterable<String> iterable = this.blacklistTags;
        if (iterable == null) {
            return false;
        }
        Iterator<String> it = iterable.iterator();
        while (it.hasNext()) {
            if (logItem.tag.equals(it.next())) {
                return true;
            }
        }
        return false;
    }

    public BlacklistTagsFilterInterceptor(Iterable<String> iterable) {
        iterable.getClass();
        this.blacklistTags = iterable;
    }
}