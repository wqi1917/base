package com.hms.message.up;

import javax.ws.rs.core.HttpHeaders;

import com.hms.message.BaseSender;
import com.hms.util.CommonConstants;
import com.sun.jersey.api.client.WebResource;

/**
 * 上行消息
 * 
 * @author wangq
 * @version $Id: UpMsgSender.java, v 0.1 2014-11-11 下午1:55:40 wangq Exp $
 */
public class UpMsgSender extends BaseSender {

    private final String msgEntity;

    public UpMsgSender(String msgEntity, String msgbiztype) {
        super(msgbiztype, msgbiztype);
        this.msgEntity = msgEntity;
    }

    @Override
    protected String sendMessage(WebResource wr) {
        loggerInfo("In UpMsgSender function, msgEntity info:" + msgEntity);

        String response = wr.header(HttpHeaders.CONTENT_TYPE, CommonConstants.CONTENT_TYPE_XML)
            .entity(msgEntity).post(String.class);
        return response;
    }

}
