/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.manage.handler.old;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hms.core.cache.CacheManageFactory;
import com.hms.dao.vo.ConfigInfoVO;
import com.hms.dto.MsgInfoDTO;
import com.hms.dto.composite.CSTextMsgContent;
import com.hms.dto.composite.SendOpenMsgEvent;
import com.hms.manage.ManageHandlerResult;
import com.hms.manage.task.TaskReceive;
import com.hms.util.CommonConstants;
import com.hms.util.ServiceUtil;

/**
 * 
 * @author wangq
 * @version $Id: SendMsgServiceHandlerImpl.java, v 0.1 2014-11-11 下午2:38:53 wangq Exp $
 */
public class SendMsgServiceHandlerImpl implements ISendMsgServiceHandler {

    public static Logger       logger = Logger.getLogger(SendMsgServiceHandlerImpl.class);

    @Autowired
    private CacheManageFactory cacheManageFactory;

    /**
     * 
     * @see com.hms.manage.handler.old.ISendMsgServiceHandler#sendTextMsg(com.hms.dto.MsgInfoDTO, java.lang.String, java.lang.String)
     */
    public ManageHandlerResult sendTextMsg(MsgInfoDTO msgInfo, String msgText, String extparam) {

        //#1 封装文本消息
        CSTextMsgContent textMsgContent = findCsMsgContent(msgInfo.getMsgbiztype(),
            msgInfo.getMsgid(), msgText, extparam);

        String contentStr = ServiceUtil.convertJava2XmlStr(textMsgContent, "UTF-8", true);

        String msgcontent = contentStr.substring(contentStr.indexOf("<textmsg>") + 9,
            contentStr.indexOf("</textmsg>"));
        return sendContentMsg(msgInfo, msgcontent);
    }

    /**
     * 
     * @see com.hms.manage.handler.old.ISendMsgServiceHandler#sendTextMsg(com.hms.dto.MsgInfoDTO, java.lang.String)
     */
    public ManageHandlerResult sendContentMsg(MsgInfoDTO msgInfo, String msgContent) {

        if (logger.isInfoEnabled()) {
            logger.info("SendMsgServiceHandlerImpl中sendContentMsg：" + msgContent);
        }
        //#1 消息业务类型校验
        ConfigInfoVO infoVo = (ConfigInfoVO) cacheManageFactory.getCacheObject(
            CacheManageFactory.CACHE_TYPE_CONFIG_INFO, msgInfo.getMsgbiztype());
        if (infoVo == null) {
            logger.warn("未找到下行消息业务的注册信息！ msgContent:" + msgContent);
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.NOT_FOUND,
                "未找到下行消息业务的注册信息！", "msgContent:" + msgContent);
        }
        if (infoVo.getDirection() != CommonConstants.DOWN_MSG_DIRECTION) {
            logger.warn("参数异常：下行消息方向错误 msgContent:" + msgContent);
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "参数异常：下行消息方向错误！", "msgContent:" + msgContent);
        }
        //        if (infoVo.getDownmsg_service() == null) {
        //            throw new CommonException(CommonConstants.RET_ERROR, CommonConstants.NOT_FOUND,
        //                "参数异常：" + "下行消息接口为null！", msgInfo.getMsgid());
        //        }
        //#2 延时消息处理

        //#3 同异步消息处理
        TaskReceive.sendDownMsg(new SendOpenMsgEvent(msgInfo, msgContent));

        return new ManageHandlerResult(true, "msgContent:" + msgContent);

    }

    /**
     * 封装消息
     * 
     * @param msgtext
     * @param extparam2 
     * @CSTextMsgContentaram extparam 
     * @return
     */
    private CSTextMsgContent findCsMsgContent(String msgbiztype, String msgid, String msgtext,
                                              String extparam) {
        CSTextMsgContent csMsgContent = new CSTextMsgContent();
        csMsgContent.setMedia_type(CommonConstants.MEDIA_TYPE_TEXT);
        csMsgContent.setCreate_time(new Date());
        csMsgContent.setSms_digest(msgtext);
        csMsgContent.setText(msgtext);
        csMsgContent.setMsgid(msgid);
        csMsgContent.setExtparam(extparam);
        csMsgContent.setMsgbiztype(msgbiztype);
        return csMsgContent;
    }

}
