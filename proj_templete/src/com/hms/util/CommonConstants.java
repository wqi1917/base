package com.hms.util;

public class CommonConstants {

    /**
     * Common constants
     */
    public static final String  RET_INFO              = "info";
    public static final String  RET_SUCCESS           = "success";
    public static final String  RET_ERROR             = "error";
    public static final String  RET_FAILED            = "failed";

    /**
     * 业务异常码
     */
    public static final String  PARAM_ERROR           = "00001";
    public static final String  CREATE_ERROR          = "00002";
    public static final String  DELETE_ERROR          = "00003";
    public static final String  NOT_FOUND             = "00004";
    public static final String  UPDATE_ERROR          = "00005";
    public static final String  SEND_ERROR            = "00006";

    /**
     * Media type definition
     */
    public static final int     MEDIA_TYPE_TEXT       = 10;
    public static final int     MEDIA_TYPE_PIC        = 20;
    public static final int     MEDIA_TYPE_VIDEO      = 30;
    public static final int     MEDIA_TYPE_AUDIO      = 40;
    public static final int     MEDIA_TYPE_ARTICLE    = 50;
    public static final int     MEDIA_TYPE_SMS        = 60;

    /**
     * Message status
     */
    public static final Integer MSG_SEND_CREATED      = 0;
    public static final Integer MSG_SEND_OUT          = 1;
    public static final Integer MSG_SEND_ARRIVED      = 2;
    public static final Integer MSG_SEND_FAIL         = 3;

    public static final Integer MSG_RECV_CREATED      = 20;
    public static final Integer MSG_RECV_REPLIED      = 21;

    /**
     * uri value
     */
    public static final String  CORPORATION_MSGGW_URI = "corporation_msggw_uri";

    /**
     * url address
     */
    public static final String  IM_AS_URL             = "im_as_url";
    public static final String  OPENFIRE_URL          = "openfire_url";

    /**
     * 消息方向
     * 0:下行消息方向
     * 1：上行消息方向
     */
    public static final int     DOWN_MSG_DIRECTION    = 0;
    public static final int     UP_MSG_DIRECTION      = 1;

    /**
     * 通用分隔符
     */
    public static final String  LIST_SEPERATOR        = ";";

    public static final String  CONTENT_TYPE_XML      = "text/xml";

    public static final Integer MAX_RECEIVER_NUM      = 100;

}
