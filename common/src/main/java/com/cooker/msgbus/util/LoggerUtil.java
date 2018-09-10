package com.cooker.msgbus.util;

import com.cooker.msgbus.log.DefaultLogService;
import com.cooker.msgbus.log.LogService;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/05 下午3:46
 * 功能描述:
 * 修改历史:
 */
public class LoggerUtil{
    // 可以通过设置为不同logservice控制log行为。
    private static LogService logService = new DefaultLogService();

    public static void trace(String var1) {
        logService.trace(var1);
    }

    public static void trace(String var1, Object var2) {
        logService.trace(var1, var2);
    }

    public static void trace(String var1, Object var2, Object var3) {
        logService.trace(var1, var2, var3);
    }

    public static void trace(String var1, Object... var2) {
        logService.trace(var1, var2);
    }

    public static void trace(String var1, Throwable var2) {
        logService.trace(var1, var2);
    }

    public static boolean isTraceEnabled() {
        return logService.isTraceEnabled();
    }

    public static void debug(String var1) {
        logService.debug(var1);
    }

    public static void debug(String var1, Object var2) {
        logService.debug(var1, var2);
    }

    public static void debug(String var1, Object var2, Object var3) {
        logService.debug(var1, var2, var3);
    }

    public static void debug(String var1, Object... var2) {
        logService.debug(var1, var2);
    }

    public static void debug(String var1, Throwable var2) {
        logService.debug(var1, var2);
    }

    public static boolean isDebugEnabled() {
        return logService.isDebugEnabled();
    }

    public static void info(String var1) {
        logService.info(var1);
    }

    public static void info(String var1, Object var2) {
        logService.info(var1, var2);
    }

    public static void info(String var1, Object var2, Object var3) {
        logService.info(var1, var2, var3);
    }

    public static void info(String var1, Object... var2) {
        logService.info(var1, var2);
    }

    public static void info(String var1, Throwable var2) {
        logService.info(var1, var2);
    }

    public static boolean isInfoEnabled() {
        return logService.isInfoEnabled();
    }

    public static void warn(String var1) {
        logService.warn(var1);
    }

    public static void warn(String var1, Object var2) {
        logService.warn(var1, var2);
    }

    public static void warn(String var1, Object... var2) {
        logService.warn(var1, var2);
    }

    public static void warn(String var1, Object var2, Object var3) {
        logService.warn(var1, var2, var3);
    }

    public static void warn(String var1, Throwable var2) {
        logService.warn(var1, var2);
    }

    public static boolean isWarnEnabled() {
        return logService.isWarnEnabled();
    }

    public static void error(String var1) {
        logService.error(var1);
    }

    public static void error(String var1, Object var2) {
        logService.error(var1, var2);
    }

    public static void error(String var1, Object var2, Object var3) {
        logService.error(var1, var2, var3);
    }

    public static void error(String var1, Object... var2) {
        logService.error(var1, var2);
    }

    public static void error(String var1, Throwable var2) {
        logService.error(var1, var2);
    }

    public static boolean isErrorEnabled() {
        return logService.isErrorEnabled();
    }
}
