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
-- Current Database: `fish2_info`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `fish2_info` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `fish2_info`;

--
-- Table structure for table `accountsinfo`
--

DROP TABLE IF EXISTS `accountsinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountsinfo` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) DEFAULT '0',
  `nickName` varchar(32) DEFAULT NULL,
  `userIndex` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `ensurePass` varchar(8) DEFAULT NULL,
  `faceID` smallint(6) DEFAULT '1',
  `boxID` smallint(6) DEFAULT NULL,
  `vip` int(11) DEFAULT '0',
  `nullity` tinyint(4) DEFAULT NULL,
  `machineSerial` varchar(80) DEFAULT NULL,
  `gameLogonTimes` int(11) unsigned zerofill DEFAULT '00000000000',
  `registerIP` varchar(15) DEFAULT NULL,
  `lastLogonDate` datetime DEFAULT NULL,
  `registerDate` datetime DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `channel` varchar(40) DEFAULT NULL,
  `regVersion` varchar(40) DEFAULT NULL,
  `invitationCode` varchar(10) DEFAULT NULL,
  `gunStyle` int(11) unsigned zerofill DEFAULT '00000000000',
  `nobleDays` int(11) unsigned zerofill DEFAULT '00000000000',
  `callsNum` int(11) DEFAULT NULL,
  `logonType` varchar(4) DEFAULT NULL,
  `wxToken` varchar(64) DEFAULT NULL,
  `binding` bit(1) DEFAULT NULL,
  `changedName` bit(1) DEFAULT NULL,
  `sendPass` varchar(32) DEFAULT NULL,
  `guideType` int(11) DEFAULT '2' COMMENT '引导游戏类型默认为ddz',
  PRIMARY KEY (`userID`),
  KEY `invitationCode_INDEX` (`invitationCode`),
  KEY `name_pass_serial_INDEX` (`userIndex`,`password`) USING BTREE,
  KEY `serial_INDEX` (`machineSerial`)
) ENGINE=InnoDB AUTO_INCREMENT=188838484 DEFAULT CHARSET=utf8 CHECKSUM=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `src` varchar(128) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `target` varchar(128) DEFAULT NULL,
  `pop` bit(1) DEFAULT NULL,
  `motion` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `date_index` (`startTime`,`endTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `activityrec`
--

DROP TABLE IF EXISTS `activityrec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activityrec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `actId` int(11) DEFAULT NULL,
  `logTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `link_index` (`userId`,`actId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alibind`
--

DROP TABLE IF EXISTS `alibind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alibind` (
  `userId` int(11) NOT NULL,
  `aliAccount` varchar(64) DEFAULT NULL,
  `realName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alibindrec`
--

DROP TABLE IF EXISTS `alibindrec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alibindrec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `mobileId` varchar(80) DEFAULT NULL,
  `version` varchar(20) DEFAULT NULL,
  `channel` varchar(40) DEFAULT NULL,
  `logDate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `aliAccount` varchar(64) DEFAULT NULL,
  `realName` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3616317 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) DEFAULT NULL,
  `title` varchar(32) DEFAULT NULL,
  `sendname` varchar(10) DEFAULT NULL,
  `content` varchar(512) DEFAULT '',
  `propId` varchar(64) DEFAULT '',
  `propNum` varchar(64) DEFAULT '',
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `pop` bit(1) DEFAULT b'0',
  `num` int(11) DEFAULT NULL,
  `userId` int(11) unsigned zerofill DEFAULT '00000000000',
  `status` tinyint(3) unsigned zerofill DEFAULT '000',
  `dealTime` datetime DEFAULT NULL,
  `subId` int(11) unsigned zerofill DEFAULT '00000000000',
  PRIMARY KEY (`id`),
  KEY `sel_index` (`userId`,`subId`)
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `announcerec`
--

DROP TABLE IF EXISTS `announcerec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcerec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `announceId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `logTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sel_index` (`announceId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `baccarat_video_account`
--

DROP TABLE IF EXISTS `baccarat_video_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baccarat_video_account` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL DEFAULT '',
  `password` varchar(32) NOT NULL DEFAULT '',
  `update_time` datetime DEFAULT '1970-01-01 08:00:00',
  `create_time` datetime DEFAULT '1970-01-01 08:00:00',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `name_INDEX` (`username`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='视讯百家乐荷官系统管理员帐号表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bag`
--

DROP TABLE IF EXISTS `bag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `propID` int(11) DEFAULT NULL,
  `propNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userID_propID_INDEX` (`userID`,`propID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bankbind`
--

DROP TABLE IF EXISTS `bankbind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bankbind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL DEFAULT '0',
  `channel` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '姓名',
  `idCard` varchar(50) COLLATE utf8_bin DEFAULT '' COMMENT '身份证号',
  `cardAccount` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `bank` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '开户行',
  `province` varchar(50) COLLATE utf8_bin DEFAULT '' COMMENT '开户行省份',
  `city` varchar(50) COLLATE utf8_bin DEFAULT '' COMMENT '开户行城市',
  `bankBranch` varchar(50) COLLATE utf8_bin DEFAULT '' COMMENT '开户行支行',
  `submitExtractTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`),
  UNIQUE KEY `cardAccount` (`cardAccount`)
) ENGINE=InnoDB AUTO_INCREMENT=198781 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='绑定银行卡';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bankextractmoney`
--

DROP TABLE IF EXISTS `bankextractmoney`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bankextractmoney` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `phoneNum` varchar(32) DEFAULT NULL,
  `mobileId` varchar(80) DEFAULT NULL,
  `ip` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `sucTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `channel` varchar(40) DEFAULT NULL,
  `tradeOrder` varchar(32) DEFAULT NULL,
  `charge` int(11) DEFAULT NULL,
  `cardAccount` varchar(64) DEFAULT NULL COMMENT '银行卡号',
  `bankAddress` varchar(128) DEFAULT NULL COMMENT '银行卡号支行信息',
  `bankId` varchar(32) DEFAULT NULL COMMENT '银行简称（ICBC）',
  `realName` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `idCard` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `city` varchar(32) DEFAULT NULL,
  `manualMsg` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userIndex` (`userId`),
  KEY `aliAccountIndex` (`cardAccount`),
  KEY `statusIndex` (`status`),
  KEY `tradeOrderIndex` (`tradeOrder`)
) ENGINE=InnoDB AUTO_INCREMENT=1386717 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `changepasslogs`
--

