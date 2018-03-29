/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.81
Source Server Version : 50721
Source Host           : 192.168.10.81:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-03-29 17:40:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(128) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `department_code` varchar(255) DEFAULT NULL COMMENT '部门code',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(128) NOT NULL AUTO_INCREMENT,
  `user_id` int(128) DEFAULT NULL COMMENT '用户id',
  `arrive_time` datetime DEFAULT NULL COMMENT '签到时间',
  `image` varchar(255) DEFAULT NULL COMMENT '签到图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `id` int(128) NOT NULL AUTO_INCREMENT,
  `arrive_time` datetime DEFAULT NULL COMMENT '规定签到时间',
  `leave_time` datetime DEFAULT NULL COMMENT '规定签退时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(128) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `user_img` varchar(255) DEFAULT NULL COMMENT '用户头像路径',
  `user_code` varchar(255) DEFAULT NULL COMMENT '用户code',
  `department_id` int(128) DEFAULT NULL COMMENT '部门id',
  `role` varchar(255) DEFAULT NULL COMMENT '角色 admin/user',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
