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
-- Current Database: `fish2_server`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `fish2_server` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `fish2_server`;

--
-- Table structure for table `admin_deal_log`
--

DROP TABLE IF EXISTS `admin_deal_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_deal_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `adminName` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `type` int(11) NOT NULL,
  `info` varchar(20000) COLLATE utf8_bin DEFAULT '',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23207 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `adminchargelog`
--

DROP TABLE IF EXISTS `adminchargelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adminchargelog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(50) NOT NULL,
  `proxyName` varchar(50) NOT NULL,
  `elderMoney` int(11) NOT NULL DEFAULT '0',
  `money` int(11) NOT NULL DEFAULT '0',
  `logTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_index` (`adminName`),
  KEY `proxy_index` (`proxyName`)
) ENGINE=InnoDB AUTO_INCREMENT=253338 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admininfo`
--

DROP TABLE IF EXISTS `admininfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admininfo` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `passwd` varchar(50) NOT NULL,
  `roles` varchar(128) DEFAULT NULL,
  `channels` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `Index 2` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `adminmenu`
--

DROP TABLE IF EXISTS `adminmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adminmenu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `style` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bank_bin`
--

DROP TABLE IF EXISTS `bank_bin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_bin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `binctt` varchar(13) NOT NULL COMMENT 'bin码值',
  `bank_code` varchar(10) NOT NULL DEFAULT '' COMMENT '内部银行编码',
  `bank_name` varchar(100) NOT NULL DEFAULT '' COMMENT '银行名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `binctt` (`binctt`)
) ENGINE=InnoDB AUTO_INCREMENT=2388 DEFAULT CHARSET=utf8 COMMENT='银行bin码规则';
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
) ENGINE=InnoDB AUTO_INCREMENT=3123 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='银行配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `brand_setting`
--

DROP TABLE IF EXISTS `brand_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand_setting` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `brand` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `brandmapping`
--

DROP TABLE IF EXISTS `brandmapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brandmapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pattern` varchar(50) DEFAULT NULL COMMENT '匹配',
  `subId` varchar(50) DEFAULT NULL,
  `matchType` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1 COMMENT='支付品牌映射';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `changecannonrec`
--

DROP TABLE IF EXISTS `changecannonrec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `changecannonrec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shotNum` int(11) DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=178614 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cheatinfo`
--

DROP TABLE IF EXISTS `cheatinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cheatinfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `level` int(11) NOT NULL DEFAULT '0',
  `total` int(11) NOT NULL DEFAULT '0',
  `mates` text,
  `state` int(1) NOT NULL DEFAULT '0',
  `banned` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`),
  KEY `state` (`state`)
) ENGINE=InnoDB AUTO_INCREMENT=19984 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cheatmatch`
--

DROP TABLE IF EXISTS `cheatmatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cheatmatch` (
  `userA` int(11) NOT NULL,
  `userB` int(11) NOT NULL,
  `count1` int(11) NOT NULL DEFAULT '0' COMMENT 'a card <= b card when a fold',
  `count2` int(11) NOT NULL DEFAULT '0' COMMENT 'a card > b card when a fold',
  `count3` int(11) NOT NULL DEFAULT '0' COMMENT 'b card <= a card when b fold',
  `count4` int(11) NOT NULL DEFAULT '0' COMMENT 'b card > a card when b fold',
  PRIMARY KEY (`userA`,`userB`),
  UNIQUE KEY `userB` (`userB`,`userA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cheatmessage`
--

DROP TABLE IF EXISTS `cheatmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cheatmessage` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `level` int(11) NOT NULL DEFAULT '0',
  `state` int(1) NOT NULL DEFAULT '0',
  `detail` blob,
  `banned` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`),
  KEY `state` (`state`)
) ENGINE=InnoDB AUTO_INCREMENT=6715 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `closeuser`
--

DROP TABLE IF EXISTS `closeuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `closeuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `adminName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '封号kefu编号',
  `userId` int(11) DEFAULT NULL COMMENT '用户ID',
  `msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `orderTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '封号时间记录',
  `auditExtractId` int(11) DEFAULT NULL COMMENT 'adminkefu审核id',
  `auditAdminName` varchar(50) DEFAULT NULL COMMENT '审核adminkefu编号',
  `secondLogTime` datetime DEFAULT NULL COMMENT '审核adminkefu审核时间记录',
  `auditStatus` int(11) DEFAULT '999' COMMENT '审核adminkefu处理客服订单的状态0 正确  1错误    999未审查',
  `auditName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '审查客服名字',
  `aName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '客服姓名',
  `isExamination` int(11) NOT NULL DEFAULT '0' COMMENT '是否审查   0未审查  1审查',
  `refuseMsg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `channel` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `chargeSum` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `index_a` (`userId`,`orderTime`)
) ENGINE=InnoDB AUTO_INCREMENT=25756 DEFAULT CHARSET=utf8 COMMENT='客服封号表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `createproxylog`
--

