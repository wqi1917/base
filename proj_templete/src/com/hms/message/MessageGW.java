package com.hms.message;

import com.hms.dto.MsgInfoDTO;
import com.hms.message.down.AccountManageSender;
import com.hms.message.down.OpenfireXmppSender;
import com.hms.message.up.UpMsgSender;

/**
 * 
 * @author wangq
 * @version $Id: BaseSender.java, v 0.1 2014-9-25 上午11:22:06 wangq Exp $
 */
public class MessageGW {

    /**
     * 发送下行消息
     * 
     * @param msgEntity
     * @param paramMap
     */
    public static MessageResult sendDownMsg(MsgInfoDTO infoDto, String msgContent, boolean issync) {
        BaseSender sender = new OpenfireXmppSender(infoDto, msgContent);
        if (issync) {
            return sender.sendSync();
        }
        return sender.send();

    }

    /**
     * 发送上行行消息
     * 
     * @param msgEntity
     * @param paramMap
     */
    public static MessageResult sendUpMsg(String msgEntity, String msgbiztype, boolean issync) {

        BaseSender sender = new UpMsgSender(msgEntity, msgbiztype);
        if (issync) {
            return sender.sendSync();
        }
        return sender.send();
    }

    /**
     * 发送账户操作消息
     * 
     * @param type
     * @param userinfos
     * @return
     */
    public static MessageResult sendAccountMsg(String type, String userinfos) {
        AccountManageSender sender = new AccountManageSender(type, userinfos);
        return sender.send();

    }

}
