/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.message.resource;

import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;

import com.hms.core.ServerFactory;
import com.hms.core.cache.CacheManageFactory;
import com.hms.core.pool.ObjectPool;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 *  业务对象连接池
 * @author wangq
 * @version $Id: OpenfireResourcePool.java, v 0.1 2014-11-20 下午4:31:46 wangq Exp $
 */
public class BizPlatformResourcePool extends ObjectPool {

    public static Logger              logger             = Logger
                                                             .getLogger(BizPlatformResourcePool.class);

    private static CacheManageFactory cacheManageFactory = (CacheManageFactory) ServerFactory
                                                             .getBean("cacheManageFactory");

    private static final int          numObjects         = 1;                                                               // 对象池的大小     

    private static final int          maxObjects         = 6;                                                               // 对象池最大的大小     

    private static final int          range              = 1;                                                               // 新创建幅度

    private String                    msgbiztype         = null;

    public BizPlatformResourcePool(String msgbiztype) {
        super(numObjects, maxObjects, range);
        this.msgbiztype = msgbiztype;
    }

    /**
     * 获取对象，继承类实现
     * 
     * @return
     */
    @Override
    protected Object createObject() {

        ClientConfig cc = new DefaultClientConfig();
        Client client = Client.create(cc);
        //        ConfigInfoVO infoVo = (ConfigInfoVO) getFactory().getCacheObject(
        //            CacheManageFactory.CACHE_TYPE_CONFIG_INFO, msgbiztype);
        //        ConfigInfoVO infoVo = (ConfigInfoVO) cacheManageFactory.getCacheObject(
        //            CacheManageFactory.CACHE_TYPE_CONFIG_INFO, msgbiztype);
        //
        //        if (infoVo == null) {
        //            logger.warn("消息通道类型不存在！ msgbiztype = " + msgbiztype);
        //            return null;
        //        }
        //        String url = infoVo.getUpmsg_service();
        String url = null;
        return client.resource(UriBuilder.fromUri(url).build());
    }

}
