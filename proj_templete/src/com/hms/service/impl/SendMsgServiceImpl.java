/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.service.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hms.dto.MsgInfoDTO;
import com.hms.manage.ManageHandlerResult;
import com.hms.manage.handler.old.ISendMsgServiceHandler;
import com.hms.service.ISendMsgService;
import com.hms.service.ServiceConstants;
import com.hms.service.ServiceException;
import com.hms.service.ServiceReturn;
import com.hms.util.ServiceUtil;
import com.hms.util.StringUtil;
import com.sun.jersey.api.core.HttpContext;

/**
 * 消息发送接口
 * 
 * @author wangq
 * @version $Id: SendMsgServiceImpl.java, v 0.1 2014-11-11 下午3:27:28 wangq Exp $
 */
@Path("/sendmsg")
public class SendMsgServiceImpl implements ISendMsgService {

    public static Logger           logger = Logger.getLogger(SendMsgServiceImpl.class);
    @Autowired
    private ISendMsgServiceHandler sendMsgServiceHandler;

    /** 
     * 
     * @see com.usercenter.service.ISendMsgService#sendMsgByText(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean)
     */
    public Response sendMsgByText(String msgbiztype, String msgid, String msgdigest,
                                  String receivers, String msgtext, String extparam) {

        if (logger.isInfoEnabled()) {
            logger.info("下行文本消息sendtextmsg：msgbiztype" + msgbiztype + ";msgid=" + msgid
                        + ";msgdigest=" + msgdigest + ";receivers=[" + receivers + "];msgtext="
                        + msgtext + ";extparam=" + extparam);
        }
        //#1 参数校验
        String check = checkParam(msgbiztype, msgid, receivers, msgtext);
        if (check != null) {
            throw new ServiceException(ServiceConstants.RET_ERROR, ServiceConstants.PARAM_LACK,
                "参数异常：" + check, "msgid:" + msgid);
        }

        //#2 封装msgInfoDTO消息
        MsgInfoDTO msgInfo = new MsgInfoDTO(msgbiztype, msgid, msgdigest, receivers, null);

        //#3 调用处理类处理发送请求
        ManageHandlerResult result = sendMsgServiceHandler.sendTextMsg(msgInfo, msgtext, extparam);

        //#4 根据处理结果，返回请求
        return ServiceReturn.build(result);
    }

    /**
     * 
     * @see com.usercenter.service.ISendMsgService#sendMsgByContent(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean)
     */
    public Response sendMsgByContent(String msgbiztype, String msgid, String msgdigest,
                                     String receivers, String msgContent) {
        return sendMsgContent(msgbiztype, msgid, msgdigest, receivers, msgContent, null);
    }

    /**
     * 
     * @see com.usercenter.service.ISendMsgService#sendMsgByDelay(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String)
     */
    public Response sendMsgByDelay(String msgbiztype, String msgid, String msgdigest,
                                   String receivers, String msgContent, String delay) {
        return sendMsgContent(msgbiztype, msgid, msgdigest, receivers, msgContent, delay);
    }

    /**
     * 
     * @see com.usercenter.service.ISendMsgService#sendMsgByhttp(com.sun.jersey.api.core.HttpContext)
     */
    public Response sendMsgByhttp(HttpContext httpContext) {

        String entity = httpContext.getRequest().getEntity(String.class);

        //#1 获取消息信息
        String msgInfoStr = StringUtil.getSubString(entity, "<msginfo>", "</msginfo>", false);
        if (msgInfoStr == null) {
            throw new ServiceException(ServiceConstants.RET_ERROR,
                ServiceConstants.PARAM_BAD_FORMAT, "参数异常：msginfo结构异常");
        }

        String msgcontent = StringUtil.getSubString(entity, "<msgcontent>", "</msgcontent>", false);
        if (msgcontent == null) {
            throw new ServiceException(ServiceConstants.RET_ERROR,
                ServiceConstants.PARAM_BAD_FORMAT, "参数异常：msgcontent结构异常");
        }

        //#2 封装msgInfoDTO消息
        MsgInfoDTO msgInfo = ServiceUtil.convertXmlStr2JavaPojo(new MsgInfoDTO(), msgInfoStr);

        return sendMsgContent(msgInfo.getMsgbiztype(), msgInfo.getMsgid(), msgInfo.getMsgdigest(),
            msgInfo.getReceivers(), msgcontent, msgInfo.getDelay());
    }

    /**
     * 处理消息发现业务
     * 
     * @param msgbiztype
     * @param msgid
     * @param msgdigest
     * @param receivers
     * @param msgContent
     * @param issync
     * @param delay
     * @return
     */
    private Response sendMsgContent(String msgbiztype, String msgid, String msgdigest,
                                    String receivers, String msgContent, String delay) {

        if (logger.isInfoEnabled()) {
            logger.info("下行文本消息sendtextmsg：msgbiztype" + msgbiztype + ";msgid=" + msgid
                        + ";msgdigest=" + msgdigest + ";receivers=[" + receivers + "];msgContent="
                        + msgContent + ";delay=" + delay);
        }

        //#1 参数校验 
        String check = checkParam(msgbiztype, msgid, receivers, msgContent);
        if (check != null) {
            throw new ServiceException(ServiceConstants.RET_ERROR, ServiceConstants.PARAM_LACK,
                "参数异常：" + check, msgid);
        }

        //#2 封装msgInfoDTO消息
        MsgInfoDTO msgInfo = new MsgInfoDTO(msgbiztype, msgid, msgdigest, receivers, null);

        //#3 调用处理类处理发送请求
        ManageHandlerResult result = sendMsgServiceHandler.sendContentMsg(msgInfo, msgContent);

        //#4 根据处理结果，返回请求
        return ServiceReturn.build(result);
    }

    /**
     * 校验参数是否为空
     * 
     * @param msgbiztype
     * @param msgid
     * @param receivers
     * @param msgContent
     * @return
     */
    private String checkParam(String msgbiztype, String msgid, String receivers, String msgContent) {
        if (StringUtil.isEmpty(msgbiztype)) {
            return "msgbiztype不能为空！ ";
        }
        if (StringUtil.isEmpty(msgid)) {
            return "msgid不能为空！ ";
        }
        if (StringUtil.isEmpty(receivers)) {
            return "receivers不能为空！ ";
        }
        if (StringUtil.isEmpty(msgContent)) {
            return "msgContent不能为空！ ";
        }

        return null;
    }

}