DROP TABLE IF EXISTS `createproxylog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `createproxylog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `creater` varchar(50) DEFAULT NULL,
  `logtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name_index` (`name`),
  KEY `creater_index` (`creater`)
) ENGINE=InnoDB AUTO_INCREMENT=2053 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `extract_state_log`
--

DROP TABLE IF EXISTS `extract_state_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extract_state_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `extractId` int(11) NOT NULL COMMENT '兑换id',
  `extractType` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'unionpay' COMMENT '兑换类型',
  `outStatus` int(11) DEFAULT NULL COMMENT '原状态',
  `newStatus` int(11) DEFAULT NULL COMMENT '新状态',
  `adminName` varchar(50) NOT NULL COMMENT '操作人',
  `userId` int(11) NOT NULL DEFAULT '0' COMMENT '玩家id',
  `logTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `extractassign`
--

DROP TABLE IF EXISTS `extractassign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extractassign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `extractId` int(11) NOT NULL DEFAULT '0',
  `extractType` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT 'alipay',
  `adminName` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `userId` int(11) NOT NULL DEFAULT '0',
  `channel` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `chargeSum` bigint(20) DEFAULT NULL,
  `extractSum` bigint(20) DEFAULT NULL,
  `money` int(11) NOT NULL DEFAULT '0',
  `score` int(11) NOT NULL DEFAULT '0',
  `aliAccount` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `aliName` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `manulMsg` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `createTime` datetime NOT NULL,
  `gameData` text COLLATE utf8_bin NOT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  `logTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fetchUser` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_extractId_extractType` (`extractId`,`extractType`),
  KEY `adminName` (`adminName`),
  KEY `fetchUser` (`fetchUser`)
) ENGINE=InnoDB AUTO_INCREMENT=9530967 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='兑换分配表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `extractassignlog`
--

DROP TABLE IF EXISTS `extractassignlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extractassignlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `extractId` int(11) NOT NULL DEFAULT '0' COMMENT '订单审核人id',
  `adminName` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '订单审核人编号',
  `userId` int(11) NOT NULL COMMENT '玩家id',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `gameData` varchar(500) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '游戏数据',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '0.通过   1.未通过   2.拒绝并封号',
  `firstlogTime` datetime NOT NULL COMMENT '订单分配时间',
  `orderTime` datetime NOT NULL COMMENT '处理时间',
  `auditExtractId` int(11) DEFAULT NULL COMMENT 'kefu审核id',
  `auditAdminName` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '审核kefu编号',
  `secondLogTime` datetime DEFAULT NULL COMMENT '审核kefu审核时间记录',
  `auditStatus` int(11) DEFAULT '999' COMMENT '审核kefu处理客服订单的状态0 正确  1错误    999未审查',
  `auditName` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '审查客服名字',
  `isOvertime` int(11) DEFAULT '0' COMMENT '0 未超时  1 超时',
  `isTitle` int(11) DEFAULT '0' COMMENT 'userId是否标红  0 未达到  1达到',
  `aName` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '客服姓名',
  `isExamination` int(11) NOT NULL DEFAULT '0' COMMENT '是否审查   0未审查  1审查',
  `extractType` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `channel` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '渠道品牌',
  `msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '客服审核客服拒绝原因',
  `chargeSum` int(11) NOT NULL DEFAULT '0' COMMENT '充值总额',
  PRIMARY KEY (`id`),
  KEY `index_aa` (`secondLogTime`,`adminName`,`auditName`),
  KEY `index_oaac` (`orderTime`,`adminName`,`auditStatus`,`channel`),
  KEY `index_userId` (`userId`),
  KEY `index_eId` (`extractId`)
) ENGINE=InnoDB AUTO_INCREMENT=10807145 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='兑换日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `extractuser`
--

