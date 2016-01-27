package com.softdesign.school.utils;
import android.util.Log;

import java.lang.Integer;
import java.lang.String;

public class Lg {
    /*Префикс сообщений в логгере*/
    private static final String PREFIX = "SCHOOL-SOFTDESIGN";
    /*максимальное значение логируемого значения*/
    public static final int LOGCAT_BUFFER_SIZE = 3000;
    /*Проверка на включение логирования*/
    private static boolean shouldLog() {
//        return BuildConfig.IS_LOGCAT_LOGGER_ENABLED;
//        return true;
        return true;
    }
    /*отправка сообщения на уровне VERBOSE*/
    public static void v(String tag, String msg) {
        helpLog(Log.VERBOSE, tag, msg);
    }
    /*отправка сообщения на уровне DEBUG*/
    public static void d(String tag, String msg) {
        helpLog(Log.DEBUG, tag, msg);
    }
    /*отправка сообщения на уровне INFO*/
    public static void i(String tag, String msg) {
        helpLog(Log.INFO, tag, msg);
    }
    /*отправка сообщения на уровне WARN*/
    public static void w(String tag, String msg) {
        helpLog(Log.WARN, tag, msg);
    }
    /*отправка сообщения на уровне ERROR*/
    public static void e(String tag, String msg) {
        helpLog(Log.ERROR, tag, msg);
    }
    /*отправка сообщения на уровне ASSERT*/
    public static void a(String tag, String msg) {
        helpLog(Log.ASSERT, tag, msg);
    }
    /*Обработка для логгера, форматирование логгера */
    public static void helpLog(Integer level, String tag, String msg) {
        if (shouldLog()) {
            String str = msg;

            while (str.length() > LOGCAT_BUFFER_SIZE) {
                String substr = str.substring(0, LOGCAT_BUFFER_SIZE);
                str = substr.substring(LOGCAT_BUFFER_SIZE);
                Log(level, PREFIX + tag, substr);
            }
            Log(level, PREFIX + tag, str);
        }
    }
    /*вызывает метод этого класса соответтвующий 1му из этих 6ти уровней*/
    private static void Log(Integer level, String tag, String msg) {
        switch (level) {
            case Log.VERBOSE:
                Log.v(tag, msg);
                break;
            case Log.DEBUG:
                Log.d(tag, msg);
                break;
            case Log.INFO:
                Log.i(tag, msg);
                break;
            case Log.WARN:
                Log.w(tag, msg);
                break;
            case Log.ERROR:
                Log.e(tag, msg);
                break;
            case Log.ASSERT:
                Log.println(Log.ASSERT, tag, msg);
                break;

        }
    }


}
