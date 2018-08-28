
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for depart_count
-- ----------------------------
DROP TABLE IF EXISTS `depart_count`;
CREATE TABLE `depart_count` (
  `depart_id` int(11) NOT NULL COMMENT '部门id',
  `depart_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '部门名称',
  `good` int(11) NOT NULL COMMENT '好评数',
  `bad` int(11) NOT NULL COMMENT '差评数',
  `yearmonth` int(11) NOT NULL COMMENT '只有年月，如201612'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of depart_count
-- ----------------------------
INSERT INTO `depart_count` VALUES ('1', '迎宾大道', '4', '0', '201711');
INSERT INTO `depart_count` VALUES ('2', '运营部', '26', '2', '201711');
INSERT INTO `depart_count` VALUES ('3', '财务管理部', '14', '0', '201711');
INSERT INTO `depart_count` VALUES ('4', '运营子1', '28', '9', '201711');

-- ----------------------------
-- Table structure for thumb_up
-- ----------------------------
DROP TABLE IF EXISTS `thumb_up`;
CREATE TABLE `thumb_up` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `from_userid` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '成员UserID。对应管理端的帐号',
  `from_username` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '成员名称',
  `from_depart` varchar(100) COLLATE utf8_bin DEFAULT '' COMMENT '人员所在部门',
  `from_position` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '职位信息',
  `from_mobile` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码',
  `from_gender` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '性别。0表示未定义，1表示男性，2表示女性',
  `from_email` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `from_avatar` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '头像url。注：如果要获取小图将url最后的”/0”改成”/100”即可',
  `from_telephone` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '座机',
  `from_status` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '激活状态: 1=已激活，2=已禁用，4=未激活。已激活代表已激活企业微信或已关注微信插件。未激活代表既未激活企业微信又未关注微信插件。',
  `thumb_up` int(11) NOT NULL DEFAULT '0' COMMENT '1=点赞， -1=差评',
  `reason` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '点赞或差评 原因',
  `to_isleader` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '上级字段，标识是否为上级。1',
  `to_department_id` int(11) NOT NULL COMMENT '部门ID',
  `to_department_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '部门名称',
  `to_userid` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '成员UserID。对应管理端的帐号',
  `to_username` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '成员名称',
  `to_position` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '职位信息',
  `to_mobile` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码',
  `to_gender` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '性别。0表示未定义，1表示男性，2表示女性',
  `to_email` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `to_avatar` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '头像url。注：如果要获取小图将url最后的”/0”改成”/100”即可',
  `to_telephone` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '座机',
  `to_status` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '激活状态: 1=已激活，2=已禁用，4=未激活。已激活代表已激活企业微信或已关注微信插件。未激活代表既未激活企业微信又未关注微信插件。',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '事件发生时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1083 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for wx_token
-- ----------------------------
DROP TABLE IF EXISTS `wx_token`;
CREATE TABLE `wx_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `token` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'token临时存放',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=409 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of wx_token
-- ----------------------------
INSERT INTO `wx_token` VALUES ('407', 'sThFnVVW5HHWmJ7PHJaAo4LvS-zY-i6sUpF6Yw52B0I2QISOgEmg6fRF1SyJ7SIHKxtryuPhVBvBwAnxJq6Si75rNjEvdfYgTPiIMIz00S8c26RARRW_wU0q8wgaz9NfEw9OW3LSMjv-kCYkfWEu_1VdGSSlSLo9pFsX6rFQQZc5xoYF-09gBGoTT-zNKfi3M_o1C7vCyOOnn0pKtYmDLA');
INSERT INTO `wx_token` VALUES ('408', 'sThFnVVW5HHWmJ7PHJaAo4LvS-zY-i6sUpF6Yw52B0I2QISOgEmg6fRF1SyJ7SIHKxtryuPhVBvBwAnxJq6Si75rNjEvdfYgTPiIMIz00S8c26RARRW_wU0q8wgaz9NfEw9OW3LSMjv-kCYkfWEu_1VdGSSlSLo9pFsX6rFQQZc5xoYF-09gBGoTT-zNKfi3M_o1C7vCyOOnn0pKtYmDLA');



DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '员工唯一id',
  `user_name` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '员工姓名',
  `user_depart` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '员工所有部门',
  `avatar` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_user_id_uindex` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='所有员工表';
