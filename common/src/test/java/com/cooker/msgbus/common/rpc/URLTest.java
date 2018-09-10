package com.cooker.msgbus.common.rpc;

import com.cooker.msgbus.rpc.URL;
import org.junit.Test;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/06 下午6:25
 * 功能描述:
 * 修改历史:
 */
public class URLTest {

    @Test
    public void valueOfURL(){
      URL url = URL.valueOf("netty://22:80/xxx?sa=xx&xx=w&xx=saw");
      System.out.println(url);

      url = URL.valueOf("netty://22:80/?sa=xx&xx=w&xx=saw");
      System.out.println(url);
    }

}
