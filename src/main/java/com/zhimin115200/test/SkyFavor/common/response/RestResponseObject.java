package com.zhimin115200.test.SkyFavor.common.response;

import java.io.Serializable;


public class RestResponseObject extends BasicResult {

    private Serializable data;

    public RestResponseObject() {
        super();
    }

    public RestResponseObject(String code, String msg) {
        super(code, msg);
    }

    public Serializable getData() {
        return data;
    }

    public void setData(Serializable data) {
        this.data = data;
    }
    
}
