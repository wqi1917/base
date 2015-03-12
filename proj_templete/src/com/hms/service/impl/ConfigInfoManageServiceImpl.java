package com.hms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.hms.dto.ConfigInfoDto;
import com.hms.manage.ManageHandlerResult;
import com.hms.manage.handler.old.IConfigInfoManageHandler;
import com.hms.service.IConfigInfoManageService;
import com.hms.service.ServiceException;
import com.hms.util.CommonConstants;
import com.hms.util.StringUtil;

/**
 * 
 * 
 * @author yanglei
 * @version $Id: ConfigInfoManageServiceImpl.java, v 0.1 2014年11月20日 上午11:26:34 yanglei Exp $
 */
@Path("/configinfo")
public class ConfigInfoManageServiceImpl implements IConfigInfoManageService {

    @Autowired
    private IConfigInfoManageHandler configInfoManageHandler;

    /**
     * 
     * @see com.usercenter.service.IConfigInfoManageService#add(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
     */
    public Response add(Integer direction, String msgbiztype, String msgbiztype_name,
                        String business_plat, String upmsg_service, String downmsg_service,
                        String callback_service, Integer max_receiver_num) {

        if (direction == null) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "新增操作的参数异常：direction不能为空！");
        }
        if (StringUtil.isEmpty(msgbiztype)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "新增操作的参数异常：msgbiztype不能为空！");
        }
        if (StringUtil.isEmpty(msgbiztype_name)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "新增操作的参数异常：msgbiztype_name不能为空！");
        }

        ConfigInfoDto configInfoDto = new ConfigInfoDto();
        configInfoDto.setDirection(direction);
        configInfoDto.setMsgbiztype(msgbiztype);
        configInfoDto.setMsgbiztype_name(msgbiztype_name);
        configInfoDto.setBusiness_plat(business_plat);
        configInfoDto.setUpmsg_service(upmsg_service);
        configInfoDto.setDownmsg_service(downmsg_service);
        configInfoDto.setCallback_service(callback_service);
        configInfoDto.setMax_receiver_num(max_receiver_num);
        configInfoDto.setCreatetime(new Date());

        ManageHandlerResult result = configInfoManageHandler.insert(configInfoDto);
        return Response.ok().entity(createRetMsg("add", result)).build();
    }

    /**
     * 
     * @see com.usercenter.service.IConfigInfoManageService#delete(java.lang.Integer)
     */
    public Response delete(Integer config_uuid) {

        if (config_uuid == null) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "删除操作的参数异常：config_uuid不能为空！");
        }
        ManageHandlerResult result = configInfoManageHandler.delete(config_uuid);
        return Response.ok().entity(createRetMsg("deletebyconfig_uuid", result)).build();
    }

    /**
     * 
     * @see com.usercenter.service.IConfigInfoManageService#delete(java.lang.String)
     */
    public Response delete(String business_plat) {

        if (StringUtil.isEmpty(business_plat)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "删除操作的参数异常：business_plat不能为空！");
        }
        ManageHandlerResult result = configInfoManageHandler.delete(business_plat);
        return Response.ok().entity(createRetMsg("deletebybusiness_plat", result)).build();
    }

    /**
     * 
     * @see com.usercenter.service.IConfigInfoManageService#update(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
     */
    public Response update(Integer config_uuid, Integer direction, String msgbiztype,
                           String msgbiztype_name, String business_plat, String upmsg_service,
                           String downmsg_service, String callback_service, Integer max_receiver_num) {

        if (config_uuid == null) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "更新操作的参数异常：config_uuid不能为空！");
        }
        if (direction == null) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "更新操作的参数异常：direction不能为空！");
        }
        if (StringUtil.isEmpty(msgbiztype)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "更新操作的参数异常：msgbiztype不能为空！");
        }
        if (StringUtil.isEmpty(msgbiztype_name)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "更新操作的参数异常：msgbiztype_name不能为空！");
        }
        ConfigInfoDto configInfoDto = new ConfigInfoDto();
        configInfoDto.setConfig_uuid(direction);
        configInfoDto.setDirection(direction);
        configInfoDto.setMsgbiztype(msgbiztype);
        configInfoDto.setMsgbiztype_name(msgbiztype_name);
        configInfoDto.setBusiness_plat(business_plat);
        configInfoDto.setUpmsg_service(upmsg_service);
        configInfoDto.setDownmsg_service(downmsg_service);
        configInfoDto.setCallback_service(callback_service);
        configInfoDto.setMax_receiver_num(max_receiver_num);
        ManageHandlerResult result = configInfoManageHandler.update(config_uuid, configInfoDto);
        return Response.ok().entity(createRetMsg("update", result)).build();
    }

    /**
     * 
     * @see com.usercenter.service.IConfigInfoManageService#selectbyconfig_uuid(java.lang.Integer)
     */
    public String selectbyconfig_uuid(Integer config_uuid) {

        if (config_uuid == null) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "查询操作的参数异常：config_uuid不能为空！");
        }
        ConfigInfoDto configInfoRet = configInfoManageHandler.selectbyconfig_uuid(config_uuid);
        if (configInfoRet == null) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.NOT_FOUND,
                "通过config_uuid未查询到消息业务类型信息！");
        }
        /*System.out.println("config_uuid:" + configInfoRet.getConfig_uuid());
        System.out.println("direction:" + configInfoRet.getDirection());
        System.out.println("msgbiztype:" + configInfoRet.getMsgbiztype());
        System.out.println("msgbiztype_name:" + configInfoRet.getMsgbiztype_name());
        System.out.println("business_plat:" + configInfoRet.getBusiness_plat());
        System.out.println("upmsg_service:" + configInfoRet.getUpmsg_service());
        System.out.println("downmsg_service:" + configInfoRet.getDownmsg_service());
        System.out.println("callback_service:" + configInfoRet.getCallback_service());
        System.out.println("max_receiver_num:" + configInfoRet.getMax_receiver_num());
        System.out.println("createtime:" + configInfoRet.getCreatetime());
        System.out.println("lastupdatetime:" + configInfoRet.getLastupdatetime());*/
        JSONObject array = JSONObject.fromObject(configInfoRet);
        return array.toString();
    }

    /**
     * 
     * @see com.usercenter.service.IConfigInfoManageService#selectbymsgbiztype(java.lang.String)
     */
    public String selectbymsgbiztype(String msgbiztype) {

        if (StringUtil.isEmpty(msgbiztype)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "查询操作的参数异常：msgbiztype不能为空！");
        }
        ConfigInfoDto configInfoRet = configInfoManageHandler.selectbymsgbiztype(msgbiztype);
        if (configInfoRet == null) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.NOT_FOUND,
                "通过msgbiztype未查询到消息业务类型信息！");
        }
        /*System.out.println("config_uuid:" + configInfoRet.getConfig_uuid());
        System.out.println("direction:" + configInfoRet.getDirection());
        System.out.println("msgbiztype:" + configInfoRet.getMsgbiztype());
        System.out.println("msgbiztype_name:" + configInfoRet.getMsgbiztype_name());
        System.out.println("business_plat:" + configInfoRet.getBusiness_plat());
        System.out.println("upmsg_service:" + configInfoRet.getUpmsg_service());
        System.out.println("downmsg_service:" + configInfoRet.getDownmsg_service());
        System.out.println("callback_service:" + configInfoRet.getCallback_service());
        System.out.println("max_receiver_num:" + configInfoRet.getMax_receiver_num());
        System.out.println("createtime:" + configInfoRet.getCreatetime());
        System.out.println("lastupdatetime:" + configInfoRet.getLastupdatetime());*/
        JSONObject array = JSONObject.fromObject(configInfoRet);
        return array.toString();
    }

    /**
     * 
     * @see com.usercenter.service.IConfigInfoManageService#selectbybusiness_plat(java.lang.String)
     */
    public Response selectbybusiness_plat(String business_plat) {

        if (StringUtil.isEmpty(business_plat)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_ERROR,
                "查询操作的参数异常：business_plat不能为空！");
        }
        List<ConfigInfoDto> configInfoRet = configInfoManageHandler
            .selectbybusiness_plat(business_plat);
        if (configInfoRet == null) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.NOT_FOUND,
                "通过business_plat未查询到消息业务类型信息！");

        }
        /*for (int i = 0; i < configInfoRet.size(); i++) {
            System.out.println("config_uuid:" + configInfoRet.get(i).getConfig_uuid());
            System.out.println("direction:" + configInfoRet.get(i).getDirection());
            System.out.println("msgbiztype:" + configInfoRet.get(i).getMsgbiztype());
            System.out.println("msgbiztype_name:" + configInfoRet.get(i).getMsgbiztype_name());
            System.out.println("business_plat:" + configInfoRet.get(i).getBusiness_plat());
            System.out.println("upmsg_service:" + configInfoRet.get(i).getUpmsg_service());
            System.out.println("downmsg_service:" + configInfoRet.get(i).getDownmsg_service());
            System.out.println("callback_service:" + configInfoRet.get(i).getCallback_service());
            System.out.println("max_receiver_num:" + configInfoRet.get(i).getMax_receiver_num());
            System.out.println("createtime:" + configInfoRet.get(i).getCreatetime());
            System.out.println("lastupdatetime:" + configInfoRet.get(i).getLastupdatetime());
        }*/
        return Response.ok()
            .entity(createRetMsg("selectbybusiness_plat", new ManageHandlerResult())).build();
    }

    /**
     * 返回状态
     * 
     * @param msgid
     * @param result
     * @return
     */
    private String createRetMsg(String operation, ManageHandlerResult result) {

        if (result.isSuccess()) {
            Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("status", CommonConstants.RET_SUCCESS);
            map.put("operation", operation);
            return JSONObject.fromObject(map).toString();
        }
        throw new ServiceException(result.getError_code(), result.getStatus(), result.getReason(),
            result.getMsgid());

    }

    /**
     * 
     * @see com.usercenter.service.IConfigInfoManageService#getList(java.lang.Integer, java.lang.Integer)
     */
    public String getList(Integer page, Integer size) {

        List<ConfigInfoDto> configInfoRet = configInfoManageHandler.getList(page, size);

        if (configInfoRet == null) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.NOT_FOUND,
                "getList失败！");
        }
        JSONArray array = JSONArray.fromObject(configInfoRet);
        JSONObject json = new JSONObject();
        json.put("rows", array);
        int count = configInfoManageHandler.getSize();
        json.put("total", count);
        String result = json.toString();
        System.out.println(result);
        return result;
    }

    /**
     * 
     * @see com.usercenter.service.IConfigInfoManageService#selectByMsgBiztypeOrName(java.lang.String)
     */
    public String selectByMsgBiztypeOrName(String MsgBiztypeOrName, Integer page, Integer size) {
        String result = "";
        if (MsgBiztypeOrName != null && !MsgBiztypeOrName.equals("")) {
            List<ConfigInfoDto> configInfoRet = configInfoManageHandler.selectByMsgBiztypeOrName(
                MsgBiztypeOrName, page, size);
            JSONObject json = new JSONObject();

            if (configInfoRet == null) {
                json.put("rows", "");
                json.put("total", 0);
                result = json.toString();
                return result;
                //                throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.NOT_FOUND,
                //                    "通过MsgBiztypeOrName未查询到消息业务类型信息！");
            }
            JSONArray array = JSONArray.fromObject(configInfoRet);
            json.put("rows", array);
            int count = configInfoManageHandler.getSelectByMsgBiztypeOrNameSize(MsgBiztypeOrName);
            json.put("total", count);
            result = json.toString();
            System.out.println(result);
            return result;
        }
        return result;
    }
}
