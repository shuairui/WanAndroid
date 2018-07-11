package com.example.library.utils;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class LogUtils {
    public static boolean mLogEnable = true;
    private static String mClassname = LogUtils.class.getName();
    private static ArrayList<String> mMethods = new ArrayList<>();

    public LogUtils() {
    }

    public static void init(boolean logEnable) {
        mLogEnable = logEnable;
    }

    public static void d(String tag, String msg) {
        if (mLogEnable) {
            Log.d(tag, getMsgWithLineNumber(msg));
        }

    }

    public static void e(String tag, String msg) {
        if (mLogEnable) {
            Log.e(tag, getMsgWithLineNumber(msg));
        }

    }

    public static void i(String tag, String msg) {
        if (mLogEnable) {
            Log.i(tag, getMsgWithLineNumber(msg));
        }

    }

    public static void w(String tag, String msg) {
        if (mLogEnable) {
            Log.w(tag, getMsgWithLineNumber(msg));
        }

    }

    public static void v(String tag, String msg) {
        if (mLogEnable) {
            Log.v(tag, getMsgWithLineNumber(msg));
        }

    }

    public static void d(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.d(content[0], content[1]);
        }

    }

    public static void e(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.e(content[0], content[1]);
        }

    }

    public static void i(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.i(content[0], content[1]);
        }

    }

    public static void i() {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber("");
            Log.i(content[0], content[1]);
        }

    }

    public static void w(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.w(content[0], content[1]);
        }

    }

    public static void v(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.v(content[0], content[1]);
        }

    }

    public static String getMsgWithLineNumber(String msg) {
        try {
            StackTraceElement[] e = (new Throwable()).getStackTrace();
            int var2 = e.length;

            for (StackTraceElement st : e) {
                if (!mClassname.equals(st.getClassName()) && !mMethods.contains(st.getMethodName())) {
                    int b = st.getClassName().lastIndexOf(".") + 1;
                    String TAG = st.getClassName().substring(b);
                    return TAG + "->" + st.getMethodName() + "():" + st.getLineNumber() + "->" + msg;
                }
            }
        } catch (Exception var8) {
        }

        return msg;
    }

    public static String[] getMsgAndTagWithLineNumber(String msg) {
        try {
            StackTraceElement[] e = (new Throwable()).getStackTrace();
            int var2 = e.length;

            for (StackTraceElement st : e) {
                if (!mClassname.equals(st.getClassName()) && !mMethods.contains(st.getMethodName())) {
                    int b = st.getClassName().lastIndexOf(".") + 1;
                    String TAG = st.getClassName().substring(b);
                    String message = st.getMethodName() + "():" + st.getLineNumber() + "->" + msg;
                    return new String[]{TAG, message};
                }
            }
        } catch (Exception var9) {
        }

        return new String[]{"universal tag", msg};
    }

    static {
        Method[] ms = LogUtils.class.getDeclaredMethods();
        for (Method m : ms) {
            mMethods.add(m.getName());
        }
    }

    /**
     * 截断输出日志
     *
     * @param msg
     */
    public static void all_e(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.e(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// 打印剩余日志
        }
    }
}
