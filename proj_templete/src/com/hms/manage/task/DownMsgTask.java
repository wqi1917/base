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
public class DownMsgTask extends BaseTask {

    public DownMsgTask(SendOpenMsgEvent event) {
        super.event = event;
    }

    /** 
     * 
     * @see com.hms.manage.task.BaseTask#beforeCheck()
     */
    @Override
    public boolean beforeCheck() {
        return true;
    }

    /** 
     * 
     * @see com.hms.manage.task.BaseTask#taskHander()
     */
    @Override
    public boolean taskHander() {
        MessageGW.sendDownMsg(event.getMsgInfo(), event.getMsgContent(), false);
        return true;
    }

    /** 
     * 
     * @see com.hms.manage.task.BaseTask#afterEvent()
     */
    @Override
    public void afterEvent() {

    }

}
