package com.elvishew.xlog.printer.file.writer;

import com.elvishew.xlog.internal.Platform;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/writer/SimpleWriter.class */
public class SimpleWriter extends Writer {
    private String logFileName;
    private File logFile;
    private BufferedWriter bufferedWriter;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.elvishew.xlog.printer.file.writer.SimpleWriter] */
    @Override // com.elvishew.xlog.printer.file.writer.Writer
    public boolean open(File file) {
        this.logFileName = file.getName();
        this.logFile = file;
        boolean z = false;
        ?? exists = file.exists();
        ?? r0 = exists;
        if (exists == 0) {
            try {
                File parentFile = this.logFile.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.logFile.createNewFile();
                r0 = 1;
                z = true;
            } catch (Exception unused) {
                exists.printStackTrace();
                close();
                return false;
            }
        }
        try {
            boolean z2 = z;
            this.bufferedWriter = new BufferedWriter(new FileWriter(this.logFile, true));
            if (!z2) {
                return true;
            }
            r0 = this;
            r0.onNewFileCreated(r0.logFile);
            return true;
        } catch (Exception unused2) {
            r0.printStackTrace();
            close();
            return false;
        }
    }

    @Override // com.elvishew.xlog.printer.file.writer.Writer
    public boolean isOpened() {
        return this.bufferedWriter != null && this.logFile.exists();
    }

    @Override // com.elvishew.xlog.printer.file.writer.Writer
    public File getOpenedFile() {
        return this.logFile;
    }

    @Override // com.elvishew.xlog.printer.file.writer.Writer
    public String getOpenedFileName() {
        return this.logFileName;
    }

    public void onNewFileCreated(File file) {
    }

    @Override // com.elvishew.xlog.printer.file.writer.Writer
    public void appendLog(String str) {
        try {
            this.bufferedWriter.write(str);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        } catch (Exception e) {
            Platform.get().warn("append log failed: " + e.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.BufferedWriter] */
    @Override // com.elvishew.xlog.printer.file.writer.Writer
    public boolean close() {
        ?? r0 = this.bufferedWriter;
        if (r0 != 0) {
            try {
                r0 = r0;
                r0.close();
            } catch (Exception unused) {
                r0.printStackTrace();
            }
        }
        this.bufferedWriter = null;
        this.logFileName = null;
        this.logFile = null;
        return true;
    }
}