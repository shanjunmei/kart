/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.196
Source Server Version : 50630
Source Host           : 192.168.2.196:3306
Source Database       : kart_db

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2017-02-21 14:46:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `name` varchar(50) DEFAULT NULL COMMENT '活动名称',
  `type` varchar(10) DEFAULT NULL COMMENT '类型:0:产品详情,1:文章详情,2:web连接',
  `url` varchar(200) DEFAULT NULL COMMENT '活动URL',
  `image` varchar(500) DEFAULT NULL COMMENT '图片路径',
  `introduction` text COMMENT '简介',
  `is_ banner` varchar(10) DEFAULT NULL COMMENT '轮播banner：1是，2否',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `start_date_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_date_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `status` varchar(500) DEFAULT NULL COMMENT '状态',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_date` timestamp NULL DEFAULT null COMMENT '创建日期',
  `last_update_by` varchar(64) DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_game_personnel_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for file_repo
-- ----------------------------
DROP TABLE IF EXISTS `file_repo`;
CREATE TABLE `file_repo` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `code` varchar(100) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `content_type` varchar(255) DEFAULT NULL COMMENT '文件类型',
  `content` mediumblob COMMENT '文件内容',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_date` timestamp NULL DEFAULT null COMMENT '创建日期',
  `last_update_by` varchar(64) DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `code` varchar(50) DEFAULT NULL COMMENT '场次编码',
  `name` varchar(50) DEFAULT NULL COMMENT '场次名称',
  `time` varchar(50) DEFAULT NULL COMMENT '单场时长',
  `retail_price` decimal(9,2) DEFAULT NULL COMMENT '门市价格',
  `preferential_price` decimal(9,2) DEFAULT NULL COMMENT '优惠价格',
  `participants_number` varchar(50) DEFAULT NULL COMMENT '已参赛人数',
  `bespeak_num` varchar(50) DEFAULT NULL COMMENT '预约数量',
  `type` varchar(10) DEFAULT NULL COMMENT '赛车类型:0:公共车(入门级),1:公共车(专业级),2:自备车',
  `game_time` varchar(10) DEFAULT NULL COMMENT '场次时间',
  `game_num` varchar(10) DEFAULT NULL COMMENT '场次序号',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `effective_time` varchar(20) DEFAULT NULL COMMENT '有效时间',
  `Predetermined_state` varchar(10) DEFAULT NULL COMMENT '预定状态:0可预订 1:不可预定',
  `status` varchar(10) DEFAULT NULL COMMENT '状态:0:启用 1:禁用',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_date` timestamp NULL DEFAULT null COMMENT '创建日期',
  `last_update_by` varchar(64) DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_game_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `wx_headimgurl` varchar(1000) DEFAULT NULL COMMENT '头像',
  `wx_openid` varchar(50) DEFAULT NULL COMMENT '微信Id',
  `wx_nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `wx_subscribe_time` timestamp NULL DEFAULT NULL COMMENT '关注时间',
  `wx_city` varchar(50) DEFAULT NULL COMMENT '用户所在城市',
  `wx_country` varchar(50) DEFAULT NULL COMMENT '用户所在国家',
  `wx_province` varchar(50) DEFAULT NULL COMMENT '用户所在省份',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_date` timestamp NULL DEFAULT null COMMENT '创建日期',
  `last_update_by` varchar(64) DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_member_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for member_info
