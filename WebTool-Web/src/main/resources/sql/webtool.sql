/*
SQLyog Ultimate v8.32 
MySQL - 5.7.28-log : Database - webtool
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`webtool` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `webtool`;

/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  `info1` varchar(100) DEFAULT NULL,
  `info2` varchar(100) DEFAULT NULL,
  KEY `fk_authorities_users` (`username`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tb_bh_channelinfo` */

DROP TABLE IF EXISTS `tb_bh_channelinfo`;

CREATE TABLE `tb_bh_channelinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channelkey` varchar(100) DEFAULT NULL COMMENT '渠道列表key',
  `logininfo` varchar(1000) DEFAULT NULL COMMENT '登录信息',
  `remark` varchar(5000) DEFAULT NULL COMMENT '备用信息',
  `createdate` bigint(10) unsigned DEFAULT NULL COMMENT '创建日期',
  `updatedate` bigint(10) unsigned DEFAULT NULL COMMENT '更新日期',
  `proxyUrl` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `channelkey_idx` (`channelkey`),
  KEY `createdate_idx` (`createdate`)
) ENGINE=InnoDB AUTO_INCREMENT=6564036 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `tb_bh_channelinfo_01` */

DROP TABLE IF EXISTS `tb_bh_channelinfo_01`;

CREATE TABLE `tb_bh_channelinfo_01` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channelkey` varchar(100) DEFAULT NULL COMMENT '渠道列表key',
  `logininfo` varchar(1000) DEFAULT NULL COMMENT '登录信息',
  `remark` varchar(5000) DEFAULT NULL COMMENT '备用信息',
  `createdate` bigint(10) unsigned DEFAULT NULL COMMENT '创建日期',
  `updatedate` bigint(10) unsigned DEFAULT NULL COMMENT '更新日期',
  `proxyUrl` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `channelkey_idx` (`channelkey`),
  KEY `createdate_idx` (`createdate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `tb_bh_channelinfo_latest` */

DROP TABLE IF EXISTS `tb_bh_channelinfo_latest`;

CREATE TABLE `tb_bh_channelinfo_latest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channelkey` varchar(100) DEFAULT NULL COMMENT '渠道列表key',
  `logininfo` varchar(1000) DEFAULT NULL COMMENT '登录信息',
  `remark` varchar(5000) DEFAULT NULL COMMENT '备用信息',
  `createdate` bigint(10) unsigned DEFAULT NULL COMMENT '创建日期',
  `updatedate` bigint(10) unsigned DEFAULT NULL COMMENT '更新日期',
  `proxyUrl` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `channelkey_idx` (`channelkey`),
  KEY `createdate_idx` (`createdate`)
) ENGINE=InnoDB AUTO_INCREMENT=1340 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `tb_bh_dbcache` */

DROP TABLE IF EXISTS `tb_bh_dbcache`;

