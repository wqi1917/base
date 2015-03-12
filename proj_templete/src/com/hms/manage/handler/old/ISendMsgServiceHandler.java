/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.manage.handler.old;

import com.hms.dto.MsgInfoDTO;
import com.hms.manage.ManageHandlerResult;

/**
 * 
 * @author wangq
 * @version $Id: SendMsgServiceHandler.java, v 0.1 2014-11-11 下午2:39:15 wangq Exp $
 */
public interface ISendMsgServiceHandler {

    /**
     * 发送文本消息
     * 
     * @param msgInfo
     * @param msgText
     * @param extparam
     * @return
     */
    ManageHandlerResult sendTextMsg(MsgInfoDTO msgInfo, String msgText, String extparam);

    /**
     * 发送结构消息
     * 
     * @param msgInfo
     * @param msgContent
     * @return
     */
    ManageHandlerResult sendContentMsg(MsgInfoDTO msgInfo, String msgContent);

}
