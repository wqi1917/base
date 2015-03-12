/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.message.resource;

import javax.ws.rs.core.UriBuilder;

import com.hms.core.pool.ObjectPool;
import com.hms.util.CommonConstants;
import com.hms.util.Configures;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * openfire http连接池
 * @author wangq
 * @version $Id: OpenfireResourcePool.java, v 0.1 2014-11-20 下午4:31:46 wangq Exp $
 */
public class OpenfireResourcePool extends ObjectPool {

    private static final int numObjects = 1; // 对象池的大小     

    private static final int maxObjects = 6; // 对象池最大的大小     

    private static final int range      = 1; // 新创建幅度

    public OpenfireResourcePool() {
        super(numObjects, maxObjects, range);
    }

    /**
     * 获取对象，继承类实现
     * 
     * @return
     */
    protected Object createObject() {

        ClientConfig cc = new DefaultClientConfig();
        Client client = Client.create(cc);
        String openfire_url = Configures.getInstance().getParam(CommonConstants.OPENFIRE_URL);
        WebResource webResource = client.resource(UriBuilder.fromUri(openfire_url).build());

        return webResource;
    }

}
