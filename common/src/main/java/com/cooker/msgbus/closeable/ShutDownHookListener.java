package com.cooker.msgbus.closeable;

import com.cooker.msgbus.util.LoggerUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/07 下午1:48
 * 功能描述: 通过web中间件关闭服务，不需要kill [PID]
 * 修改历史:
 */
public class ShutDownHookListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LoggerUtil.info("ShutDownHookListener to call ShutDownHook stop server");
        ShutDownHook.runHook(true);
    }
}
