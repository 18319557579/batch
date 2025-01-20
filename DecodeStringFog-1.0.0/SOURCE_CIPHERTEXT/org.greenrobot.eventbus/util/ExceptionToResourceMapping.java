package org.greenrobot.eventbus.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.greenrobot.eventbus.Logger;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/util/ExceptionToResourceMapping.class */
public class ExceptionToResourceMapping {
    public final Map<Class<? extends Throwable>, Integer> throwableToMsgIdMap = new HashMap();

    public Integer mapThrowable(Throwable th) {
        int i = 20;
        Throwable th2 = th;
        do {
            Integer mapThrowableFlat = mapThrowableFlat(th2);
            if (mapThrowableFlat == null) {
                th2 = th2.getCause();
                int i2 = i - 1;
                i = i2;
                if (i2 <= 0 || th2 == th) {
                    break;
                }
            } else {
                return mapThrowableFlat;
            }
        } while (th2 != null);
        Logger.Default.get().log(Level.FINE, "No specific message resource ID found for " + th);
        return null;
    }

    public Integer mapThrowableFlat(Throwable th) {
        Class<?> cls = th.getClass();
        Integer num = this.throwableToMsgIdMap.get(cls);
        Integer num2 = num;
        if (num == null) {
            Class<? extends Throwable> cls2 = null;
            for (Map.Entry<Class<? extends Throwable>, Integer> entry : this.throwableToMsgIdMap.entrySet()) {
                Class<? extends Throwable> key = entry.getKey();
                if (key.isAssignableFrom(cls) && (cls2 == null || cls2.isAssignableFrom(key))) {
                    cls2 = key;
                    num2 = entry.getValue();
                }
            }
        }
        return num2;
    }

    public ExceptionToResourceMapping addMapping(Class<? extends Throwable> cls, int i) {
        this.throwableToMsgIdMap.put(cls, Integer.valueOf(i));
        return this;
    }
}