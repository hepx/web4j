/*
Navicat MySQL Data Transfer

Source Server         : localhsot
Source Server Version : 50517
Source Host           : localhost:3306
Source Database       : web4j

Target Server Type    : MYSQL
Target Server Version : 50517
File Encoding         : 65001

Date: 2013-07-26 11:35:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_auth`
-- ----------------------------
DROP TABLE IF EXISTS `tb_auth`;
CREATE TABLE `tb_auth` (
  `roleId` int(11) NOT NULL,
  `moduleId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`moduleId`),
  KEY `roleId` (`roleId`),
  KEY `moduleId` (`moduleId`),
  CONSTRAINT `module_FK` FOREIGN KEY (`moduleId`) REFERENCES `tb_module` (`moduleId`),
  CONSTRAINT `role_FK` FOREIGN KEY (`roleId`) REFERENCES `tb_role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_auth
-- ----------------------------
INSERT INTO `tb_auth` VALUES ('1', '1');
INSERT INTO `tb_auth` VALUES ('1', '2');
INSERT INTO `tb_auth` VALUES ('1', '6');
INSERT INTO `tb_auth` VALUES ('4', '1');
INSERT INTO `tb_auth` VALUES ('4', '6');
INSERT INTO `tb_auth` VALUES ('5', '1');
INSERT INTO `tb_auth` VALUES ('5', '2');
INSERT INTO `tb_auth` VALUES ('5', '3');
INSERT INTO `tb_auth` VALUES ('5', '5');
INSERT INTO `tb_auth` VALUES ('5', '6');

-- ----------------------------
-- Table structure for `tb_log`
-- ----------------------------
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log` (
  `logId` int(11) NOT NULL AUTO_INCREMENT,
  `operator` varchar(30) DEFAULT NULL,
  `msg` varchar(255) DEFAULT NULL,
  `logTime` datetime DEFAULT NULL,
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB AUTO_INCREMENT=363 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_log
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_module`
-- ----------------------------
DROP TABLE IF EXISTS `tb_module`;
CREATE TABLE `tb_module` (
  `moduleId` int(11) NOT NULL AUTO_INCREMENT,
  `moduleName` varchar(30) NOT NULL,
  `moduleDesc` varchar(30) DEFAULT NULL,
  `moduleCode` varchar(30) NOT NULL,
  `parentCode` varchar(30) DEFAULT NULL,
  `moduleIcon` varchar(20) DEFAULT NULL,
  `moduleUrl` varchar(100) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`moduleId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_module
-- ----------------------------
INSERT INTO `tb_module` VALUES ('1', '系统管理', '系统管理', 'sysMgr', null, 'icon-wrench', null, '1');
INSERT INTO `tb_module` VALUES ('2', '用户管理', '用户管理', 'userMgr', 'sysMgr', null, '/user/show', '1');
INSERT INTO `tb_module` VALUES ('3', '角色管理', '角色管理', 'roleMgr', 'sysMgr', null, '/role/show', '2');
INSERT INTO `tb_module` VALUES ('5', '模块管理', '模块管理', 'moduleMgr', 'sysMgr', null, '/module/show', '4');
INSERT INTO `tb_module` VALUES ('6', '日志管理', '日志管理', 'logMgr', 'sysMgr', null, '/log/show', '5');

-- ----------------------------
-- Table structure for `tb_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(30) DEFAULT NULL,
  `roleCode` varchar(20) DEFAULT NULL,
  `roleDesc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '系统管理员', 'sysadmin', '系统管理员');
INSERT INTO `tb_role` VALUES ('4', '测试人员', 'test', '测试人员');
INSERT INTO `tb_role` VALUES ('5', '超级用户', 'super', '系统默认的最高用户');

-- ----------------------------
-- Table structure for `tb_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tb_userinfo`;
CREATE TABLE `tb_userinfo` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `roleFK` (`roleId`),
  CONSTRAINT `roleFK` FOREIGN KEY (`roleId`) REFERENCES `tb_role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_userinfo
-- ----------------------------
INSERT INTO `tb_userinfo` VALUES ('1', 'admin', '21218cca77804d2ba1922c33e0151105', 'admin@admin.com', '13510122174', '', '5');
INSERT INTO `tb_userinfo` VALUES ('10', 'test', '21218cca77804d2ba1922c33e0151105', 'test@aa.com', '12345678909', '', '4');