DROP TABLE IF EXISTS `extractuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extractuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `ratio` int(11) NOT NULL DEFAULT '0',
  `minQuota` bigint(20) DEFAULT NULL,
  `maxQuota` bigint(20) DEFAULT NULL,
  `dealCount` int(11) NOT NULL DEFAULT '0',
  `stop` bit(1) NOT NULL DEFAULT b'0',
  `groups` int(11) NOT NULL DEFAULT '1' COMMENT '分组',
  `isKPI` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否kpi   0不是 1是',
  `isExamination` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否审查   0不是 1 是',
  `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '名字',
  `logTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `presetCoverage` varchar(50) COLLATE utf8_bin DEFAULT '0' COMMENT '预设覆盖率',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '客服状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `extractwork`
--

DROP TABLE IF EXISTS `extractwork`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extractwork` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `dealCount` int(11) NOT NULL DEFAULT '0',
  `refuseCount` int(11) NOT NULL DEFAULT '0',
  `dealDate` varchar(50) COLLATE utf8_bin DEFAULT '0' COMMENT '处理日期',
  `auditorTrueCount` int(11) NOT NULL DEFAULT '0' COMMENT '审核正确的订单',
  `auditorFalseCount` int(11) NOT NULL DEFAULT '0' COMMENT '审核错误的订单',
  `auditorNoCount` int(11) NOT NULL DEFAULT '0' COMMENT '未审核的订单',
  `sysCount` int(11) NOT NULL DEFAULT '0' COMMENT '系统处理黑名单数量统计',
  `closeUser` int(11) NOT NULL DEFAULT '0' COMMENT '封号订单数量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_date_unique` (`adminName`,`dealDate`),
  KEY `dealDate_index` (`dealDate`),
  KEY `adminName_index` (`adminName`)
) ENGINE=InnoDB AUTO_INCREMENT=2886153 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='每日兑换KPI数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gamesetting`
--

DROP TABLE IF EXISTS `gamesetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gamesetting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `value` varchar(1000) DEFAULT NULL,
  `desp` varchar(258) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `channel` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sel_index` (`code`,`version`,`channel`)
) ENGINE=InnoDB AUTO_INCREMENT=3687934 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gameversion`
--

DROP TABLE IF EXISTS `gameversion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gameversion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `versionCode` varchar(30) NOT NULL,
  `des` varchar(1000) DEFAULT NULL,
  `isStrict` tinyint(1) unsigned zerofill DEFAULT '0',
  `sendTime` timestamp NULL DEFAULT NULL,
  `versionNum` int(11) DEFAULT NULL,
  `md5` varchar(32) DEFAULT '',
  `apkPath` varchar(200) DEFAULT '',
  `channels` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `versionNum_INDEX` (`versionNum`)
) ENGINE=InnoDB AUTO_INCREMENT=8461 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `goldenfriedcheat_bak`
--

DROP TABLE IF EXISTS `goldenfriedcheat_bak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goldenfriedcheat_bak` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `roomId` int(11) NOT NULL DEFAULT '0',
  `cards` varchar(11) NOT NULL DEFAULT '',
  `actions` varchar(255) NOT NULL DEFAULT '',
  `mates` varchar(255) NOT NULL DEFAULT '',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `time` (`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `goldenfriedcheatmate`
--

DROP TABLE IF EXISTS `goldenfriedcheatmate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goldenfriedcheatmate` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `player1` int(11) NOT NULL DEFAULT '0',
  `player2` int(11) NOT NULL DEFAULT '0',
  `count` int(11) NOT NULL DEFAULT '0',
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `player1_player2` (`player1`,`player2`),
  UNIQUE KEY `player2_player1` (`player2`,`player1`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `goldenfriedmate`
--

DROP TABLE IF EXISTS `goldenfriedmate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goldenfriedmate` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `player1` int(11) NOT NULL DEFAULT '0',
  `player2` int(11) NOT NULL DEFAULT '0',
  `count` int(11) NOT NULL DEFAULT '0',
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MEMORY DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `goldenfriedround_bak`
--

DROP TABLE IF EXISTS `goldenfriedround_bak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goldenfriedround_bak` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serverId` int(11) NOT NULL DEFAULT '0',
  `roomId` int(11) NOT NULL DEFAULT '0',
  `actions` varchar(255) NOT NULL DEFAULT '',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `time` (`time`)
) ENGINE=InnoDB AUTO_INCREMENT=11895536 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `landlordcheatmate`
--

