package com.hms.dto.servermsg;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.hms.dto.CDataAdapter;
import com.hms.dto.JaxbDateSerializer;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "msgbiztype", "msgid", "msg_digest", "create_time", "msg_content" })
public class SMsgInfo {

    private String msgid;      //消息ID

    private String msgbiztype; //消息业务表示

    private String msg_digest; //摘要

    private Date   create_time; //创建时间

    private String msg_content; // 消息内容。禁止发空消息

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

    /**
     * Getter method for property <tt>msg_digest</tt>.
     * 
     * @return property value of msg_digest
     */
    public String getMsg_digest() {
        return msg_digest;
    }

    /**
     * Setter method for property <tt>msg_digest</tt>.
     * 
     * @param msg_digest value to be assigned to property msg_digest
     */
    public void setMsg_digest(String msg_digest) {
        this.msg_digest = msg_digest;
    }

    /**
     * Getter method for property <tt>create_time</tt>.
     * 
     * @return property value of create_time
     */
    @XmlJavaTypeAdapter(JaxbDateSerializer.class)
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

    @XmlJavaTypeAdapter(CDataAdapter.class)
    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

}
