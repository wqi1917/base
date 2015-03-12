/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.dto;

import java.util.Date;

/**
 * 消息基本信息
 * 
 * @author wangq
 * @version $Id: MsgInfoDTO.java, v 0.1 2014-11-11 上午11:14:27 wangq Exp $
 */
public class MsgInfoDTO {

    /** 消息业务类型，网关使用参数【必选】 */
    private String msgbiztype;

    /** 消息业务id，网关使用参数【必选】 */
    private String msgid;

    /** 消息摘要，网关使用参数【可选】 */
    private String msgdigest;

    /**  接收者，多个接收者以“;"分隔，IM-AS参数【必选】 */
    private String receivers;

    /** 延时发送，延时策略【可选】 */
    private String delay;

    /** 消息发送方标识*/
    private String sender;

    /** 创建时间 */
    private Date   createDate;

    public MsgInfoDTO() {

    }

    public MsgInfoDTO(String msgbiztype, String msgid, String msgdigest, String receivers,
                      String delay) {
        super();
        this.msgbiztype = msgbiztype;
        this.msgid = msgid;
        this.msgdigest = msgdigest;
        this.receivers = receivers;
        this.delay = delay;
        this.createDate = new Date();
    }

    public MsgInfoDTO(String msgbiztype, String msgid, String msgdigest, String sender) {
        super();
        this.msgbiztype = msgbiztype;
        this.msgid = msgid;
        this.msgdigest = msgdigest;
        this.sender = sender;
        this.createDate = new Date();
    }

    /**
     * Getter method for property <tt>msgbiztype</tt>.
     * 
     * @return property value of msgbiztype
     */
    public String getMsgbiztype() {
        return msgbiztype;
    }

    /**
     * Setter method for property <tt>msgbiztype</tt>.
     * 
     * @param msgbiztype value to be assigned to property msgbiztype
     */
    public void setMsgbiztype(String msgbiztype) {
        this.msgbiztype = msgbiztype;
    }

    /**
     * Getter method for property <tt>msgid</tt>.
     * 
     * @return property value of msgid
     */
    public String getMsgid() {
        return msgid;
    }

    /**
     * Setter method for property <tt>msgid</tt>.
     * 
     * @param msgid value to be assigned to property msgid
     */
    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    /**
     * Getter method for property <tt>msgdigest</tt>.
     * 
     * @return property value of msgdigest
     */
    public String getMsgdigest() {
        return msgdigest;
    }

    /**
     * Setter method for property <tt>msgdigest</tt>.
     * 
     * @param msgdigest value to be assigned to property msgdigest
     */
    public void setMsgdigest(String msgdigest) {
        this.msgdigest = msgdigest;
    }

    /**
     * Getter method for property <tt>receivers</tt>.
     * 
     * @return property value of receivers
     */
    public String getReceivers() {
        return receivers;
    }

    /**
     * Setter method for property <tt>receivers</tt>.
     * 
     * @param receivers value to be assigned to property receivers
     */
    public void setReceivers(String receivers) {
        this.receivers = receivers;
    }

    /**
     * Getter method for property <tt>delay</tt>.
     * 
     * @return property value of delay
     */
    public String getDelay() {
        return delay;
    }

    /**
     * Setter method for property <tt>delay</tt>.
     * 
     * @param delay value to be assigned to property delay
     */
    public void setDelay(String delay) {
        this.delay = delay;
    }

    /**
     * Getter method for property <tt>sender</tt>.
     * 
     * @return property value of sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * Setter method for property <tt>sender</tt>.
     * 
     * @param sender value to be assigned to property sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Getter method for property <tt>createDate</tt>.
     * 
     * @return property value of createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Setter method for property <tt>createDate</tt>.
     * 
     * @param createDate value to be assigned to property createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
