package com.hdsx.lwgl.statanalysis.entity;

public class ReturnMessage
{
    private int code;
    private Object data;
    private Long total;
    private String message;

    public int getCode()
    {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public Long getTotal() {
        return this.total;
    }
    public void setTotal(Long total) { this.total = total; }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}