DROP TABLE IF EXISTS `configinfo`;
CREATE TABLE `configinfo` (
  `config_uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `direction` int(11) NOT NULL COMMENT '消息方向',
  `msgbiztype` varchar(32) NOT NULL COMMENT '消息业务类型编码',
  `msgbiztype_name` varchar(100) NOT NULL COMMENT '消息业务类型名称',
  `business_plat` varchar(32) DEFAULT NULL COMMENT '所属系统',
  `upmsg_service` varchar(200) DEFAULT NULL COMMENT '上行消息接口',
  `downmsg_service` varchar(200) DEFAULT NULL COMMENT '下行消息接口',
  `callback_service` varchar(200) DEFAULT NULL COMMENT '异步回调接口',
  `max_receiver_num` int(11) DEFAULT '0' COMMENT '群发接收者最大数量 ',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `lastupdatetime` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`config_uuid`) 
) ENGINE=InnoDB AUTO_INCREMENT=4213 DEFAULT CHARSET=utf8;
