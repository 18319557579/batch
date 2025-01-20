package com.elvishew.xlog.internal.util;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/internal/util/ObjectToStringUtil.class */
public class ObjectToStringUtil {
    public static String bundleToString(Bundle bundle) {
        if (bundle == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append("Bundle[{");
        bundleToShortString(bundle, sb);
        sb.append("}]");
        return sb.toString();
    }

    public static String intentToString(Intent intent) {
        if (intent == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append("Intent { ");
        intentToShortString(intent, sb);
        sb.append(" }");
        return sb.toString();
    }

    private static void bundleToShortString(Bundle bundle, StringBuilder sb) {
        boolean z = true;
        for (String str : bundle.keySet()) {
            boolean z2 = z;
            if (!z2) {
                sb.append(", ");
            }
            sb.append(str).append('=');
            Object obj = bundle.get(str);
            if (obj instanceof int[]) {
                sb.append(Arrays.toString((int[]) obj));
            } else if (obj instanceof byte[]) {
                sb.append(Arrays.toString((byte[]) obj));
            } else if (obj instanceof boolean[]) {
                sb.append(Arrays.toString((boolean[]) obj));
            } else if (obj instanceof short[]) {
                sb.append(Arrays.toString((short[]) obj));
            } else if (obj instanceof long[]) {
                sb.append(Arrays.toString((long[]) obj));
            } else if (obj instanceof float[]) {
                sb.append(Arrays.toString((float[]) obj));
            } else if (obj instanceof double[]) {
                sb.append(Arrays.toString((double[]) obj));
            } else if (obj instanceof String[]) {
                sb.append(Arrays.toString((String[]) obj));
            } else if (obj instanceof CharSequence[]) {
                sb.append(Arrays.toString((CharSequence[]) obj));
            } else if (obj instanceof Parcelable[]) {
                sb.append(Arrays.toString((Parcelable[]) obj));
            } else if (obj instanceof Bundle) {
                sb.append(bundleToString((Bundle) obj));
            } else {
                sb.append(obj);
            }
            z = false;
        }
    }

    private static void intentToShortString(Intent intent, StringBuilder sb) {
        Intent selector;
        boolean z = true;
        String action = intent.getAction();
        if (action != null) {
            sb.append("act=").append(action);
            z = false;
        }
        Set<String> categories = intent.getCategories();
        if (categories != null) {
            if (!z) {
                sb.append(' ');
            }
            z = false;
            sb.append("cat=[");
            boolean z2 = true;
            for (String str : categories) {
                boolean z3 = z2;
                if (!z3) {
                    sb.append(',');
                }
                sb.append(str);
                z2 = false;
            }
            sb.append("]");
        }
        Uri data = intent.getData();
        if (data != null) {
            if (!z) {
                sb.append(' ');
            }
            z = false;
            sb.append("dat=");
            if (Build.VERSION.SDK_INT >= 14) {
                sb.append(uriToSafeString(data));
            } else {
                String scheme = data.getScheme();
                if (scheme == null) {
                    sb.append(data);
                } else if (scheme.equalsIgnoreCase("tel")) {
                    sb.append("tel:xxx-xxx-xxxx");
                } else if (scheme.equalsIgnoreCase("smsto")) {
                    sb.append("smsto:xxx-xxx-xxxx");
                } else {
                    sb.append(data);
                }
            }
        }
        String type = intent.getType();
        if (type != null) {
            if (!z) {
                sb.append(' ');
            }
            z = false;
            sb.append("typ=").append(type);
        }
        int flags = intent.getFlags();
        if (flags != 0) {
            if (!z) {
                sb.append(' ');
            }
            z = false;
            sb.append("flg=0x").append(Integer.toHexString(flags));
        }
        String str2 = intent.getPackage();
        if (str2 != null) {
            if (!z) {
                sb.append(' ');
            }
            z = false;
            sb.append("pkg=").append(str2);
        }
        ComponentName component = intent.getComponent();
        if (component != null) {
            if (!z) {
                sb.append(' ');
            }
            z = false;
            sb.append("cmp=").append(component.flattenToShortString());
        }
        Rect sourceBounds = intent.getSourceBounds();
        if (sourceBounds != null) {
            if (!z) {
                sb.append(' ');
            }
            z = false;
            sb.append("bnds=").append(sourceBounds.toShortString());
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 16 && intent.getClipData() != null) {
            if (!z) {
                sb.append(' ');
            }
            z = false;
            sb.append("(has clip)");
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (!z) {
                sb.append(' ');
            }
            sb.append("extras={");
            bundleToShortString(extras, sb);
            sb.append('}');
        }
        if (i < 15 || (selector = intent.getSelector()) == null) {
            return;
        }
        sb.append(" sel=");
        intentToShortString(selector, sb);
        sb.append("}");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [int] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.String] */
    private static String uriToSafeString(Uri uri) {
        ?? r0 = Build.VERSION.SDK_INT;
        if (r0 >= 14) {
            try {
                Method declaredMethod = Uri.class.getDeclaredMethod("toSafeString", new Class[0]);
                declaredMethod.setAccessible(true);
                r0 = (String) declaredMethod.invoke(uri, new Object[0]);
                return r0;
            } catch (IllegalAccessException unused) {
                r0.printStackTrace();
            } catch (NoSuchMethodException unused2) {
                r0.printStackTrace();
            } catch (InvocationTargetException unused3) {
                r0.printStackTrace();
            }
        }
        return uri.toString();
    }
}