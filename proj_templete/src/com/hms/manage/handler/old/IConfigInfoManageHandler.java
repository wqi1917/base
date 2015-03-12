/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.manage.handler.old;

import java.util.List;

import com.hms.dto.ConfigInfoDto;
import com.hms.manage.ManageHandlerResult;

/**
 * 消息业务类型操作handler
 * 
 * @author wangq
 * @version $Id: IConfigInfoManageHandler.java, v 0.1 2014-11-20 上午9:29:40 wangq Exp $
 */
public interface IConfigInfoManageHandler {
    /**
     * 新增一条消息业务类型信息
     * 
     * @param dto
     * @return
     */
    ManageHandlerResult insert(ConfigInfoDto configInfoDto);

    /**
     * 根据config_uuid删除一条消息业务类型信息
     * 
     * @param dto 
     * @return
     */

    ManageHandlerResult delete(int config_uuid);

    /**
     * 根据系统平台删除若干消息业务类型信息
     * 
     * @param business_plat
     * @return
     */

    ManageHandlerResult delete(String business_plat);

    /**
     * 更新一条消息业务类型信息
     * 
     * @param config_uuid
     * @param dto
     * @return
     */
    ManageHandlerResult update(int config_uuid, ConfigInfoDto configInfoDto);

    /**
     * 根据config_uuid选择一条消息业务类型信息
     * 
     * @param config_uuid
     * @return
     */
    public ConfigInfoDto selectbyconfig_uuid(int config_uuid);

    /**
     * 根据msgbiztype选择一条消息业务类型信息
     * 
     * @param msgbiztype
     * @return
     */
    public ConfigInfoDto selectbymsgbiztype(String msgbiztype);

    /**
     * 根据business_plat选择一条消息业务类型信息
     * 
     * @param business_plat
     * @return
     */
    public List<ConfigInfoDto> selectbybusiness_plat(String business_plat);

    /**
     * 获得当前页码指定数量的消息业务信息
     * 
     * @param page
     * @param size
     * @return
     */
    public List<ConfigInfoDto> getList(Integer page, Integer size);

    /**
     * 获取消息业务的条目总数
     * 
     * @return
     */
    public Integer getSize();

    /**
     * 根据msgbiztype或msgbiztyp_name获得查询到的消息业务数量
     * 
     * @param MsgBiztypeOrName
     * @return
     */
    public Integer getSelectByMsgBiztypeOrNameSize(String MsgBiztypeOrName);

    /**
     * 根据msgbiztype或msgbiztyp_name获得查询到的所有消息业务信息
     * 
     * @param MsgBiztypeOrName
     * @return
     */
    public List<ConfigInfoDto> selectByMsgBiztypeOrName(String MsgBiztypeOrName, Integer page,
                                                        Integer size);
}
