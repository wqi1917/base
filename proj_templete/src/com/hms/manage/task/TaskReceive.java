package com.hms.manage.task;

import org.apache.log4j.Logger;

import com.hms.core.pool.ThreadPoolFactory;
import com.hms.dto.composite.SendOpenMsgEvent;

/**
 * 
 * 
 * @author wangq
 * @version $Id: TaskReceive.java, v 0.1 2014-11-11 下午2:17:27 wangq Exp $
 */
public class TaskReceive {
    public static Logger logger = Logger.getLogger(TaskReceive.class);

    public static void sendDownMsg(SendOpenMsgEvent event) {
        if (logger.isInfoEnabled()) {
            logger.info("增加下行消息任务：msgbiztype=" + event.getMsgInfo().getMsgbiztype() + ";msgid="
                        + event.getMsgInfo().getMsgid());
        }
        try {

            ThreadPoolFactory.getDownPoolInstance().execute(new DownMsgTask(event));
        } catch (Exception e) {
            logger.error("增加下行消息任务异常：msgbiztype=" + event.getMsgInfo().getMsgbiztype() + ";msgid="
                         + event.getMsgInfo().getMsgid(), e);
        }

    }

    /**
     * 发送下行消息
     * 
     * @param msgEntity
     * @param paramMap
     */
    public static void sendUpMsg(SendOpenMsgEvent event) {
        if (logger.isInfoEnabled()) {
            logger.info("增加上行消息任务：msgbiztype=" + event.getMsgInfo().getMsgbiztype() + ";msgid="
                        + event.getMsgInfo().getMsgid());
        }
        try {

            ThreadPoolFactory.getUpPoolInstance().execute(new UpMsgTask(event));
        } catch (Exception e) {
            logger.error("增加上行消息任务异常：msgbiztype=" + event.getMsgInfo().getMsgbiztype() + ";msgid="
                         + event.getMsgInfo().getMsgid(), e);
        }
    }

}
