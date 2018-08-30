/*
Navicat Oracle Data Transfer
Oracle Client Version : 11.2.0.1.0

Source Server         : 贷超dev5
Source Server Version : 110200
Source Host           : 10.83.24.5:1521
Source Schema         : SITFQG

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

Date: 2018-08-30 17:48:51
*/


-- ----------------------------
-- Table structure for SM_C_USER
-- ----------------------------
DROP TABLE "SITFQG"."SM_C_USER";
CREATE TABLE "SITFQG"."SM_C_USER" (
"ID" NUMBER NOT NULL ,
"MOBILE" NUMBER(11) NULL ,
"NAME" VARCHAR2(100 BYTE) NULL ,
"OPEN_ID" VARCHAR2(400 BYTE) NULL ,
"BATCH_NAME" VARCHAR2(100 BYTE) NULL ,
"CREATE_TIME" DATE NULL ,
"SEX" NUMBER(2) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SITFQG"."SM_C_USER"."SEX" IS '1:男,2:女';

-- ----------------------------
-- Indexes structure for table SM_C_USER
-- ----------------------------

-- ----------------------------
-- Triggers structure for table SM_C_USER
-- ----------------------------
CREATE OR REPLACE TRIGGER "SITFQG"."SM_C_USER_TRIGGER" BEFORE INSERT ON "SITFQG"."SM_C_USER" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW ENABLE WHEN (new.ID is null)
begin  
select SM_C_USER_SEQUENCE.nextval into:new.ID from dual;  
end;
-- ----------------------------
-- Checks structure for table SM_C_USER
-- ----------------------------
ALTER TABLE "SITFQG"."SM_C_USER" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SM_C_USER
-- ----------------------------
ALTER TABLE "SITFQG"."SM_C_USER" ADD PRIMARY KEY ("ID");
