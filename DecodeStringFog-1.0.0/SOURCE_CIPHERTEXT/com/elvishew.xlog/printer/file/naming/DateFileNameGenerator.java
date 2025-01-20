package com.elvishew.xlog.printer.file.naming;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/naming/DateFileNameGenerator.class */
public class DateFileNameGenerator implements FileNameGenerator {
    ThreadLocal<SimpleDateFormat> mLocalDateFormat = new EvLveE1();

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/naming/DateFileNameGenerator$EvLveE1.class */
    public class EvLveE1 extends ThreadLocal<SimpleDateFormat> {
        public EvLveE1() {
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        }
    }

    @Override // com.elvishew.xlog.printer.file.naming.FileNameGenerator
    public boolean isFileNameChangeable() {
        return true;
    }

    @Override // com.elvishew.xlog.printer.file.naming.FileNameGenerator
    public String generateFileName(int i, long j) {
        SimpleDateFormat simpleDateFormat = this.mLocalDateFormat.get();
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date(j));
    }
}