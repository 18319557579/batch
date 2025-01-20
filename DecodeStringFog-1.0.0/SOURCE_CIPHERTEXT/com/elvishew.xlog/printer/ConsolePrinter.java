package com.elvishew.xlog.printer;

import com.elvishew.xlog.flattener.Flattener;
import com.elvishew.xlog.internal.DefaultsFactory;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/ConsolePrinter.class */
public class ConsolePrinter implements Printer {
    private Flattener flattener;

    public ConsolePrinter() {
        this.flattener = DefaultsFactory.createFlattener();
    }

    @Override // com.elvishew.xlog.printer.Printer
    public void println(int i, String str, String str2) {
        System.out.println(this.flattener.flatten(i, str, str2).toString());
    }

    public ConsolePrinter(Flattener flattener) {
        this.flattener = flattener;
    }
}