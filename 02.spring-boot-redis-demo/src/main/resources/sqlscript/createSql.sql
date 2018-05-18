SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `test` ;

CREATE SCHEMA IF NOT EXISTS `test` DEFAULT CHARACTER SET utf8 ;
USE `test` ;

DROP TABLE IF EXISTS `t_demo` ;
CREATE TABLE IF NOT EXISTS `t_demo` (
	id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL,
	content VARCHAR(1024),
	remark VARCHAR(500)
);

INSERT INTO t_demo(name, content) VALUES('xxx', 'xxx content');
INSERT INTO t_demo(name, content) VALUES('yyy', 'yyy content');
