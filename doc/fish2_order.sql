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
-- Current Database: `fish2_order`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `fish2_order` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `fish2_order`;

--
-- Table structure for table `channel`
--

DROP TABLE IF EXISTS `channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel` varchar(40) DEFAULT NULL,
  `version` varchar(20) DEFAULT NULL,
  `name` varchar(200) DEFAULT '' COMMENT '包名',
  `url` varchar(200) DEFAULT '' COMMENT '包地址',
  `icon` varchar(200) DEFAULT '' COMMENT '包icon',
  `desp` varchar(200) DEFAULT '' COMMENT '包描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `channel` (`channel`)
) ENGINE=InnoDB AUTO_INCREMENT=706241 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `iapcheck`
--

DROP TABLE IF EXISTS `iapcheck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iapcheck` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tid` varchar(255) NOT NULL,
  `uniqueid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `iapcheck_tid_uindex` (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nobleget`
--

DROP TABLE IF EXISTS `nobleget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nobleget` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `getdata` date DEFAULT NULL,
  `gettime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `did` varchar(20) DEFAULT NULL COMMENT '订单号',
  `userId` int(7) DEFAULT NULL COMMENT '用户id',
  `money` int(8) DEFAULT NULL COMMENT '支付金额',
  `createtime` datetime DEFAULT NULL COMMENT '订单生成时间(时间戳)',
  `paytime` datetime DEFAULT NULL COMMENT '订单支付时间(时间戳)',
  `paystatus` int(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '支付状态(0待支付，1成功，2失败)',
  `paytype` int(1) DEFAULT NULL COMMENT '支付类型',
  `channel` varchar(40) DEFAULT NULL COMMENT '渠道',
  `version` varchar(20) DEFAULT NULL,
  `paych` varchar(10) DEFAULT NULL,
  `payorder` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `did_paystatus_INDEX` (`did`),
  KEY `createtime_INDEX` (`createtime`),
  KEY `userId_INDEX` (`userId`),
  KEY `user_type_status` (`userId`,`paytype`,`paystatus`,`createtime`)
) ENGINE=InnoDB AUTO_INCREMENT=192553959 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orderlist`
--

DROP TABLE IF EXISTS `orderlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsid` int(11) DEFAULT NULL,
  `version` varchar(255) DEFAULT '' COMMENT '版本',
  `channel` int(11) DEFAULT NULL COMMENT '渠道',
  `money` int(11) DEFAULT '0' COMMENT '价格',
  `score` int(11) DEFAULT '0' COMMENT '数值',
  `goodsdesc` varchar(255) DEFAULT '' COMMENT '描述',
  `paytype` varchar(255) DEFAULT '' COMMENT '支付方式',
  `icon` int(11) DEFAULT '0',
  `recommand` int(11) DEFAULT '0',
  `exscore` int(11) DEFAULT '0',
  `excount` int(11) DEFAULT '0',
  `gold` int(11) DEFAULT '0',
  `gem` int(11) DEFAULT '0',
  `skillid` varchar(11) DEFAULT '0',
  `skillcount` varchar(255) DEFAULT '0',
  `creattime` datetime DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `isopen` int(255) DEFAULT '0',
  `goodsorder` int(255) DEFAULT '0',
  `goodstype` int(11) DEFAULT NULL,
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