/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.hms.manage.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hms.dao.AccountAppDaoMapper;
import com.hms.dao.AccountDeveloperDaoMapper;
import com.hms.dao.AccountInfoDaoMapper;
import com.hms.dao.po.AccountAppPO;
import com.hms.dao.po.AccountDeveloperPO;
import com.hms.dao.po.AccountInfoPO;
import com.hms.dto.PoVoConvert;
import com.hms.dto.UserInofDto;
import com.hms.manage.ManageHandlerResult;
import com.hms.manage.handler.IUserManageHandler;
import com.hms.util.StringUtil;

/**
 * 
 * @author wqi
 * @version $Id: UserManageHandlerImpl.java, v 0.1 2015-2-27 下午2:47:16 wangq Exp $
 */
public class UserManageHandlerImpl implements IUserManageHandler {

    public static Logger              logger = Logger.getLogger(UserManageHandlerImpl.class);

    private AccountInfoDaoMapper      accountInfoDaoMapper;
    private AccountAppDaoMapper       accountAppDaoMapper;
    private AccountDeveloperDaoMapper accountDeveloperDaoMapper;

    /** 
     * @see com.hms.manage.handler.IUserManageHandler#createUser(com.hms.dto.UserInofDto)
     */
    public ManageHandlerResult createUser(UserInofDto userInfo) {

        long account = 11L;
        userInfo.setAccount(account);
        userInfo.setCreatetime(new Date());
        userInfo.setLastmodifytime(new Date());
        AccountInfoPO accountInfoPO = PoVoConvert.UserInofDtoTOInfoPO(userInfo);
        boolean returnV = accountInfoDaoMapper.insert(accountInfoPO);

        if (!returnV) {

        }

        if (StringUtil.isNotBlank(userInfo.getAppkey())) {
            AccountAppPO appPo = PoVoConvert.UserInofDtoTOAppPO(userInfo);
            accountAppDaoMapper.insert(appPo);
        }

        if (userInfo.isDeveloper()) {
            AccountDeveloperPO devPo = PoVoConvert.UserInofDtoTODevPO(userInfo);
            accountDeveloperDaoMapper.insert(devPo);
        }

        return null;
    }

    /** 
     * @see com.hms.manage.handler.IUserManageHandler#createUsers(java.util.List)
     */
    public ManageHandlerResult createUsers(List<UserInofDto> userInfoList, String appkey) {

        // #1 参数校验
        if (userInfoList == null || userInfoList.size() == 0) {

        }

        // #2 数据封装
        List<AccountInfoPO> infoList = new ArrayList<AccountInfoPO>(userInfoList.size());
        List<AccountAppPO> appList = new ArrayList<AccountAppPO>();
        for (UserInofDto dto : userInfoList) {
            long account = 11L;
            dto.setAccount(account);
            dto.setCreatetime(new Date());
            dto.setLastmodifytime(new Date());
            dto.setAppkey(appkey);
            infoList.add(PoVoConvert.UserInofDtoTOInfoPO(dto));
            appList.add(PoVoConvert.UserInofDtoTOAppPO(dto));
        }
        accountInfoDaoMapper.insertBatch(infoList);

        return null;
    }

    /** 
     * @see com.hms.manage.handler.IUserManageHandler#opendeveloper(java.lang.String, java.lang.String)
     */
    public ManageHandlerResult opendeveloper(String username, String password) {

        // #1 参数校验

        // #2 获取用户信息
        AccountInfoPO infoPo = accountInfoDaoMapper.getByAccountNamePass(username, password);

        // #3 密码校验

        // #4 检查是否已经存在

        // #5 封装对象

        return null;
    }

    /** 
     * @see com.hms.manage.handler.IUserManageHandler#openApplication(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public Response openApplication(String username, String password, String appkey, String nickname) {

        // #1 参数校验

        // #2 获取用户信息
        AccountInfoPO infoPo = accountInfoDaoMapper.getByAccountNamePass(username, password);

        // #3 密码校验

        // #4 检查是否存在 

        // #5 封装
        AccountAppPO appPo = new AccountAppPO();

        appPo.setAccount(infoPo.getAccount());
        appPo.setAppkey(appkey);
        appPo.setNickname(nickname != null ? nickname : infoPo.getAccountname());
        appPo.setCreatetime(new Date());
        appPo.setLastmodifytime(new Date());
        appPo.setStatus(1);
        accountAppDaoMapper.insert(appPo);
        return null;
    }

    /** 
     * @see com.hms.manage.handler.IUserManageHandler#deleteByName(java.lang.String, java.lang.String)
     */
    public Response deleteByName(String username, String appkey) {

        // #1 参数校验

        // #2 获取用户信息
        AccountInfoPO infoPo = null;
        AccountAppPO appPo = null;

        if (appPo != null) {
            accountAppDaoMapper.deleteByAccountAppkey(appPo.getAccount(), appkey);
        } else {
            accountAppDaoMapper.deleteByAccountnameAppkey(username, appkey);
        }

        return null;
    }

    /** 
     * @see com.hms.manage.handler.IUserManageHandler#deleteByAcccount(java.lang.String, java.lang.String)
     */
    public Response deleteByAcccount(String account, String appkey) {

        // #1 参数校验
        accountAppDaoMapper.deleteByAccountAppkey(Long.parseLong(account), appkey);
        return null;
    }

    /** 
     * @see com.hms.manage.handler.IUserManageHandler#deleteDevByName(java.lang.String)
     */
    public Response deleteDevByName(String username) {

        // #1 参数校验

        // #2 获取用户信息
        AccountInfoPO infoPo = null;

        AccountDeveloperPO devPo = null;

        if (devPo != null) {
            accountDeveloperDaoMapper.deleteByAccount(devPo.getAccount());
        } else {
            accountDeveloperDaoMapper.deleteByAccountname(username);
        }

        return null;
    }

    /** 
     * @see com.hms.manage.handler.IUserManageHandler#updateUser(com.hms.dto.UserInofDto)
     */
    public Response updateUser(UserInofDto userInfo) {

        return null;
    }

    /** 
     * @see com.hms.manage.handler.IUserManageHandler#updateUsers(java.util.List)
     */
    public Response updateUsers(List<UserInofDto> userInfoList) {
        return null;
    }

    /** 
     * @see com.hms.manage.handler.IUserManageHandler#updateNickName(java.lang.String, java.lang.String, java.lang.String)
     */
    public Response updateNickName(String username, String appkey, String nickname) {
        // #1 参数校验

        // #2 变更
        AccountInfoPO infoPo = null;

        AccountDeveloperPO devPo = null;
        accountAppDaoMapper.update(devPo.getAccount(), appkey, nickname);
        return null;
    }

}
