/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.manage.task;

import com.hms.dto.composite.SendOpenMsgEvent;

/**
 *  任务基类
 * @author wangq
 * @version $Id: BaseTask.java, v 0.1 2014-9-25 上午11:22:06 wangq Exp $
 */
public abstract class BaseTask implements Runnable {

    public SendOpenMsgEvent event;

    /** 
     * @see java.lang.Runnable#run()
     */
    public void run() {
        try {
            if (!beforeCheck()) {
                return;
            }
            boolean result = taskHander();
            if (!result) {
                return;
            }

            afterEvent();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    /**
     * 事前校验
     * 
     * @return
     */
    public abstract boolean beforeCheck();

    /**
     * 任务处理
     * 
     * @return
     */
    public abstract boolean taskHander();

    /**
     * 时候处理
     */
    public abstract void afterEvent();

}
