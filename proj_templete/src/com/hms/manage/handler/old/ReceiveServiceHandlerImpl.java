/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.manage.handler.old;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hms.core.cache.CacheManageFactory;
import com.hms.dao.vo.ConfigInfoVO;
import com.hms.dto.MsgInfoDTO;
import com.hms.dto.composite.SendOpenMsgEvent;
import com.hms.manage.ManageHandlerResult;
import com.hms.manage.task.TaskReceive;
import com.hms.util.CommonConstants;

/**
 * 
 * @author wangq
 * @version $Id: ReceiveServiceHandlerImpl.java, v 0.1 2014-11-12 上午8:55:35 wangq Exp $
 */
public class ReceiveServiceHandlerImpl implements IReceiveServiceHandler {

    public static Logger       logger = Logger.getLogger(ReceiveServiceHandlerImpl.class);

    @Autowired
    private CacheManageFactory cacheManageFactory;

    /** 
     * 
     * @see com.hms.manage.handler.old.IReceiveServiceHandler#msgDeliver(com.hms.dto.MsgInfoDTO, java.lang.String)
     */
    public ManageHandlerResult msgDeliver(MsgInfoDTO msgInfo, String entity) {

        if (logger.isInfoEnabled()) {
            logger.info("ReceiveServiceHandlerImpl 中 entity：" + entity);
        }
        //#1 消息业务类型校验
        ConfigInfoVO infoVo = (ConfigInfoVO) cacheManageFactory.getCacheObject(
            CacheManageFactory.CACHE_TYPE_CONFIG_INFO, msgInfo.getMsgbiztype());

        if (infoVo == null) {
            logger.warn("未找到上行消息业务的注册信息！msgid:" + msgInfo.getMsgid());
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.NOT_FOUND,
                "未找到上行消息业务的注册信息！", "msgid:" + msgInfo.getMsgid());
        }
        if (infoVo.getDirection() != CommonConstants.UP_MSG_DIRECTION) {
            logger.warn("参数异常：上行消息方向错误！msgid:" + msgInfo.getMsgid());
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "参数异常：上行消息方向错误！", "msgid:" + msgInfo.getMsgid());
        }
        if (infoVo.getUpmsg_service() == null) {
            logger.warn("参数异常：上行消息的接口信息为null！msgid:" + msgInfo.getMsgid());
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "参数异常：上行消息的接口信息为null！", "msgid:" + msgInfo.getMsgid());
        }

        //#2 延时消息处理

        //#3 同异步消息处理

        TaskReceive.sendUpMsg(new SendOpenMsgEvent(msgInfo, entity));

        return new ManageHandlerResult(true, "msgid:" + msgInfo.getMsgid());
    }

}
