package com.elvishew.xlog.printer.file.writer;

import java.io.File;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/writer/Writer.class */
public abstract class Writer {
    public abstract boolean open(File file);

    public abstract boolean isOpened();

    public abstract File getOpenedFile();

    public abstract String getOpenedFileName();

    public abstract void appendLog(String str);

    public abstract boolean close();
}