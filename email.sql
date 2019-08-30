/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : email

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 02/07/2019 12:43:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for email
-- ----------------------------
DROP TABLE IF EXISTS `email`;
CREATE TABLE `email`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addressee_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `addresser_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `filepath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `readed` int(1) NOT NULL COMMENT '0：表示未读，1：表示已读，3：表示收件已删除，4：表示发件已删除',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `filename` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `readed`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of email
-- ----------------------------
INSERT INTO `email` VALUES (6, 'xm@666.com', 'mm@666.com', 'miss', 'E:\\download\\doc_up\\2018-07-12\\4d2ebc6c-5ef6-485a-931e-99782e3b0515_miss.doc', '2018-07-13 09:15:36', 1, 'aa', 'hello.doc');
INSERT INTO `email` VALUES (8, 'mm@666.com', 'xm@666.com', 'hello', 'E:\\download\\doc_up\\2018-07-12\\bad50ec1-727d-4a04-b1f1-2fa301845bc9_hello.doc', '2018-07-12 17:18:00', 0, '你好，<div>&nbsp; &nbsp; &nbsp; how are you?<img src=\"http://localhost:8080/springmvc/editor/plugins/emoticons/etc_36.gif\" border=\"0\" /></div><div><br /></div><div>Best,</div><div>xm</div><div><br /></div>', 'hello.doc');
INSERT INTO `email` VALUES (14, 'xm@666.com', 'mm@666.com', 'hh', 'E:\\download\\doc_up\\2018-07-12\\4d2ebc6c-5ef6-485a-931e-99782e3b0515_miss.doc', '2018-07-13 10:29:22', 1, 'aa', 'hello.doc');
INSERT INTO `email` VALUES (15, 'nn@666.com', 'mm@666.com', 'kkkkk', 'E:\\download\\doc_up\\2018-07-12\\4d2ebc6c-5ef6-485a-931e-99782e3b0515_miss.doc', '2018-07-13 10:29:14', 1, 'aa', 'hello.doc');
INSERT INTO `email` VALUES (16, 'mm@666.com', 'xm@666.com', 'hello111', 'E:\\download\\doc_up\\2018-07-13\\c2dec6f1-3e54-4df8-81b9-a5917e9f3a3f_hello111.doc', '2018-07-13 11:51:00', 0, '<h1 style=\"text-align:center;\"><img src=\"http://localhost:8080/springmvc/editor/plugins/emoticons/etc_01.gif\" border=\"0\" />hello</h1>', 'hello.doc');
INSERT INTO `email` VALUES (18, 'mm@666.com', 'hh@666.com', 'happy', 'E:\\download\\doc_up\\2018-07-13\\564b3eae-82a8-45ed-87b9-a4e4902bcc48_happy.doc', '2018-07-13 14:37:48', 1, '<div style=\"text-align:center;\">生日<img src=\"http://localhost:8080/springmvc/editor/plugins/emoticons/etc_13.gif\" border=\"0\" /></div>', 'hello.doc');
INSERT INTO `email` VALUES (19, 'hh@666.com', '1376235429@qq.com', 'main', 'E:\\download\\doc_up\\2019-04-23\\cad15d22-d6b0-42b4-8995-51403444d47f_main.txt', '2019-04-23 17:14:00', 0, '??asd', '??????1.txt');
INSERT INTO `email` VALUES (20, 'hh@666.com', 'mm@666.com', '????', 'E:\\download\\doc_up\\2019-04-23\\3cfa0ff2-6840-45da-a66f-b88010caf351_????.js', '2019-04-23 19:26:00', 0, '<h1><span style=\"font-size:24px;font-family:\"Arial Black\";\">??????</span><img src=\"http://localhost:8080/emailSystem/editor/plugins/emoticons/etc_33.gif\" border=\"0\" /></h1>', 'vue.js');
INSERT INTO `email` VALUES (21, 'hh@666.com', '', '', 'E:\\download\\doc_up\\2019-04-24\\371467e1-d14c-4705-b362-e3b6a67354ec_.', '2019-04-24 10:57:00', 0, '', '');
INSERT INTO `email` VALUES (22, 'hh@666.com', '', '', 'E:\\download\\doc_up\\2019-04-24\\6cb88e49-056c-491f-a3b1-01c8ada92f74_.', '2019-04-24 10:57:00', 0, '', '');
INSERT INTO `email` VALUES (24, 'zs0101@365.com', 'ls0202@365.com', '我是张三', 'D:\\TomcatFile\\2019-04-29\\eab1646e-acac-4846-854a-29d3db40fa96.jpg', '2019-04-30 17:34:57', 4, '你好漂亮', 'head.jpg');
INSERT INTO `email` VALUES (25, 'zs0101@365.com', 'ls0202@365.com', '我是张三的哥', 'D:\\TomcatFile\\2019-04-29\\bb6daeff-e55a-43d7-9bdf-80b93e6eab32.jpg', '2019-04-30 17:34:57', 4, '你好', 'head.jpg');
INSERT INTO `email` VALUES (26, 'ls0202@365.com', 'zs0101@365.com', '你好，我是李四', 'D:\\TomcatFile\\2019-04-30\\652b61e5-dd9b-414c-8796-d73b625c74b5.jpg', '2019-04-30 17:03:41', 2, '你好啊，哈哈', 'head.jpg');
INSERT INTO `email` VALUES (28, 'ls0202@365.com', 'zs0101@365.com', '测试2', '', '2019-04-30 17:10:13', 2, '测试2', '无附件');
INSERT INTO `email` VALUES (29, 'ls0202@365.com', 'zs0101@365.com', '测试3', '', '2019-04-30 17:10:13', 2, '测试3', '无附件');
INSERT INTO `email` VALUES (30, 'zs0101@365.com', 'ls0202@365.com', '你好', '', '2019-04-30 16:04:09', 0, '大赛', '无附件');
INSERT INTO `email` VALUES (31, 'ls0202@365.com', 'zs0101@365.com', '我是李四', '', '2019-04-30 17:38:49', 1, '大声道', '无附件');
INSERT INTO `email` VALUES (32, 'ls0202@365.com', 'hh@666.com', '我是李四', '', '2019-04-30 17:25:11', 0, '大声道', '无附件');
INSERT INTO `email` VALUES (33, 'ls0202@365.com', 'xm@666.com', '我是李四', '', '2019-04-30 17:25:11', 0, '大声道', '无附件');

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `userid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `friend_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `friend_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('mm@666.com', 'xm@666.com', '小明');
INSERT INTO `friend` VALUES ('mm@666.com', 'hh@666.com', '哈哈');
INSERT INTO `friend` VALUES ('ls0202@365.com', 'zs0101@365.com', '张三');
INSERT INTO `friend` VALUES ('zs0101@365.com', 'ls0202@365.com', '李四');
INSERT INTO `friend` VALUES ('zs0101@365.com', 'ls0101@365.com', '李四');

-- ----------------------------
-- Table structure for save
-- ----------------------------
DROP TABLE IF EXISTS `save`;
CREATE TABLE `save`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addressee_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `addresser_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `filepath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `readed` int(1) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `filename` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of save
-- ----------------------------
INSERT INTO `save` VALUES (13, 'mm@666.com', 'xm@666.com', 'miss', 'E:\\download\\doc_up\\2018-07-13\\a80fc220-e330-4914-ad3e-221c97e63231_miss.doc', '2018-07-13 08:18:00', 0, 'ccccccccccc', 'hello.doc');
INSERT INTO `save` VALUES (14, 'mm@666.com', 'xm@666.com', '草稿箱', 'E:\\download\\doc_up\\2018-07-13\\a6a7ba57-a9dd-4ac2-a1ac-0423be4469fc_草稿箱.', '2018-07-13 13:03:00', 0, '<div style=\"text-align:center;\"><span style=\"font-size:24px;\">草稿箱</span></div>', '');
INSERT INTO `save` VALUES (15, 'zs0101@365.com', 'mm@666.com', '草稿', 'D:\\TomcatFile\\2019-04-28\\6e72e782-67ec-41be-ad50-d36cc9bd3618.png', '2019-04-28 23:55:30', 0, '这是一个草稿<br />', 'top-left.png');
INSERT INTO `save` VALUES (16, 'zs0101@365.com', 'ls0202@365.com', '我是张三', 'D:\\TomcatFile\\2019-04-29\\9c51d326-9ff6-4a0c-a920-bfce316de874.jpg', '2019-04-29 15:06:38', 0, '你好漂亮', 'head.jpg');
INSERT INTO `save` VALUES (17, 'zs0101@365.com', 'ls0202@365.com', '我是张三的哥级', '', '2019-04-29 15:10:08', 0, '你好', '无附件');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('hh@666.com', '123456', '哈哈');
INSERT INTO `user` VALUES ('ls0101@365.com', '123456', '李四');
INSERT INTO `user` VALUES ('ls0202@365.com', '123', '李四');
INSERT INTO `user` VALUES ('mm@666.com', '123456', '徐梦');
INSERT INTO `user` VALUES ('nn@666.com', '123456', '娜娜');
INSERT INTO `user` VALUES ('xh@666.com', '123456', '小红');
INSERT INTO `user` VALUES ('xm@666.com', '123456', '小明');
INSERT INTO `user` VALUES ('zs0101@365.com', '123', '张三');

SET FOREIGN_KEY_CHECKS = 1;
