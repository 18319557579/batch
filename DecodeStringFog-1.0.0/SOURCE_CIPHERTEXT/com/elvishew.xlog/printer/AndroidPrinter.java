package com.elvishew.xlog.printer;

import android.util.Log;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/AndroidPrinter.class */
public class AndroidPrinter implements Printer {
    static final int DEFAULT_MAX_CHUNK_SIZE = 4000;
    private boolean autoSeparate;
    private int maxChunkSize;

    public AndroidPrinter() {
        this(false, DEFAULT_MAX_CHUNK_SIZE);
    }

    public static int adjustEnd(String str, int i, int i2) {
        if (i2 != str.length() && str.charAt(i2) != '\n') {
            for (int i3 = i2 - 1; i < i3; i3--) {
                if (str.charAt(i3) == '\n') {
                    return i3;
                }
            }
            return i2;
        }
        return i2;
    }

    @Override // com.elvishew.xlog.printer.Printer
    public void println(int i, String str, String str2) {
        int length = str2.length();
        int i2 = 0;
        while (i2 < length) {
            if (str2.charAt(i2) == '\n') {
                i2++;
            } else {
                int min = Math.min(i2 + this.maxChunkSize, length);
                if (this.autoSeparate) {
                    int indexOf = str2.indexOf(10, i2);
                    if (indexOf != -1) {
                        min = Math.min(min, indexOf);
                    }
                } else {
                    min = adjustEnd(str2, i2, min);
                }
                printChunk(i, str, str2.substring(i2, min));
                i2 = min;
            }
        }
    }

    public void printChunk(int i, String str, String str2) {
        Log.println(i, str, str2);
    }

    public AndroidPrinter(boolean z) {
        this(z, DEFAULT_MAX_CHUNK_SIZE);
    }

    public AndroidPrinter(int i) {
        this(false, i);
    }

    public AndroidPrinter(boolean z, int i) {
        this.autoSeparate = z;
        this.maxChunkSize = i;
    }
}