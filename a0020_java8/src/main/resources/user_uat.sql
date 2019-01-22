/*
Navicat MySQL Data Transfer

Source Server         : 10.20.12.23 (UAT )
Source Server Version : 50723
Source Host           : 10.20.12.23:3306
Source Database       : user

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-01-22 14:55:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for agent
-- ----------------------------
DROP TABLE IF EXISTS `agent`;
CREATE TABLE `agent` (
  `id` bigint(20) NOT NULL COMMENT '坐席id',
  `ent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业id',
  `department_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '所属坐席组',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `agent_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '坐席名称',
  `agent_number` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '坐席号码',
  `agent_role` tinyint(4) NOT NULL DEFAULT '2' COMMENT '0:管理员坐席，1：班长坐席，2：业务员坐席',
  `extension_number` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '分机号码',
  `is_delete` int(4) NOT NULL DEFAULT '0' COMMENT '是否已删除（0：未删除1：已删除）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_seat_telephone_ent` (`extension_number`,`ent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `ent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业id',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `code` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '部门号码',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父部门ID',
  `sort` bigint(20) NOT NULL DEFAULT '0' COMMENT '在父部门的次序',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除（0：未删除1：已删除）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '部门类型：1、部门；2、坐席班组；3临时坐席班组',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100510931968307210 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='部门信息';

-- ----------------------------
-- Table structure for department_user
-- ----------------------------
DROP TABLE IF EXISTS `department_user`;
CREATE TABLE `department_user` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `ent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业id',
  `ent_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '企业名称',
  `department_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '部门id',
  `department_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `employee_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名称',
  `station_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '岗位： 0=普通 1=主管 ',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户部门关系';

-- ----------------------------
-- Table structure for developer
-- ----------------------------
DROP TABLE IF EXISTS `developer`;
CREATE TABLE `developer` (
  `id` bigint(20) NOT NULL COMMENT '开发者主键',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '开发者名称',
  `developer_type` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '开发者类型(0:企业，1：ISV)',
  `ent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '企业编号',
  `apply_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `apply_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '申请用户编号',
  `audit_status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '申请状态  1.未审核  2,审核中  3审核通过  4.审核未通过',
  `audit_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审批时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
  `is_delete` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否已删除（0：未删除1：已删除）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for developer_app
-- ----------------------------
DROP TABLE IF EXISTS `developer_app`;
CREATE TABLE `developer_app` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `app_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '应用名称',
  `developer_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '开发者编号',
  `app_type` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT 'app类型(0:一般类型;1:私有化部署)',
  `ent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '企业编号',
  `client_id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'oauth client_id',
  `client_secret` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'oauth client_secret',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用 1.启用  2.停用',
  `notify_url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '异步回调地址',
  `token` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '加解密需要用到的token,普通企业可以随机填写',
  `aes_key` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '数据加密密钥。用于回调数据的加密，长度固定为43个字符，从a-z, A-Z, 0-9共62个字符中选取,您可以随机生成',
  `aes_enable` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否启用数据加密 0：未启用；1：已启用',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除（0：未删除1：已删除）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `user_id` bigint(20) NOT NULL COMMENT '关联的user id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_client_id` (`client_id`) USING BTREE,
  KEY `id_ent_id_type` (`ent_id`,`app_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `ent_id` bigint(20) NOT NULL COMMENT '企业id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '企业内姓名',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0=不可用 (停用) 1=可用',
  `clue_number` int(11) NOT NULL DEFAULT '0' COMMENT '个人线索数',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1= 已删除 0=未删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='企业员工表';

-- ----------------------------
-- Table structure for enterprise
-- ----------------------------
DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise` (
  `ent_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '企业id',
  `admin_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '管理员的user_id',
  `ent_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '企业名称',
  `agent_id` bigint(20) DEFAULT NULL COMMENT '代理商id',
  `agent_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '代理商名字',
  `is_developer` tinyint(4) DEFAULT NULL COMMENT '是否是开发者',
  `developer_type` tinyint(4) DEFAULT NULL COMMENT '开发者类型(0:企业，1：ISV)',
  `private_clue_number` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '企业私有线索数',
  `public_clue_number` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '企业共享线索数',
  `valid_time` datetime DEFAULT NULL COMMENT '生效时间',
  `expire_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '失效日期',
  `active` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '0:未激活，1已激活',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `left_private_clue` int(11) NOT NULL DEFAULT '0' COMMENT '企业私有线索数剩余量',
  `left_public_clue` int(11) NOT NULL DEFAULT '0' COMMENT '企业私有线索数剩余量',
  `is_delete` int(4) unsigned NOT NULL DEFAULT '0' COMMENT '是否已删除（0：未删除1：已删除）',
  `create_by` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  PRIMARY KEY (`ent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100009 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='企业信息';

-- ----------------------------
-- Table structure for enterprise_config
-- ----------------------------
DROP TABLE IF EXISTS `enterprise_config`;
CREATE TABLE `enterprise_config` (
  `id` bigint(20) NOT NULL COMMENT '主键编号',
  `ent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '企业编号',
  `code` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '配置code',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '配置名称',
  `value` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '配置值',
  `type` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '配置类型(0:系统设置；1:用户设置)',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用(0:未启用；1：已启用)',
  `is_delete` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否已删除(0:未删除;1:已删除)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_entid_code` (`ent_id`,`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=FIXED;

-- ----------------------------
-- Table structure for number_location
-- ----------------------------
DROP TABLE IF EXISTS `number_location`;
CREATE TABLE `number_location` (
  `number_prefix` varchar(7) NOT NULL COMMENT '号码前缀',
  `area_code` varchar(4) DEFAULT NULL COMMENT '区号',
  `area_name` varchar(10) DEFAULT NULL COMMENT '区域',
  `operator` varchar(4) DEFAULT NULL COMMENT '运营商',
  PRIMARY KEY (`number_prefix`) USING BTREE,
  KEY `area_index` (`area_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '英文名',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '中文名',
  `type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型, API, UI',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `service_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '服务名',
  `resource` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限所代表资源，例如要访问的URL',
  `method` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '访问资源的方法',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `permission_relation`;
CREATE TABLE `permission_relation` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `father_id` bigint(20) unsigned NOT NULL COMMENT '上层权限权限ID，一般为UI权限',
  `permission_id` bigint(20) unsigned NOT NULL COMMENT '当前权限ID',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '英文名， 冗余',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '中文名',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `service_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '服务名',
  `resource` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限所代表资源，例如要访问的URL, 冗余',
  `method` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '访问资源的方法, 冗余',
  `type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型: UI,API 冗余',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`father_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `ent_id` bigint(20) unsigned NOT NULL COMMENT '企业ID, 此处必须配置到每个具体企业ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '中文名',
  `type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型: WEB,OPEN',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) unsigned NOT NULL COMMENT '权限ID',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '英文名， 冗余',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '中文名',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `service_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '服务名',
  `resource` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限所代表资源，例如要访问的URL, 冗余',
  `method` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '访问资源的方法, 冗余',
  `type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型: UI,API 冗余',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '用户名',
  `account` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '账号',
  `is_admin` tinyint(4) DEFAULT NULL COMMENT '是否管理员(1: 管理员  0: 普通用户)',
  `mobile` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '手机号',
  `email` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '邮箱',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `active` tinyint(4) DEFAULT '0' COMMENT '是否已经激活（0：未激活，1：已激活）',
  `user_status` tinyint(4) DEFAULT '1' COMMENT '用户状态（0：不可用，1：可用）',
  `avatar` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '头像',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `protocol_status` tinyint(4) DEFAULT '0' COMMENT '协议状态（0为未同意，1为同意）',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除（0：未删除，1：已删除）',
  `last_login_time` datetime DEFAULT NULL COMMENT '上一次登录时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `user_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0=企业web用户  1=企业开放平台用户和APP绑定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息';

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `ent_id` bigint(20) unsigned NOT NULL COMMENT '企业ID， 冗余字段',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '中文名',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型: WEB,OPEN',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