DROP TABLE IF EXISTS `landlordcheatmate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `landlordcheatmate` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `player1` int(11) NOT NULL DEFAULT '0',
  `player2` int(11) NOT NULL DEFAULT '0',
  `count` int(11) NOT NULL DEFAULT '0',
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `player1_player2` (`player1`,`player2`),
  UNIQUE KEY `player2_player1` (`player2`,`player1`)
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `landlorderinfo`
--

DROP TABLE IF EXISTS `landlorderinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `landlorderinfo` (
  `userid` int(11) NOT NULL DEFAULT '0',
  `win` int(11) NOT NULL DEFAULT '0',
  `lose` int(11) NOT NULL DEFAULT '0',
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `landlordmate`
--

DROP TABLE IF EXISTS `landlordmate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `landlordmate` (
  `player1` int(11) NOT NULL DEFAULT '0',
  `player2` int(11) NOT NULL DEFAULT '0',
  `count` int(11) NOT NULL DEFAULT '0',
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`player1`,`player2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `style` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `miscmonitorinfo`
--

DROP TABLE IF EXISTS `miscmonitorinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miscmonitorinfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `key` varchar(50) NOT NULL DEFAULT '',
  `value` varchar(50) NOT NULL DEFAULT '',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`key`)
) ENGINE=InnoDB AUTO_INCREMENT=374790 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `packagesetting`
--

DROP TABLE IF EXISTS `packagesetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `packagesetting` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `channel_prefix` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `brand_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `url_prefix` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ann_title` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ann_content` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `sms_content` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `update_domain` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `official_url` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `channel_unique` (`channel_prefix`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paymerchant_setting`
--

DROP TABLE IF EXISTS `paymerchant_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymerchant_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tunnel` varchar(50) NOT NULL DEFAULT '' COMMENT '支付通道',
  `merchant` varchar(50) NOT NULL DEFAULT '' COMMENT '商户号',
  `merchantName` varchar(50) NOT NULL DEFAULT '' COMMENT '商户品牌名称',
  `token` varchar(100) NOT NULL DEFAULT '' COMMENT '支付token',
  `queryUrl` varchar(100) NOT NULL DEFAULT '' COMMENT '查单地址',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_tm` (`tunnel`,`merchant`),
  UNIQUE KEY `unique_merchantname_tunnel` (`merchantName`,`tunnel`)
) ENGINE=InnoDB AUTO_INCREMENT=490 DEFAULT CHARSET=utf8 COMMENT='支付查单配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '渠道账号',
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道密码',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '推广渠道名',
  `url` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '推广渠道网址',
  `remark` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '推广备注',
  `promotionurl` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '推广网址',
  `iosno` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'ios渠道号',
  `androidno` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'android渠道号',
  `desp` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='推广渠道管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `promotion_setting`
--

DROP TABLE IF EXISTS `promotion_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion_setting` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `brand` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `platform` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `name` varchar(50) COLLATE utf8_bin DEFAULT '',
  `version` varchar(50) COLLATE utf8_bin DEFAULT '',
  `url` varchar(200) COLLATE utf8_bin DEFAULT '',
  `ann_title` varchar(50) COLLATE utf8_bin DEFAULT '',
  `ann_content` varchar(500) COLLATE utf8_bin DEFAULT '',
  `sms_content` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `update_domain` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `official_url` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `current_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `brand_platform` (`brand`,`platform`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proxychargelog`
--

DROP TABLE IF EXISTS `proxychargelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proxychargelog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `proxyId` varchar(50) DEFAULT NULL,
  `proxyMoney` int(11) DEFAULT NULL,
  `userIndex` varchar(255) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `logTime` datetime DEFAULT NULL,
  `annId` int(11) NOT NULL DEFAULT '0',
  `isLock` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `userId_index` (`userId`),
  KEY `userIndex_index` (`userIndex`),
  KEY `proxyId_index` (`proxyId`),
  KEY `annId_index` (`annId`)
) ENGINE=InnoDB AUTO_INCREMENT=13740059 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proxyuser`
--

DROP TABLE IF EXISTS `proxyuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proxyuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `passwd` varchar(32) DEFAULT NULL,
  `regDate` datetime DEFAULT NULL,
  `valuable` bit(1) DEFAULT b'1',
  `lastLoginDate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `money` int(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `wechat` varchar(20) DEFAULT NULL,
  `dispName` varchar(20) DEFAULT NULL,
  `createUser` varchar(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `freeze` bit(1) DEFAULT b'0',
  `totalMoney` bigint(20) DEFAULT NULL,
  `priority` int(11) DEFAULT '0' COMMENT '优先级',
  `brand` varchar(20) DEFAULT 'ds',
  `province` varchar(20) DEFAULT '',
  `city` varchar(20) NOT NULL DEFAULT '' COMMENT '城市',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_primary` (`username`),
  UNIQUE KEY `qq_primary` (`qq`),
  UNIQUE KEY `wechat_primary` (`wechat`),
  KEY `username_index` (`username`),
  KEY `createUser_index` (`createUser`),
  KEY `brand` (`brand`)
) ENGINE=InnoDB AUTO_INCREMENT=2142 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `realip`
--

