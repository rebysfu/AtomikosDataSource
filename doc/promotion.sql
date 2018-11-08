-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: poker_record
-- ------------------------------------------------------
-- Server version	5.7.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `promotion`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `promotion` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `promotion`;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `agent_id` int(11) unsigned NOT NULL,
  `nickname` varchar(100) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '用户昵称',
  `username` varchar(100) NOT NULL DEFAULT '',
  `password` varchar(100) NOT NULL DEFAULT '',
  `settlement_password` varchar(100) DEFAULT '',
  `phone` varchar(100) DEFAULT NULL,
  `mail` varchar(100) DEFAULT '',
  `user_icon` varchar(100) NOT NULL DEFAULT '' COMMENT '头像图片',
  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户状态：1启用 2禁用',
  `last_login_date` varchar(30) NOT NULL DEFAULT '' COMMENT '上次登录时间',
  `last_login_ip` varchar(30) NOT NULL DEFAULT '' COMMENT '上次登录的ip',
  `ban` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否封号',
  `is_bind_ip` tinyint(1) DEFAULT '0' COMMENT '是否绑定ip',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`agent_id`),
  UNIQUE KEY `username_index` (`username`),
  UNIQUE KEY `phone` (`phone`),
  KEY `phone_index` (`phone`),
  KEY `nickname` (`nickname`,`phone`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_bak_20180515`
--

DROP TABLE IF EXISTS `account_bak_20180515`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_bak_20180515` (
  `agent_id` int(11) unsigned NOT NULL,
  `nickname` varchar(100) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '用户昵称',
  `username` varchar(100) NOT NULL DEFAULT '',
  `password` varchar(100) NOT NULL DEFAULT '',
  `settlement_password` varchar(100) DEFAULT '',
  `phone` varchar(100) DEFAULT '',
  `mail` varchar(100) DEFAULT '',
  `user_icon` varchar(100) NOT NULL DEFAULT '' COMMENT '头像图片',
  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户状态：1启用 2禁用',
  `last_login_date` varchar(30) NOT NULL DEFAULT '' COMMENT '上次登录时间',
  `last_login_ip` varchar(30) NOT NULL DEFAULT '' COMMENT '上次登录的ip',
  `ban` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否封号',
  `is_bind_ip` tinyint(1) DEFAULT '0' COMMENT '是否绑定ip',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`agent_id`),
  UNIQUE KEY `username_index` (`username`),
  KEY `phone_index` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_roles`
--

DROP TABLE IF EXISTS `account_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_roles` (
  `agent_id` int(11) NOT NULL DEFAULT '0',
  `roles_id` int(11) NOT NULL,
  UNIQUE KEY `agent_id` (`agent_id`,`roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_temp`
--

DROP TABLE IF EXISTS `account_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_temp` (
  `agent_id` int(11) unsigned NOT NULL,
  `adder_id` int(11) unsigned NOT NULL,
  `username` varchar(100) NOT NULL DEFAULT '',
  `password` varchar(100) NOT NULL DEFAULT '',
  `rate` int(3) NOT NULL DEFAULT '0',
  `remark` varchar(256) DEFAULT '',
  `date` datetime NOT NULL COMMENT '时间',
  `use` int(1) NOT NULL DEFAULT '0' COMMENT '是否使用',
  `change_rate` int(1) NOT NULL DEFAULT '0' COMMENT '是否修改过比例',
  PRIMARY KEY (`agent_id`),
  KEY `agent_id` (`agent_id`,`use`),
  KEY `adder_id` (`adder_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='临时账号表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `superior` int(11) unsigned NOT NULL,
  `money` bigint(20) unsigned NOT NULL DEFAULT '0',
  `total_money` bigint(20) NOT NULL DEFAULT '0' COMMENT '历史总收入',
  `level` int(2) NOT NULL,
  `royalty_rate` int(11) NOT NULL COMMENT '提成比例',
  `brand` int(11) NOT NULL DEFAULT '1',
  `brand_version` varchar(11) NOT NULL DEFAULT '0' COMMENT '品牌版本',
  `remark` varchar(200) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '备注',
  `date` bigint(20) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `royalty_rate_index` (`royalty_rate`),
  KEY `superior` (`superior`)
) ENGINE=InnoDB AUTO_INCREMENT=663495 DEFAULT CHARSET=utf8 COMMENT='代理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agent_bak`
--

