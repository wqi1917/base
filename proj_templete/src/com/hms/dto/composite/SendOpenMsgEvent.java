/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.dto.composite;

import com.hms.dto.MsgInfoDTO;

/**
 * 消息事件
 * @author wangq
 * @version $Id: SendOpenMsgEvent.java, v 0.1 2014-9-10 下午2:13:12 wangq Exp $
 */
public class SendOpenMsgEvent {

    // 消息id
    private MsgInfoDTO msgInfo;

    // 消息体
    private String     msgContent;

    public SendOpenMsgEvent() {
        super();
    }

    /**
     * 鏋勯�鍑芥暟
     * @param sender
     * @param receivers
     * @param msgContent
     */
    public SendOpenMsgEvent(MsgInfoDTO msgInfo, String msgContent) {
        super();
        this.msgInfo = msgInfo;
        this.msgContent = msgContent;
    }

    /**
     * Getter method for property <tt>msgInfo</tt>.
     * 
     * @return property value of msgInfo
     */
    public MsgInfoDTO getMsgInfo() {
        return msgInfo;
    }

    /**
     * Setter method for property <tt>msgInfo</tt>.
     * 
     * @param msgInfo value to be assigned to property msgInfo
     */
    public void setMsgInfo(MsgInfoDTO msgInfo) {
        this.msgInfo = msgInfo;
    }

    /**
     * Getter method for property <tt>msgContent</tt>.
     * 
     * @return property value of msgContent
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * Setter method for property <tt>msgContent</tt>.
     * 
     * @param msgContent value to be assigned to property msgContent
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

}
