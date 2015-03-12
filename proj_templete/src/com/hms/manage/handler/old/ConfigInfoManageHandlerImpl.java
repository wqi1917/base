package com.hms.manage.handler.old;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.hms.dao.ConfigInfoDaoMapper;
import com.hms.dao.vo.ConfigInfoVO;
import com.hms.dto.ConfigInfoDto;
import com.hms.dto.PoVoConvert;
import com.hms.manage.ManageHandlerResult;
import com.hms.util.CommonConstants;

/**
 * 
 * 
 * @author yanglei
 * @version $Id: ConfigInfoManageHandlerImpl.java, v 0.1 2014年11月20日 上午11:36:03 yanglei Exp $
 */
public class ConfigInfoManageHandlerImpl implements IConfigInfoManageHandler {

    public static Logger        logger = Logger.getLogger(ReceiveServiceHandlerImpl.class);

    private ConfigInfoDaoMapper configInfoDaoMapper;

    @Resource
    public void setConfigInfoDaoMapper(ConfigInfoDaoMapper configInfoDaoMapper) {
        this.configInfoDaoMapper = configInfoDaoMapper;

    }

    /**
     * 
     * @see com.hms.manage.handler.old.IConfigInfoManageHandler#insert(com.hms.dto.ConfigInfoDto)
     */
    public ManageHandlerResult insert(ConfigInfoDto configInfoDto) {
        if (logger.isInfoEnabled()) {
            logger.info("新增配置：configInfoDto=" + configInfoDto.toString());
        }

        boolean isSuccess = configInfoDaoMapper.insert(PoVoConvert
            .configInfoDto2VoConvert(configInfoDto));

        if (!isSuccess) {
            logger.warn("未成功插入消息业务的注册信息！configInfoDto=" + configInfoDto.toString());
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.CREATE_ERROR,
                "未成功插入消息业务的注册信息！");
        }
        return new ManageHandlerResult();
    }

    /**
     * 
     * @see com.hms.manage.handler.old.IConfigInfoManageHandler#delete(int)
     */
    public ManageHandlerResult delete(int config_uuid) {

        boolean isSuccess = configInfoDaoMapper.delete(config_uuid);
        if (isSuccess) {
            return new ManageHandlerResult();
        } else {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.DELETE_ERROR,
                "通过config_uuid未成功删除消息业务的注册信息！");
        }
    }

    /**
     * 
     * @see com.hms.manage.handler.old.IConfigInfoManageHandler#delete(java.lang.String)
     */
    public ManageHandlerResult delete(String business_plat) {

        boolean isSuccess = configInfoDaoMapper.deleteByPlat(business_plat);
        if (isSuccess) {
            return new ManageHandlerResult();
        } else {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.DELETE_ERROR,
                "通过business_plat未成功删除消息业务的注册信息！");
        }
    }

    /**
     * 
     * @see com.hms.manage.handler.old.IConfigInfoManageHandler#update(int, com.hms.dto.ConfigInfoDto)
     */
    public ManageHandlerResult update(int config_uuid, ConfigInfoDto configInfoDto) {
        if (configInfoDto == null) {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.UPDATE_ERROR,
                "参数异常：预更新的消息业务类型为null！");
        }
        boolean isSuccess = configInfoDaoMapper.update(config_uuid,
            PoVoConvert.configInfoDto2VoConvert(configInfoDto));
        if (isSuccess) {
            return new ManageHandlerResult();
        } else {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.UPDATE_ERROR,
                "未成功更新消息业务的注册信息！");
        }
    }

    /**
     * 
     * @see com.hms.manage.handler.old.IConfigInfoManageHandler#selectByconfig_uuid(int)
     */
    public ConfigInfoDto selectbyconfig_uuid(int config_uuid) {

        return PoVoConvert.configInfoVo2DtoConvert(configInfoDaoMapper
            .selectbyconfig_uuid(config_uuid));
    }

    /**
     * 
     * @see com.hms.manage.handler.old.IConfigInfoManageHandler#selectbymsgbiztype(java.lang.String)
     */
    public ConfigInfoDto selectbymsgbiztype(String msgbiztype) {
        return PoVoConvert.configInfoVo2DtoConvert(configInfoDaoMapper
            .selectbymsgbiztype(msgbiztype));
    }

    /**
     * 
     * @see com.hms.manage.handler.old.IConfigInfoManageHandler#selectByPlatform(java.lang.String)
     */
    public List<ConfigInfoDto> selectbybusiness_plat(String business_plat) {

        List<ConfigInfoVO> listVo = configInfoDaoMapper.selectbybusiness_plat(business_plat);
        if (listVo == null) {
            return null;
        }
        List<ConfigInfoDto> listDto = new ArrayList<ConfigInfoDto>();
        for (int i = 0; i < listVo.size(); i++) {
            listDto.add(PoVoConvert.configInfoVo2DtoConvert(listVo.get(i)));
        }
        return listDto;
    }

    /**
     * 
     * @see com.hms.manage.handler.old.IConfigInfoManageHandler#getList(java.lang.Integer, java.lang.Integer)
     */
    public List<ConfigInfoDto> getList(Integer page, Integer size) {
        int begin = size * (page - 1);
        List<ConfigInfoVO> listVo = configInfoDaoMapper.getList(begin, size);
        if (listVo == null) {
            return null;
        }
        List<ConfigInfoDto> listDto = new ArrayList<ConfigInfoDto>();
        for (int i = 0; i < listVo.size(); i++) {
            listDto.add(PoVoConvert.configInfoVo2DtoConvert(listVo.get(i)));
        }
        return listDto;

    }

    /**
     * 
     * @see com.hms.manage.handler.old.IConfigInfoManageHandler#getSize()
     */
    public Integer getSize() {
        return configInfoDaoMapper.getSize();
    }

    /**
     * 
     * @see com.hms.manage.handler.old.IConfigInfoManageHandler#selectByMsgBiztypeOrName(java.lang.String)
     */
    public List<ConfigInfoDto> selectByMsgBiztypeOrName(String MsgBiztypeOrName, Integer page,
                                                        Integer size) {
        int begin = size * (page - 1);
        List<ConfigInfoVO> listVo = configInfoDaoMapper.selectByMsgBiztypeOrName(MsgBiztypeOrName,
            begin, size);
        if (listVo == null) {
            return null;
        }
        List<ConfigInfoDto> listDto = new ArrayList<ConfigInfoDto>();
        for (int i = 0; i < listVo.size(); i++) {
            listDto.add(PoVoConvert.configInfoVo2DtoConvert(listVo.get(i)));
        }
        return listDto;
    }

    /**
     * 
     * @see com.hms.manage.handler.old.IConfigInfoManageHandler#getSelectByMsgBiztypeOrNameSize(java.lang.String)
     */
    public Integer getSelectByMsgBiztypeOrNameSize(String MsgBiztypeOrName) {
        return configInfoDaoMapper.getSelectByMsgBiztypeOrNameSize(MsgBiztypeOrName);
    }
}
