package com.elvishew.xlog.printer.file.clean;

import java.io.File;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/clean/NeverCleanStrategy.class */
public class NeverCleanStrategy implements CleanStrategy {
    @Override // com.elvishew.xlog.printer.file.clean.CleanStrategy
    public boolean shouldClean(File file) {
        return false;
    }
}