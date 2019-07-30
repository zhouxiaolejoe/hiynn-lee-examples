/*
 Navicat Premium Data Transfer

 Source Server         : docker mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 192.168.238.101:3306
 Source Schema         : test1

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 30/07/2019 13:51:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '帐号',
  `password` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `reg_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'QUJBNUYyM0M3OTNEN0I4MUFBOTZBOTkwOEI1NDI0MUE=', 'admin', '2019-03-05 11:42:56');
INSERT INTO `t_user` VALUES (2, 'lisi', 'RTM3MDJENjU0MDg5QURFNUZEQTkxNTNCOEZFQ0MzMkM=', 'wang', '2019-03-05 11:42:56');
INSERT INTO `t_user` VALUES (3, 'guest', 'QTNCMzMwREY3MkMwQjRGQjNBQzUyOTM0NTFFMzJCNDg=', 'guest', '2019-03-05 11:42:56');
INSERT INTO `t_user` VALUES (4, 'zhangsan', 'QTNCMzMwREY3MkMwQjRGQjNBQzUyOTM0NTFFMzJCNDg=', 'guest', '2019-03-05 11:42:56');
INSERT INTO `t_user` VALUES (12, '444', '333', '444', NULL);

SET FOREIGN_KEY_CHECKS = 1;
