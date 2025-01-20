package com.elvishew.xlog.printer.file.backup;

import java.io.File;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/backup/FileSizeBackupStrategy2.class */
public class FileSizeBackupStrategy2 extends AbstractBackupStrategy {
    private long maxSize;
    private int maxBackupIndex;

    public FileSizeBackupStrategy2(long j, int i) {
        this.maxSize = j;
        this.maxBackupIndex = i;
    }

    @Override // com.elvishew.xlog.printer.file.backup.BackupStrategy
    public boolean shouldBackup(File file) {
        return file.length() > this.maxSize;
    }

    @Override // com.elvishew.xlog.printer.file.backup.BackupStrategy2
    public int getMaxBackupIndex() {
        return this.maxBackupIndex;
    }
}