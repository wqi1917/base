/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.hms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hms.dao.po.AccountInfoPO;

/**
 * 
 * 
 * @author wqi
 * @version $Id: AccountInfoDaoMapper.java, v 0.1 2015-2-27 上午9:10:20 wangq Exp $
 */
public interface AccountInfoDaoMapper {

    /**
     * 新增记录
     * 
     * @param accountInfoPO
     * @return
     */
    public boolean insert(AccountInfoPO accountInfoPO);


    /**
     * 批量新增
     * 
     * @param accountInfoList
     * @return
     */
    public boolean insertBatch(List<AccountInfoPO> accountInfoList);

    /**
     * 删除记录
     * 
     * @param account
     * @return
     */
    public boolean deleteByAccount(long account);

    /**
     * 删除记录
     * 
     * @param accountname
     * @return
     */
    public boolean deleteByName(String accountname);

    /**
     * 修过记录
     * 
     * @param configinfo
     * @return
     */
    public boolean update(@Param("accountinfo") AccountInfoPO accountinfo);

    /**
     * 修过记录
     * 
     * @param configinfo
     * @return
     */
    public boolean batchUpdate(List<AccountInfoPO> accountInfoList);


    /**
     * 查询记录
     * 
     * @param account
     * @return
     */
    public AccountInfoPO selectByAccount(long account);

    /**
     * 根据msgbiztype选择一条记录
     * 
     * @param accountname
     */
    public AccountInfoPO selectByAccountName(String accountname);

    /**
     * 根据business_plat选择一条记录
     * 
     * @param config_uuid
     */
    public List<AccountInfoPO> selectByPhone(String phone);


    /**
     * 根据用户名、密码
     * 
     * @param accountname
     */
    public AccountInfoPO getByAccountNamePass(String accountname, String password);

    /**
    * 根据账户、密码
    * 
    * @param accountname
    */
    public AccountInfoPO getByAccountPass(long account, String password);

    /**
     * 获得当前页码指定数量的消息业务信息
     * 
     * @param begin
     * @param size
     * @return
     */
    public List<AccountInfoPO> getList(@Param("begin") Integer begin, @Param("size") Integer size);

    /**
     * 获得所有数据总数
     * 
     * @return
     */
    public Integer getSize();

}
