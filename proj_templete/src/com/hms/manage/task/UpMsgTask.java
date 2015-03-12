/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.manage.task;

import com.hms.dto.composite.SendOpenMsgEvent;
import com.hms.message.MessageGW;

/**
 * 开放消息任务
 * @author wangq
 * @version $Id: OpenMsTask.java, v 0.1 2014-9-25 下午4:12:19 wangq Exp $
 */
public class UpMsgTask extends BaseTask {

    private String msgEntity = null;

    public UpMsgTask(SendOpenMsgEvent event) {
        super.event = event;
    }

    @Override
    public boolean beforeCheck() {
        return true;
    }

    @Override
    public boolean taskHander() {
        this.msgEntity = event.getMsgContent();
        MessageGW.sendUpMsg(msgEntity, event.getMsgInfo().getMsgbiztype(), false);
        return true;
    }

    @Override
    public void afterEvent() {
    }

}
