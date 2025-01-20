package com.elvishew.xlog.printer.file.backup;

import java.io.File;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/backup/BackupStrategy.class */
public interface BackupStrategy {
    boolean shouldBackup(File file);
}