-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.0.20-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 myframework 的数据库结构
DROP DATABASE IF EXISTS `myframework`;
CREATE DATABASE IF NOT EXISTS `myframework` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `myframework`;


-- 导出  表 myframework.t_base_dept 结构
DROP TABLE IF EXISTS `t_base_dept`;
CREATE TABLE IF NOT EXISTS `t_base_dept` (
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

-- 数据导出被取消选择。


-- 导出  表 myframework.t_base_log 结构
DROP TABLE IF EXISTS `t_base_log`;
CREATE TABLE IF NOT EXISTS `t_base_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表。';

-- 数据导出被取消选择。


-- 导出  表 myframework.t_base_permission 结构
DROP TABLE IF EXISTS `t_base_permission`;
CREATE TABLE IF NOT EXISTS `t_base_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表。';

-- 数据导出被取消选择。


-- 导出  表 myframework.t_base_res 结构
DROP TABLE IF EXISTS `t_base_res`;
CREATE TABLE IF NOT EXISTS `t_base_res` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `node_id` int(11) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `URL` varchar(128) NOT NULL,
  `lft` int(11) NOT NULL,
  `rgt` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `is_enable` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `node_id` (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表。';

-- 数据导出被取消选择。


-- 导出  表 myframework.t_base_role 结构
DROP TABLE IF EXISTS `t_base_role`;
CREATE TABLE IF NOT EXISTS `t_base_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表。';

-- 数据导出被取消选择。


-- 导出  表 myframework.t_base_role_permission 结构
DROP TABLE IF EXISTS `t_base_role_permission`;
CREATE TABLE IF NOT EXISTS `t_base_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限关联表。';

-- 数据导出被取消选择。


-- 导出  表 myframework.t_base_user 结构
DROP TABLE IF EXISTS `t_base_user`;
CREATE TABLE IF NOT EXISTS `t_base_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `salt` varchar(50) DEFAULT NULL,
  `locked` tinyint(4) DEFAULT '0',
  `real_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `picture_url` varchar(128) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `credit` int(11) DEFAULT NULL,
  `last_ip` varchar(50) DEFAULT NULL,
  `last_visit` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表。';

-- 数据导出被取消选择。


-- 导出  表 myframework.t_base_user_role 结构
DROP TABLE IF EXISTS `t_base_user_role`;
CREATE TABLE IF NOT EXISTS `t_base_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色关联表。';

-- 数据导出被取消选择。


-- 导出  表 myframework.t_upload_file 结构
DROP TABLE IF EXISTS `t_upload_file`;
CREATE TABLE IF NOT EXISTS `t_upload_file` (
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

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
