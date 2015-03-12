package com.hms.dto.composite;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 文本消息 消息体
 * 
 * @author wangq
 * @version $Id: CSTextMsgContent.java, v 0.1 2014-11-11 上午10:57:54 wangq Exp $
 */
@XmlRootElement(name = "textmsg")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CSTextMsgContent {

    /** 消息业务类型，网关使用参数【必选】 */
    private String  msgbiztype;

    /** 消息业务id，网关使用参数【必选】 */
    private String  msgid;

    /** 素材类型*/
    private Integer media_type;
    /** 创建时间*/
    private Date    create_time;
    /** 摘要*/
    private String  sms_digest;
    /** 消息内容*/
    private String  text;
    /** 业务扩展*/
    private String  extparam;

    /**
     * Getter method for property <tt>media_type</tt>.
     * 
     * @return property value of media_type
     */
    public Integer getMedia_type() {
        return media_type;
    }

    /**
     * Setter method for property <tt>media_type</tt>.
     * 
     * @param media_type value to be assigned to property media_type
     */
    public void setMedia_type(Integer media_type) {
        this.media_type = media_type;
    }

    /**
     * Getter method for property <tt>create_time</tt>.
     * 
     * @return property value of create_time
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * Setter method for property <tt>create_time</tt>.
     * 
     * @param create_time value to be assigned to property create_time
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    /**
     * Getter method for property <tt>sms_digest</tt>.
     * 
     * @return property value of sms_digest
     */
    public String getSms_digest() {
        return sms_digest;
    }

    /**
     * Setter method for property <tt>sms_digest</tt>.
     * 
     * @param sms_digest value to be assigned to property sms_digest
     */
    public void setSms_digest(String sms_digest) {
        this.sms_digest = sms_digest;
    }

    /**
     * Getter method for property <tt>text</tt>.
     * 
     * @return property value of text
     */
    public String getText() {
        return text;
    }

    /**
     * Setter method for property <tt>text</tt>.
     * 
     * @param text value to be assigned to property text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Getter method for property <tt>extparam</tt>.
     * 
     * @return property value of extparam
     */
    public String getExtparam() {
        return extparam;
    }

    /**
     * Setter method for property <tt>extparam</tt>.
     * 
     * @param extparam value to be assigned to property extparam
     */
    public void setExtparam(String extparam) {
        this.extparam = extparam;
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

}
