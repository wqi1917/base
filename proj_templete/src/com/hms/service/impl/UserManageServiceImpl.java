/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.hms.service.impl;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hms.service.IUserManageService;
import com.hms.util.StringUtil;
import com.sun.jersey.api.core.HttpContext;

/**
 * 
 * @author wangq
 * @version $Id: UserManageServiceImpl.java, v 0.1 2015-2-26 下午4:09:02 wangq Exp $
 */
public class UserManageServiceImpl implements IUserManageService {

    public static Logger logger = Logger.getLogger(UserManageServiceImpl.class);

    /** 
     * @see com.hms.service.IUserManageService#createUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String)
     */
    public Response createUser(String username, String password, String name, String email, String phone, boolean developer, String appkey) {

        // #1 校验参数
        if (StringUtil.isBlank(username) || StringUtil.isBlank(password)) {

        }
        // #2 校验用户是否存在

        // #3 封装对象

        // #4 调用handler处理

        // #5 整理返回结果

        return null;
    }

    /** 
     * @see com.hms.service.IUserManageService#opendeveloper(java.lang.String, java.lang.String)
     */
    public Response opendeveloper(String username, String password) {
        return null;
    }

    /** 
     * @see com.hms.service.IUserManageService#openApplication(java.lang.String, java.lang.String, java.lang.String)
     */
    public Response openApplication(String username, String password, String appkey) {
        return null;
    }

    /** 
     * @see com.hms.service.IUserManageService#createUser(com.sun.jersey.api.core.HttpContext)
     */
    public Response createUser(HttpContext httpContexty) {
        return null;
    }

    /** 
     * @see com.hms.service.IUserManageService#createUsers(com.sun.jersey.api.core.HttpContext)
     */
    public Response createUsers(HttpContext httpContexty) {
        return null;
    }

    /** 
     * @see com.hms.service.IUserManageService#deleteByName(java.lang.String, java.lang.String)
     */
    public Response deleteByName(String username, String appkey) {
        return null;
    }

    /** 
     * @see com.hms.service.IUserManageService#deleteByAcccount(java.lang.String, java.lang.String)
     */
    public Response deleteByAcccount(String account, String appkey) {
        return null;
    }

    /** 
     * @see com.hms.service.IUserManageService#deleteDevByName(java.lang.String)
     */
    public Response deleteDevByName(String username) {
        return null;
    }

    /** 
     * @see com.hms.service.IUserManageService#updateUser(com.sun.jersey.api.core.HttpContext)
     */
    public Response updateUser(HttpContext httpContexty) {
        return null;
    }

    /** 
     * @see com.hms.service.IUserManageService#updateUsers(com.sun.jersey.api.core.HttpContext)
     */
    public Response updateUsers(HttpContext httpContexty) {
        return null;
    }

    /** 
     * @see com.hms.service.IUserManageService#updateNickName(java.lang.String, java.lang.String, java.lang.String)
     */
    public Response updateNickName(String username, String appkey, String nickname) {
        return null;
    }


}
