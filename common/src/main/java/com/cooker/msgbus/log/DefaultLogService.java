package com.cooker.msgbus.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/05 下午3:40
 * 功能描述:
 * 修改历史:
 */
public class DefaultLogService implements LogService {
    private static Logger trace = LoggerFactory.getLogger("trace");
    private static Logger debug = LoggerFactory.getLogger("debug");
    private static Logger info = LoggerFactory.getLogger("info");
    private static Logger warn = LoggerFactory.getLogger("warn");
    private static Logger error = LoggerFactory.getLogger("error");

    @Override
    public void trace(String msg) {
        trace.trace(msg);
    }

    @Override
    public void trace(String var1, Object var2) {
        trace.trace(var1, var2);
    }

    @Override
    public void trace(String var1, Object var2, Object var3) {
        trace.trace(var1, var2, var3);
    }

    @Override
    public void trace(String format, Object... argArray) {
        trace.trace(format, argArray);
    }

    @Override
    public void trace(String var1, Throwable var2) {
        trace.trace(var1, var2);
    }

    @Override
    public void debug(String msg) {
        debug.debug(msg);
    }

    @Override
    public void debug(String var1, Object var2) {
        debug.debug(var1, var2);
    }

    @Override
    public void debug(String var1, Object var2, Object var3) {
        debug.debug(var1, var2, var3);
    }

    @Override
    public void debug(String format, Object... argArray) {
        debug.debug(format, argArray);
    }

    @Override
    public void debug(String msg, Throwable t) {
        debug.debug(msg, t);
    }

    @Override
    public void info(String msg) {
        info.info(msg);
    }

    @Override
    public void info(String var1, Object var2) {
        info.info(var1, var2);
    }

    @Override
    public void info(String var1, Object var2, Object var3) {
        info.info(var1, var2, var3);
    }

    @Override
    public void info(String format, Object... argArray) {
        info.info(format, argArray);
    }

    @Override
    public void info(String msg, Throwable t) {
        info.info(msg, t);
    }

    @Override
    public void warn(String msg) {
        warn.warn(msg);
    }

    @Override
    public void warn(String var1, Object var2) {
        warn.warn(var1, var2);
    }

    @Override
    public void warn(String format, Object... argArray) {
        warn.warn(format, argArray);
    }

    @Override
    public void warn(String var1, Object var2, Object var3) {
        warn.warn(var1, var2, var3);
    }

    @Override
    public void warn(String msg, Throwable t) {
        warn.warn(msg, t);
    }

    @Override
    public void error(String msg) {
        error.error(msg);
    }

    @Override
    public void error(String var1, Object var2) {
        error.error(var1, var2);
    }

    @Override
    public void error(String var1, Object var2, Object var3) {
        error.error(var1, var2, var3);
    }

    @Override
    public void error(String format, Object... argArray) {
        error.error(format, argArray);
    }

    @Override
    public void error(String msg, Throwable t) {
        error.error(msg, t);
    }

    @Override
    public boolean isTraceEnabled() {
        return trace.isTraceEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return debug.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return info.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return warn.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return error.isErrorEnabled();
    }

}
