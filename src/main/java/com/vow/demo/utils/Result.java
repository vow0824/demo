package com.vow.demo.utils;

/**
 * @author: wushaopeng
 * @date: 2022/11/21 14:27
 */
public class Result<T> {

    private String returnCode;

    private String returnMsg;

    private T returnData;

    public static <T> Result<T> success(T t) {
        Result<T> result = new Result<T>();
        result.setReturnCode("200");
        result.setReturnMsg("success");
        result.setReturnData(t);
        return result;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.setReturnCode("200");
        result.setReturnMsg("success");
        return result;
    }


    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public T getReturnData() {
        return returnData;
    }

    public void setReturnData(T returnData) {
        this.returnData = returnData;
    }
}
