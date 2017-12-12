package com.zhimin115200.test.SkyFavor.common.response;

public class BasicResult {

    public BasicResult() {

    }

    public BasicResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 代码 0为成功 -1为错误
     */
    private String code;

    /**
     * 成功或错误信息
     */
    private String msg;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
