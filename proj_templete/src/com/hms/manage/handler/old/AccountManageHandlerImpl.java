/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.manage.handler.old;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hms.manage.ManageHandlerResult;
import com.hms.message.MessageGW;
import com.hms.message.MessageResult;

/**
 * 
 * @author wangq
 * @version $Id: AccountManageHandlerImpl.java, v 0.1 2014-11-19 下午3:18:20 wangq Exp $
 */
public class AccountManageHandlerImpl implements IAccountManageHandler {
    public static Logger logger = Logger.getLogger(AccountManageHandlerImpl.class);

    /** 
     * @see com.hms.manage.handler.old.IAccountManageHandler#msgDeliver(java.lang.String, java.lang.String, java.lang.String)
     */
    public ManageHandlerResult manage(String platform, String type, String userinfos) {
        logger.info("账户管理：platform=" + platform + ";type=" + type + "userinfos=" + userinfos);

        // TODO  #1 根据platform 获取平台信息，校验是否可以创建账户信息
        // #2 获取账户前缀，校验userInfos值是否正确 

        try {
            if ("add".equals(type)) {
                logger.info("openservice add operation");
                Map users = createUsers(userinfos);
                //                replyMessage(addMessage(users),response, out);
            } else if ("delete".equals(type)) {
                Log.info("openservice delete operation");
                String deletecount = plugin.deleteUsers(userinfos);
                replyMessage("<success>" + deletecount.split(":")[0] + "</success><error>" + deletecount.split(":")[1] + "</error>", response, out);
            } else if ("update".equals(type)) {
                Log.info("openservice update operation");
                int updatecount = plugin.updateUsers(userinfos);
                //                replyMessage("<success>"+updatecount+"</success>",response,out);
            } else if ("search".equals(type)) {
                Log.info("openservice search operation");
                Collection collection = plugin.searchUsers(userinfos);
                replyMessage(searchMessage(collection), response, out);
            } else {
                Log.error(SimpleUser.ERROR_REASON_TYPE_ERROR);
                replyMessage(SimpleUser.ERROR_REASON_TYPE_ERROR, response, out);
                //                System.err.println("The openService servlet received an invalid request of type: " + type);
            }
        }
        //        catch (UserAlreadyExistsException e) {
        //            replyMessage("UserAlreadyExistsException",response, out);
        //        }
        //
        //        catch (IllegalArgumentException e) {
        //            replyMessage("IllegalArgumentException",response, out);
        //        }
        catch (Exception e) {
            e.printStackTrace();
            //            replyMessage(e.toString(),response, out);
        }

        // #3 发送账号操作请求
        MessageResult result = MessageGW.sendAccountMsg(type, userinfos);
        ManageHandlerResult rt = new ManageHandlerResult();
        rt.setResultObj(result.getResponse());
        return rt;
    }

    public Map createUsers(String userinfo) throws StringprepException, SQLException, UserAlreadyExistsException {
        //暂不使用域名
        //记录条件成功的列表

        Collection successusers = new HashSet();
        //记录因为重复，而添加失败的
        Collection errorusers = new HashSet();
        //记录因传入参数格式错误，而添加失败的
        Collection errorparameter = new HashSet();
        //记录因传入密码为空，而添加失败的
        Collection errorpassword = new HashSet();

        HashSet list = new HashSet();
        String users[] = userinfo.split(";");
        Connection con = null;
        PreparedStatement pstmt = null;
        //        con = DbConnectionManager.getConnection();
        //        pstmt = con.prepareStatement(INSERT_USER1);
        //        boolean usePlainPassword = JiveGlobals.getBooleanProperty("user.usePlainPassword");
        try {
            for (String string : users) {
                SimpleUser user = new SimpleUser();
                String singleuserinfo[] = string.split(":");
                //判断传入参数是否正确,用户名，密码，名称 都为可选字段
                if (singleuserinfo.length != 3) {
                    if ("".equals(singleuserinfo[0])) {
                        //用户名为空字符串，不做任何操作，比如参数 ";;" 两个分好中间为空字符串
                        continue;
                    }
                    logger.error("用户名" + singleuserinfo[0] + "参数设置错误");
                    user.setUsername(singleuserinfo[0]);
                    errorparameter.add(user);
                    continue;
                }
                String username = singleuserinfo[0];
                String password = singleuserinfo[1];
                String name = singleuserinfo[2];
                user.setName(name);
                user.setUsername(username);
                user.setPassword(password);

                //For old api, we get phone number from name
                if (username.indexOf("_") > 0)
                    user.setPhone(username.substring(0, username.indexOf("_")).replace("\\+86", "").replace(" 86", ""));

                if ("".equals(username) || username == null) {
                    //用户名为空字符串，不做任何操作，比如参数 ";;" 两个分好中间为空字符串
                    continue;
                }
                //批处理添加，排除添加列表中重复用户名
                if (list.contains(username)) {
                    Log.error("参数中含有重复用户名" + username + "已添加,不重复添加。");
                    errorusers.add(user);
                    continue;
                }
                list.add(username);

                username = username.trim().toLowerCase();
                username = JID.escapeNode(username);
                username = Stringprep.nodeprep(username);

                if (isExistUser(username)) {
                    errorusers.add(user);
                    Log.error("用户名" + username + "已经存在");
                } else if ("".equals(password) || password == null) {
                    errorpassword.add(user);
                    Log.error("密码不能为空");
                } else {
                    // 用户不存在，可以创建
                    //判断密码是否加密存储 
                    String encryptedPassword = null;
                    if (!usePlainPassword) {
                        try {
                            encryptedPassword = AuthFactory.encryptPassword(password);
                            // 设置密码为null
                            password = null;
                        } catch (UnsupportedOperationException uoe) {
                            //密码加密失败，明文存储
                        }
                    }
                    Date now = new Date();
                    pstmt.setString(1, username);
                    if (password == null) {
                        pstmt.setNull(2, Types.VARCHAR);
                    } else {
                        pstmt.setString(2, password);
                    }
                    if (encryptedPassword == null) {
                        pstmt.setNull(3, Types.VARCHAR);
                    } else {
                        pstmt.setString(3, encryptedPassword);
                    }
                    if (name == null) {
                        pstmt.setNull(4, Types.VARCHAR);
                    } else {
                        pstmt.setString(4, name);
                    }
                    pstmt.setNull(5, Types.VARCHAR);
                    pstmt.setString(6, StringUtils.dateToMillis(now));
                    pstmt.setString(7, StringUtils.dateToMillis(now));

                    if (user.getPhone() == null || "".equals(user.getPhone())) {
                        pstmt.setNull(8, Types.VARCHAR);
                    } else {
                        pstmt.setString(8, user.getPhone());
                    }

                    pstmt.addBatch();
                    successusers.add(user);
                    ;
                }
            }
            pstmt.executeBatch();

            Map usersmap = new HashMap();
            usersmap.put("successusers", successusers.size());
            usersmap.put("errorusers", errorusers);
            usersmap.put("errorparameter", errorparameter);
            usersmap.put("errorpassword", errorpassword);

            return usersmap;
        } catch (Exception e) {
            Log.error(LocaleUtils.getLocalizedString("admin.error"), e);
        } finally {
            DbConnectionManager.closeConnection(pstmt, con);
        }
        return null;

    }

}
