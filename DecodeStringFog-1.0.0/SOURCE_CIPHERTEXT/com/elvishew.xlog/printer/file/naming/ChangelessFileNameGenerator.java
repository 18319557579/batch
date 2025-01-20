package com.elvishew.xlog.printer.file.naming;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/naming/ChangelessFileNameGenerator.class */
public class ChangelessFileNameGenerator implements FileNameGenerator {
    private final String fileName;

    public ChangelessFileNameGenerator(String str) {
        this.fileName = str;
    }

    @Override // com.elvishew.xlog.printer.file.naming.FileNameGenerator
    public boolean isFileNameChangeable() {
        return false;
    }

    @Override // com.elvishew.xlog.printer.file.naming.FileNameGenerator
    public String generateFileName(int i, long j) {
        return this.fileName;
    }
}