-- ----------------------------
DROP TABLE IF EXISTS `member_info`;
CREATE TABLE `member_info` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `member_code` varchar(32) DEFAULT NULL COMMENT '会员编码',
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `gender` varchar(50) DEFAULT NULL COMMENT '性别',
  `id_number` varchar(50) DEFAULT NULL COMMENT '身份证号码',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_date` timestamp NULL DEFAULT null COMMENT '创建日期',
  `last_update_by` varchar(64) DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_member_info_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `code` varchar(100) NOT NULL COMMENT '编码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `href` varchar(50) DEFAULT NULL COMMENT '链接',
  `url` varchar(50) DEFAULT NULL COMMENT '链接',
  `sort` varchar(50) DEFAULT NULL COMMENT '排序',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_date` timestamp NULL DEFAULT null COMMENT '创建日期',
  `last_update_by` varchar(64) DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `order_code` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `game_code` varchar(50) DEFAULT NULL COMMENT '场次编码',
  `member_info_code` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `vehicle_code` varchar(50) DEFAULT NULL COMMENT '车辆ID',
  `number` varchar(50) DEFAULT NULL COMMENT '车号',
  `time` varchar(100) DEFAULT NULL COMMENT '成绩',
  `status` varchar(50) DEFAULT NULL COMMENT '状态(0:正常；1:已退款；)',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_date` timestamp NULL DEFAULT null COMMENT '创建日期',
  `last_update_by` varchar(64) DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_game_personnel_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `verification_code` varchar(50) DEFAULT NULL COMMENT '验证码',
  `order_no` varchar(50) DEFAULT NULL COMMENT '订单编号,该订单唯一标示',
  `order_name` varchar(50) DEFAULT NULL COMMENT '订单名称',
  `pay_order_no` varchar(50) DEFAULT NULL COMMENT '对应支付网关订单编号',
  `member_code` varchar(50) DEFAULT NULL COMMENT '下单会员编码',
  `member_phone` varchar(50) DEFAULT NULL COMMENT '下单会员手机号',
  `status` varchar(10) DEFAULT NULL COMMENT '订单状态(0:未支付；1:已支付；2:已退款)',
  `pay_type` varchar(50) DEFAULT NULL COMMENT '支付类型',
  `buy_count` varchar(50) DEFAULT NULL COMMENT '购买数量',
  `total_price` varchar(50) DEFAULT NULL COMMENT '订单总金额',
  `favorable_price` varchar(50) DEFAULT NULL COMMENT '优惠金额',
  `actual_price` varchar(50) DEFAULT NULL COMMENT '订单支付金额',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '订单支付时间',
  `finish_time` timestamp NULL DEFAULT NULL COMMENT '订单完成时间',
  `charge_back_time` timestamp NULL DEFAULT NULL COMMENT '订单完成时间',
  `charge_back_opr` varchar(50) DEFAULT NULL COMMENT '退单操作人',
  `charge_id` varchar(50) DEFAULT NULL COMMENT '支付ID',
  `order_source` varchar(10) DEFAULT NULL COMMENT '订单来源0：网上购买；1：门店购买',
  `order_type` varchar(50) DEFAULT NULL COMMENT '订单类型',
  `game_code` varchar(50) DEFAULT NULL COMMENT '场次编码',
  `use_state` varchar(50) DEFAULT NULL COMMENT '使用状态0:未可用；1:已使用；',
  `use_time` timestamp NULL DEFAULT NULL COMMENT '使用时间',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_date` timestamp NULL DEFAULT null COMMENT '创建日期',
  `last_update_by` varchar(64) DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `effective_time` varchar(20) DEFAULT NULL COMMENT '有效日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_order_order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL COMMENT '用户编号',
  `code` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `name` varchar(200) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL COMMENT '邮件地址',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_date` timestamp NULL DEFAULT null COMMENT '创建日期',
  `last_update_by` varchar(64) DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_user_id` (`id`) USING BTREE,
  KEY `index_login_name` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `code` varchar(50) DEFAULT NULL COMMENT '车辆编码',
  `name` varchar(50) DEFAULT NULL COMMENT '车辆名称',
  `type` varchar(50) DEFAULT NULL COMMENT '车辆类型:0:入门级，1:专业级,2:自备车',
  `status` varchar(50) DEFAULT NULL COMMENT '状态:0:可用,1:不可用',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_date` timestamp NULL DEFAULT null COMMENT '创建日期',
  `last_update_by` varchar(64) DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_vehicle_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