DROP TABLE IF EXISTS `changepasslogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `changepasslogs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `userIndex` varchar(32) DEFAULT NULL,
  `openId` varchar(64) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `logTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userlog` (`userIndex`,`logTime`)
) ENGINE=InnoDB AUTO_INCREMENT=30495358 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `channelcode`
--

DROP TABLE IF EXISTS `channelcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channelcode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `code` int(11) DEFAULT NULL,
  `sign` varchar(32) DEFAULT NULL,
  `version` varchar(64) DEFAULT NULL,
  `defaultUrl` varchar(200) DEFAULT NULL,
  `shortName` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name_index` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `clientextendlogs`
--

DROP TABLE IF EXISTS `clientextendlogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientextendlogs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL DEFAULT '0',
  `clientVer` varchar(50) NOT NULL DEFAULT '0',
  `logTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `userID` (`userID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `clientloginlogs`
--

DROP TABLE IF EXISTS `clientloginlogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientloginlogs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(32) DEFAULT NULL,
  `ip` varchar(32) DEFAULT NULL,
  `clientVersion` int(11) DEFAULT NULL,
  `logTime` datetime DEFAULT NULL,
  `osVersion` varchar(20) DEFAULT NULL,
  `mobile` varchar(64) DEFAULT NULL,
  `isJb` bit(1) DEFAULT NULL,
  `machineSerial` varchar(64) DEFAULT NULL,
  `bundleVersion` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userlog` (`userId`,`logTime`),
  KEY `machinekey` (`machineSerial`)
) ENGINE=InnoDB AUTO_INCREMENT=842654670 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dailyactuser`
--

DROP TABLE IF EXISTS `dailyactuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dailyactuser` (
  `userID` int(11) NOT NULL,
  `regChannel` varchar(40) NOT NULL,
  `logTime` datetime NOT NULL,
  `mobileId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dailychargeuser`
--

DROP TABLE IF EXISTS `dailychargeuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dailychargeuser` (
  `userID` int(11) NOT NULL,
  `regChannel` varchar(40) NOT NULL,
  `logTime` datetime NOT NULL,
  `mobileId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
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
  `channel` varchar(40) DEFAULT '0',
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
  UNIQUE KEY `unique_channel_calctime` (`calcTime`,`channel`)
) ENGINE=InnoDB AUTO_INCREMENT=568051170 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `daystatistics2`
--

DROP TABLE IF EXISTS `daystatistics2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daystatistics2` (
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
  `channel` varchar(40) DEFAULT '0',
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
  UNIQUE KEY `unique_channel_calctime` (`calcTime`,`channel`)
) ENGINE=InnoDB AUTO_INCREMENT=197407 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `daystatistics3`
--

DROP TABLE IF EXISTS `daystatistics3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daystatistics3` (
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
  `channel` varchar(40) DEFAULT '0',
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
  KEY `calcTime` (`calcTime`),
  KEY `channel` (`channel`)
) ENGINE=InnoDB AUTO_INCREMENT=501789 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `exchangecode`
--

DROP TABLE IF EXISTS `exchangecode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exchangecode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(12) DEFAULT NULL,
  `starTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `disposable` bit(1) DEFAULT NULL,
  `propId` varchar(128) DEFAULT NULL,
  `propNum` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `exchangecoderec`
--

DROP TABLE IF EXISTS `exchangecoderec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exchangecoderec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codeId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `extractcache`
--

DROP TABLE IF EXISTS `extractcache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extractcache` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `extractId` int(11) NOT NULL DEFAULT '0',
  `extractType` varchar(50) NOT NULL DEFAULT 'alipay',
  `chargeSum` int(11) DEFAULT '0',
  `extractSum` int(11) DEFAULT '0',
  `score` int(11) DEFAULT '0',
  `type` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `extractId` (`extractId`),
  UNIQUE KEY `unique_extractId_extractType` (`extractId`,`extractType`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='兑换缓冲池';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `extractmoney`
--

DROP TABLE IF EXISTS `extractmoney`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extractmoney` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `mobileId` varchar(80) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `sucTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `clientVersion` varchar(20) DEFAULT NULL,
  `channel` varchar(40) DEFAULT NULL,
  `blackAccount` bit(1) DEFAULT NULL,
  `aliOrder` varchar(32) DEFAULT NULL,
  `charge` int(11) DEFAULT NULL,
  `msg` varchar(64) DEFAULT NULL,
  `payAmount` varchar(32) DEFAULT NULL,
  `payAccount` varchar(64) DEFAULT NULL,
  `aliAccount` varchar(64) DEFAULT NULL,
  `realName` varchar(32) DEFAULT NULL,
  `manualMsg` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userIndex` (`userId`),
  KEY `aliAccountIndex` (`aliAccount`),
  KEY `statusIndex` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=25558088 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `channel` varchar(40) COLLATE utf8_bin NOT NULL DEFAULT '',
  `qq` bigint(20) NOT NULL DEFAULT '0',
  `desp` varchar(300) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `errormessage` varchar(400) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ip` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29134 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `football_bonus`
--

DROP TABLE IF EXISTS `football_bonus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `football_bonus` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '奖券ID',
  `uid` int(11) DEFAULT NULL COMMENT '玩家ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `uid_id_idx` (`uid`,`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91127 DEFAULT CHARSET=latin1 COMMENT='奖券总表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `football_bonus_award`
--

DROP TABLE IF EXISTS `football_bonus_award`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `football_bonus_award` (
  `idx` int(8) NOT NULL AUTO_INCREMENT COMMENT '索引',
  `id` int(11) DEFAULT NULL COMMENT '奖券ID',
  `level` smallint(4) DEFAULT NULL COMMENT '玩家奖励等级',
  PRIMARY KEY (`idx`),
  KEY `id_level_idx` (`id`,`level`)
) ENGINE=InnoDB AUTO_INCREMENT=258 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `football_bonus_userinfo`
--

DROP TABLE IF EXISTS `football_bonus_userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `football_bonus_userinfo` (
  `uid` int(11) NOT NULL,
  `money` double DEFAULT '0' COMMENT '玩家下注总额',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='抽奖玩家信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `football_money_ranking_list`
--

DROP TABLE IF EXISTS `football_money_ranking_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `football_money_ranking_list` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `userid` bigint(22) DEFAULT NULL,
  `moneysum` double DEFAULT '0',
  `updatetime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `football_result`
--

DROP TABLE IF EXISTS `football_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `football_result` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `eventid` bigint(22) DEFAULT NULL,
  `matchid` bigint(22) DEFAULT NULL,
  `result` varchar(45) DEFAULT NULL,
  `homeid` bigint(22) DEFAULT NULL,
  `awayid` bigint(22) DEFAULT NULL,
  `eventdate` bigint(22) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `idx_football_result_eventid` (`eventid`),
  KEY `idx_football_result_matchid` (`matchid`)
) ENGINE=InnoDB AUTO_INCREMENT=3040 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `football_unsettled_player`
--

DROP TABLE IF EXISTS `football_unsettled_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `football_unsettled_player` (
  `udid` int(11) NOT NULL,
  `update_time` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`udid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `football_user_group`
--

DROP TABLE IF EXISTS `football_user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `football_user_group` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `teamid` varchar(50) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `idx_football_user_group_userid_teamid` (`userid`,`teamid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `football_user_money`
--

DROP TABLE IF EXISTS `football_user_money`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `football_user_money` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `uid` bigint(22) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `process` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `football_wager_record`
--

DROP TABLE IF EXISTS `football_wager_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `football_wager_record` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `wid` bigint(18) DEFAULT NULL,
  `betime` varchar(50) DEFAULT NULL,
  `wager` double DEFAULT '0',
  `returnamount` double DEFAULT NULL,
  `odds` float DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `betTitle` varchar(100) DEFAULT NULL,
  `settleTime` varchar(50) DEFAULT NULL,
  `stubuid` int(11) DEFAULT NULL,
  `bn` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `updateSettleTime` varchar(50) DEFAULT NULL,
  `createTime` bigint(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `idx_football_wager_record_wid` (`wid`),
  KEY `idx_football_wager_record_uid` (`uid`),
  KEY `idx_football_wager_record_state` (`state`)
) ENGINE=InnoDB AUTO_INCREMENT=1300043 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `football_wager_record_match`
--

DROP TABLE IF EXISTS `football_wager_record_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `football_wager_record_match` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `wid` bigint(18) DEFAULT NULL,
  `idx` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `hometeamname` varchar(100) DEFAULT NULL,
  `awayteamname` varchar(100) DEFAULT NULL,
  `selectname` varchar(100) DEFAULT NULL,
  `handicap` varchar(20) DEFAULT NULL,
  `oddsvalue` float DEFAULT NULL,
  `eventID` bigint(18) DEFAULT NULL,
  `eventname` varchar(100) DEFAULT NULL,
  `inplayscore` varchar(100) DEFAULT NULL,
  `results` varchar(100) DEFAULT NULL,
  `eventdatatime` bigint(18) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `betTitle` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`mid`),
  KEY `idx_football_wager_record_match_wid` (`wid`)
) ENGINE=InnoDB AUTO_INCREMENT=1629014 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `friedfield`
--

DROP TABLE IF EXISTS `friedfield`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friedfield` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel` varchar(40) DEFAULT '',
  `initIntegral` int(11) DEFAULT NULL,
  `initCalls` int(11) unsigned zerofill NOT NULL DEFAULT '00000000000',
  `initGold` int(11) unsigned zerofill NOT NULL,
  `initDiamond` int(11) unsigned zerofill NOT NULL,
  `initLockCnt` int(11) unsigned zerofill NOT NULL,
  `initFixCnt` int(11) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  KEY `platform_channel_INDEX` (`channel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gameloginlock`
--

DROP TABLE IF EXISTS `gameloginlock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gameloginlock` (
  `userId` int(11) NOT NULL,
  `logTime` datetime DEFAULT NULL,
  `machineSerial` varchar(64) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gameloginlogs`
--

DROP TABLE IF EXISTS `gameloginlogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gameloginlogs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `serverID` int(11) DEFAULT NULL,
  `clientIp` varchar(32) DEFAULT NULL,
  `logTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47584084 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gamescoreinfo`
--

DROP TABLE IF EXISTS `gamescoreinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gamescoreinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT '0',
  `score` bigint(20) DEFAULT '0',
  `safeMoney` bigint(20) DEFAULT '0',
  `diamond` bigint(20) DEFAULT '0',
  `ingot` bigint(20) DEFAULT '0',
  `totalIngot` bigint(20) DEFAULT '0',
  `ingotContribution` bigint(20) DEFAULT '0',
  `chargeSum` int(11) DEFAULT '0',
  `level` int(11) DEFAULT '1',
  `experience` int(11) DEFAULT '0',
  `curGun` int(11) DEFAULT '1',
  `topGun` int(11) DEFAULT '1',
  `lockCnt` int(11) DEFAULT '0',
  `fixCnt` int(11) DEFAULT '0',
  `topScore` bigint(20) DEFAULT '0',
  `scoreUpdateDate` datetime DEFAULT NULL,
  `topDiamond` bigint(20) DEFAULT '0',
  `diamondUpdateDate` datetime DEFAULT NULL,
  `contributeVal` bigint(20) DEFAULT '0',
  `forceVal` bigint(20) DEFAULT '0',
  `diamondDropLib` bigint(20) DEFAULT '0',
  `missFishLib` bigint(20) DEFAULT '0',
  `boxContributeVal` bigint(20) DEFAULT '0',
  `targetControlVal` bigint(20) DEFAULT '0',
  `nowControlVal` bigint(20) DEFAULT '0',
  `totalControlVal` bigint(20) DEFAULT '0',
  `rateVal` bigint(20) DEFAULT '0',
  `dayWinVal` bigint(20) DEFAULT '0',
  `moneyPool` int(11) DEFAULT '0',
  `deadPrizeFishCnt` int(11) DEFAULT '0',
  `lastResetDate` datetime DEFAULT NULL,
  `shotNum` bigint(20) DEFAULT '0',
  `dayShootMoney` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `userID_INDEX` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=185834937 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `goldenfriedcheat`
--

DROP TABLE IF EXISTS `goldenfriedcheat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goldenfriedcheat` (
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
) ENGINE=InnoDB AUTO_INCREMENT=160424 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `goldenfriedroundfast`
--

DROP TABLE IF EXISTS `goldenfriedroundfast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goldenfriedroundfast` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serverId` int(11) NOT NULL DEFAULT '0',
  `roomId` int(11) NOT NULL DEFAULT '0',
  `player1` int(11) NOT NULL DEFAULT '0',
  `player2` int(11) NOT NULL DEFAULT '0',
  `player3` int(11) NOT NULL DEFAULT '0',
  `player4` int(11) NOT NULL DEFAULT '0',
  `player5` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `p1_key` (`player1`),
  KEY `p2_key` (`player2`),
  KEY `p3_key` (`player3`),
  KEY `p4_key` (`player4`),
  KEY `p5_key` (`player5`)
) ENGINE=InnoDB AUTO_INCREMENT=11530058 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ingotcontributionlogs`
--

DROP TABLE IF EXISTS `ingotcontributionlogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingotcontributionlogs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `beforeIngot` int(11) DEFAULT NULL,
  `afterIngot` int(11) DEFAULT NULL,
  `beforeIngotContribution` bigint(20) DEFAULT NULL,
  `afterIngotContribution` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invitation`
--

DROP TABLE IF EXISTS `invitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitation` (
  `invitedID` int(11) NOT NULL DEFAULT '0',
  `mobileId` varchar(80) DEFAULT NULL,
  `invitationCode` int(11) DEFAULT NULL,
  `first` bit(1) DEFAULT NULL,
  `second` bit(1) DEFAULT NULL,
  `third` bit(1) DEFAULT NULL,
  `forth` bit(1) DEFAULT NULL,
  `useDate` datetime DEFAULT NULL,
  PRIMARY KEY (`invitedID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `itemunlock`
--

DROP TABLE IF EXISTS `itemunlock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemunlock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `headID` int(11) DEFAULT NULL,
  `logTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_type` (`userID`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=11885905 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `memberinfo`
--

DROP TABLE IF EXISTS `memberinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `memberinfo` (
  `userID` bigint(11) NOT NULL,
  `membercode` varchar(50) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phonebindrec`
--

DROP TABLE IF EXISTS `phonebindrec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phonebindrec` (
  `userID` int(11) NOT NULL,
  `phoneNum` varchar(20) NOT NULL,
  `channel` varchar(40) DEFAULT NULL,
  `mobileId` varchar(64) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `logTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `phone_unique` (`phoneNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `questionrec`
--

DROP TABLE IF EXISTS `questionrec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questionrec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `questionId` int(11) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `logData` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userid` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reportdata`
--

DROP TABLE IF EXISTS `reportdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reportdata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chargeSum` bigint(20) DEFAULT '0' COMMENT '内部平台充值总额',
  `thirdpartSum` bigint(20) DEFAULT '0' COMMENT '外部平台充值总额',
  `proxySum` bigint(20) DEFAULT '0' COMMENT '代理实际充值总额',
  `proxyAddScore` bigint(20) DEFAULT '0' COMMENT '代理补货',
  `rateScore` bigint(20) DEFAULT '0' COMMENT '系统税收',
  `winScore` bigint(20) DEFAULT '0' COMMENT '系统输赢',
  `extractSum` bigint(20) DEFAULT '0' COMMENT '兑换总额（税后）',
  `proxyExtractSum` bigint(20) DEFAULT '0' COMMENT '代理兑换（税后）',
  `bankExtractSum` bigint(20) DEFAULT '0',
  `calcDate` varchar(50) COLLATE utf8_bin DEFAULT '0' COMMENT '报表日期',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `brand` varchar(20) COLLATE utf8_bin DEFAULT '' COMMENT '品牌',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_date_brand` (`calcDate`,`brand`)
) ENGINE=InnoDB AUTO_INCREMENT=481 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='每日报表数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reward`
--

DROP TABLE IF EXISTS `reward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reward` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `propID` int(11) DEFAULT NULL,
  `propNum` int(11) DEFAULT NULL,
  `logTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rmbrec`
--

DROP TABLE IF EXISTS `rmbrec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rmbrec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `exchangeId` int(11) DEFAULT NULL,
  `beforeIngot` bigint(20) DEFAULT NULL,
  `afterIngot` bigint(20) DEFAULT NULL,
  `logDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `server_active`
--

DROP TABLE IF EXISTS `server_active`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `server_active` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serverId` int(11) DEFAULT '0' COMMENT '服务器id',
  `winScore` bigint(20) DEFAULT '0',
  `loseScore` bigint(20) DEFAULT '0',
  `rateScore` bigint(20) DEFAULT '0',
  `actCount` int(11) DEFAULT '0' COMMENT '活跃数量',
  `gamblingCount` int(11) DEFAULT '0' COMMENT '牌局数量',
  `calcTime` datetime DEFAULT NULL COMMENT '统计时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `serverId_calcTime` (`serverId`,`calcTime`)
) ENGINE=InnoDB AUTO_INCREMENT=35422 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='每个服的每日活跃玩家数量';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `signinrec`
--

DROP TABLE IF EXISTS `signinrec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `signinrec` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `indexNum` int(11) DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `slotmachine_report`
--

DROP TABLE IF EXISTS `slotmachine_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `slotmachine_report` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serverType` int(11) DEFAULT NULL COMMENT '游戏类型',
  `calctime` datetime NOT NULL COMMENT '统计时间',
  `brand` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌',
  `dau` int(11) DEFAULT NULL COMMENT '日活',
  `rotationCount` bigint(20) DEFAULT NULL COMMENT '旋转次数',
  `betTotal` bigint(20) DEFAULT NULL COMMENT '下注总额',
  `playTime` bigint(20) DEFAULT NULL COMMENT '在线时长（秒）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `server_time_brand` (`serverType`,`calctime`,`brand`)
) ENGINE=InnoDB AUTO_INCREMENT=604 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stubrecord`
--

DROP TABLE IF EXISTS `stubrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stubrecord` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `moneyAmount` int(11) DEFAULT '0',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) DEFAULT NULL,
  `refid` varchar(80) DEFAULT NULL,
  `transactionid` int(11) DEFAULT NULL,
  `wid` bigint(22) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `returnMoney` int(11) DEFAULT '0',
  `returnState` int(11) DEFAULT '0',
  `betBerr` int(11) DEFAULT '-1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`uid`),
  UNIQUE KEY `idx_stubrecord_refid` (`refid`),
  KEY `idx_stubrecord_transactionid` (`transactionid`),
  KEY `idx_stubrecord_userid` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2011991 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `suggestion`
--

DROP TABLE IF EXISTS `suggestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suggestion` (
  `suggestId` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `mobileId` varchar(80) DEFAULT NULL,
  `submitTime` datetime DEFAULT NULL,
  `isReply` bit(1) NOT NULL DEFAULT b'0',
  `replyContent` varchar(500) DEFAULT NULL,
  `replyUser` varchar(30) DEFAULT NULL,
  `assigned` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`suggestId`),
  KEY `index_mobileId` (`mobileId`) USING BTREE,
  KEY `idx_assigned` (`assigned`,`submitTime`),
  KEY `idx_assign_user` (`assigned`,`mobileId`)
) ENGINE=InnoDB AUTO_INCREMENT=4548865 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `summarystatistics`
--

DROP TABLE IF EXISTS `summarystatistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `summarystatistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `regCount` int(11) DEFAULT '0' COMMENT '新注册人数',
  `regBindCount` int(11) DEFAULT '0' COMMENT '新注册绑定人数',
  `bindCount` int(11) DEFAULT '0' COMMENT '今日绑定总量',
  `activeCount` int(11) DEFAULT '0' COMMENT '活跃人数',
  `androidActiveCount` int(11) DEFAULT '0' COMMENT 'android登陆人数',
  `ipCount` int(11) DEFAULT '0' COMMENT '新进入IP',
  `onlineChargeSum` bigint(20) DEFAULT '0' COMMENT '在线充值总数',
  `newOnlineChargeSum` bigint(20) DEFAULT '0' COMMENT '新用户在线充值总数',
  `onlineChargeUserCount` int(11) DEFAULT '0' COMMENT '在线充值人数',
  `onlineChargeNewUserCount` int(11) DEFAULT '0' COMMENT '新用户在线充值人数',
  `onlineChargeCount` int(11) DEFAULT '0' COMMENT '在线充值笔数',
  `proxyChargeSum` bigint(20) DEFAULT '0' COMMENT '代理充值总数',
  `newProxyChargeSum` bigint(20) DEFAULT '0' COMMENT '新用户代理充值总数',
  `proxyChargeUserCount` int(11) DEFAULT '0' COMMENT '代理充值人数',
  `proxyChargeNewUserCount` int(11) DEFAULT '0' COMMENT '新用户代理充值人数',
  `proxyChargeCount` int(11) DEFAULT '0' COMMENT '代理充值笔数',
  `extractSum` bigint(20) DEFAULT '0' COMMENT '兑换总数',
  `extractCount` int(11) DEFAULT '0' COMMENT '兑换单数',
  `extractUserCount` int(11) DEFAULT '0' COMMENT '兑换人数',
  `oneDayRetention` int(11) DEFAULT '0' COMMENT '次日留存',
  `twoDayRetention` int(11) DEFAULT '0' COMMENT '二日留存',
  `threeDayRetention` int(11) DEFAULT '0' COMMENT '三日留存',
  `fourDayRetention` int(11) DEFAULT '0' COMMENT '四日留存',
  `fiveDayRetention` int(11) DEFAULT '0' COMMENT '五日留存',
  `sixDayRetention` int(11) DEFAULT '0' COMMENT '六日留存',
  `sevenDayRetention` int(11) DEFAULT '0' COMMENT '七日留存',
  `eightDayRetention` int(11) DEFAULT '0' COMMENT '八日留存',
  `nineDayRetention` int(11) DEFAULT '0' COMMENT '九日留存',
  `tenDayRetention` int(11) DEFAULT '0' COMMENT '十日留存',
  `ddzRateScore` bigint(20) DEFAULT '0' COMMENT '斗地主税收总数',
  `ddzUserCount` int(11) DEFAULT '0' COMMENT '斗地主人数',
  `ddzOnlineCount` int(11) DEFAULT '0' COMMENT '斗地主当前在线人数',
  `zjhRateScore` bigint(20) DEFAULT '0' COMMENT '扎金花税收总数',
  `zjhUserCount` int(11) DEFAULT '0' COMMENT '扎金花人数',
  `zjhOnlineCount` int(11) DEFAULT '0' COMMENT '扎金花当前在线人数',
  `brnnRateScore` bigint(20) DEFAULT '0' COMMENT '百人牛牛税收总数',
  `brnnUserCount` int(11) DEFAULT '0' COMMENT '百人牛牛人数',
  `brnnSystemBankerCount` int(11) DEFAULT '0' COMMENT '百人牛牛系统当庄次数',
  `brnnWinScore` bigint(20) DEFAULT '0' COMMENT '百人牛牛系统输赢',
  `brnnOnlineCount` int(11) DEFAULT '0' COMMENT '百人牛牛当前在线人数',
  `qznnRateScore` bigint(20) DEFAULT '0' COMMENT '抢庄牛牛税收总数',
  `qznnUserCount` int(11) DEFAULT '0' COMMENT '抢庄牛牛人数',
  `qznnOnlineCount` int(11) DEFAULT '0' COMMENT '抢庄牛牛当前在线人数',
  `byRateScore` bigint(20) DEFAULT '0' COMMENT '捕鱼税收',
  `byUserCount` int(11) DEFAULT '0' COMMENT '捕鱼人数',
  `byWinScore` bigint(20) DEFAULT '0' COMMENT '捕鱼系统输赢',
  `byOnlineCount` int(11) DEFAULT '0' COMMENT '捕鱼当前在线人数',
  `maxOnlineCount` int(11) DEFAULT '0' COMMENT '最高在线人数',
  `currentOnlineCount` int(11) DEFAULT '0' COMMENT '当前在线总人数',
  `calcTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '统计时间',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `channel` varchar(50) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `calcTime` (`calcTime`,`channel`)
) ENGINE=InnoDB AUTO_INCREMENT=4512 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='汇总统计';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `summarystatistics_20181007`
--

DROP TABLE IF EXISTS `summarystatistics_20181007`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `summarystatistics_20181007` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `regCount` int(11) DEFAULT '0' COMMENT '新注册人数',
  `regBindCount` int(11) DEFAULT '0' COMMENT '新注册绑定人数',
  `bindCount` int(11) DEFAULT '0' COMMENT '今日绑定总量',
  `activeCount` int(11) DEFAULT '0' COMMENT '活跃人数',
  `androidActiveCount` int(11) DEFAULT '0' COMMENT 'android登陆人数',
  `ipCount` int(11) DEFAULT '0' COMMENT '新进入IP',
  `onlineChargeSum` bigint(20) DEFAULT '0' COMMENT '在线充值总数',
  `newOnlineChargeSum` bigint(20) DEFAULT '0' COMMENT '新用户在线充值总数',
  `onlineChargeUserCount` int(11) DEFAULT '0' COMMENT '在线充值人数',
  `onlineChargeNewUserCount` int(11) DEFAULT '0' COMMENT '新用户在线充值人数',
  `onlineChargeCount` int(11) DEFAULT '0' COMMENT '在线充值笔数',
  `proxyChargeSum` bigint(20) DEFAULT '0' COMMENT '代理充值总数',
  `newProxyChargeSum` bigint(20) DEFAULT '0' COMMENT '新用户代理充值总数',
  `proxyChargeUserCount` int(11) DEFAULT '0' COMMENT '代理充值人数',
  `proxyChargeNewUserCount` int(11) DEFAULT '0' COMMENT '新用户代理充值人数',
  `proxyChargeCount` int(11) DEFAULT '0' COMMENT '代理充值笔数',
  `extractSum` bigint(20) DEFAULT '0' COMMENT '兑换总数',
  `extractCount` int(11) DEFAULT '0' COMMENT '兑换单数',
  `extractUserCount` int(11) DEFAULT '0' COMMENT '兑换人数',
  `oneDayRetention` int(11) DEFAULT '0' COMMENT '次日留存',
  `twoDayRetention` int(11) DEFAULT '0' COMMENT '二日留存',
  `threeDayRetention` int(11) DEFAULT '0' COMMENT '三日留存',
  `fourDayRetention` int(11) DEFAULT '0' COMMENT '四日留存',
  `fiveDayRetention` int(11) DEFAULT '0' COMMENT '五日留存',
  `sixDayRetention` int(11) DEFAULT '0' COMMENT '六日留存',
  `sevenDayRetention` int(11) DEFAULT '0' COMMENT '七日留存',
  `eightDayRetention` int(11) DEFAULT '0' COMMENT '八日留存',
  `nineDayRetention` int(11) DEFAULT '0' COMMENT '九日留存',
  `tenDayRetention` int(11) DEFAULT '0' COMMENT '十日留存',
  `ddzRateScore` bigint(20) DEFAULT '0' COMMENT '斗地主税收总数',
  `ddzUserCount` int(11) DEFAULT '0' COMMENT '斗地主人数',
  `ddzOnlineCount` int(11) DEFAULT '0' COMMENT '斗地主当前在线人数',
  `zjhRateScore` bigint(20) DEFAULT '0' COMMENT '扎金花税收总数',
  `zjhUserCount` int(11) DEFAULT '0' COMMENT '扎金花人数',
  `zjhOnlineCount` int(11) DEFAULT '0' COMMENT '扎金花当前在线人数',
  `brnnRateScore` bigint(20) DEFAULT '0' COMMENT '百人牛牛税收总数',
  `brnnUserCount` int(11) DEFAULT '0' COMMENT '百人牛牛人数',
  `brnnSystemBankerCount` int(11) DEFAULT '0' COMMENT '百人牛牛系统当庄次数',
  `brnnWinScore` bigint(20) DEFAULT '0' COMMENT '百人牛牛系统输赢',
  `brnnOnlineCount` int(11) DEFAULT '0' COMMENT '百人牛牛当前在线人数',
  `qznnRateScore` bigint(20) DEFAULT '0' COMMENT '抢庄牛牛税收总数',
  `qznnUserCount` int(11) DEFAULT '0' COMMENT '抢庄牛牛人数',
  `qznnOnlineCount` int(11) DEFAULT '0' COMMENT '抢庄牛牛当前在线人数',
  `byRateScore` bigint(20) DEFAULT '0' COMMENT '捕鱼税收',
  `byUserCount` int(11) DEFAULT '0' COMMENT '捕鱼人数',
  `byWinScore` bigint(20) DEFAULT '0' COMMENT '捕鱼系统输赢',
  `byOnlineCount` int(11) DEFAULT '0' COMMENT '捕鱼当前在线人数',
  `maxOnlineCount` int(11) DEFAULT '0' COMMENT '最高在线人数',
  `currentOnlineCount` int(11) DEFAULT '0' COMMENT '当前在线总人数',
  `calcTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '统计时间',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `channel` varchar(50) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `calcTime` (`calcTime`,`channel`)
) ENGINE=InnoDB AUTO_INCREMENT=4482 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='汇总统计';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `system_announcement`
--

DROP TABLE IF EXISTS `system_announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) DEFAULT NULL,
  `title` varchar(32) DEFAULT NULL,
  `sendname` varchar(10) DEFAULT NULL,
  `content` varchar(512) DEFAULT '',
  `propId` varchar(64) DEFAULT '',
  `propNum` varchar(64) DEFAULT '',
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `pop` bit(1) DEFAULT b'0',
  `num` int(11) DEFAULT NULL,
  `userId` int(11) unsigned zerofill DEFAULT '00000000000',
  `status` tinyint(3) unsigned zerofill DEFAULT '000',
  `dealTime` datetime DEFAULT NULL,
  `subId` int(11) unsigned zerofill DEFAULT '00000000000',
  `channel` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sel_index` (`userId`,`subId`),
  KEY `channel` (`channel`)
) ENGINE=InnoDB AUTO_INCREMENT=705704 DEFAULT CHARSET=utf8;
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
-- Table structure for table `wchatshare`
--

DROP TABLE IF EXISTS `wchatshare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wchatshare` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `sharedata` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `weekactiverec`
--

DROP TABLE IF EXISTS `weekactiverec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weekactiverec` (
  `userID` int(11) NOT NULL,
  `point` int(11) DEFAULT NULL,
  `items` int(11) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wxbindrec`
--

DROP TABLE IF EXISTS `wxbindrec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wxbindrec` (
  `id` int(11) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `openid` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `data` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wxprizerec`
--

DROP TABLE IF EXISTS `wxprizerec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wxprizerec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) DEFAULT NULL,
  `sendStatus` varchar(16) DEFAULT NULL,
  `recvStatus` varchar(16) DEFAULT NULL,
  `openId` varchar(32) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `wxOrderId` varchar(32) DEFAULT NULL,
  `orderId` varchar(28) DEFAULT NULL,
  `sendErrMsg` varchar(64) DEFAULT NULL,
  `recvErrMsg` varchar(64) DEFAULT NULL,
  `sendDate` datetime DEFAULT NULL,
  `recvDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wxsigninrec`
--

DROP TABLE IF EXISTS `wxsigninrec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wxsigninrec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `userIndex` varchar(32) DEFAULT NULL,
  `openId` varchar(32) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `logDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
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
