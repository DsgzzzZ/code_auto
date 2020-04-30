package com.win.common;

/**
 * @ClassName CommonResultVO
 * @Description TODO(构造返回参数类)
 * @author huiziqin
 * @Date 2018年1月8日 下午1:00:20
 * @version 1.0.0
 * @param <T>
 */
public class CommonResultVO<T> {

    private int code;

    private String message;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
