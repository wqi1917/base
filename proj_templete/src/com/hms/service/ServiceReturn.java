package com.hms.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.hms.manage.ManageHandlerResult;
import com.hms.util.StringUtil;

/**
 * 调用返回
 * 
 * @author wangq
 * @version $Id: CommonException.java, v 0.1 2014-11-11 下午3:23:13 wangq Exp $
 */
public class ServiceReturn {
    public static Logger      logger           = Logger.getLogger(ServiceReturn.class);
    /**
     * 
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 141345641231423113L;

    public static Response build(ManageHandlerResult result) {

        if (result.isSuccess()) {
            String ret = createRetMsg(result);
            if (logger.isInfoEnabled()) {
                logger.info("调用成功：" + ret);
            }
            return Response.ok().entity(ret).build();
        }
        throw new ServiceException(result.getError_code(), result.getStatus(), result.getReason(),
            result.getMsgid());
    }

    /**
     * 璁剧疆杩斿洖缁撴灉
     * 
     * @param serverid
     * @return
     */
    private static String createRetMsg(ManageHandlerResult result) {

        Map<String, String> map = new HashMap<String, String>(2);
        map.put("status", ServiceConstants.RET_SUCCESS);
        if (StringUtil.isNotEmpty(result.getBizReason())) {
            map.put("bizReason", result.getBizReason());
        }
        return JSONObject.fromObject(map).toString();

    }
}