CREATE TABLE `tb_bh_dbcache` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dbkey` varchar(200) DEFAULT NULL COMMENT '数据库Key',
  `dbcontent` varchar(10000) DEFAULT NULL COMMENT '数据库Content',
  `createdate` bigint(20) unsigned DEFAULT NULL COMMENT '创建时间',
  `updatedate` bigint(20) unsigned DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `tb_bh_imgquestion` */

DROP TABLE IF EXISTS `tb_bh_imgquestion`;

CREATE TABLE `tb_bh_imgquestion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channelkey` varchar(100) DEFAULT NULL COMMENT '渠道key',
  `guid` varchar(100) DEFAULT NULL COMMENT 'guid（标记请求唯一标记）',
  `imgdata` varchar(5000) DEFAULT NULL COMMENT '图片原始数据',
  `imgurl` varchar(100) DEFAULT NULL COMMENT '图片url(如存入本地需要)',
  `imganswer` varchar(100) DEFAULT NULL COMMENT '图片结果',
  `imganswerstatus` varchar(100) DEFAULT NULL COMMENT '图片识别结果（是否正确）1 未识别 2 识别错误,不允许识别 3 图片更新，再次识别',
  `imgoperatestatus` varchar(100) DEFAULT NULL COMMENT '操作状态（如正在操作中）',
  `imgcreatedate` bigint(20) unsigned DEFAULT NULL COMMENT '生成时间',
  `imgupdatedate` bigint(20) unsigned DEFAULT NULL COMMENT '更新时间',
  `remark1` varchar(100) DEFAULT NULL COMMENT '备注1',
  `remark2` varchar(100) DEFAULT NULL COMMENT '备注2',
  `remark3` varchar(100) DEFAULT NULL COMMENT '备注3',
  `remark4` varchar(100) DEFAULT NULL COMMENT '备注4',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `tb_bh_note` */

DROP TABLE IF EXISTS `tb_bh_note`;

CREATE TABLE `tb_bh_note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createuser` varchar(100) DEFAULT NULL,
  `createdate` bigint(20) unsigned DEFAULT NULL,
  `updatedate` bigint(20) unsigned DEFAULT NULL,
  `content` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `tb_bh_version` */

DROP TABLE IF EXISTS `tb_bh_version`;

CREATE TABLE `tb_bh_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version_code` varchar(100) DEFAULT NULL COMMENT '版本类型',
  `version_desc` varchar(200) DEFAULT NULL COMMENT '版本描述',
  `version_status` varchar(10) DEFAULT NULL COMMENT '版本状态',
  `createdate` bigint(20) unsigned DEFAULT NULL COMMENT '创建时间',
  `updatedate` bigint(20) unsigned DEFAULT NULL COMMENT '更新时间',
  `remark1` varchar(500) DEFAULT NULL COMMENT '备注1',
  `remark2` varchar(500) DEFAULT NULL COMMENT '备注2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `tb_file` */

DROP TABLE IF EXISTS `tb_file`;

CREATE TABLE `tb_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fileName` varchar(100) DEFAULT NULL,
  `fileStatus` int(11) DEFAULT NULL,
  `remark1` varchar(1000) DEFAULT NULL,
  `remark2` varchar(1000) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  `httpFilePath` varchar(255) DEFAULT NULL,
  `userCode` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=378 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `tb_image` */

DROP TABLE IF EXISTS `tb_image`;

CREATE TABLE `tb_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `caseId` varchar(100) DEFAULT NULL COMMENT '案件编号',
  `imgName` varchar(100) DEFAULT NULL COMMENT '图片名',
  `imgPath` varchar(200) DEFAULT NULL COMMENT '图片路径',
  `delStatus` varchar(10) DEFAULT NULL COMMENT '删除状态',
  `imageDate` varchar(100) DEFAULT NULL COMMENT '日期',
  `info1` varchar(200) DEFAULT NULL COMMENT '备用1',
  `info2` varchar(200) DEFAULT NULL COMMENT '备用2',
  `info3` varchar(200) DEFAULT NULL COMMENT '备用3',
  `info4` varchar(200) DEFAULT NULL COMMENT '备用4',
  `tips` varchar(1000) DEFAULT NULL COMMENT '行提示',
  `imgResult` text COMMENT '图片对应Json信息',
  `imgResultModelClass` varchar(200) DEFAULT NULL COMMENT '图片对应反射Class',
  `imgResultDate` varchar(50) DEFAULT NULL COMMENT '图片结果返回时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1147 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_image_case_info` */

DROP TABLE IF EXISTS `tb_image_case_info`;

CREATE TABLE `tb_image_case_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `caseId` varchar(100) DEFAULT NULL COMMENT '案件编号',
  `caseStatus` varchar(50) DEFAULT NULL COMMENT '案件状态',
  `caseDate` varchar(100) DEFAULT NULL COMMENT '案件日期',
  `userCode` varchar(20) DEFAULT NULL COMMENT '用户信息',
  `info1` varchar(200) DEFAULT NULL COMMENT '备用1',
  `info2` varchar(200) DEFAULT NULL COMMENT '备用2',
  `info3` varchar(200) DEFAULT NULL COMMENT '备用3',
  `info4` varchar(200) DEFAULT NULL COMMENT '备用4',
  `tips` varchar(1000) DEFAULT NULL COMMENT '提示',
  `result` text COMMENT '解析结果',
  `resultDate` varchar(50) DEFAULT NULL COMMENT '解析结果返回时间',
  `apiSign` char(1) DEFAULT NULL COMMENT 'api推送标记,用户标记是否需要推送',
  `apiPushSign` char(1) DEFAULT NULL COMMENT 'api推送标记,标记是否已经推送完成',
  `apiPushData` varchar(100) DEFAULT NULL COMMENT 'api推送标记,推送完成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=513 DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) CHARACTER SET utf8 NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `usernameAlias` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `info1` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `info2` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
