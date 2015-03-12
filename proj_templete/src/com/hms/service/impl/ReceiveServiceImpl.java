package com.hms.service.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.hms.dto.MsgInfoDTO;
import com.hms.dto.servermsg.SMsgDeliver;
import com.hms.manage.ManageHandlerResult;
import com.hms.manage.handler.old.IReceiveServiceHandler;
import com.hms.service.IReceiveService;
import com.hms.service.ServiceConstants;
import com.hms.service.ServiceException;
import com.hms.service.ServiceReturn;
import com.hms.util.ServiceUtil;
import com.hms.util.StringUtil;
import com.sun.jersey.api.core.HttpContext;

/**
 * 
 * 
 * @author wangq
 * @version $Id: ReceiveServiceImpl.java, v 0.1 2014-11-12 上午8:54:40 wangq Exp $
 */
@Path("/msg")
public class ReceiveServiceImpl implements IReceiveService {

    @Autowired
    private IReceiveServiceHandler receiveServiceHandler;

    public Response recvmsg(HttpContext httpContext) {

        // #1 获取请求参数
        String sjid = httpContext.getRequest().getHeaderValue(ServiceConstants.H_SJID);
        // TODO sjid 校验

        String entity = httpContext.getRequest().getEntity(String.class);

        // #2 参数校验及封装
        SMsgDeliver sMsgDeliver = getSMsgDeliver(entity);

        // #3 封装MsgInfoDto对象
        MsgInfoDTO msgInfo = new MsgInfoDTO(sMsgDeliver.getMsgbiztype(), sMsgDeliver.getMsg_id(),
            sMsgDeliver.getMsgdigest(), sMsgDeliver.getSender());

        // #4 处理消息
        ManageHandlerResult result = receiveServiceHandler.msgDeliver(msgInfo, entity);

        // #5 返回结果
        return ServiceReturn.build(result);
    }

    private SMsgDeliver getSMsgDeliver(String entity) {
        // #1 参数转换 Convert input xml to SMsgDeliver class
        SMsgDeliver sMsgDeliver = ServiceUtil.convertXmlStr2Java2(SMsgDeliver.class, entity);

        if (sMsgDeliver == null) {
            throw new ServiceException(ServiceConstants.RET_ERROR,
                ServiceConstants.PARAM_BAD_FORMAT, "recvmsg 参数异常", "entity:" + entity);
        }

        // #2 校验msg_content是否存在
        if (StringUtil.isEmpty(sMsgDeliver.getMsg_content())) {
            String msg_content = StringUtil.getSubString(entity, "<msg_content>", "</msg_content>",
                false);
            if (!StringUtil.isBlank(msg_content)) {
                sMsgDeliver.setMsg_content(msg_content);
            }
        }

        // #3 校验参数
        String checkStr = checkParam(sMsgDeliver);

        if (checkStr != null) {
            throw new ServiceException(ServiceConstants.RET_ERROR, ServiceConstants.PARAM_LACK,
                "参数异常：" + checkStr, "entity:" + entity);
        }
        return sMsgDeliver;
    }

    /**
     * 参数校验
     * 
     * @param sMsgDeliver
     * @return
     */
    private String checkParam(SMsgDeliver sMsgDeliver) {

        if (StringUtil.isEmpty(sMsgDeliver.getMsgbiztype())) {
            return "Msgbiztype不能为空";
        }

        if (StringUtil.isEmpty(sMsgDeliver.getMsg_id())) {
            return "Msg_id不能为空";
        }

        if (StringUtil.isEmpty(sMsgDeliver.getSender())) {
            return "Sender不能为空";
        }

        if (StringUtil.isEmpty(sMsgDeliver.getMsg_content())) {
            return "Msg_content不能为空";
        }

        return null;
    }
}
