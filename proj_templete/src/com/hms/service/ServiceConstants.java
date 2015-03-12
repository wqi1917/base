package com.hms.service;

public class ServiceConstants {

    /**
     * Openfire 对应参数
     */
    public static final String H_SJID               = "sjid";

    /**
     * Common constants
     */
    public static final String RET_INFO             = "info";
    public static final String RET_SUCCESS          = "success";
    public static final String RET_ERROR            = "error";
    public static final String RET_FAILED           = "failed";

    // System error
    public static final String SUCCESS              = "000000";
    // 100000  未知错误
    public static final String SYS_ERROR_UNKNOWN    = "100000";
    // 100001  系统忙
    public static final String SYS_BUSY             = "100001";
    // 100002  操作超时
    public static final String SYS_TIME_OUT         = "100002";
    // 100003  网络异常
    public static final String SYS_NETWORK_ERROR    = "100003";
    // 100004  数据库操作异常
    public static final String SYS_DB_ERROR         = "100004";

    // Parameters error
    //  200000  未知错误
    public static final String PARAM_ERROR_UNKNOWN  = "200000";
    //  200001  必选参数为空
    public static final String PARAM_LACK           = "200001";
    //  200002  参数格式错误
    public static final String PARAM_BAD_FORMAT     = "200002";
    //  200003  参数长度超出范围
    public static final String PARAM_OVERLENGTH     = "200003";
    //  200004  所有输入参数都为空
    public static final String PARAM_EMPTY          = "200004";
    //  200005  消息版本号非法
    public static final String PARAM_BAD_MSGVERSION = "200005";
    //  200008  消息名称错误
    public static final String PARAM_BAD_MSGNAME    = "200008";
    //  200009  消息解析失败
    public static final String PARAM_BAD_MSGFORMAT  = "200009";
}
