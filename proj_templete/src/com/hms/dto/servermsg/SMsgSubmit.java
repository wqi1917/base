package com.hms.dto.servermsg;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.hms.dto.CDataAdapter;

@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "msg_id", "sms_digest", "sender", "receiver_num", "receiver", "msg_level",
                      "msg", "msg_content", "sendpolicy", "msg_length" })
public class SMsgSubmit {

    private String             msg_id;      //消息ID

    private String             sender;      //消息发送方标识

    private Integer            receiver_num; //接收方用户个数，小于100个

    private List<SMsgUserInfo> receiver;    //消息接收方标识

    private String             msg_level;   //消息级别，用于后续扩展

    private SMsgInfo           msg;         // 消息内容

    private String             msg_content; // 消息内容。禁止发空消息

    private SMsgPolicy         sendpolicy;  //消息发送处理策略

    private Long               msg_length;  //信息长度

    private String             sms_digest;  //短消息内容

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getReceiver_num() {
        return receiver_num;
    }

    public void setReceiver_num(Integer receiver_num) {
        this.receiver_num = receiver_num;
    }

    @XmlElementWrapper
    @XmlElement(name = "uriinfo")
    public List<SMsgUserInfo> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<SMsgUserInfo> receiver) {
        this.receiver = receiver;
    }

    public String getMsg_level() {
        return msg_level;
    }

    public void setMsg_level(String msg_level) {
        this.msg_level = msg_level;
    }

    public SMsgPolicy getSendpolicy() {
        return sendpolicy;
    }

    public void setSendpolicy(SMsgPolicy sendpolicy) {
        this.sendpolicy = sendpolicy;
    }

    public Long getMsg_length() {
        return msg_length;
    }

    public void setMsg_length(Long msg_length) {
        this.msg_length = msg_length;
    }

    public String getSms_digest() {
        return sms_digest;
    }

    public void setSms_digest(String sms_digest) {
        this.sms_digest = sms_digest;
    }

    @XmlJavaTypeAdapter(CDataAdapter.class)
    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    /**
     * Getter method for property <tt>msg</tt>.
     * 
     * @return property value of msg
     */
    public SMsgInfo getMsg() {
        return msg;
    }

    /**
     * Setter method for property <tt>msg</tt>.
     * 
     * @param msg value to be assigned to property msg
     */
    public void setMsg(SMsgInfo msg) {
        this.msg = msg;
    }

}
