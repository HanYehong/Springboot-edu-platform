/*
Navicat MySQL Data Transfer

Source Server         : hyh
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : yida

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-12-29 16:17:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attend
-- ----------------------------
DROP TABLE IF EXISTS `attend`;
CREATE TABLE `attend` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `stu_id` bigint(11) NOT NULL,
  `cour_id` int(11) NOT NULL,
  `att_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_attend` tinyint(1) NOT NULL COMMENT '是否到课（1代表出勤 0代表缺课）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1代表删除  默认0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attend
-- ----------------------------
INSERT INTO `attend` VALUES ('1', '1008', '3001', '2018-10-06 16:32:43', '1', '2018-10-06 16:32:43', null, '0');
INSERT INTO `attend` VALUES ('2', '1008', '3002', '2018-10-06 16:32:56', '1', '2018-10-06 16:32:56', null, '0');
INSERT INTO `attend` VALUES ('3', '1008', '3001', '2018-10-03 16:33:06', '0', '2018-10-06 16:33:15', null, '0');
INSERT INTO `attend` VALUES ('4', '1008', '3003', '2018-10-06 16:33:24', '1', '2018-10-06 16:33:24', null, '0');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cour_id` int(4) NOT NULL,
  `cour_name` varchar(20) NOT NULL COMMENT '课程名',
  `cour_grade` tinyint(4) DEFAULT NULL COMMENT '年级',
  `cour_teacher` int(11) DEFAULT NULL,
  `cour_price` decimal(10,2) DEFAULT NULL COMMENT '课程价格',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_cour_id` (`cour_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '3001', '语文', '2', '2051', '50.00', '2018-10-03 15:22:12', '2018-10-05 14:02:13', '0');
INSERT INTO `course` VALUES ('2', '3002', '数学', '3', '2051', '50.00', '2018-10-03 15:23:43', '2018-10-27 16:31:17', '0');
INSERT INTO `course` VALUES ('3', '3003', '物理', '7', '2053', '50.00', '2018-10-03 15:24:53', '2018-10-05 14:02:21', '0');
INSERT INTO `course` VALUES ('4', '3004', '语文', '6', '2051', '50.00', '2018-10-29 15:16:11', null, '0');
INSERT INTO `course` VALUES ('5', '3005', '数学', '8', '2053', '50.00', '2018-10-29 15:16:31', null, '0');
INSERT INTO `course` VALUES ('6', '3006', '化学', '9', '2052', '50.00', '2018-10-29 15:16:54', null, '0');
INSERT INTO `course` VALUES ('7', '3007', '英语', '7', '2051', '50.00', '2018-10-29 15:17:12', null, '0');
INSERT INTO `course` VALUES ('8', '3008', '语文', '5', '2051', '50.00', '2018-10-29 15:17:35', null, '0');

-- ----------------------------
-- Table structure for course_time
-- ----------------------------
DROP TABLE IF EXISTS `course_time`;
CREATE TABLE `course_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course` int(4) NOT NULL,
  `cour_week` tinyint(4) DEFAULT NULL COMMENT '周几',
  `cour_begin` time(6) DEFAULT NULL COMMENT '开始时间',
  `cour_end` time(6) DEFAULT NULL COMMENT '结束时间',
  `cour_rank` tinyint(1) DEFAULT NULL,
  `cour_room` varchar(50) DEFAULT NULL COMMENT '上课地点',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_time
-- ----------------------------
INSERT INTO `course_time` VALUES ('1', '3001', '3', '10:30:00.000000', '12:30:00.000000', '2', '教学A楼203室', '2018-10-05 12:54:31', null, '0');
INSERT INTO `course_time` VALUES ('2', '3002', '4', '16:30:00.000000', '18:30:00.000000', '4', '教学A楼101室', '2018-10-05 12:55:20', '2018-10-05 12:55:24', '0');
INSERT INTO `course_time` VALUES ('3', '3003', '1', '08:00:00.000000', '10:00:00.000000', '1', '教学B楼305室', '2018-10-05 12:56:15', null, '0');
INSERT INTO `course_time` VALUES ('4', '3001', '1', '08:00:00.000000', '10:00:00.000000', '1', '教学A楼203室', '2018-10-14 10:46:24', null, '0');

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) NOT NULL,
  `exam_name` varchar(50) NOT NULL,
  `subject` varchar(20) NOT NULL,
  `grade` int(4) NOT NULL,
  `score` int(4) NOT NULL,
  `use_time` time NOT NULL,
  `is_pass` tinyint(1) NOT NULL,
  `ques_ids` varchar(500) NOT NULL,
  `ans_ids` varchar(500) NOT NULL,
  `true_ids` varchar(500) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam
-- ----------------------------

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('3');

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tea_id` int(11) NOT NULL,
  `cour_id` int(11) NOT NULL,
  `hw_content` varchar(500) NOT NULL DEFAULT '',
  `hw_src` varchar(255) NOT NULL DEFAULT '',
  `hw_deadtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1代表删除  默认0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES ('1', '2051', '3001', '《xxxx》读后感。', '', '2018-11-14 18:00:00', '2018-11-14 08:25:10', '2018-11-14 08:26:26', '0');
INSERT INTO `homework` VALUES ('2', '2051', '3001', '第十章第三小题', '', '2018-11-30 18:00:00', '2018-11-14 08:26:13', null, '0');
INSERT INTO `homework` VALUES ('3', '2051', '3001', '第九章第一题', '', '2018-11-30 22:31:43', '2018-11-14 08:30:47', '2018-11-16 13:32:23', '0');

-- ----------------------------
-- Table structure for homework_do
-- ----------------------------
DROP TABLE IF EXISTS `homework_do`;
CREATE TABLE `homework_do` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hw_id` int(11) NOT NULL,
  `stu_id` int(11) NOT NULL,
  `content` varchar(500) NOT NULL DEFAULT '',
  `src` varchar(255) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1代表删除  默认0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework_do
-- ----------------------------
INSERT INTO `homework_do` VALUES ('1', '1', '1008', 'balabalabala', '', '2018-11-14 08:32:45', '2018-11-14 08:33:05', '0');
INSERT INTO `homework_do` VALUES ('2', '2', '1008', 'hahahahaha', '', '2018-11-14 08:32:56', '2018-11-14 08:33:10', '0');
INSERT INTO `homework_do` VALUES ('11', '3', '1008', '1111', '', '2018-11-16 13:32:58', null, '0');

-- ----------------------------
-- Table structure for practice
-- ----------------------------
DROP TABLE IF EXISTS `practice`;
CREATE TABLE `practice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) NOT NULL,
  `ques_id` int(11) NOT NULL,
  `fault_count` int(11) NOT NULL DEFAULT '0' COMMENT '是否做对',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice
-- ----------------------------
INSERT INTO `practice` VALUES ('5', '1008', '9', '1', '2018-10-22 09:35:56', '2018-10-22 09:50:52', '0');
INSERT INTO `practice` VALUES ('21', '1008', '8', '3', '2018-11-13 00:40:39', '2018-11-14 22:37:47', '0');
INSERT INTO `practice` VALUES ('26', '1008', '11', '8', '2018-11-13 00:41:21', '2018-11-16 14:03:49', '0');
INSERT INTO `practice` VALUES ('29', '1008', '12', '1', '2018-11-14 13:37:43', null, '0');
INSERT INTO `practice` VALUES ('31', '1008', '16', '1', '2018-11-14 13:37:57', null, '0');
INSERT INTO `practice` VALUES ('32', '1008', '15', '3', '2018-11-14 15:18:06', '2018-11-16 11:38:09', '0');
INSERT INTO `practice` VALUES ('33', '1008', '3', '1', '2018-11-16 14:03:55', null, '0');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(10) NOT NULL,
  `grade` int(4) NOT NULL,
  `subject_grade` int(11) DEFAULT NULL,
  `question` varchar(255) NOT NULL,
  `ans1` varchar(255) NOT NULL DEFAULT '',
  `ans2` varchar(255) NOT NULL DEFAULT '',
  `ans3` varchar(255) NOT NULL DEFAULT '',
  `ans4` varchar(255) NOT NULL DEFAULT '',
  `true_ans` int(11) NOT NULL DEFAULT '1' COMMENT '正确答案',
  `is_double` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否多选（1是 0不是）',
  `ans_analyze` varchar(255) NOT NULL DEFAULT '' COMMENT '题目解析',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '数学', '3', '1', '（1+9）×10=？', '99', '67', '100', '90', '3', '0', '先加减后乘除', '2018-10-13 22:50:00', '2018-10-22 16:40:56', '0');
INSERT INTO `question` VALUES ('2', '语文', '2', '2', '“天”字的拼音怎么写？', 'tian', 'tan', 'da', 'ta', '1', '0', '天tian', '2018-10-13 22:51:02', '2018-10-22 16:40:58', '0');
INSERT INTO `question` VALUES ('3', '语文', '2', '2', '哈哈哈', '1', '2', '3', '4', '1', '0', '1111', '2018-10-19 15:12:33', '2018-10-22 16:41:00', '0');
INSERT INTO `question` VALUES ('4', '语文', '3', '3', '哈哈哈', '2', '3', '4', '5', '2', '0', '1112', '2018-10-19 15:12:34', '2018-10-22 16:41:01', '0');
INSERT INTO `question` VALUES ('5', '语文', '4', '4', '啊啊啊啊啊啊啊啊', '3', '4', '5', '6', '3', '0', '1113', '2018-10-19 15:12:34', '2018-10-22 16:41:02', '0');
INSERT INTO `question` VALUES ('6', '数学', '5', '5', '哈哈哈', '4', '5', '6', '7', '4', '0', '1114', '2018-10-19 15:12:34', '2018-10-22 16:41:04', '0');
INSERT INTO `question` VALUES ('7', '语文', '6', '6', 'aaaaaaa', '5', '6', '7', '8', '2', '0', '1115', '2018-10-19 15:12:34', '2018-10-22 16:41:06', '0');
INSERT INTO `question` VALUES ('8', '物理', '7', '7', 'lll', '6', '7', '8', '9', '2', '0', '1116', '2018-10-19 15:12:34', '2018-10-22 16:41:08', '0');
INSERT INTO `question` VALUES ('9', '语文', '8', '8', '哈哈哈', '7', '8', '9', '10', '1', '0', '1117', '2018-10-19 15:12:34', '2018-10-22 16:41:09', '0');
INSERT INTO `question` VALUES ('10', '生物', '9', '9', '哈哈哈', '8', '9', '10', '11', '4', '0', '1118', '2018-10-19 15:12:34', '2018-10-22 16:41:11', '0');
INSERT INTO `question` VALUES ('11', '语文', '2', '2', '嘻嘻嘻', '9', '10', '11', '12', '3', '0', '1119', '2018-10-19 15:12:34', '2018-10-22 16:41:15', '0');
INSERT INTO `question` VALUES ('12', '语文', '2', '0', '语文二年级的题目111', '1', '2', '3', '4', '1', '0', '1111', '2018-11-14 13:36:07', null, '0');
INSERT INTO `question` VALUES ('13', '语文', '8', '0', '21321', '1', '2', '3', '4', '3', '0', '1117', '2018-11-14 13:36:07', null, '0');
INSERT INTO `question` VALUES ('14', '生物', '9', '0', '人人', '1', '2', '3', '4', '4', '0', '1118', '2018-11-14 13:36:07', null, '0');
INSERT INTO `question` VALUES ('15', '语文', '2', '0', '6464', '1', '2', '3', '4', '1', '0', '1119', '2018-11-14 13:36:07', null, '0');
INSERT INTO `question` VALUES ('16', '语文', '2', '0', '语文二年级的题目22222', '1', '2', '3', '4', '1', '0', '1111', '2018-11-14 13:36:07', null, '0');
INSERT INTO `question` VALUES ('17', '语文', '2', '0', '语文二年级的题目3333', '1', '2', '3', '4', '2', '0', '1111', '2018-11-14 13:36:07', null, '0');
INSERT INTO `question` VALUES ('18', '语文', '4', '0', '213143141', '1', '2', '3', '4', '3', '0', '1113', '2018-11-14 13:36:07', null, '0');
INSERT INTO `question` VALUES ('19', '数学', '5', '0', '缺勤缺勤群群群群群群群群群群群', '1', '2', '3', '4', '4', '0', '1114', '2018-11-14 13:36:07', null, '0');
INSERT INTO `question` VALUES ('20', '语文', '6', '0', '热特特人', '1', '2', '3', '4', '2', '0', '1115', '2018-11-14 13:36:07', null, '0');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` bigint(11) NOT NULL,
  `cour_id` int(11) NOT NULL,
  `cour_name` varchar(20) NOT NULL,
  `score_main_id` int(11) NOT NULL,
  `score` double(4,1) NOT NULL,
  `up_or_down` double(3,0) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1代表删除  默认0',
  PRIMARY KEY (`id`),
  KEY `student_id` (`stu_id`),
  KEY `course_id` (`score_main_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('2', '1008', '3001', '语文', '1', '88.0', '0', '2018-10-05 23:59:21', '2018-10-25 00:20:20', '0');
INSERT INTO `score` VALUES ('3', '1008', '3002', '数学', '2', '90.0', '0', '2018-10-05 23:59:43', '2018-10-25 00:20:22', '0');
INSERT INTO `score` VALUES ('4', '1008', '3003', '物理', '3', '87.0', '0', '2018-10-06 00:00:03', '2018-10-25 00:20:24', '0');
INSERT INTO `score` VALUES ('5', '1008', '3001', '语文', '4', '90.0', '-2', '2018-10-06 22:20:47', '2018-10-25 00:20:26', '0');
INSERT INTO `score` VALUES ('6', '1008', '3001', '语文', '5', '99.0', '9', '2018-10-06 22:21:01', '2018-10-25 00:20:28', '0');
INSERT INTO `score` VALUES ('7', '1008', '3001', '语文', '6', '80.0', '-19', '2018-10-06 22:21:08', '2018-10-25 00:20:30', '0');
INSERT INTO `score` VALUES ('9', '1008', '3001', '语文', '8', '60.0', '-20', '2018-10-06 22:21:23', '2018-10-29 11:11:49', '0');
INSERT INTO `score` VALUES ('10', '1008', '3002', '数学', '9', '60.0', '-30', '2018-10-06 22:21:29', '2018-10-25 00:20:36', '0');
INSERT INTO `score` VALUES ('11', '1008', '3002', '数学', '10', '55.0', '-5', '2018-10-06 22:21:35', '2018-10-25 00:20:38', '0');
INSERT INTO `score` VALUES ('12', '1008', '3002', '数学', '11', '78.0', '23', '2018-10-06 22:21:42', '2018-10-25 00:20:40', '0');
INSERT INTO `score` VALUES ('13', '1008', '3003', '物理', '12', '77.0', '-10', '2018-10-06 22:21:51', '2018-10-25 00:20:42', '0');
INSERT INTO `score` VALUES ('14', '1008', '3003', '物理', '13', '90.0', '13', '2018-10-06 22:21:56', '2018-10-25 00:20:49', '0');
INSERT INTO `score` VALUES ('15', '1008', '3003', '物理', '14', '85.0', '-5', '2018-10-06 22:22:08', '2018-10-25 00:20:58', '0');
INSERT INTO `score` VALUES ('16', '1008', '3001', '语文', '15', '61.0', '1', '2018-10-29 01:01:36', '2018-10-29 08:23:00', '0');
INSERT INTO `score` VALUES ('17', '1008', '3001', '语文', '21', '70.0', '9', '2018-10-29 09:43:09', null, '0');
INSERT INTO `score` VALUES ('21', '1008', '3001', '语文', '33', '90.0', '20', '2018-10-29 10:09:43', '2018-10-29 11:10:27', '0');
INSERT INTO `score` VALUES ('22', '1008', '3001', '语文', '36', '90.0', '0', '2018-11-14 22:52:41', null, '0');
INSERT INTO `score` VALUES ('23', '1009', '3001', '语文', '36', '77.0', '77', '2018-11-14 22:52:46', null, '0');
INSERT INTO `score` VALUES ('24', '1010', '3001', '语文', '36', '89.0', '89', '2018-11-14 22:53:05', null, '0');
INSERT INTO `score` VALUES ('25', '1008', '3001', '语文', '36', '80.0', '-10', '2018-11-16 14:07:54', null, '0');
INSERT INTO `score` VALUES ('26', '1009', '3001', '语文', '36', '50.0', '-27', '2018-11-16 14:07:59', null, '0');
INSERT INTO `score` VALUES ('27', '1010', '3001', '语文', '36', '90.0', '1', '2018-11-16 14:08:02', null, '0');

-- ----------------------------
-- Table structure for score_main
-- ----------------------------
DROP TABLE IF EXISTS `score_main`;
CREATE TABLE `score_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cour_id` int(11) NOT NULL,
  `total_score` double(4,1) NOT NULL DEFAULT '100.0',
  `sco_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1代表删除  默认0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score_main
-- ----------------------------
INSERT INTO `score_main` VALUES ('1', '3001', '100.0', '2018-10-28 11:33:38', '2018-10-14 17:45:17', '2018-10-28 11:33:38', '0');
INSERT INTO `score_main` VALUES ('2', '3002', '100.0', '2018-10-28 11:34:35', '2018-10-14 17:45:31', '2018-10-28 11:34:35', '0');
INSERT INTO `score_main` VALUES ('3', '3003', '100.0', '2018-10-28 11:34:52', '2018-10-14 17:45:48', '2018-10-28 11:34:52', '0');
INSERT INTO `score_main` VALUES ('4', '3001', '100.0', '2018-10-28 11:35:03', '2018-10-14 17:45:58', '2018-10-28 11:35:03', '0');
INSERT INTO `score_main` VALUES ('5', '3001', '100.0', '2018-10-28 11:35:13', '2018-10-14 17:46:01', '2018-10-28 11:35:13', '0');
INSERT INTO `score_main` VALUES ('6', '3001', '100.0', '2018-10-28 11:35:22', '2018-10-14 17:46:05', '2018-10-28 11:35:22', '0');
INSERT INTO `score_main` VALUES ('8', '3001', '100.0', '2018-10-28 11:35:46', '2018-10-14 17:46:11', '2018-10-28 11:35:46', '0');
INSERT INTO `score_main` VALUES ('9', '3002', '100.0', '2018-10-28 11:35:56', '2018-10-14 17:46:17', '2018-10-28 11:35:56', '0');
INSERT INTO `score_main` VALUES ('10', '3002', '100.0', '2018-10-14 17:46:19', '2018-10-14 17:46:19', null, '0');
INSERT INTO `score_main` VALUES ('11', '3002', '100.0', '2018-10-14 17:46:23', '2018-10-14 17:46:23', null, '0');
INSERT INTO `score_main` VALUES ('12', '3003', '100.0', '2018-10-14 17:46:26', '2018-10-14 17:46:26', null, '0');
INSERT INTO `score_main` VALUES ('13', '3003', '100.0', '2018-10-14 17:46:29', '2018-10-14 17:46:29', null, '0');
INSERT INTO `score_main` VALUES ('14', '3003', '100.0', '2018-10-14 17:46:33', '2018-10-14 17:46:33', null, '0');
INSERT INTO `score_main` VALUES ('15', '3001', '80.0', '2018-10-31 08:00:00', '2018-10-29 00:36:25', null, '0');
INSERT INTO `score_main` VALUES ('21', '3001', '60.0', '2018-11-01 08:00:00', '2018-10-29 08:53:49', null, '0');
INSERT INTO `score_main` VALUES ('33', '3001', '100.0', '2018-11-10 08:00:00', '2018-10-29 10:09:28', null, '0');
INSERT INTO `score_main` VALUES ('34', '3001', '100.0', '2018-10-31 08:00:00', '2018-10-30 10:31:01', null, '0');
INSERT INTO `score_main` VALUES ('35', '3001', '100.0', '2018-10-30 08:00:00', '2018-10-30 10:38:24', null, '0');
INSERT INTO `score_main` VALUES ('36', '3001', '100.0', '2018-11-27 08:00:00', '2018-11-14 22:52:23', null, '0');
INSERT INTO `score_main` VALUES ('37', '3001', '100.0', '2018-11-16 14:07:39', '2018-11-16 14:07:39', null, '0');
INSERT INTO `score_main` VALUES ('38', '3001', '100.0', '2018-11-16 14:07:40', '2018-11-16 14:07:40', null, '0');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `stu_id` bigint(11) NOT NULL,
  `stu_name` varchar(20) NOT NULL,
  `stu_sex` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0代表女生 1代表男生',
  `stu_phone` bigint(11) NOT NULL,
  `stu_password` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1代表删除  默认0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_id` (`stu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '1008', '顾李云', '0', '15195895252', '5ad4a349a15549cbb495d93b91ced2bd', '2018-09-25 09:53:55', '2018-11-05 14:32:59', '0');
INSERT INTO `student` VALUES ('2', '1009', '张三', '0', '15189809881', 'bf48103ad04e738be00add6755f211af', '2018-10-29 20:59:16', '2018-11-05 14:33:54', '0');
INSERT INTO `student` VALUES ('3', '1010', '张雨雯', '0', '15199998888', '4d444c6bbe2ffe1d227518695aeb5480', '2018-11-13 09:04:02', '2018-11-13 09:19:01', '0');

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `stu_id` bigint(11) NOT NULL,
  `cour_id` int(4) NOT NULL,
  `total` int(11) NOT NULL DEFAULT '0',
  `remain` int(11) NOT NULL DEFAULT '0',
  `true_count` int(11) NOT NULL DEFAULT '0',
  `false_count` int(11) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `student_id` (`stu_id`),
  KEY `course_id` (`cour_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES ('1', '1008', '3001', '10', '8', '37', '31', '2018-10-03 15:26:53', '2018-11-16 14:03:58', '0');
INSERT INTO `student_course` VALUES ('2', '1008', '3002', '8', '8', '18', '7', '2018-10-03 15:27:02', '2018-11-14 00:07:52', '0');
INSERT INTO `student_course` VALUES ('3', '1008', '3003', '10', '8', '14', '8', '2018-10-03 15:28:48', '2018-11-14 22:37:47', '0');
INSERT INTO `student_course` VALUES ('9', '1009', '3001', '10', '10', '0', '0', '2018-10-29 20:59:16', null, '0');
INSERT INTO `student_course` VALUES ('10', '1009', '3006', '5', '5', '0', '0', '2018-10-29 20:59:16', null, '0');
INSERT INTO `student_course` VALUES ('11', '1010', '3001', '10', '10', '0', '0', '2018-11-13 09:04:02', null, '0');
INSERT INTO `student_course` VALUES ('12', '1010', '3003', '10', '10', '0', '0', '2018-11-13 09:04:02', null, '0');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tea_id` int(11) NOT NULL,
  `tea_name` varchar(20) NOT NULL,
  `tea_sex` tinyint(1) NOT NULL DEFAULT '1',
  `tea_birthday` date NOT NULL,
  `tea_phone` bigint(11) NOT NULL DEFAULT '0',
  `tea_education` varchar(10) NOT NULL DEFAULT '' COMMENT '学历（本科、硕士、博士、博士后）',
  `tea_grade` varchar(10) NOT NULL DEFAULT '' COMMENT '等级（初级、中级、高级、特级）',
  `tea_photo` varchar(255) NOT NULL DEFAULT '',
  `tea_password` varchar(50) NOT NULL,
  `is_admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是管理员（0不是 1是）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1代表删除  默认0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `teacher_id` (`tea_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('4', '2051', '张三', '1', '1984-06-13', '15189809881', '硕士', '高级', ' ', 'beb1e27bb8f0773baebf6ad54e15e01a', '0', '2018-10-06 00:04:55', '2018-11-14 14:03:24', '0');
INSERT INTO `teacher` VALUES ('5', '2052', '李四', '0', '2018-10-10', '15195895252', '硕士', '特级', ' ', 'aaa', '0', '2018-10-06 00:05:25', '2018-11-14 14:03:28', '0');
INSERT INTO `teacher` VALUES ('6', '2053', '王五', '1', '2018-10-28', '15189809881', '博士', '高级', ' ', '111111', '0', '2018-10-06 00:05:49', '2018-11-14 14:03:33', '0');

-- ----------------------------
-- Table structure for tuition
-- ----------------------------
DROP TABLE IF EXISTS `tuition`;
CREATE TABLE `tuition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` bigint(11) NOT NULL,
  `cour_id` int(11) NOT NULL,
  `tuition` double(10,2) NOT NULL,
  `cour_add` int(11) DEFAULT NULL,
  `tui_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1代表删除  默认0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tuition
-- ----------------------------
INSERT INTO `tuition` VALUES ('1', '1008', '3001', '300.00', '6', '2018-10-06 16:25:20', '2018-10-06 16:25:20', null, '0');
INSERT INTO `tuition` VALUES ('2', '1008', '3002', '500.00', '10', '2018-10-06 16:25:29', '2018-10-06 16:25:29', null, '0');
INSERT INTO `tuition` VALUES ('3', '1008', '3003', '100.00', '2', '2018-10-06 16:25:39', '2018-10-06 16:25:39', null, '0');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
