package com.qf.beautifulapp.result;

public enum ResponseCode {
    SUCCESS("200","success"),
    FAILED("500","服务器错误");
    private  String code;

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

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
