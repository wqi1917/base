/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.manage.handler.old;

import com.hms.dto.MsgInfoDTO;
import com.hms.manage.ManageHandlerResult;

/**
 * 上行消息处理
 * @author wangq
 * @version $Id: IReceiveServiceHandler.java, v 0.1 2014-11-12 上午8:43:59 wangq Exp $
 */
public interface IReceiveServiceHandler {

    /**
     * 上行消息处理
     * 
     * @param msgInfo
     * @param entity
     * @return
     */
    ManageHandlerResult msgDeliver(MsgInfoDTO msgInfo, String entity);

}
