/*
Navicat MariaDB Data Transfer

Source Server         : myframework
Source Server Version : 100119
Source Host           : localhost:3306
Source Database       : myframework

Target Server Type    : MariaDB
Target Server Version : 100119
File Encoding         : 65001

Date: 2016-11-13 01:19:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_base_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_base_dept`;
CREATE TABLE `t_base_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `node_id` int(11) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `lft` int(5) NOT NULL,
  `rgt` int(5) NOT NULL,
  `parent_id` int(5) NOT NULL,
  `lvl` int(5) DEFAULT NULL,
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `node_id` (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表。';

-- ----------------------------
-- Table structure for t_base_log
-- ----------------------------
DROP TABLE IF EXISTS `t_base_log`;
CREATE TABLE `t_base_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表。';

-- ----------------------------
-- Table structure for t_base_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_base_permission`;
CREATE TABLE `t_base_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表。';

-- ----------------------------
-- Table structure for t_base_permission_res
-- ----------------------------
DROP TABLE IF EXISTS `t_base_permission_res`;
CREATE TABLE `t_base_permission_res` (
  `id` int(11) NOT NULL,
  `permission_id` int(11) DEFAULT NULL,
  `res_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_base_res
-- ----------------------------
DROP TABLE IF EXISTS `t_base_res`;
CREATE TABLE `t_base_res` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `node_id` int(11) DEFAULT NULL,
  `name` varchar(50) NOT NULL COMMENT '资源名字',
  `type` varchar(50) DEFAULT NULL COMMENT '资源类型',
  `lft` int(11) NOT NULL,
  `rgt` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL COMMENT '父编号',
  `is_enable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可用',
  `URL` varchar(128) NOT NULL,
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `node_id` (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表。';

-- ----------------------------
-- Table structure for t_base_role
-- ----------------------------
DROP TABLE IF EXISTS `t_base_role`;
CREATE TABLE `t_base_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL COMMENT '角色名称',
  `description` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `available` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可用',
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表。';

-- ----------------------------
-- Table structure for t_base_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_base_role_permission`;
CREATE TABLE `t_base_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限关联表。';

-- ----------------------------
-- Table structure for t_base_user
-- ----------------------------
DROP TABLE IF EXISTS `t_base_user`;
CREATE TABLE `t_base_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `picture_url` varchar(128) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `salt` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `locked` tinyint(4) DEFAULT '0',
  `admin` tinyint(1) DEFAULT NULL,
  `credit` int(11) DEFAULT NULL,
  `last_ip` varchar(50) DEFAULT NULL,
  `last_visit` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表。';

-- ----------------------------
-- Table structure for t_base_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_base_user_role`;
CREATE TABLE `t_base_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色关联表。';

-- ----------------------------
-- Table structure for t_upload_file
-- ----------------------------
DROP TABLE IF EXISTS `t_upload_file`;
CREATE TABLE `t_upload_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(50) DEFAULT NULL,
  `file_size` int(11) DEFAULT NULL,
  `save_name` varchar(100) DEFAULT NULL,
  `save_path` varchar(100) DEFAULT NULL,
  `file_type` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上传的文件表。';
SET FOREIGN_KEY_CHECKS=1;
