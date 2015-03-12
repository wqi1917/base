package com.hms.service;

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
@Path("/usemanage")
public interface IUserManageService {

    /**
     * create user  [Post /server/usemanage/createoneuser]
     * @param username
     * @param password
     * @param name
     * @param email
     * @param phone
     * @param developer
     * @param appkey
     * @return
     */
    @POST
    @Path("createoneuser")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes("application/x-www-form-urlencoded")
    public Response createUser(@FormParam("username") String username, @FormParam("password") String password, @FormParam("name") String name,
                               @FormParam("email") String email, @FormParam("phone") String phone, @FormParam("developer") boolean developer,
                               @FormParam("appkey") String appkey);

    /**
     * open the user development authority  [Post /server/usemanage/opendeveloper]
     * 
     * @param username
     * @param password
     * @return
     */
    @POST
    @Path("opendeveloper")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes("application/x-www-form-urlencoded")
    public Response opendeveloper(@FormParam("username") String username, @FormParam("password") String password);

    /**
     * open the user application authority  [Post /server/usemanage/openapplication]
     * 开通用户应用权限
     * @param username
     * @param password
     * @return
     */
    @POST
    @Path("openapplication")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes("application/x-www-form-urlencoded")
    public Response openApplication(@FormParam("username") String username, @FormParam("password") String password, @FormParam("appkey") String appkey);

    /**
     * create user  [Post /server/usemanage/createuser]
     *  创建用户
     * @param httpContext
     * @return
     */
    @POST
    @Path("createuser")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response createUser(@Context HttpContext httpContexty);

    /**
     * create users  [Post /server/usemanage/createusers]
     * 
     *  批量创建用户 
     * @param httpContext
     * @return
     */
    @POST
    @Path("createusers")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response createUsers(@Context HttpContext httpContexty);

    /**
     * delete user by username  [Post /server/usemanage/deletebyname]
     * 
     * @param username
     * @param appkey
     * @return
     */
    @POST
    @Path("deletebyname")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response deleteByName(@FormParam("username") String username, @FormParam("appkey") String appkey);

    /**
     * delete user by account  [Post /server/usemanage/deletebyaccount]
     * 
     * @param username
     * @param appkey
     * @return
     */
    @POST
    @Path("deletebyaccount")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response deleteByAcccount(@FormParam("account") String account, @FormParam("appkey") String appkey);

    /**
     * delete dev user by username  [Post /server/usemanage/deletedevbyname]
     * 
     * @param username
     * @param appkey
     * @return
     */
    @POST
    @Path("deletedevbyname")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response deleteDevByName(@FormParam("username") String username);

    /**
     * update users  [Post /server/usemanage/updateuser]
     *  {"username":"xxx","appkey":"key1","name":"newname","email":"xxxx","Phone":"xxxx","password":"xxxx", }
     *  批量创建用户 
     * @param httpContext
     * @return
     */
    @POST
    @Path("updateuser")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updateUser(@Context HttpContext httpContexty);

    /**
     * update users  [Post /server/usemanage/updateusers]
     * 
     *  批量创建用户 
     * @param httpContext
     * @return
     */
    @POST
    @Path("updateusers")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updateUsers(@Context HttpContext httpContexty);

    /**
     * update the user appliction nickname  [Post /server/usemanage/updatenickname]
     * 
     * @param username
     * @param appkey
     * @param appkey
     * @return
     */
    @POST
    @Path("updatenickname")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updateNickName(@FormParam("username") String username, @FormParam("appkey") String appkey, @FormParam("nickname") String nickname);

}
