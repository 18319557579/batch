package com.elvishew.xlog.printer.file.backup;

import java.io.File;

@Deprecated
/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/backup/FileSizeBackupStrategy.class */
public class FileSizeBackupStrategy implements BackupStrategy {
    private long maxSize;

    public FileSizeBackupStrategy(long j) {
        this.maxSize = j;
    }

    @Override // com.elvishew.xlog.printer.file.backup.BackupStrategy
    public boolean shouldBackup(File file) {
        return file.length() > this.maxSize;
    }
}