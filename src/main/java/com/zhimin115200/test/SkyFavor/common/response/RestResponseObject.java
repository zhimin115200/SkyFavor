package com.zhimin115200.test.SkyFavor.common.response;


import com.alibaba.fastjson.JSONObject;

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
        if (data != null) {
            JSONObject jsonObject;
            try {
                jsonObject = JSONObject.parseObject(JSONObject.toJSONString(data));
            } catch (Exception e) {
                return data;
            }
            return jsonObject;
        } else {
            return null;
        }
    }

    public void setData(Serializable data) {
        this.data = data;
    }
    
}
