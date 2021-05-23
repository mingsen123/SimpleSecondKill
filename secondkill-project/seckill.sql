
CREATE DATABASE IF NOT EXISTS secondkill;
use secondkill;
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS=0;



DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `price` double(10,0) NOT NULL DEFAULT '0' COMMENT '商品价格',
  `description` varchar(500) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商品描述',
  `sales` int NOT NULL DEFAULT '0' COMMENT '商品销量',
  `img_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商品描述图片URL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT INTO `item` VALUES ('1', '0', '0', '', '0', '');
INSERT INTO `item` VALUES ('3', '123', '12', '12', '0', '1');
INSERT INTO `item` VALUES ('4', 'iphone12', '1200', '苹果手机', '3', 'https://i04piccdn.sogoucdn.com/8ab000d01c8374d4');


DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stock` int NOT NULL DEFAULT '0' COMMENT '库存',
  `item_id` int NOT NULL DEFAULT '0' COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `item_stock` VALUES ('1', '0', '1');
INSERT INTO `item_stock` VALUES ('3', '1', '3');
INSERT INTO `item_stock` VALUES ('4', '94', '4');

DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `user_id` int NOT NULL DEFAULT '0' COMMENT '下单用户id',
  `item_id` int NOT NULL DEFAULT '0' COMMENT '购买商品id',
  `item_price` double NOT NULL DEFAULT '0' COMMENT '购买商品时的价格',
  `amount` int NOT NULL DEFAULT '0' COMMENT '购买的数量',
  `order_price` double NOT NULL DEFAULT '0' COMMENT '购买的金额',
  `promo_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `order_info` VALUES ('2021051900000000', '48', '4', '0', '1', '0', '0');
INSERT INTO `order_info` VALUES ('2021051900000100', '48', '4', '0', '1', '0', '0');
INSERT INTO `order_info` VALUES ('2021051900000200', '48', '4', '1200', '1', '1200', '0');
INSERT INTO `order_info` VALUES ('2021051900000300', '48', '4', '1200', '1', '1200', '0');
INSERT INTO `order_info` VALUES ('2021052200000400', '48', '4', '100', '1', '100', '1');
INSERT INTO `order_info` VALUES ('2021052200000500', '48', '4', '100', '1', '100', '1');

DROP TABLE IF EXISTS `promo`;
CREATE TABLE `promo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(60) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '秒杀活动名称',
  `start_date` datetime NOT NULL DEFAULT '0000-01-01 00:00:00',
  `item_id` int NOT NULL DEFAULT '0' COMMENT '商品id',
  `promo_item_price` double NOT NULL DEFAULT '0' COMMENT '秒杀活动商品价格',
  `end_date` datetime NOT NULL DEFAULT '0000-01-01 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `promo` VALUES ('1', 'iphone12抢购', '2021-05-22 22:32:33', '4', '100', '2021-05-23 22:29:59');


DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info` (
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `current_value` int NOT NULL DEFAULT '0',
  `step` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT INTO `sequence_info` VALUES ('order_info', '6', '1');


DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `gender` tinyint NOT NULL DEFAULT '0' COMMENT '1代表男性；2代表女性',
  `age` int NOT NULL DEFAULT '0' COMMENT '用户年龄',
  `telphone` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `register_mode` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '注册方式//byphone,bywechat,byalipay',
  `third_party_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '第三方注册id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `telphone_unique_index` (`telphone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT INTO `user_info` VALUES ('1', 'wms', '0', '0', '12345678910', '', '');
INSERT INTO `user_info` VALUES ('45', '12', '1', '15', '132131321', 'byphone', '');
INSERT INTO `user_info` VALUES ('46', '吴铭森', '1', '20', '131123456789', 'byphone', '');
INSERT INTO `user_info` VALUES ('48', '吴铭森', '1', '20', '12345612345', 'byphone', '');


DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `encrpt_password` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `user_id` int NOT NULL DEFAULT '0' COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `user_password` VALUES ('1', 'hkjhkhklho', '1');
INSERT INTO `user_password` VALUES ('25', 'ICy5YqxZB1uWSwcVLSNLcA==', '45');
INSERT INTO `user_password` VALUES ('26', 'ICy5YqxZB1uWSwcVLSNLcA==', '46');
INSERT INTO `user_password` VALUES ('27', 'ICy5YqxZB1uWSwcVLSNLcA==', '48');
