package com.whu.charityangel.model.response;

import java.io.Serializable;

/**
 * @author Chris
 * @date 2021/7/9 13:12
 * @Email:gem7991@dingtalk.com
 */
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int errCode;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public BaseResponse() {
        super();
    }

    public BaseResponse(int errCode, String msg, T data) {
        super();
        this.errCode = errCode;
        this.msg = msg;
        this.data = data;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