DROP TABLE IF EXISTS `realip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `realip` (
  `intranet` varchar(15) NOT NULL,
  `extranet` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`intranet`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recordauditname`
--

DROP TABLE IF EXISTS `recordauditname`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recordauditname` (
  `AdminName` varchar(45) NOT NULL,
  `AuditName` varchar(45) DEFAULT NULL,
  `CountNum` int(11) DEFAULT NULL,
  `StaTime` varchar(45) NOT NULL,
  KEY `index_all` (`StaTime`,`AdminName`,`AuditName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sendmoneylog`
--

DROP TABLE IF EXISTS `sendmoneylog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sendmoneylog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `upname` varchar(50) DEFAULT '0',
  `downname` varchar(50) DEFAULT '0',
  `money` int(11) NOT NULL DEFAULT '0',
  `logtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `up_index` (`upname`),
  KEY `down_index` (`downname`)
) ENGINE=InnoDB AUTO_INCREMENT=4717 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `server`
--

DROP TABLE IF EXISTS `server`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `server` (
  `serverID` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `kindID` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `subType` int(11) NOT NULL DEFAULT '0',
  `ip` varchar(32) NOT NULL,
  `port` int(11) NOT NULL,
  `tableCount` int(11) NOT NULL,
  `gamePlayer` int(11) NOT NULL,
  `beginTime` int(11) NOT NULL,
  `endTime` int(11) NOT NULL,
  `totalNum` int(11) NOT NULL,
  `realTotalNum` int(11) NOT NULL,
  `nullity` int(11) NOT NULL,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cellScore` int(11) NOT NULL,
  `lessScore` int(11) DEFAULT '0',
  `minGold` int(11) DEFAULT '0',
  `maxGold` int(11) DEFAULT '0',
  `forbid` bit(1) DEFAULT b'0',
  `slb` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`serverID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servercountlog`
--

DROP TABLE IF EXISTS `servercountlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servercountlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` int(11) NOT NULL DEFAULT '0',
  `logTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `serverId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12760460 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `updatefile`
--

DROP TABLE IF EXISTS `updatefile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `updatefile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` varchar(20) DEFAULT NULL,
  `tagNum` int(11) DEFAULT NULL,
  `fileName` varchar(2000) DEFAULT NULL,
  `fileMD5` varchar(2000) DEFAULT NULL,
  `isEncrypt` bit(1) DEFAULT NULL,
  `channel` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userdevice`
--

DROP TABLE IF EXISTS `userdevice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userdevice` (
  `userId` int(11) NOT NULL DEFAULT '0',
  `machineSerial` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`userId`,`machineSerial`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinfo` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `passwd` varchar(50) NOT NULL,
  `roles` varchar(128) DEFAULT NULL,
  `channels` varchar(128) DEFAULT NULL,
  `loginIp` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0',
  `isValidSms` bit(1) DEFAULT b'0',
  `phone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `Index 2` (`username`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=2082 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wechat_monitor_domain`
--

DROP TABLE IF EXISTS `wechat_monitor_domain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wechat_monitor_domain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT '-10' COMMENT '类型1.cps_List 2全民分品牌_list 3.全民分等级_hash 4.官网_list 5全民_db',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'url域名',
  `brand` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '品牌',
  `level` int(11) DEFAULT '-10' COMMENT '级别',
  `state` varchar(50) NOT NULL COMMENT '状态 0未使用 1.正在使用 2.封并未处理 3.已处理-废弃',
  `desp` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '信息',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=294 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `worktimerec`
--

DROP TABLE IF EXISTS `worktimerec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worktimerec` (
  `AdminName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `WorkDifTime` int(11) NOT NULL,
  `countNum` int(11) NOT NULL,
  `recTime` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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