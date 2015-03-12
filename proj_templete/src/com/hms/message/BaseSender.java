package com.hms.message;

import org.apache.log4j.Logger;

import com.hms.message.resource.ResourceFactory;
import com.hms.util.CommonConstants;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * @author wangq
 * @version $Id: BaseSender.java, v 0.1 2014-9-25 上午11:22:06 wangq Exp $
 */
public abstract class BaseSender {

    public static Logger logger = Logger.getLogger(BaseSender.class);

    /** 请求返回值 */
    public String        response;

    public String        syncResourceType;
    public String        resourceType;

    public BaseSender(String syncResourceType, String resourceType) {
        this.syncResourceType = syncResourceType;
        this.resourceType = resourceType;
    }

    /**
     * 同步发送消息
     * 
     * @return
     */
    public MessageResult sendSync() {
        loggerInfo("BaseSender同步发送，syncResourceType=" + syncResourceType);
        return sendMessage(syncResourceType);
    }

    /**
     * 异步发送
     * 
     * @return
     */
    public MessageResult send() {
        loggerInfo("BaseSender异步发送，resourceType=" + resourceType);
        return sendMessage(resourceType);
    }

    /**
     * 发送消息
     * 
     * @param resourceType
     * @return
     */
    private MessageResult sendMessage(String resourceType) {
        MessageResult result = null;
        // #1 事前事件
        if ((result = beforeEvent()) != null && !result.isSuccess()) {
            loggerInfo("发送消息校验失败：resourceType=" + resourceType + ";result=" + result.toString());
            return result;
        }

        // #2 申请链接
        WebResource wr = ResourceFactory.getResource(resourceType);
        if (wr == null) {
            loggerError("创建链接失败resourceType=" + resourceType, null);
            return new MessageResult(CommonConstants.RET_ERROR, CommonConstants.SEND_ERROR,
                "创建链接失败");
        }

        // #3 发送消息
        try {
            response = sendMessage(wr);
            // 当前使用success标记是否发送成功
            if (!"success".equals(response)) {
                loggerInfo("消息发送成功！");
            }
        } catch (Exception e) {
            loggerError("消息发送异常resourceType=" + resourceType, e);
            return new MessageResult(CommonConstants.RET_ERROR, CommonConstants.SEND_ERROR,
                "发送消息异常");
        }

        // #4 归还链接
        ResourceFactory.returnResource(resourceType, wr);

        // #5 事后事件
        if ((result = afterEvent()) != null && !result.isSuccess()) {
            loggerInfo("发送消息事后处理是吧：resourceType=" + resourceType + ";result=" + result.toString());
            return result;
        }

        return new MessageResult(response);
    }

    /**
     * 发送前处理
     * 
     * @return
     */
    protected MessageResult afterEvent() {
        return null;
    }

    /**
     * 发送后处理
     * 
     * @return
     */
    protected MessageResult beforeEvent() {
        return null;
    }

    /**
     * 记录日志信息
     *      根据级别判断是否记录
     * 
     * @param message
     */
    protected void loggerInfo(String message) {
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }

    /**
     * 记录异常信息 
     * 
     * @param message
     * @param e
     */
    protected void loggerError(String message, Exception e) {
        if (e != null) {
            logger.error(message, e);
        } else {
            logger.error(message);
        }
    }

    /**
     * 具体的放业务
     * 
     * @param wr
     * @return
     */
    protected abstract String sendMessage(WebResource wr);

}
