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

Date: 2018-08-30 17:48:37
*/


-- ----------------------------
-- Table structure for SM_C_TASK
-- ----------------------------
DROP TABLE "SITFQG"."SM_C_TASK";
CREATE TABLE "SITFQG"."SM_C_TASK" (
"ID" NUMBER NOT NULL ,
"NAME" VARCHAR2(255 BYTE) NULL ,
"BATCH_NAME" VARCHAR2(255 BYTE) NULL ,
"STATUS" VARCHAR2(255 BYTE) NULL ,
"TYPE" NUMBER(3) NULL ,
"EXECUTE_TIME" DATE NULL ,
"CONTENT" VARCHAR2(255 BYTE) NULL ,
"EXT1" VARCHAR2(255 BYTE) NULL ,
"EXT2" VARCHAR2(255 BYTE) NULL ,
"CREATE_TIME" DATE NULL ,
"UPDATE_TIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SITFQG"."SM_C_TASK"."BATCH_NAME" IS '对应批次';
COMMENT ON COLUMN "SITFQG"."SM_C_TASK"."STATUS" IS '推送状态，0:未开启，1:待执行，2:已执行，3:执行失败';
COMMENT ON COLUMN "SITFQG"."SM_C_TASK"."TYPE" IS '消息类型，1:文字，2：图文';

-- ----------------------------
-- Indexes structure for table SM_C_TASK
-- ----------------------------

-- ----------------------------
-- Triggers structure for table SM_C_TASK
-- ----------------------------
CREATE OR REPLACE TRIGGER "SITFQG"."SM_C_TASK_TRIGGER" BEFORE INSERT ON "SITFQG"."SM_C_TASK" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW ENABLE WHEN (new.ID is null)
begin  
select SM_C_TASK_SEQUENCE.nextval into:new.ID from dual;  
end;
-- ----------------------------
-- Checks structure for table SM_C_TASK
-- ----------------------------
ALTER TABLE "SITFQG"."SM_C_TASK" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SM_C_TASK
-- ----------------------------
ALTER TABLE "SITFQG"."SM_C_TASK" ADD PRIMARY KEY ("ID");
