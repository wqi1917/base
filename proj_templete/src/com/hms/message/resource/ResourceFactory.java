/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.message.resource;

import java.util.HashMap;

import com.hms.core.pool.ObjectPool;
import com.hms.util.StringUtil;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * @author wangq
 * @version $Id: CacheManageFactory.java, v 0.1 2014-11-12 上午9:15:49 wangq Exp $
 */
public class ResourceFactory {

    /** 消息类型注册信息 缓存*/
    public final static String                              OPENFRE          = "OPENFRE";
    public final static String                              OPENFRE_SYNC     = "OPENFRE_SYNC";
    public final static String                              BIZ_PLATFORM     = "BIZ_PLATFORM";

    private static ObjectPool                               openfirePool     = new OpenfireResourcePool();

    private static ObjectPool                               openfirePoolSync = new OpenfireResourcePool();

    private static HashMap<String, BizPlatformResourcePool> bizPool          = new HashMap<String, BizPlatformResourcePool>();

    /**
     * 获取http链接
     * 
     * @param key
     * @return
     */
    public static WebResource getResource(String key) {
        ObjectPool pool = getPool(key);
        return pool != null ? (WebResource) pool.getObject() : null;
    }

    /**
     * 获取http链接
     * 
     * @param key
     * @param wr
     */
    public static boolean returnResource(String key, WebResource wr) {
        ObjectPool pool = getPool(key);
        if (pool == null) {
            return false;
        }
        if (wr != null) {
            pool.returnObject(wr);
        }
        return true;
    }

    /**
     * 获取对象池
     * 
     * @param key
     * @return
     */
    private static ObjectPool getPool(String key) {

        if (StringUtil.isEmpty(key)) {
            return null;
        }

        ObjectPool pool = null;
        key = key.toUpperCase();
        if (OPENFRE.equalsIgnoreCase(key)) {
            pool = openfirePool;
        } else if (OPENFRE.equalsIgnoreCase(key)) {
            pool = openfirePoolSync;
        } else {

            if (!bizPool.containsKey(key)) {
                synchronized (key) {
                    if (!bizPool.containsKey(key)) {
                        BizPlatformResourcePool biz = new BizPlatformResourcePool(key);
                        bizPool.put(key, biz);
                    }
                }
            }
            pool = bizPool.get(key);
        }
        return pool;
    }

}
