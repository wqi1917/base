/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.dto;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.hms.dao.po.AccountAppPO;
import com.hms.dao.po.AccountDeveloperPO;
import com.hms.dao.po.AccountInfoPO;

/**
 * 数据库Po对象到业务对象VO转换类
 * @author wangqi
 * @version $Id: PoVoConvert.java, v 0.1 2014-5-26 上午11:48:32 wangqi Exp $
 */
public class PoVoConvert {

    /**
     * 将Map数据转化为Object对象
     * 
     * @param fieldMap
     * @param object
     * @return
     */
    public static <T> T mapToObject(Map<String, String> fieldMap, T object) {

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {

            field.setAccessible(true);

            try {
                if (fieldMap.get(field.getName()) != null) {

                    if (field.getType() == Integer.class)
                        field.set(object, Integer.valueOf(fieldMap.get(field.getName())));

                    if (field.getType() == Long.class)
                        field.set(object, Long.valueOf(fieldMap.get(field.getName())));

                    if (field.getType() == String.class)
                        field.set(object, fieldMap.get(field.getName()));

                    if (field.getType() == Date.class) {

                        field.set(object, string2Date(fieldMap.get(field.getName())));
                    }

                } else {
                    field.setAccessible(false);
                    continue;
                }
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            field.setAccessible(false);
        }
        return object;
    }

    /**
     * 将字符串转化为时间
     * 
     * @param str
     * @return
     */
    public static Date string2Date(String str) {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sd.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * userInfo 和 accountInfoPO转换
     * 
     * @param userInfo
     * @return
     */
    public static AccountInfoPO UserInofDtoTOInfoPO(UserInofDto userInfo) {
        if (userInfo == null) {
            return null;
        }
        AccountInfoPO accountInfoPO = new AccountInfoPO();
        accountInfoPO.setAccount(userInfo.getAccount());
        accountInfoPO.setAccountname(userInfo.getAccountname());
        accountInfoPO.setEmail(userInfo.getEmail());
        accountInfoPO.setPhone(userInfo.getPhone());
        accountInfoPO.setCreatetime(userInfo.getCreatetime());
        accountInfoPO.setLastmodifytime(userInfo.getLastmodifytime());
        accountInfoPO.setPassword(userInfo.getPassword());
        return accountInfoPO;
    }

    /**
     * userInfo 转换为AccountAppPO
     * 
     * @param userInfo
     * @return
     */
    public static AccountAppPO UserInofDtoTOAppPO(UserInofDto userInfo) {
        if (userInfo == null) {
            return null;
        }
        AccountAppPO appPo = new AccountAppPO();
        appPo.setAccount(userInfo.getAccount());
        appPo.setAppkey(userInfo.getAppkey());
        appPo.setNickname(userInfo.getNickname());
        appPo.setCreatetime(userInfo.getCreatetime());
        appPo.setLastmodifytime(userInfo.getLastmodifytime());
        return appPo;
    }

    /**
     * userInfo 转换为 AccountDeveloperPO
     * 
     * @param userInfo
     * @return
     */
    public static AccountDeveloperPO UserInofDtoTODevPO(UserInofDto userInfo) {
        if (userInfo == null) {
            return null;
        }
        AccountDeveloperPO appPo = new AccountDeveloperPO();
        appPo.setAccount(userInfo.getAccount());
    
        appPo.setCreatetime(userInfo.getCreatetime());
        appPo.setLastmodifytime(userInfo.getLastmodifytime());
        return appPo;
    }

    //    /**
    //     * 数据库VO对象到业务DTO对象的转换
    //     * 
    //     * @param configInfoVo
    //     * @return
    //     */
    //
    //    public static ConfigInfoDto configInfoVo2DtoConvert(ConfigInfoVO configInfoVo) {
    //        if (configInfoVo == null) {
    //            return null;
    //        }
    //        ConfigInfoDto configInfoDto = new ConfigInfoDto();
    //        configInfoDto.setConfig_uuid(configInfoVo.getConfig_uuid());
    //        configInfoDto.setDirection(configInfoVo.getDirection());
    //        configInfoDto.setMsgbiztype(configInfoVo.getMsgbiztype());
    //        configInfoDto.setMsgbiztype_name(configInfoVo.getMsgbiztype_name());
    //        configInfoDto.setBusiness_plat(configInfoVo.getBusiness_plat());
    //        configInfoDto.setUpmsg_service(configInfoVo.getUpmsg_service());
    //        configInfoDto.setDownmsg_service(configInfoVo.getDownmsg_service());
    //        configInfoDto.setCallback_service(configInfoVo.getCallback_service());
    //        configInfoDto.setMax_receiver_num(configInfoVo.getMax_receiver_num());
    //        configInfoDto.setCreatetime(configInfoVo.getCreatetime());
    //        configInfoDto.setLastupdatetime(configInfoVo.getLastupdatetime());
    //
    //        return configInfoDto;
    //    }
    //
    //    /**
    //     * 业务DTO对象到数据库VO对象的转换
    //     * 
    //     * @param configInfoDto
    //     * @return
    //     */
    //    public static ConfigInfoVO configInfoDto2VoConvert(ConfigInfoDto configInfoDto) {
    //        if (configInfoDto == null) {
    //            return null;
    //        }
    //        ConfigInfoVO configInfoVo = new ConfigInfoVO();
    //        configInfoVo.setConfig_uuid(configInfoDto.getConfig_uuid());
    //        configInfoVo.setDirection(configInfoDto.getDirection());
    //        configInfoVo.setMsgbiztype(configInfoDto.getMsgbiztype());
    //        configInfoVo.setMsgbiztype_name(configInfoDto.getMsgbiztype_name());
    //        configInfoVo.setBusiness_plat(configInfoDto.getBusiness_plat());
    //        configInfoVo.setUpmsg_service(configInfoDto.getUpmsg_service());
    //        configInfoVo.setDownmsg_service(configInfoDto.getDownmsg_service());
    //        configInfoVo.setCallback_service(configInfoDto.getCallback_service());
    //        configInfoVo.setMax_receiver_num(configInfoDto.getMax_receiver_num());
    //        configInfoVo.setCreatetime(configInfoDto.getCreatetime());
    //        configInfoVo.setLastupdatetime(configInfoDto.getLastupdatetime());
    //
    //        return configInfoVo;
    //    }
}
