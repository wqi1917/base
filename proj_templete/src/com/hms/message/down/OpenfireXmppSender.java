/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.message.down;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;

import com.hms.dto.MsgInfoDTO;
import com.hms.dto.servermsg.SMsgInfo;
import com.hms.dto.servermsg.SMsgSubmit;
import com.hms.dto.servermsg.SMsgUserInfo;
import com.hms.message.BaseSender;
import com.hms.message.MessageResult;
import com.hms.message.resource.ResourceFactory;
import com.hms.util.CommonConstants;
import com.hms.util.Configures;
import com.hms.util.ServiceUtil;
import com.sun.jersey.api.client.WebResource;

/**
 * openfire 下行消息
 * @author wangq
 * @version $Id: OpenfireXmppSender.java, v 0.1 2014-9-25 下午3:54:25 wangq Exp $
 */
public class OpenfireXmppSender extends BaseSender {

    private static String    openfire_msgsubmit_url = "plugins/pubacct/msg/msgsubmit";
    private final String     msgContent;
    private final MsgInfoDTO infoDto;
    private String           msgEntity;

    public OpenfireXmppSender(MsgInfoDTO infoDto, String msgContent) {
        super(ResourceFactory.OPENFRE_SYNC, ResourceFactory.OPENFRE);
        this.infoDto = infoDto;
        this.msgContent = msgContent;
    }

    /**
     * 
     * @see com.hms.message.BaseSender#sendMessage(com.sun.jersey.api.client.WebResource)
     */
    @Override
    protected String sendMessage(WebResource wr) {
        loggerInfo("In OpenfireXmppSender function, msgEntity info:" + msgEntity);
        String response = wr.path(openfire_msgsubmit_url)
            .header(HttpHeaders.CONTENT_TYPE, CommonConstants.CONTENT_TYPE_XML)
            .header(HttpHeaders.CONTENT_LENGTH, msgEntity.length()).entity(msgEntity)
            .post(String.class);

        return response;
    }

    /** 
     *  封装消息内容
     * @see com.hms.message.BaseSender#beforeEvent()
     */
    @Override
    protected MessageResult beforeEvent() {

        // Create receiver
        List<SMsgUserInfo> receiver_uris = new ArrayList<SMsgUserInfo>();
        String[] split = infoDto.getReceivers().split(CommonConstants.LIST_SEPERATOR);
        for (String receive_uri : split) {
            SMsgUserInfo userInfo = new SMsgUserInfo();
            userInfo.setUri(receive_uri);
            receiver_uris.add(userInfo);
        }

        SMsgSubmit SendMessage = new SMsgSubmit();
        SendMessage.setReceiver_num(receiver_uris.size());
        SendMessage.setReceiver(receiver_uris);
        SendMessage.setSender(Configures.getInstance().getParam(
            CommonConstants.CORPORATION_MSGGW_URI));

        //  采用长度是否进行控制
        //        SendMessage.setSms_digest(infoDto.getMsgdigest());
        SMsgInfo msginfo = getSMsgInfo();
        SendMessage.setMsg(msginfo);//For content bloc
        SendMessage.setMsg_length(new Long(msgContent.length()));

        this.msgEntity = ServiceUtil.convertJava2XmlStr(SendMessage, "UTF-8", true);
        loggerInfo(msgEntity);

        return null;
    }

    private SMsgInfo getSMsgInfo() {
        SMsgInfo info = new SMsgInfo();
        info.setMsgbiztype(infoDto.getMsgbiztype());
        info.setMsgid(infoDto.getMsgid());
        info.setMsg_digest(infoDto.getMsgdigest());
        info.setCreate_time(infoDto.getCreateDate());
        info.setMsg_content(msgContent);
        return info;
    }

}
