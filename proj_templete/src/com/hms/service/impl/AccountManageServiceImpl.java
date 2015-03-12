package com.hms.service.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.hms.manage.ManageHandlerResult;
import com.hms.manage.handler.old.IAccountManageHandler;
import com.hms.service.IUserManageService;
import com.hms.service.ServiceConstants;
import com.hms.service.ServiceException;
import com.hms.service.ServiceReturn;
import com.hms.util.StringUtil;
import com.sun.jersey.api.core.HttpContext;

//ACCOUNT_MANAGE_TYPE
enum ACCOUNT_MANAGE_TYPE {
    add, delete, update, search;
}

/**
 * 
 * 
 * @author wangq
 * @version $Id: UserManageServiceImpl.java, v 0.1 2014-11-19 上午10:19:35 wangq Exp $
 */
@Path("/account")
public class AccountManageServiceImpl implements IUserManageService {

    @Autowired
    private IAccountManageHandler accountManageHandler;

    /**
     * 
     * @see com.hms.service.IUserManageService#manageform(java.lang.String, java.lang.String, java.lang.String)
     */
    public Response manageform(String platform, String type, String userinfos) {

        // #1 platform type userinfos 空值校验
        //        if (StringUtil.isEmpty(platform)) {
        //            throw new ServiceException(ServiceConstants.RET_ERROR,
        //                ServiceConstants.PARAM_LACK, "参数异常：platform为空！");
        //        }

        if (StringUtil.isEmpty(type)) {
            throw new ServiceException(ServiceConstants.RET_ERROR, ServiceConstants.PARAM_LACK,
 "参数异常：type为空！");
        }
        if (StringUtil.isEmpty(userinfos)) {
            throw new ServiceException(ServiceConstants.RET_ERROR, ServiceConstants.PARAM_LACK,
 "参数异常：userinfos为空！");
        }


        // #2 type类型判断add/delete/update/search 
        try {
            ACCOUNT_MANAGE_TYPE optType = ACCOUNT_MANAGE_TYPE.valueOf(type);
        } catch (Exception e) {
            throw new ServiceException(ServiceConstants.RET_ERROR, ServiceConstants.PARAM_BAD_FORMAT, "参数异常：type操作类型错误！");
        }
        // #3 调用用 AccountManageHandler 
        ManageHandlerResult result = accountManageHandler.manage(platform, type, userinfos);

        // #4 返回结果处理
        return ServiceReturn.build(result);
    }

    /**
     * 
     * @see com.hms.service.IUserManageService#manageentity(com.sun.jersey.api.core.HttpContext)
     */
    public Response manageentity(HttpContext httpContext) {
        String platform = httpContext.getRequest().getQueryParameters().getFirst("platform");
        String type = httpContext.getRequest().getQueryParameters().getFirst("type");
        String userinfos = httpContext.getRequest().getQueryParameters().getFirst("userinfos");
        return manageform(platform, type, userinfos);
    }
}
