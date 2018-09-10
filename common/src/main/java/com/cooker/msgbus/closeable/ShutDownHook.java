package com.cooker.msgbus.closeable;

import com.cooker.msgbus.util.LoggerUtil;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.Closeable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/07 上午11:26
 * 功能描述: 全局关闭钩子
 * 修改历史:
 */
public class ShutDownHook extends Thread{

    //默认优先级，值越小越早关闭
    private final static int defaultPriority = 20;
    private static ShutDownHook instance;
    private static List<CloseObject> closes = new LinkedList<>();

    public static void init(){
        if(instance == null){
            instance = new ShutDownHook();
            LoggerUtil.info("ShutDownHook is init ok...");
        }
    }

    public static void register(Closeable closeable){
        register(closeable, defaultPriority);
    }

    public static synchronized void register(Closeable closeable, int priority){
        if (instance == null){
            init();
        }
        CloseObject closeObject = new CloseObject(priority, closeable);
        closes.add(closeObject);
        LoggerUtil.info("ShutDownHook add closeable：{}", closeable.getClass());
    }

    public static void runHook(boolean sync){
        if(instance != null){
            if(sync)
                instance.run();
            else
                instance.start();
        }
    }

    private synchronized void closeAll(){
        Collections.sort(closes);
        for (CloseObject res : closes){
            Closeable close = res.closeable;
            try {
                close.close();
            }catch (Exception e){
                LoggerUtil.error("ShutDownHook close {} fail", close.getClass(), e);
            }
            LoggerUtil.info("ShutDownHook close {} success", close.getClass());
        }
        closes.clear();
    }

    @Override
    public void run() {
        closeAll();
    }

    static class CloseObject implements Comparable<CloseObject>{
        int priority;
        Closeable closeable;

        public CloseObject(int priority, Closeable closeable) {
            this.priority = priority;
            this.closeable = closeable;
        }

        @Override
        public int compareTo(CloseObject o) {
            return NumberUtils.compare(this.priority, o.priority);
        }
    }

}
