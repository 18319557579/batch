package com.elvishew.xlog.printer.file.naming;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/naming/FileNameGenerator.class */
public interface FileNameGenerator {
    boolean isFileNameChangeable();

    String generateFileName(int i, long j);
}