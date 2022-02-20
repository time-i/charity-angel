package com.whu.charityangel.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whu.charityangel.common.exception.BizException;
import com.whu.charityangel.common.exception.BizException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Chris
 * @date 2021/7/9 14:32
 * @Email:gem7991@dingtalk.com
 */


@ApiModel(value="返回参数",description="返回参数对象")
public class ResponseMsg<T> implements Serializable {
    //请求流水号
    @ApiModelProperty(value="报文编号",name="msgId",required=false)
    private String msgId;
    //返回数据
    @ApiModelProperty(value="返回数据",name="data",required=false)
    private T data;
    //状态码
    @ApiModelProperty(value="状态码",name="rtCode",required=true)
    private String rtCode;
    //描述信息
    @ApiModelProperty(value="描述信息",name="message",required=false)
    private String message;

    public ResponseMsg() {
    }

    public ResponseMsg(String rtCode, String message){
        this(null, null , rtCode, message);
    }

    public ResponseMsg(String msgId, T data, String rtCode, String message) {
        this.msgId = msgId;
        this.data = data;
        this.rtCode = rtCode;
        this.message = message;
    }


    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRtCode() {
        return rtCode;
    }

    public void setRtCode(String rtCode) {
        this.rtCode = rtCode;
    }


    public static <T> ResponseMsg<T> successResponse(){
        return new ResponseMsg<T>(null,null,"0","操作成功");
    }

    public static <T> ResponseMsg<T> successResponse(T data){
        return new ResponseMsg<T>(null,data, StatusCode.MSG_OK.getRtCode(), StatusCode.MSG_OK.getMsgCn());
    }

    public static <T> ResponseMsg<T> errorResponse(String code ,String message){


        return new ResponseMsg<T>(null,null,code,message);


    }

    public static <T> ResponseMsg<T> errorResponse(String message){


        return new ResponseMsg<T>(null,null,"1",message);


    }


    public static <T> ResponseMsg<T> errorResponse(StatusCode StatusCode){


        return new ResponseMsg<T>(null,null,StatusCode.getRtCode(),StatusCode.getMsgCn());

    }

    public static <T> ResponseMsg<T> errorResponse(StatusCode StatusCode, T data) {
        return new ResponseMsg<T>(null, data,
                StatusCode.getRtCode(),
                StatusCode.getMsgCn());
    }



    public static <T> ResponseMsg<T> buildSuccessResponse(String msg){
        return new ResponseMsg<T>(null, null, StatusCode.MSG_OK.getRtCode(), msg);
    }

    public static <T> ResponseMsg<T> buildSuccessResponse(String rtCode, String msg){
        return new ResponseMsg<T>(null, null, rtCode, msg);
    }


    public static <T> ResponseMsg<T> errorResponse(SalixError salixError){
        return new ResponseMsg<T>(null,null,salixError.getRtCode(),salixError.getMsgCn());
    }

    public static <T> ResponseMsg<T> errorResponse(SalixError salixError, T data) {
        return new ResponseMsg<T>(null, data,
                salixError.getRtCode(),
                salixError.getMsgCn());
    }


    @Override
    public String toString() {
        return "ResponseMsg{" +
                "msgId='" + msgId + '\'' +
                ", data=" + data +
                ", rtCode='" + rtCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new BizException(e.getMessage());
        }
        return json;
    }
}

