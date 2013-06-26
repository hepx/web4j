/*
Navicat MySQL Data Transfer

Source Server         : localhsot
Source Server Version : 50517
Source Host           : localhost:3306
Source Database       : web4j

Target Server Type    : MYSQL
Target Server Version : 50517
File Encoding         : 65001

Date: 2013-06-09 18:14:04
*/

SET FOREIGN_KEY_CHECKS=0;

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
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_userinfo
-- ----------------------------
INSERT INTO `tb_userinfo` VALUES ('1', 'admin', '21218cca77804d2ba1922c33e0151105', 'admin', '13510122174', '');
INSERT INTO `tb_userinfo` VALUES ('2', 'cvs', '21218cca77804d2ba1922c33e0151105', 'cvs@email.com', '138888888', '');
INSERT INTO `tb_userinfo` VALUES ('3', 'test', '21218cca77804d2ba1922c33e0151105', 'test@email.com', null, '');
