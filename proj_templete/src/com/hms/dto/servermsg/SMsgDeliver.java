package com.hms.dto.servermsg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.hms.dto.CDataAdapter;

/**
 * 上行消息消息体
 * 
 * @author wangq
 * @version $Id: SMsgDeliver.java, v 0.1 2014-11-11 下午1:48:39 wangq Exp $
 */
@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SMsgDeliver {

    /** 消息业务类型*/
    private String msgbiztype;

    /** 消息ID*/
    private String msg_id;

    /** 消息发送方标识*/
    private String sender;

    /** 消息摘要，网关使用参数【可选】 */
    private String msgdigest;

    /** 消息内容*/
    private String msg_content;

    /** 信息长度*/
    private Long   msg_length;

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
     * Getter method for property <tt>msg_id</tt>.
     * 
     * @return property value of msg_id
     */
    public String getMsg_id() {
        return msg_id;
    }

    /**
     * Setter method for property <tt>msg_id</tt>.
     * 
     * @param msg_id value to be assigned to property msg_id
     */
    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
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
     * Getter method for property <tt>msg_content</tt>.
     * 
     * @return property value of msg_content
     */
    @XmlJavaTypeAdapter(CDataAdapter.class)
    public String getMsg_content() {
        return msg_content;
    }

    /**
     * Setter method for property <tt>msg_content</tt>.
     * 
     * @param msg_content value to be assigned to property msg_content
     */
    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    /**
     * Getter method for property <tt>msg_length</tt>.
     * 
     * @return property value of msg_length
     */
    public Long getMsg_length() {
        return msg_length;
    }

    /**
     * Setter method for property <tt>msg_length</tt>.
     * 
     * @param msg_length value to be assigned to property msg_length
     */
    public void setMsg_length(Long msg_length) {
        this.msg_length = msg_length;
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

}
