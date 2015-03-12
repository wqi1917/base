/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.core.cache;

import javax.annotation.Resource;

import com.hms.core.redis.CacheManageBase;
import com.hms.dao.AccountInfoDaoMapper;
import com.hms.dao.po.AccountInfoPO;

/**
 * 
 * @author wangq
 * @version $Id: ConfigInfoCacheManage.java, v 0.1 2014-11-12 下午3:46:39 wangq Exp $
 */
public class AccountInfoCacheManage extends CacheManageBase {

    private AccountInfoDaoMapper accountInfoDaoMapper;

    @Resource
    public void setConfigInfoDaoMapper(AccountInfoDaoMapper accountInfoDaoMapper) {
        this.accountInfoDaoMapper = accountInfoDaoMapper;

    }

    /** 
     * @see com.hms.core.redis.msggw.manage.cache.CacheManageBase#uploadCache(java.lang.String, java.lang.String)
     */
    @Override
    public Object uploadCache(String cacheType, String cacheIndex) {

        AccountInfoPO infoPO = accountInfoDaoMapper.selectByAccountName(cacheIndex);
        if (infoPO == null) {
            return null;
        }
        return infoPO;
    }
}
