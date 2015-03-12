/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.hms.core.SSDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.nutz.ssdb4j.SSDBs;
import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hms.core.util.StringUtil;

/**
 * 
 * @author wqi
 * @version $Id: SSDBManager.java, v 0.1 2015-3-1 下午8:26:06 wangq Exp $
 */
public class SSDBManager {

    private static final Logger         logger = LoggerFactory.getLogger(SSDBManager.class);

    private volatile static SSDBManager ssDBManager;

    private static SSDB                 ssdbPool;

    /**
     * @throws java.lang.Exception
     */
    public SSDBManager() {
        
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("ssdb");

            String masterHost = bundle.getString("masterHost");
            int masterPort = Integer.valueOf(bundle.getString("masterPort"));

            String slaveHost = bundle.getString("slaveHost");
            int slavePort = !"".equals(bundle.getString("slavePort")) ? Integer.valueOf(bundle.getString("slavePort")) : 0;
            int timeout = Integer.valueOf(bundle.getString("timeout"));
            int maxActive = Integer.valueOf(bundle.getString("maxActive"));
            boolean testWhileIdle = Boolean.valueOf(bundle.getString("testWhileIdle"));

            Config config = new Config();
            config.maxActive = maxActive;
            config.testWhileIdle = testWhileIdle;

            if (StringUtil.isBlank(slaveHost)) {
                SSDBManager.ssdbPool = SSDBs.pool(masterHost, masterPort, timeout, config);
            } else {
                SSDBManager.ssdbPool = SSDBs.replication(masterHost, masterPort, slaveHost, slavePort, timeout, config);
            }
        } catch (Exception e) {
            logger.error("ssdb加载异常", e);
        }

    }

    /**
     * 获取连接池对象
     * @return
     */
    public static SSDB getSsdbPool() {
        return ssdbPool;
    }

    public static SSDBManager getInstance() {
        if (ssDBManager == null) {
            synchronized ("ssDBManager") {
                if (ssDBManager == null) {
                    ssDBManager = new SSDBManager();
                }
            }
        }
        return ssDBManager;
    }

    public void close() {
        try {
            ssdbPool.close();
        } catch (IOException e) {
            logger.error("关闭ssdb链接异常", e);
        }
    }

    @Override
    protected void finalize() {
        close();
    }

    ///////////////////////key--value 存储  //////////////////////////////////////
    /**
     * KV存储
     *  获取key对应值
     * 
     * @param key
     * @return
     */
    public Response get(Object key) {
        return ssdbPool.get(key);
    }

    /**
     * KV存储 
     *     存储值
     * @param key
     * @param val
     * @return
     */
    public Response set(Object key, Object val) {
        return ssdbPool.set(key, val);
    }

    /**
     * 设置指定 key 的值内容, 同时设置存活时间. cmd:setx('key', 'value', 60); 单位:秒
     * 
     * @param key
     * @param val
     * @param ttl
     * @return
     */
    public Response setx(Object key, Object val, int ttl) {
        return ssdbPool.setx(key, val, ttl);
    }

    /**
     * 删除KV
     * 
     * @param key
     * @return
     */
    public Response del(Object key) {
        return ssdbPool.del(key);
    }

    /**
     * 递增
     * 
     * @param key
     * @param val
     * @return
     */
    public Response incr(Object key, int val) {
        return ssdbPool.incr(key, val);
    }

    /**
     * 递减
     * 
     * @param key
     * @param val
     * @return
     */
    public Response decr(Object key, int val) {
        return ssdbPool.decr(key, val);
    }

    /**
     * 判断是否存在
     * 
     * @param key
     * @return
     */
    public Response exists(Object key) {
        return ssdbPool.exists(key);
    }

    //
    //    Response multi_exists(Object... keys);
    //
    //    /**閬嶅巻閿�*/
    //    Response keys(Object start, Object end, int limit);
    //
    //    /**鎵归噺set*/
    //    Response multi_set(Object... pairs);
    //
    //    Response multi_get(Object... keys);
    //
    //    /**鎵归噺鍒犻櫎*/
    //    Response multi_del(Object... keys);
    //
    //    //-----
    //    Response scan(Object start, Object end, int limit);
    //
    //    Response rscan(Object start, Object end, int limit);
    //

    ////////////////////////////hashMap 存储////////////////////////////////////////

    /**
     * hashMap存储
     *      存储
     * @param key  指定hashMap
     * @param hkey map对应key
     * @param hval key对应值
     * @return
     */
    public Response hset(Object key, Object hkey, Object hval) {
        return ssdbPool.hset(key, hkey, hval);
    }

    /**
     *  hashMap存储
     *  删除
     * @param key  指定hashMap
     * @param hkey map对应key
     * @return
     */
    public Response hdel(Object key, Object hkey) {
        return ssdbPool.hdel(key, hkey);
    }

    /**
     *  hashMap存储   
     *  获取
     * @param key
     * @param hkey
     * @return
     */
    public Response hget(Object key, Object hkey) {
        return ssdbPool.hget(key, hkey);
    }

    /**
     * hashMap存储
     * 获取某个map下size
     * @param key
     * @return
     */
    public Response hsize(Object key) {
        return ssdbPool.hsize(key);
    }

    /**
     * hashMap存储
     *   
     * @param key
     * @param hkey
     * @param limit
     * @return
     */
    public Response hlist(Object key, Object hkey, int limit) {
        return ssdbPool.hlist(key, hkey, limit);
    }

    /**
     * hashMap存储
     *   map下key对应值递增
     * @param key
     * @param hkey
     * @param val
     * @return
     */
    public Response hincr(Object key, Object hkey, int val) {
        return ssdbPool.hincr(key, hkey, val);
    }

    /**
     * hashMap存储
     *      map下key对应值递减
     * @param key
     * @param hkey
     * @param val
     * @return
     */
    public Response hdecr(Object key, Object hkey, int val) {
        return ssdbPool.hdecr(key, hkey, val);
    }

    //
    //    //-----
    //    public Response hscan(Object key, Object start, Object end, int limit){return ssdbPool; }
    //
    //    public Response hrscan(Object key, Object start, Object end, int limit){return ssdbPool; }
    //
    //    public Response hkeys(Object key, Object start, Object end, int limit){return ssdbPool; }
    //
    //    public Response hexists(Object key, Object hkey){return ssdbPool; }
    //
    //    public Response hclear(Object key){return ssdbPool; }
    //
    //    public Response hgetall(Object key){return ssdbPool; }
    //
    //    public Response hvals(Object key, Object start, Object end, int limit){return ssdbPool; }
    //
    /**
     * 批量获取 hashmap 中多个 key 对应的权重值. cmd:multi_hget('h', array('k1', 'k2'));
     * @param key
     * @param hkeys
     * @return
     */
    public Map<String, String> multi_hget(Object key, Object... hkeys) {
        Response response = ssdbPool.multi_hget(key, hkeys);

        return response != null ? response.mapString() : null;
    }

    /**
     * 批量设置 hashmap 中的 key-value. cmd:multi_zset('z', array('a' => 1,'b' => 2));
     * 
     * @param key
     * @param pairs
     * @return
     */
    public Response multi_hset(Object key, Object... pairs) {
        return ssdbPool.multi_hset(key, pairs);
    }

    /**
     * 指删除 hashmap 中的 key. cmd:multi_hdel('h', array('k1', 'k2'));
     * 
     * @param key
     * @param hkeys
     * @return
     */
    public Response multi_hdel(Object key, Object... hkeys) {
        return ssdbPool.multi_hdel(key, hkeys);
    }

    //    public Response multi_hexists(Object... keys){return ssdbPool; }
    //
    //    public Response multi_hsize(Object... keys){return ssdbPool; }
    //

    ////////////////////////////zSet 存储////////////////////////////////////////

    /**
     * 设置 zset 中指定 key 对应的权重值. cmd:zset('z', 'key', 100);
     * 
     * @param key
     * @param zkey
     * @param score
     * @return
     */
    public Response zset(Object key, Object zkey, long score) {
        return ssdbPool.zset(key, zkey, score);
    }

    /**
     * 获取 zset 中指定 key 的权重值. cmd:zget('z', 'key');
     * 
     * @param key
     * @param zkey
     * @return
     */
    public Response zget(Object key, Object zkey) {
        return ssdbPool.zget(key, zkey);
    }

    /**
     * 获取 zset 中的指定 key. cmd:zdel('hz, 'key');
     * 
     * @param key
     * @param zkey
     * @return
     */
    public Response zdel(Object key, Object zkey) {
        return ssdbPool.zdel(key, zkey);
    }

    /**
     * 使 zset 中的 key 对应的值增加 val. 参数 val 可以为负数. 如果原来的值不是整数(字符串形式的整数), 它会被先转换成整数. cmd:zincr('z', 'key', 1);
     * 
     * @param key
     * @param zkey
     * @param val
     * @return
     */
    public Response zincr(Object key, Object zkey, int val) {
        return ssdbPool.zincr(key, zkey, val);
    }

    /**
     * 使 zset 中的 key 对应的值递减 val. 参数 val 可以为负数. 如果原来的值不是整数(字符串形式的整数), 它会被先转换成整数. cmd:zincr('z', 'key', 1);
     * 
     * @param key
     * @param zkey
     * @param val
     * @return
     */
    public Response zdecr(Object key, Object zkey, int val) {
        return ssdbPool.zdecr(key, zkey, val);
    }

    //
    //    public Response zlist(Object key_start, Object key_end, int limit){return ssdbPool; }
    //
    //    public Response zsize(Object key){return ssdbPool; }
    //
    //    public Response zrank(Object key, Object zkey){return ssdbPool; }
    //
    //    public Response zrrank(Object key, Object zkey){return ssdbPool; }
    //
    //    public Response zexists(Object key, Object zkey){return ssdbPool; }
    //
    //    public Response zclear(Object key){return ssdbPool; }
    //
    //    public Response zremrangebyrank(Object key, Object zkey_start, Object score_start, Object score_end, int limit){return ssdbPool; }
    //
    //    public Response zremrangebyscore(Object key, Object zkey_start, Object score_start, Object score_end, int limit){return ssdbPool; }

    /**
     * 
     * 
     * @param key
     * @param zkey_start
     * @param score_start
     * @param score_end
     * @param limit
     * @return
     */
    public List<String> zkeys(Object key, Object zkey_start, Object score_start, Object score_end, int limit) {
        Response r = ssdbPool.zkeys(key, zkey_start, score_start, score_end, limit);
        return returnZsetList(r.listString());
    }

    /**
     * 列出 zset 中的 key-score 列表,name - zset 的名字.key_start - 参见 zkeys().score_start - 参见zkeys().score_end -
     *  参见 zkeys().limit - 最多返回这么 多个元素.cmd:zscan('z', '', 1, 100, 10);
     * 
     * @param key
     * @param zkey_start
     * @param score_start
     * @param score_end
     * @param limit
     * @return
     */
    public List<String> zscan(Object key, Object zkey_start, Object score_start, Object score_end, int limit) {
        Response r = ssdbPool.zscan(key, score_start == null ? "" : score_start, score_start == null ? "" : score_start, score_end == null ? "" : score_end,
            limit);

        return returnZsetList(r.listString());
    }

    private List<String> returnZsetList(List<String> l) {
        if (l != null && l.size() > 0) {
            List<String> l3 = new ArrayList<String>(l.size());
            int i = 0;
            for (String string : l) {
                if (i % 2 == 0) {
                    l3.add(string);
                }
                i++;
            }
            return l3;
        }
        return l;
    }

    /**
     * 
     * 列出 zset 中的 key-score 列表, 反向顺序.cmd:zrscan('a', 'z', 10); 
     * @param key
     * @param zkey_start
     * @param score_start
     * @param score_end
     * @param limit
     * @return
     */
    public List<String> zrscan(Object key, Object zkey_start, Object score_start, Object score_end, int limit) {
        Response r = ssdbPool.zrscan(key, score_start == null ? "" : score_start, score_start == null ? "" : score_start, score_end == null ? "" : score_end,
            limit);
        return returnZsetList(r.listString());
    }

    //
    //    public Response zrange(Object key, int offset, int limit){return ssdbPool; }
    //
    //    public Response zrrange(Object key, int offset, int limit){return ssdbPool; }

    /**
     * 批量设置 zset 中的 key-score.cmd:multi_zset('z', array('a' => 1,'b' => 2,)); 
     * @param key
     * @param pairs
     * @return
     */
    public Response multi_zset(Object key, Object... pairs) {
        return ssdbPool.multi_zset(key, pairs);
    }

    /**
     * 说明:批量获取 zset 中多个 key 对应的权重值.cmd:multi_zget('z', array('k1', 'k2')); 
     * 
     * @param key
     * @param zkeys
     * @return
     */
    public Response multi_zget(Object key, Object... zkeys) {
        return ssdbPool.multi_zget(key, zkeys);
    }

    /**
     * 指删除 zset 中的 key.cmd:multi_zdel('z', array('k1', 'k2'));
     * 
     * @param key
     * @param zkeys
     * @return
     */
    public Response multi_zdel(Object key, Object... zkeys) {
        return ssdbPool.multi_zdel(key, zkeys);
    }

    //    public Response multi_zexists(Object key, Object... zkeys){return ssdbPool; }
    //
    //    public Response multi_zsize(Object... keys){return ssdbPool; }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SSDBManager ssdb = new SSDBManager();
        try {

            ssdb.set("key1", "test1111");
            ssdb.set("key2", "test1112");
            ssdb.set("key3", "test1113");
            ssdb.set("key4", "test1114");

            List<String> l = ssdb.zscan("ONE2ONE_SESSION_wq123_18867101766", "", null, null, 10);

            List<String> l2 = ssdb.zrscan("ONE2ONE_SESSION_wq123_18867101766", "16809123588362240", "16809123588362240", "16809123588362240", 10);
            
            List<String> l3 = ssdb.zrscan("ONE2ONE_SESSION_ZJY123#888877VV_CSQ123#888877VV", "", "", "", 10);

            Map<String, String> m = ssdb.multi_hget("ONE2ONE_SESSION_wq123_18867101766", l);

            for (String string : l) {
                System.out.println(m.get(string));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }
}
