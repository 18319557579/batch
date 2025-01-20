package com.elvishew.xlog.flattener;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/flattener/ClassicFlattener.class */
public class ClassicFlattener extends PatternFlattener {
    private static final String DEFAULT_PATTERN = "{d} {l}/{t}: {m}";

    public ClassicFlattener() {
        super(DEFAULT_PATTERN);
    }
}