DROP TABLE IF EXISTS `agent_bak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_bak` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `superior` int(11) unsigned NOT NULL,
  `money` bigint(20) unsigned NOT NULL DEFAULT '0',
  `total_money` bigint(20) NOT NULL DEFAULT '0' COMMENT '历史总收入',
  `level` int(2) NOT NULL,
  `royalty_rate` int(11) NOT NULL COMMENT '提成比例',
  `brand` int(11) NOT NULL DEFAULT '1',
  `brand_version` varchar(11) NOT NULL DEFAULT '0' COMMENT '品牌版本',
  `remark` varchar(200) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '备注',
  `date` bigint(20) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `royalty_rate_index` (`royalty_rate`),
  KEY `superior` (`superior`)
) ENGINE=InnoDB AUTO_INCREMENT=663453 DEFAULT CHARSET=utf8 COMMENT='代理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agent_change`
--

DROP TABLE IF EXISTS `agent_change`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_change` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL,
  `changer_agent_id` int(11) NOT NULL DEFAULT '0',
  `content` varchar(300) DEFAULT '' COMMENT '代理账户信息变动表',
  `rate` int(11) DEFAULT '0' COMMENT '提成比例修改数值',
  `type` tinyint(2) NOT NULL COMMENT '信息修改类型 1解/封号 2提成比例修改 3收入变动 4其他修改',
  `date` bigint(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_type_date` (`type`,`date`),
  KEY `idx_agent_id` (`agent_id`,`create_time`),
  KEY `agent_id&create_time` (`agent_id`,`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=687889 DEFAULT CHARSET=utf8 COMMENT='代理信息变动表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agent_channel`
--

DROP TABLE IF EXISTS `agent_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_channel` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL,
  `channel` varchar(50) NOT NULL DEFAULT '0' COMMENT 'ip',
  `promotion_setting_name` varchar(100) NOT NULL,
  `date` bigint(20) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `channelIndex` (`channel`),
  KEY `agent_id` (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1271872 DEFAULT CHARSET=utf8 COMMENT='代理渠道表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agent_channel_20180809`
--

DROP TABLE IF EXISTS `agent_channel_20180809`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_channel_20180809` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL,
  `channel` varchar(50) NOT NULL DEFAULT '0' COMMENT 'ip',
  `promotion_setting_name` varchar(100) NOT NULL,
  `date` bigint(20) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `channelIndex` (`channel`),
  KEY `agent_id` (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1047402 DEFAULT CHARSET=utf8 COMMENT='代理渠道表备份';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agent_income`
--

DROP TABLE IF EXISTS `agent_income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_income` (
  `agent_id` int(11) NOT NULL,
  `channel` varchar(50) NOT NULL DEFAULT '0' COMMENT '渠道名称',
  `my_rate` int(3) DEFAULT NULL COMMENT '上级结算比例',
  `royalty_rate` int(11) DEFAULT NULL,
  `revenue` bigint(20) DEFAULT NULL COMMENT '税收',
  `income` bigint(20) DEFAULT NULL COMMENT '收入',
  `proxy_agent_id` int(11) DEFAULT NULL,
  `agent_income` bigint(20) DEFAULT NULL COMMENT '代理收入',
  `settle` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已经结算',
  `settle_date` bigint(30) DEFAULT NULL COMMENT '结算日期',
  `date` varchar(30) NOT NULL DEFAULT '' COMMENT '时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  UNIQUE KEY `agent_id_Channel_Date` (`agent_id`,`channel`,`date`),
  KEY `income` (`income`),
  KEY `channel` (`channel`,`date`,`settle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理收入记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agent_stat_d`
--

DROP TABLE IF EXISTS `agent_stat_d`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_stat_d` (
  `agent_id` int(11) NOT NULL COMMENT '代理id',
  `date` date NOT NULL COMMENT '时间',
  `new_user_count` int(11) NOT NULL DEFAULT '0' COMMENT '新用户数量',
  `user_bind_count` int(11) NOT NULL DEFAULT '0' COMMENT '绑定数量',
  `charge_sum` bigint(20) NOT NULL DEFAULT '0' COMMENT '在线充值金额',
  `proxy_charge_sum` bigint(20) NOT NULL DEFAULT '0' COMMENT '代理充值金额',
  `charge_count` int(11) NOT NULL DEFAULT '0' COMMENT '充值次数',
  `extract_sum` bigint(20) NOT NULL DEFAULT '0' COMMENT '兑换金额',
  `extract_count` int(11) NOT NULL DEFAULT '0' COMMENT '兑换次数',
  `revenue` bigint(20) NOT NULL DEFAULT '0' COMMENT '税收',
  `income` bigint(20) NOT NULL DEFAULT '0' COMMENT '本代理收入',
  `agent_income` bigint(20) NOT NULL DEFAULT '0' COMMENT '下级代理收入',
  `agent_rate` int(11) NOT NULL DEFAULT '0' COMMENT '本代理税率',
  `superior_income` bigint(20) NOT NULL DEFAULT '0' COMMENT '为上级带来的收入',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`agent_id`,`date`),
  KEY `date` (`date`,`revenue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代理统计表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agent_stat_m`
--

DROP TABLE IF EXISTS `agent_stat_m`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_stat_m` (
  `agent_id` int(11) NOT NULL COMMENT '代理id',
  `date` date NOT NULL COMMENT '时间',
  `new_user_count` int(11) NOT NULL DEFAULT '0' COMMENT '新用户数量',
  `user_bind_count` int(11) NOT NULL DEFAULT '0' COMMENT '绑定数量',
  `charge_sum` bigint(20) NOT NULL DEFAULT '0' COMMENT '在线充值金额',
  `proxy_charge_sum` bigint(20) NOT NULL DEFAULT '0' COMMENT '代理充值金额',
  `charge_count` int(11) NOT NULL DEFAULT '0' COMMENT '充值次数',
  `extract_sum` bigint(20) NOT NULL DEFAULT '0' COMMENT '兑换金额',
  `extract_count` int(11) NOT NULL DEFAULT '0' COMMENT '兑换次数',
  `revenue` bigint(20) NOT NULL DEFAULT '0' COMMENT '税收',
  `income` bigint(20) NOT NULL DEFAULT '0' COMMENT '本代理收入',
  `agent_income` bigint(20) NOT NULL DEFAULT '0' COMMENT '下级代理收入',
  `superior_income` bigint(20) NOT NULL DEFAULT '0' COMMENT '为上级带来的收入',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`date`,`agent_id`),
  KEY `idx_day_agent` (`agent_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代理统计表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agent_stat_total`
--

DROP TABLE IF EXISTS `agent_stat_total`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_stat_total` (
  `agent_id` int(11) NOT NULL COMMENT '代理id',
  `new_user_count` int(11) NOT NULL DEFAULT '0' COMMENT '新用户数量',
  `user_bind_count` int(11) NOT NULL DEFAULT '0' COMMENT '绑定数量',
  `charge_sum` bigint(20) NOT NULL DEFAULT '0' COMMENT '在线充值金额',
  `proxy_charge_sum` bigint(20) NOT NULL DEFAULT '0' COMMENT '代理充值金额',
  `charge_count` int(11) NOT NULL DEFAULT '0' COMMENT '充值次数',
  `extract_sum` bigint(20) NOT NULL DEFAULT '0' COMMENT '兑换金额',
  `extract_count` int(11) NOT NULL DEFAULT '0' COMMENT '兑换次数',
  `revenue` bigint(20) NOT NULL DEFAULT '0' COMMENT '税收',
  `income` bigint(20) NOT NULL DEFAULT '0' COMMENT '本代理收入',
  `agent_income` bigint(20) NOT NULL DEFAULT '0' COMMENT '下级代理收入',
  `superior_income` bigint(20) NOT NULL DEFAULT '0' COMMENT '为上级带来的收入',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`agent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代理统计表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ali_bind`
--

DROP TABLE IF EXISTS `ali_bind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ali_bind` (
  `agent_id` int(11) NOT NULL,
  `ali_account` varchar(64) DEFAULT NULL,
  `real_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`agent_id`),
  KEY `ali_account` (`ali_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `asset_store`
--

DROP TABLE IF EXISTS `asset_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '索引',
  `type` int(1) NOT NULL DEFAULT '-1' COMMENT '素材类型,1文本,2图片名称,3视频名称',
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(1000) NOT NULL COMMENT '内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '资源创建时间',
  `priority` int(3) NOT NULL DEFAULT '0' COMMENT '显示优先级,值越高显示的优先级越高',
  `order` int(11) NOT NULL DEFAULT '0' COMMENT '图片排序',
  `brand_type` int(11) NOT NULL DEFAULT '0' COMMENT '品牌类型：0标识通用',
  PRIMARY KEY (`id`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='素材库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bank_bind`
--

DROP TABLE IF EXISTS `bank_bind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_bind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '姓名',
  `id_card` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '身份证号',
  `card_account` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '银行卡号',
  `bank` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '开户行',
  `province` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '开户行省份',
  `city` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '开户行城市',
  `bank_branch` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '开户行支行',
  PRIMARY KEY (`id`),
  UNIQUE KEY `agent_id` (`agent_id`),
  UNIQUE KEY `card_account` (`card_account`)
) ENGINE=InnoDB AUTO_INCREMENT=24519 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='绑定银行卡';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bank_setting`
--

DROP TABLE IF EXISTS `bank_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '银行名称',
  `value` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '银行简称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=290 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='银行配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bind_ip`
--

DROP TABLE IF EXISTS `bind_ip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bind_ip` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL,
  `ip` varchar(20) NOT NULL DEFAULT '0' COMMENT 'ip',
  `date` bigint(20) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `agent_id` (`agent_id`,`ip`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8 COMMENT='IP绑定';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `brand_setting`
--

DROP TABLE IF EXISTS `brand_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand_setting` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL COMMENT '品牌code',
  `name` varchar(20) NOT NULL,
  `flyer_id` int(11) DEFAULT NULL COMMENT '落地页',
  `template_type` int(11) NOT NULL DEFAULT '0' COMMENT '二维码图',
  `asset_type` int(11) NOT NULL COMMENT '素材类型',
  `base_url` varchar(1000) DEFAULT NULL,
  `download_url` varchar(100) NOT NULL COMMENT 'app下载链接',
  `channel_url` varchar(100) NOT NULL COMMENT '生成渠道链接',
  `image_server` varchar(200) NOT NULL DEFAULT '' COMMENT '图片服务器地址',
  `image_url` varchar(100) DEFAULT NULL,
  `image_cdn` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL COMMENT '是否启用',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '品牌版本',
  `DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `remark` varchar(100) NOT NULL DEFAULT '0' COMMENT '注释',
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `STATUS` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='品牌配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `daily_statistic`
--

DROP TABLE IF EXISTS `daily_statistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daily_statistic` (
  `date` varchar(100) NOT NULL,
  `revenue` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '税收',
  `agent_income` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '代理收益',
  `DAU` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '日活',
  `operating` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '产生税收代理数量',
  `one_day_retention` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '一日留存',
  `three_day_retention` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '三日留存',
  `seven_day_retention` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '七日留存',
  `platform` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '平台:1WEB 2安卓 3IOS 4H5',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='百万代理运营统计';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `daystatistics`
--

DROP TABLE IF EXISTS `daystatistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daystatistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `calcTime` varchar(32) DEFAULT '0',
  `total` bigint(20) DEFAULT '0',
  `chargeUserCount` int(11) DEFAULT '0',
  `chargeSum` bigint(20) DEFAULT '0',
  `chargeCount` int(11) DEFAULT '0',
  `extractSum` bigint(20) DEFAULT '0',
  `extractCount` int(11) DEFAULT '0',
  `leftUser` int(11) DEFAULT '0',
  `leftMachine` int(11) DEFAULT '0',
  `newUserCount` int(11) DEFAULT '0',
  `newMachineCount` int(11) DEFAULT '0',
  `actUserCount` int(11) DEFAULT '0',
  `actMachineCount` int(11) DEFAULT '0',
  `payUserRate` int(11) DEFAULT '0',
  `payMachineRate` int(11) DEFAULT '0',
  `dayUserARPU` int(11) DEFAULT '0',
  `dayMachineARPU` int(11) DEFAULT '0',
  `dayARPPU` int(11) DEFAULT '0',
  `winScore` bigint(20) DEFAULT '0',
  `lossScore` bigint(20) DEFAULT '0',
  `coreRateScore` bigint(20) DEFAULT '0',
  `rateScore` bigint(20) DEFAULT '0',
  `avgUserRateScore` bigint(20) DEFAULT '0',
  `avgMachineRateScore` bigint(20) DEFAULT '0',
  `channel` varchar(50) DEFAULT '0',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `oneDayRetention` int(11) DEFAULT '0',
  `threeDayRetention` int(11) DEFAULT '0',
  `sevenDayRetention` int(11) DEFAULT '0',
  `pokerTaxScore` bigint(20) DEFAULT '0',
  `proxyChargeSum` bigint(20) DEFAULT '0',
  `newChargeUserCount` int(11) DEFAULT '0',
  `newChargeSum` bigint(20) DEFAULT '0',
  `newUserBindCount` int(11) DEFAULT '0',
  `userBindCount` int(11) DEFAULT '0',
  `proxyRateScore` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_channel_calctime` (`channel`,`calcTime`),
  KEY `idx_day_time` (`calcTime`,`createTime`)
) ENGINE=InnoDB AUTO_INCREMENT=851158216 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `device_info`
--

DROP TABLE IF EXISTS `device_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_info` (
  `agent_id` int(11) unsigned NOT NULL COMMENT '代理ID',
  `reg_id` varchar(64) DEFAULT '' COMMENT '设备推送标识',
  `push_type` int(11) NOT NULL COMMENT '推送类型',
  `brand` varchar(64) NOT NULL DEFAULT '' COMMENT '设备品牌',
  `device_token` varchar(128) NOT NULL DEFAULT '' COMMENT '设备token',
  `channel_id` varchar(128) DEFAULT '' COMMENT '渠道',
  `device` varchar(128) NOT NULL DEFAULT '' COMMENT '设备类型',
  `system_version` varchar(128) NOT NULL DEFAULT '' COMMENT '系统版本',
  `version` varchar(128) NOT NULL DEFAULT '' COMMENT '客户端版本',
  `hot_version` varchar(128) NOT NULL DEFAULT '' COMMENT '热更版本',
  `provider` int(1) NOT NULL DEFAULT '0' COMMENT '0:APNS推送  1:个推推送',
  `ios_type` int(11) NOT NULL DEFAULT '0' COMMENT '1:企业包2：正式包',
  `close` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否关闭推送',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`agent_id`),
  KEY `device_type` (`provider`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户设备信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `download_url`
--

DROP TABLE IF EXISTS `download_url`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `download_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `platform` int(11) DEFAULT NULL COMMENT '平台:0-安卓，1-apple',
  `channel` varchar(32) DEFAULT NULL COMMENT '渠道',
  `url` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下载链接';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `flyer`
--

DROP TABLE IF EXISTS `flyer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flyer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '',
  `theme` varchar(20) NOT NULL DEFAULT '0' COMMENT '主题',
  `addr` varchar(200) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='宣传页模板';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `login_log`
--

DROP TABLE IF EXISTS `login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL,
  `ip` varchar(30) NOT NULL DEFAULT '0' COMMENT 'ip',
  `ip_area` varchar(50) NOT NULL DEFAULT '' COMMENT 'ip所属区域',
  `platform` int(1) NOT NULL COMMENT '登录终端：1-网页 2-app',
  `date` bigint(20) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `date` (`date`),
  KEY `ip` (`ip`),
  KEY `agent_id` (`agent_id`,`platform`)
) ENGINE=InnoDB AUTO_INCREMENT=29060962 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `money_log`
--

DROP TABLE IF EXISTS `money_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `money_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) DEFAULT NULL,
  `money` bigint(20) NOT NULL COMMENT '单位/分',
  `date` bigint(20) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_aid` (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5648811 DEFAULT CHARSET=utf8 COMMENT='金钱变动';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `money_log2`
--

DROP TABLE IF EXISTS `money_log2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `money_log2` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '变动类型',
  `joint_id` int(11) DEFAULT '0',
  `origin_money` bigint(20) NOT NULL DEFAULT '0',
  `add_money` bigint(20) NOT NULL DEFAULT '0' COMMENT '增减量',
  `money` bigint(20) NOT NULL DEFAULT '0' COMMENT '变动结果/分',
  `memo` varchar(100) NOT NULL DEFAULT '0' COMMENT '注释',
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_ad` (`agent_id`,`date`)
) ENGINE=InnoDB AUTO_INCREMENT=17054168 DEFAULT CHARSET=utf8 COMMENT='金钱变动';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '',
  `content` varchar(5000) NOT NULL DEFAULT '',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '公告类型：1:百万代理公告2：app公告 3:全局公告4：个人公告',
  `date` bigint(20) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  `from_date` timestamp NULL DEFAULT NULL COMMENT '公告生效开始时间',
  `to_date` timestamp NULL DEFAULT NULL COMMENT '公告生效结束时间',
  PRIMARY KEY (`id`),
  KEY `is_del&date` (`date`,`is_del`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='公告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notice_personal`
--

DROP TABLE IF EXISTS `notice_personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice_personal` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '索引',
  `notice_refId` int(11) NOT NULL DEFAULT '0' COMMENT '引用系统公告的ID',
  `agent_id` int(11) NOT NULL COMMENT '代理ID',
  `title` varchar(100) NOT NULL COMMENT '个人公告标题',
  `content` varchar(5000) NOT NULL DEFAULT '' COMMENT '个人公告内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '个人公告创建时间',
  `read_state` int(1) NOT NULL DEFAULT '0' COMMENT '是否已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8 COMMENT='个人公告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notice_push`
--

DROP TABLE IF EXISTS `notice_push`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice_push` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL COMMENT '推送内容',
  `content` varchar(255) NOT NULL,
  `push_time` bigint(15) NOT NULL DEFAULT '0' COMMENT '推送时间，0表示立即发送',
  `broadcast` int(1) NOT NULL DEFAULT '0' COMMENT '是否广播，0-不广播，1-广播',
  `agent_id` int(11) DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL COMMENT '附加字段key。前端可以根据里面key和value自定义处理。格式key1:value1,key2:value2,…',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `deal` int(1) NOT NULL DEFAULT '0' COMMENT '0-还没有推送，1-已经推送',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_record`
--

DROP TABLE IF EXISTS `order_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL DEFAULT '0',
  `type` int(11) NOT NULL COMMENT '订单操作类型 1:通过订单2:拒绝订单3:拒绝并封号4:恢复订单5:重新提交',
  `order_type` tinyint(1) DEFAULT NULL COMMENT '订单类型',
  `order_id` int(11) NOT NULL COMMENT '备注',
  `date` bigint(20) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1218287 DEFAULT CHARSET=utf8 COMMENT='订单操作记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `promotion_image`
--

DROP TABLE IF EXISTS `promotion_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion_image` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) unsigned NOT NULL,
  `name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `agent_id` (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=114418 DEFAULT CHARSET=utf8 COMMENT='二维码图';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `promotion_level_url`
--

DROP TABLE IF EXISTS `promotion_level_url`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion_level_url` (
  `brand` int(11) unsigned NOT NULL,
  `level` int(11) NOT NULL COMMENT '代理推广等级',
  `url` varchar(1000) NOT NULL DEFAULT '' COMMENT 'url',
  KEY `brand` (`brand`),
  KEY `url` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推广等级url';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `promotion_setting`
--

DROP TABLE IF EXISTS `promotion_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion_setting` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '推广名称',
  `template` varchar(30) NOT NULL,
  `promotion_zip` varchar(200) DEFAULT NULL COMMENT '宣传页打包下载',
  `flyerId` int(11) DEFAULT NULL COMMENT '宣传页id',
  `flyer_url` varchar(200) NOT NULL COMMENT '宣传页链接',
  `flyer_addr` varchar(150) DEFAULT NULL COMMENT '宣传页二维码',
  `qrcode_id` int(11) DEFAULT NULL COMMENT '二维码模板id',
  `download_url` varchar(200) NOT NULL COMMENT '下载地址',
  `download_addr` varchar(150) DEFAULT NULL COMMENT '下载地址二维码',
  `main` tinyint(1) DEFAULT '1' COMMENT '是否是系统模板0：系统模板 1:自定义',
  `remark` varchar(100) NOT NULL DEFAULT '',
  `date` bigint(20) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `agent_id` (`agent_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=633943 DEFAULT CHARSET=utf8 COMMENT='推广配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `promotion_url`
--

DROP TABLE IF EXISTS `promotion_url`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion_url` (
  `agent_id` int(11) unsigned NOT NULL,
  `param` varchar(256) NOT NULL COMMENT '推广参数',
  `type` int(1) NOT NULL,
  `url_type` int(1) NOT NULL,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`agent_id`),
  KEY `param` (`param`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理推广链接';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `settlement_order`
--

DROP TABLE IF EXISTS `settlement_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settlement_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '结算状态：1审核中 2审核完成 3支付渠道驳回4已打款 5作废 6支付渠道审核中 7订单失败',
  `money` varchar(30) NOT NULL DEFAULT '0',
  `alipay_account` varchar(50) NOT NULL COMMENT '支付宝账号',
  `real_name` varchar(30) NOT NULL COMMENT '真实姓名',
  `remark` varchar(50) NOT NULL DEFAULT '' COMMENT '备注',
  `update_date` bigint(20) DEFAULT NULL,
  `create_date` bigint(20) DEFAULT NULL,
  `pay_account` varchar(64) DEFAULT NULL,
  `ali_order` varchar(64) DEFAULT NULL,
  `settlement_order` varchar(20) DEFAULT '',
  `pay_amount` varchar(32) DEFAULT NULL,
  `msg` varchar(64) DEFAULT NULL,
  `success_date` bigint(20) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `create_date` (`create_date`,`money`),
  KEY `is_del` (`is_del`),
  KEY `status` (`status`,`update_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1116810 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `settlement_order_bank`
--

DROP TABLE IF EXISTS `settlement_order_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settlement_order_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `money` varchar(11) DEFAULT NULL,
  `trade_order` varchar(32) DEFAULT NULL,
  `charge` int(11) DEFAULT NULL,
  `card_account` varchar(64) DEFAULT NULL COMMENT '银行卡号',
  `bank_branch` varchar(128) DEFAULT NULL COMMENT '银行卡号支行信息',
  `bank_id` varchar(32) DEFAULT NULL COMMENT '银行简称（ICBC）',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `city` varchar(32) DEFAULT NULL,
  `ip` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `success_date` datetime DEFAULT NULL,
  `msg` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `agent_id` (`agent_id`),
  KEY `card_account` (`card_account`),
  KEY `status` (`status`),
  KEY `create_date` (`create_date`,`money`)
) ENGINE=InnoDB AUTO_INCREMENT=88700 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `suggestion`
--

DROP TABLE IF EXISTS `suggestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suggestion` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '索引',
  `agent_id` int(11) DEFAULT NULL COMMENT '代理ID',
  `nickname` varchar(100) NOT NULL COMMENT '昵称',
  `username` varchar(100) NOT NULL COMMENT '账户名',
  `type` int(8) NOT NULL DEFAULT '0' COMMENT '建议类型',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '建议内容',
  `image` varchar(100) NOT NULL COMMENT '建议上传图片',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `device_info` varchar(100) NOT NULL COMMENT '设备信息',
  `reply_content` varchar(500) NOT NULL COMMENT '回复内容',
  `reply_time` timestamp NULL DEFAULT NULL COMMENT '回复时间',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '处理状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户建议表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sync_msg_info`
--

DROP TABLE IF EXISTS `sync_msg_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sync_msg_info` (
  `version` bigint(20) unsigned NOT NULL COMMENT '消息版本',
  `type` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '消息类型',
  `args` varchar(100) NOT NULL DEFAULT '' COMMENT '消息参数',
  `msg` varchar(100) NOT NULL DEFAULT '',
  `timestamp` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '消息时间戳',
  `agent_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID，0表示全部用户',
  UNIQUE KEY `version` (`version`),
  KEY `type` (`type`),
  KEY `timestamp` (`timestamp`),
  KEY `uid_version` (`agent_id`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='异步消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `system_config`
--

DROP TABLE IF EXISTS `system_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_config` (
  `k` varchar(64) NOT NULL,
  `v` varchar(300) NOT NULL DEFAULT '',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`k`,`v`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `task_meta`
--

DROP TABLE IF EXISTS `task_meta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_meta` (
  `task` varchar(20) NOT NULL COMMENT '定时任务',
  `round` varchar(20) NOT NULL DEFAULT '' COMMENT '执行批次',
  `k` varchar(20) NOT NULL COMMENT 'key',
  `v` varchar(20) NOT NULL COMMENT 'value',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `memo` text,
  PRIMARY KEY (`task`,`round`,`k`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `template`
--

DROP TABLE IF EXISTS `template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `num` int(3) NOT NULL DEFAULT '0',
  `path` varchar(100) NOT NULL DEFAULT '' COMMENT '图片路径',
  `x` int(3) DEFAULT NULL COMMENT '二维码起始x坐标',
  `y` int(3) DEFAULT NULL COMMENT '二维码起始y坐标',
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='推广模板';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `timeline`
--

DROP TABLE IF EXISTS `timeline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timeline` (
  `agent_id` int(11) NOT NULL COMMENT '代理ID',
  `time_line` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近一次拉取的时间戳',
  `type` int(11) NOT NULL COMMENT '时间线类型',
  PRIMARY KEY (`agent_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='时间线';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tutorial`
--

DROP TABLE IF EXISTS `tutorial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutorial` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '索引',
  `title` varchar(100) NOT NULL COMMENT '教程标题',
  `titleImg` varchar(100) NOT NULL COMMENT '教程标题引用图片',
  `content` longtext NOT NULL COMMENT '教程内容',
  `top_and_order` int(11) DEFAULT '0' COMMENT '置顶和置顶排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COMMENT='进阶教程';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `url_group`
--

DROP TABLE IF EXISTS `url_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `url_group` (
  `agent_id` int(11) unsigned NOT NULL,
  `level` int(11) NOT NULL COMMENT '代理推广等级',
  PRIMARY KEY (`agent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_stat_d`
--

DROP TABLE IF EXISTS `user_stat_d`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_stat_d` (
  `uid` int(11) NOT NULL,
  `channel` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `revenue` int(10) unsigned NOT NULL,
  PRIMARY KEY (`uid`,`date`),
  KEY `idx_channel` (`channel`),
  KEY `idx_date` (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/*!50100 PARTITION BY HASH (uid)
PARTITIONS 16 */;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wechat`
--

DROP TABLE IF EXISTS `wechat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wechat` (
  `id` int(11) NOT NULL,
  `wechat` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `white_list`
--

DROP TABLE IF EXISTS `white_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `white_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-10  0:45:31
