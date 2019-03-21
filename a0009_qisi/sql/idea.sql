/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50638
Source Host           : localhost:8806
Source Database       : idea

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-08-26 17:40:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0=奇思妙想  1=吐槽',
  `title` varchar(100) NOT NULL COMMENT '主要内容',
  `content` text NOT NULL COMMENT '主要内容',
  `advice` text COMMENT '建议',
  `author_wxid` varchar(80) NOT NULL COMMENT '作者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除 1=是 0=没删除',
  `close` tinyint(4) NOT NULL DEFAULT '0' COMMENT '关贴',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=414 DEFAULT CHARSET=utf8mb4 COMMENT='文章主表';

-- ----------------------------
-- Table structure for article_read
-- ----------------------------
DROP TABLE IF EXISTS `article_read`;
CREATE TABLE `article_read` (
  `article_id` int(11) NOT NULL,
  `reader_wxid` varchar(80) NOT NULL COMMENT '阅读者id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`article_id`,`reader_wxid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` bigint(20) NOT NULL COMMENT '关联文章id',
  `author_wxid` varchar(80) NOT NULL COMMENT '评论人作者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `content` varchar(255) NOT NULL COMMENT '评论内容',
  `thumb_num` int(11) DEFAULT '0' COMMENT '点赞数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=635 DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

-- ----------------------------
-- Table structure for comment_thumbup
-- ----------------------------
DROP TABLE IF EXISTS `comment_thumbup`;
CREATE TABLE `comment_thumbup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) NOT NULL,
  `thumb_userid` varchar(200) NOT NULL COMMENT '点赞人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=330 DEFAULT CHARSET=utf8mb4 COMMENT='评论点赞表';

-- ----------------------------
-- Table structure for depart
-- ----------------------------
DROP TABLE IF EXISTS `depart`;
CREATE TABLE `depart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `depart_id` varchar(50) NOT NULL COMMENT '部门id',
  `parent_id` varchar(50) NOT NULL COMMENT '父部门id',
  `depart_name` varchar(200) DEFAULT NULL COMMENT '部门名称',
  `order` int(11) DEFAULT NULL COMMENT '微信自带',
  PRIMARY KEY (`id`),
  KEY `depart_id` (`depart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4730 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- ----------------------------
-- Table structure for depart_user
-- ----------------------------
DROP TABLE IF EXISTS `depart_user`;
CREATE TABLE `depart_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `depart_id` varchar(50) NOT NULL COMMENT '部门id',
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `fk_departid` (`depart_id`),
  KEY `fk_userid` (`user_id`),
  CONSTRAINT `fk_departid` FOREIGN KEY (`depart_id`) REFERENCES `depart` (`depart_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_userid` FOREIGN KEY (`user_id`) REFERENCES `wx_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53514 DEFAULT CHARSET=utf8mb4 COMMENT='部门 用户关系表';

-- ----------------------------
-- Table structure for imgaddress
-- ----------------------------
DROP TABLE IF EXISTS `imgaddress`;
CREATE TABLE `imgaddress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` bigint(20) NOT NULL COMMENT '和article主键关联',
  `pic_origin_name` varchar(255) DEFAULT NULL COMMENT '图片名称',
  `pic_uid_name` varchar(255) NOT NULL COMMENT '图片地址',
  `pic_index` int(11) NOT NULL COMMENT '图片顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8mb4 COMMENT='图片地址表';

-- ----------------------------
-- Table structure for wx_token
-- ----------------------------
DROP TABLE IF EXISTS `wx_token`;
CREATE TABLE `wx_token` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2651 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user` (
  `user_id` varchar(100) NOT NULL COMMENT 'userId， 可以有多个相同的id， 部门可能是不同的',
  `user_name` varchar(200) DEFAULT NULL COMMENT '用户名',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='所有微信用户';



-- 2019-03-17加入

ALTER TABLE `article`
ADD COLUMN `relate_depart`  varchar(50) NOT NULL DEFAULT '' COMMENT '相关部门' AFTER `advice`;

