/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.message;

/**
 * 处理结果
 * @author wangqi
 * @version $Id: HandlerResult.java, v 0.1 2014-5-28 上午10:53:03 wangqi Exp $
 */
public class MessageResult {

    /** 是否成功     */
    private boolean isSuccess = true;

    /** 返回结果    */
    private String  response;

    /** 状态     */
    private String  status;

    /** 异常码    */
    private String  error_code;

    /** 异常描述    */
    private String  reason;

    /**
     * 构造
     * @param resultObj
     */
    public MessageResult() {
    }

    /**
     * 构造
     * @param resultObj
     */
    public MessageResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * 构造
     * @param resultObj
     */
    public MessageResult(String response) {
        this.response = response;
    }

    public MessageResult(String status, String error_code, String reason) {
        this.isSuccess = false;
        this.status = status;
        this.error_code = error_code;
        this.reason = reason;
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append("status=" + status);
        bld.append(";error_code=" + error_code);
        bld.append(";reason=" + reason);
        bld.append(";response=" + response);
        return bld.toString();
    }

    /**
     * Getter method for property <tt>isSuccess</tt>.
     * 
     * @return property value of isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * Setter method for property <tt>isSuccess</tt>.
     * 
     * @param isSuccess value to be assigned to property isSuccess
     */
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * Getter method for property <tt>status</tt>.
     * 
     * @return property value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     * 
     * @param status value to be assigned to property status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter method for property <tt>error_code</tt>.
     * 
     * @return property value of error_code
     */
    public String getError_code() {
        return error_code;
    }

    /**
     * Setter method for property <tt>error_code</tt>.
     * 
     * @param error_code value to be assigned to property error_code
     */
    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    /**
     * Getter method for property <tt>reason</tt>.
     * 
     * @return property value of reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Setter method for property <tt>reason</tt>.
     * 
     * @param reason value to be assigned to property reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
