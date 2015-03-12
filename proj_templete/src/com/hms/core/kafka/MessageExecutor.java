/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.hms.core.kafka;

/**
 * 
 * @author wqi
 * @version $Id: MessageExecutor.java, v 0.1 2015-3-3 下午3:18:17 wangq Exp $
 */
public interface MessageExecutor {
    /**
     * 消息处理类
     * 
     * @param obj  批量返回list，单个返回String
     */
    public void execute(Object obj);

    /**
     * 是否批量处理
     * 
     * @return
     */
    public boolean isBatch();
}
