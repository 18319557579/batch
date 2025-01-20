package com.elvishew.xlog.printer.file.naming;

import com.elvishew.xlog.LogLevel;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/naming/LevelFileNameGenerator.class */
public class LevelFileNameGenerator implements FileNameGenerator {
    @Override // com.elvishew.xlog.printer.file.naming.FileNameGenerator
    public boolean isFileNameChangeable() {
        return true;
    }

    @Override // com.elvishew.xlog.printer.file.naming.FileNameGenerator
    public String generateFileName(int i, long j) {
        return LogLevel.getLevelName(i);
    }
}