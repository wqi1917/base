package com.hms.service;

import java.util.Collection;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.core.HttpContext;

/**
 * 
 * 
 * @author wangq
 * @version $Id: IReceiveService.java, v 0.1 2014-11-13 下午4:44:23 wangq Exp $
 */
@Path("/account")
public interface IUserQueryService {

    /**
     * send text Msg for IM-AS [Post /server/account/manageform]
     * @param platform      操作账户的平台【必选】
     * @param type          操作类型add/delete/update/search【必选】
     * @param userinfos     用户信息【必选】
     *                      add：账户主键（平台前缀+手机号）:密码:名称;账户主键（平台前缀+手机号）:密码:名称;账户主键（平台前缀+手机号）:密码:名称
     *                      delete:账户主键（平台前缀+手机号）;账户主键（平台前缀+手机号）
     *                      update：修改密码名称：账户主键（平台前缀+手机号）:密码:名称;账户主键（平台前缀+手机号）:密码:名称
     *                              修改密码：账户主键（平台前缀+手机号）:密码;账户主键（平台前缀+手机号）:密码
     *                              修改名称：账户主键（平台前缀+手机号）::名称;账户主键（平台前缀+手机号）::名称
     *                      search：账户主键（平台前缀+手机号）;账户主键（平台前缀+手机号）
     * @return Response 
     *                      
     */
    @POST
    @Path("manageform")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes("application/x-www-form-urlencoded")
    public Response manageform(@FormParam("platform") String platform,
                               @FormParam("type") String type,
                               @FormParam("userinfos") String userinfos);

    /**
     * Receive msgdeliver from openFire [Post /server/account/manageentity]
     * 
     *  上行消息接口
     * @param httpContext
     * @return
     */
    @POST
    @Path("manageentity")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response manageentity(@Context HttpContext httpContext);

    /** 
     * @see org.jivesoftware.openfire.user.UserProvider#loadUser(java.lang.String)
     */
    @Override
    public User loadUser(String username) throws UserNotFoundException {
        return null;
    }


    /** 
     * @see org.jivesoftware.openfire.user.UserProvider#getUserCount()
     */
    @Override
    public int getUserCount() {
        return 0;
    }

    /** 
     * @see org.jivesoftware.openfire.user.UserProvider#getUsers()
     */
    @Override
    public Collection<User> getUsers() {
        return null;
    }

    /** 
     * @see org.jivesoftware.openfire.user.UserProvider#getUsernames()
     */
    @Override
    public Collection<String> getUsernames() {
        return null;
    }

    /** 
     * @see org.jivesoftware.openfire.user.UserProvider#getUsers(int, int)
     */
    @Override
    public Collection<User> getUsers(int startIndex, int numResults) {
        return null;
    }

    /** 
     * @see org.jivesoftware.openfire.user.UserProvider#getSearchFields()
     */
    @Override
    public Set<String> getSearchFields() throws UnsupportedOperationException {
        return null;
    }

    /** 
     * @see org.jivesoftware.openfire.user.UserProvider#findUsers(java.util.Set, java.lang.String)
     */
    @Override
    public Collection<User> findUsers(Set<String> fields, String query)
                                                                       throws UnsupportedOperationException {
        return null;
    }

    /** 
     * @see org.jivesoftware.openfire.user.UserProvider#findUsers(java.util.Set, java.lang.String, int, int)
     */
    @Override
    public Collection<User> findUsers(Set<String> fields, String query, int startIndex,
                                      int numResults) throws UnsupportedOperationException {
        return null;
    }

    /**
     * 
     * 
     * @param username
     * @return
     */
    public String queryUserPhone(String username);

    /**
     * 
     * @see org.jivesoftware.openfire.user.UserProvider#isExistUser(java.lang.String)
     */
    public boolean isExistUser(String username);

    /**
     * 
     * 
     * @param username
     * @return
     */
    public String getPassword(String username);



}
