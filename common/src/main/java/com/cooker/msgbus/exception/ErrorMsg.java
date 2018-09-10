package com.cooker.msgbus.exception;

import java.io.Serializable;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/06 下午5:51
 * 功能描述: 错误信息
 * 修改历史:
 */
public class ErrorMsg implements Serializable {

    private static final long serialVersionUID = -6332975345444032915L;

    private int status;
    private int code;
    private String msg;

    public ErrorMsg(int status, int code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
