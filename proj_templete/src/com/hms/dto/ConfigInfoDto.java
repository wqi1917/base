/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.dto;

import java.util.Date;

/**
 * 
 * @author wangq
 * @version $Id: ConfigInfoDto.java, v 0.1 2014-11-20 上午9:32:56 wangq Exp $
 */
public class ConfigInfoDto {

    // 主键
    private int    config_uuid;

    // 消息方向  0：下行消息  由业务服务器发向openfire 1：上行消息，由openfire发向业务服务器
    private int    direction;

    // 消息业务类型编码
    private String msgbiztype;

    // 消息业务类型名称
    private String msgbiztype_name;

    // 所属系统
    private String business_plat;

    // 上行消息接口
    private String upmsg_service;

    // 下行消息接口
    private String downmsg_service;

    // 异步回调接口
    private String callback_service;

    // 群发接收者最大数量 
    private int    max_receiver_num;

    // 创建时间
    private Date   createtime;

    // 最后修改时间
    private Date   lastupdatetime;

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append("config_uuid:" + config_uuid);
        bld.append(";direction:" + direction);
        bld.append(";msgbiztype:" + msgbiztype);
        bld.append(";msgbiztype_name:" + msgbiztype_name);
        bld.append(";business_plat:" + business_plat);
        bld.append(";upmsg_service:" + upmsg_service);
        bld.append(";downmsg_service:" + downmsg_service);
        bld.append(";callback_service:" + callback_service);
        bld.append(";max_receiver_num:" + max_receiver_num);
        return bld.toString();
    }

    /**
     * Getter method for property <tt>config_uuid</tt>.
     * 
     * @return property value of config_uuid
     */
    public int getConfig_uuid() {
        return config_uuid;
    }

    /**
     * Setter method for property <tt>config_uuid</tt>.
     * 
     * @param config_uuid value to be assigned to property config_uuid
     */
    public void setConfig_uuid(int config_uuid) {
        this.config_uuid = config_uuid;
    }

    /**
     * Getter method for property <tt>direction</tt>.
     * 
     * @return property value of direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Setter method for property <tt>direction</tt>.
     * 
     * @param direction value to be assigned to property direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Getter method for property <tt>msgbiztype</tt>.
     * 
     * @return property value of msgbiztype
     */
    public String getMsgbiztype() {
        return msgbiztype;
    }

    /**
     * Setter method for property <tt>msgbiztype</tt>.
     * 
     * @param msgbiztype value to be assigned to property msgbiztype
     */
    public void setMsgbiztype(String msgbiztype) {
        this.msgbiztype = msgbiztype;
    }

    /**
     * Getter method for property <tt>msgbiztype_name</tt>.
     * 
     * @return property value of msgbiztype_name
     */
    public String getMsgbiztype_name() {
        return msgbiztype_name;
    }

    /**
     * Setter method for property <tt>msgbiztype_name</tt>.
     * 
     * @param msgbiztype_name value to be assigned to property msgbiztype_name
     */
    public void setMsgbiztype_name(String msgbiztype_name) {
        this.msgbiztype_name = msgbiztype_name;
    }

    /**
     * Getter method for property <tt>business_plat</tt>.
     * 
     * @return property value of business_plat
     */
    public String getBusiness_plat() {
        return business_plat;
    }

    /**
     * Setter method for property <tt>business_plat</tt>.
     * 
     * @param business_plat value to be assigned to property business_plat
     */
    public void setBusiness_plat(String business_plat) {
        this.business_plat = business_plat;
    }

    /**
     * Getter method for property <tt>upmsg_service</tt>.
     * 
     * @return property value of upmsg_service
     */
    public String getUpmsg_service() {
        return upmsg_service;
    }

    /**
     * Setter method for property <tt>upmsg_service</tt>.
     * 
     * @param upmsg_service value to be assigned to property upmsg_service
     */
    public void setUpmsg_service(String upmsg_service) {
        this.upmsg_service = upmsg_service;
    }

    /**
     * Getter method for property <tt>downmsg_service</tt>.
     * 
     * @return property value of downmsg_service
     */
    public String getDownmsg_service() {
        return downmsg_service;
    }

    /**
     * Setter method for property <tt>downmsg_service</tt>.
     * 
     * @param downmsg_service value to be assigned to property downmsg_service
     */
    public void setDownmsg_service(String downmsg_service) {
        this.downmsg_service = downmsg_service;
    }

    /**
     * Getter method for property <tt>callback_service</tt>.
     * 
     * @return property value of callback_service
     */
    public String getCallback_service() {
        return callback_service;
    }

    /**
     * Setter method for property <tt>callback_service</tt>.
     * 
     * @param callback_service value to be assigned to property callback_service
     */
    public void setCallback_service(String callback_service) {
        this.callback_service = callback_service;
    }

    /**
     * Getter method for property <tt>max_receiver_num</tt>.
     * 
     * @return property value of max_receiver_num
     */
    public int getMax_receiver_num() {
        return max_receiver_num;
    }

    /**
     * Setter method for property <tt>max_receiver_num</tt>.
     * 
     *  @param max_receiver_num value to be assigned to property max_receiver_num
     */
    public void setMax_receiver_num(int max_receiver_num) {
        this.max_receiver_num = max_receiver_num;
    }

    /**
     * Getter method for property <tt>createtime</tt>.
     * 
     * @return property value of createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * Setter method for property <tt>createtime</tt>.
     * 
     * @param createtime value to be assigned to property createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * Getter method for property <tt>lastupdatetime</tt>.
     * 
     * @return property value of lastupdatetime
     */
    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    /**
     * Setter method for property <tt>lastupdatetime</tt>.
     * 
     * @param lastupdatetime value to be assigned to property lastupdatetime
     */
    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }
}
