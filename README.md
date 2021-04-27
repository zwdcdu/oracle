# Oracle 数据库应用

## 内容说明

- PPT目录：各章节的PPT
- script目录：各章节的SQL语句及源码
- doc目录：参考文档
- book.pdf： 本书的PDF文档
- tools/gitgfb_ttar.rar -windows中运行git命令工具

## 实验服务器地址

```flow js
地址：202.115.82.8
数据库：pdborcl
system密码:123，所有密码都为123

成绩网址：http://202.115.82.8:1522
```

## git命令环境下载

在Windows上登录需要ssh客户端，可以下载安装git Bash:
[https://github.com/zwdcdu/oracle/raw/master/tools/gitgfb_ttrar.rar]

## SSH登录及连接Oracle测试

linux中的用户student密码是123

```shell
$ ssh student@202.115.82.8
student@202.115.82.8's password:
[student@deep02 ~]$cat readme.txt
```

- 登录linux后连接Oracle，查询表hr.employees。

```sql
[student@deep02 ~]$ sqlplus system/123@202.115.82.8/pdborcl
sqlplus 你的用户名/123@pdborcl
SQL> select * from hr.employees;
```

- sys连接
  - [student@deep02 ~]$ sqlplus sys/123@202.115.82.8/orcl as sysdba

- hr用户连接
  - [student@deep02 ~]$ sqlplus hr/123@202.115.82.8/pdborcl

## 工具文件下载

- 在Windows端运行git bash后，下载sqldeveloper和Oracle12c安装文件

```shellv
scp student@202.115.82.8:~/tools/sqldeveloper.zip .
scp student@202.115.82.8:~/tools/sqldeveloper7.zip .
scp student@202.115.82.8:~/tools/linuxx64_12201_database.zip .
```

## 网址

- Oracle Database 下载地址:

    http://www.oracle.com/technetwork/database/enterprise-edition/downloads/index.html

- SQL Developer 客户端工具下载地址:

    https://www.oracle.com/technetwork/developer-tools/sql-developer/downloads/index.html

- Oracle 12.2安装示例schema
    https://www.linuxidc.com/Linux/2017-08/146337.htm

- Oracle 12C DataGuard部署以及维护
  - https://blog.csdn.net/kiral07/article/details/86916367
  - [老师的虚拟机实战配置](./doc/Oracle12c%20DataGuard实际配置.md)

## Git命令参考

### create a new repository on the command line

```shell
echo "# oracle" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/zwdcdu/oracle.git
git push -u origin master
```

### push an existing repository from the command line

```shell
git remote add origin https://github.com/zwdcdu/oracle.git
git push -u origin master
```

## 添加student用户

```shell
useradd student -G  dba
usermod -a -G oinstall student
usermod -a -G dba student

$rman target sys/123@202.115.82.8/orcl
RMAN> list backup;
RMAN>
run {
shutdown immediate;
startup mount;
backup database format='/home/oracle/rman_bak/%d_%s.bak';
alter database open;
}

run {
shutdown immediate;
startup mount;
restore database;
recover database;
alter database open resetlogs;
}

SQL> select name from v$controlfile;
/home/oracle/app/oracle/oradata/orcl/control01.ctl
/home/oracle/app/oracle/oradata/orcl/control02.ctl

SQL> select member from v$logfile;
/home/oracle/app/oracle/oradata/orcl/redo03.log
/home/oracle/app/oracle/oradata/orcl/redo02.log
/home/oracle/app/oracle/oradata/orcl/redo01.log

SQL> select file_name from v$datafile;
SQL> select file_name from dba_data_files;
/home/oracle/app/oracle/oradata/orcl/system01.dbf
/home/oracle/app/oracle/oradata/orcl/sysaux01.dbf
/home/oracle/app/oracle/oradata/orcl/undotbs01.dbf
/home/oracle/app/oracle/oradata/orcl/users01.dbf

SQL> show parameter pfile
/home/oracle/app/oracle/product/12.2.0/dbhome_1/dbs/spfileorcl.orav
```

## 在客户端配置TNS

- 新建Windows环境变量：TNS_ADMIN=D:\sqldeveloper\network\admin
- 新建文件：D:\sqldeveloper\network\admin\tnsnames.ora,文件内容如下：

```text
PDBORCL =
  (DESCRIPTION =
    (ADDRESS = (PROTOCOL = TCP)(HOST = 202.115.82.8)(PORT = 1521))
    (CONNECT_DATA =
      (SERVER = DEDICATED)
      (SERVICE_NAME = pdborcl)
    )
  )

ORCL =
  (DESCRIPTION =
    (ADDRESS = (PROTOCOL = TCP)(HOST = 202.115.82.8)(PORT = 1521))
    (CONNECT_DATA =
      (SERVER = DEDICATED)
      (SERVICE_NAME = orcl)
    )
  )
```

## 在sqldeveloper中查询oracle连接进程，终止部分进程

- sys用户
- 菜单：工具->监视会话
- 通过UI界面菜单，或者sql语句完成相应操作

## 启用共享连接

- sys登录
- sqlplus sys/123@localhost/orcl as sysdba

```sql
ALTER SYSTEM SET dispatchers="(PROTOCOL=TCP)(dispatchers=3)"
ALTER SYSTEM SET max_dispatchers=5
ALTER SYSTEM SET shared_servers = 1
ALTER SYSTEM SET max_shared_servers=20
ALTER SYSTEM SET shared_server_sessions=200
show parameter shared_server
```

- hr用户

```shell
共享模式登录测试：
sqlplus hr/123@localhost/pdborcl:shared
ps -ef  | grep ora_d[0-9].*[_orcl$]

专用模式登录测试：
sqlplus hr/123@localhost/pdborcl
ps -ef | grep oracleorcl

查看监听状态：
lsnrctl service
lsnrctl status
```

## 收集表的统计信息

- system登录到pdborcl

```sql

CREATE TABLE hr.emp_test as SELECT * FROM hr.employees;
INSERT INTO hr.emp_test SELECT * FROM hr.employees;
INSERT INTO hr.emp_test SELECT * FROM hr.employees;
INSERT INTO hr.emp_test SELECT * FROM hr.employees;

select count(*) from hr.emp_test;

--统计前
explain plan for SELECT * FROM hr.emp_test WHERE  employee_id=110;
SELECT * FROM TABLE(dbms_xplan.display);
--rows = 1，这是错误的基数

------------------------------------------------------------------------------
| Id  | Operation         | Name     | Rows  | Bytes | Cost (%CPU)| Time     |
------------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |          |     1 |    69 |     3   (0)| 00:00:01 |
|*  1 |  TABLE ACCESS FULL| EMP_TEST |     1 |    69 |     3   (0)| 00:00:01 |
------------------------------------------------------------------------------

--统计后，让数据库感知表hr.emp_test记录数量的变化 
EXEC DBMS_STATS.GATHER_TABLE_STATS('HR','EMP_TEST');
explain plan for SELECT * FROM hr.emp_test WHERE  employee_id=110;
SELECT * FROM TABLE(dbms_xplan.display);
--rows = 4 这是正确的基数，有利于构建正确的计划

------------------------------------------------------------------------------
| Id  | Operation         | Name     | Rows  | Bytes | Cost (%CPU)| Time     |
------------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |          |     4 |   276 |     6   (0)| 00:00:01 |
|*  1 |  TABLE ACCESS FULL| EMP_TEST |     4 |   276 |     6   (0)| 00:00:01 |
------------------------------------------------------------------------------

--实验完成后：
drop table hr.emp_test;

```


## 从pdborcl创建可插接数据库

新数据库存入：/home/student/pdb/新数据库名称

```sql
$ sqlplus / as sysdba
替代命令：
$ sqlplus sys/123@202.115.82.8/orcl as sysdba

查看创建前的数据库：
show pdbs

--1.关闭pdborcl
ALTER PLUGGABLE DATABASE pdborcl CLOSE immediate;
--或者先切换到pdborcl：
alter session set container=pdborcl
shutdown immediate
--切换数据库，回到CDB
alter session set container=cdb$root

--2.只读方式打开pdborcl
ALTER PLUGGABLE DATABASE pdborcl OPEN READ ONLY;

--3.创建数据库clonedb
CREATE PLUGGABLE DATABASE clonedb FROM pdborcl file_name_convert=('/home/oracle/app/oracle/oradata/orcl/pdborcl'，'/home/student/pdb/clonedb');

--4.打开新数据库
ALTER PLUGGABLE DATABASE clonedb OPEN;
--查看新数据库
show pdbs;

--5.创建成功后，重新打开pdborcl为读写状态
ALTER PLUGGABLE DATABASE pdborcl CLOSE immediate;
ALTER PLUGGABLE DATABASE pdborcl OPEN;

--6.测试
--创建成功后,退出sys用户，以hr登录到新数据库,测试一下
$ sqlplus hr/123@202.115.82.8/clonedb 
--查看数据库相关文件
$ ls /home/student/pdb/clonedb

--7.删除新数据库（可选）
--重新sys登录,删除新增的数据库
DROP PLUGGABLE DATABASE clonedb INCLUDING DATAFILES;
```

## 从pdborcl创建可插接数据库,简单流程

- 以yourdb为源数据库，yourdb已经打开为read only
- 将zhang改为自己的数据库名称

```sql
$ sqlplus sys/123@202.115.82.8/orcl as sysdba
CREATE PLUGGABLE DATABASE zhang1 FROM yourdb file_name_convert=('/home/student/pdb/yourdb'，'/home/student/pdb/ zhang1 ');
--4.打开新数据库
ALTER PLUGGABLE DATABASE zhang OPEN;
--查看新数据库
show pdbs;
--6.测试
--创建成功后,退出sys用户，以hr登录到新数据库,测试一下
$ sqlplus hr/123@202.115.82.8/ zhang 
--查看数据库相关文件
$ ls /home/student/pdb/ zhang
--7 删除pdb
ALTER PLUGGABLE DATABASE zhang close;
Drop pluggable database zhang including datafiles;

```

## 表空间命令

--查看表空间大小：
 select tablespace_name,sum(bytes)/1024/1024 from dba_data_files group by tablespace_name;

--查看表空间已使用大小：

 select tablespace_name,sum(bytes)/1024/1024 from dba_free_space group by tablespace_name;
 
 --查看表空间所在的文件地址
select * from dba_data_files

--查看表空间下的表
select table_name, tablespace_name,OWNER from dba_tables where tablespace_name = 'USERS';

--查看表的大小
select segment_name,segment_type,sum(bytes/1024/1024) from dba_segments
where segment_type='TABLE'
and segment_name = 'JOBS'
group by segment_name, segment_type;

## In_Memory

```sql
--创建表sales，插入100万条记录
CREATE TABLE sales
   (ID NUMBER primary key, 
	NAME VARCHAR2(50 BYTE) not null, 
	QUANTITY NUMBER(8,0), 
	PRICE NUMBER(8,0)
);

--插入100万行数据
declare 
v1 number;
v2 number;
begin
    delete from sales;
    for i in 1..1000000
    loop
        v1:=dbms_random.value(1,90);
        v2:=dbms_random.value(100,900);
        insert into sales(id,name,quantity,price) values (i,'name'||i,v1,v2);
    end loop;
    commit;
end;

插入过程中，如果遇到超出表空间 'USERS' 的空间限额，可以执行：
alter user 你的用户名 quota unlimited on users;

--进行IN-Memory前后的查询对比

--in-memory前：
--两次执行:
set autotrace on TRACEONLY 
select sum(quantity*price) total from sales;

--in-memory：
set autotrace on
alter table sales inmemory;

--in-memory后：
--两次执行:
select sum(quantity*price) total from sales;

观察consistent gets的数量，越少越快。

--查询总金额


```

## 无法启动的错误：ORA-01157: 无法标识/锁定数据文件 414 的解决办法

```sql
startup mount
alter database datafile 414 offline drop;
recover database;
alter database open;
```

## git push 错误

```shell
git push
fatal: unable to access 'https://github.com/zwdcdu/oracle.git/': OpenSSL SSL_connect: SSL_ERROR_SYSCALL in connection to github.com:443 
## git config --global http.sslVerify "false"
```

## Oracle切换归档/非归档模式

- sys login CDB:
sqlplus / as sysdba

- 查看归档/非归档模式:
select name, log_mode from v$database;  或者 archive log list;

- 开始切换，首先关闭数据库
shutdown immediate;

- 将数据库切换为非归档模式/归档模式
alter database noarchivelog; 或者 alter database archivelog;

- 打开数据库
alter database open;

## 数据库导出expdp

```text
step1: system登录，创建目录，授权给自己用户
[student@deep02 ~]$ sqlplus system/123@202.115.82.8/pdborcl
SQL>create or replace directory expdir as '/home/student/pdborcl_expdir';
目录已创建。
SQL> grant read,write on directory expdir to zwd;
授权成功。
SQL>exit

step2: 自己用户备份
[student@deep02 pdborcl_expdir]$ expdp zwd/123@202.115.82.8/pdborcl directory=expdir dumpfile=zwd.dmp

Export: Release 12.2.0.1.0 - Production on 星期日 4月 25 13:46:12 2021

Copyright (c) 1982, 2017, Oracle and/or its affiliates.  All rights reserved.

连接到: Oracle Database 12c Enterprise Edition Release 12.2.0.1.0 - 64bit Production
启动 "ZWD"."SYS_EXPORT_SCHEMA_01":  zwd/********@202.115.82.8/pdborcl directory=expdir dumpfile=zwd.dmp
处理对象类型 SCHEMA_EXPORT/TABLE/TABLE_DATA
处理对象类型 SCHEMA_EXPORT/TABLE/INDEX/STATISTICS/INDEX_STATISTICS
处理对象类型 SCHEMA_EXPORT/TABLE/STATISTICS/TABLE_STATISTICS
处理对象类型 SCHEMA_EXPORT/STATISTICS/MARKER
处理对象类型 SCHEMA_EXPORT/PRE_SCHEMA/PROCACT_SCHEMA
处理对象类型 SCHEMA_EXPORT/SEQUENCE/SEQUENCE
处理对象类型 SCHEMA_EXPORT/TABLE/TABLE
处理对象类型 SCHEMA_EXPORT/TABLE/COMMENT
处理对象类型 SCHEMA_EXPORT/VIEW/VIEW
处理对象类型 SCHEMA_EXPORT/TABLE/INDEX/INDEX
处理对象类型 SCHEMA_EXPORT/TABLE/CONSTRAINT/CONSTRAINT
处理对象类型 SCHEMA_EXPORT/TABLE/CONSTRAINT/REF_CONSTRAINT
处理对象类型 SCHEMA_EXPORT/TABLE/TRIGGER
. . 导出了 "ZWD"."ORDERS":"PARTITION_BEFORE_2016"      268.4 KB    5000 行
. . 导出了 "ZWD"."ORDERS":"PARTITION_BEFORE_2017"      268.5 KB    5000 行
. . 导出了 "ZWD"."EMPLOYEES"                           8.859 KB       7 行
. . 导出了 "ZWD"."PRODUCTS"                            5.656 KB       9 行
. . 导出了 "ZWD"."DEPARTMENTS"                         5.593 KB       3 行
. . 导出了 "ZWD"."ORDERS":"PARTITION_BEFORE_2018"          0 KB       0 行
. . 导出了 "ZWD"."ORDER_DETAILS":"PARTITION_BEFORE_2016"  425.7 KB   15000 行
. . 导出了 "ZWD"."ORDER_DETAILS":"PARTITION_BEFORE_2017"  426.1 KB   15000 行
. . 导出了 "ZWD"."ORDER_DETAILS":"PARTITION_BEFORE_2018"      0 KB       0 行
已成功加载/卸载了主表 "ZWD"."SYS_EXPORT_SCHEMA_01"
******************************************************************************
ZWD.SYS_EXPORT_SCHEMA_01 的转储文件集为:
  /home/student/pdborcl_expdir/zwd.dmp
作业 "ZWD"."SYS_EXPORT_SCHEMA_01" 已于 星期日 4月 25 13:47:13 2021 elapsed 0 00:01:01 成功完成

step3:查看备份结果
[student@deep02 pdborcl_expdir]$ ls /home/student/pdborcl_expdir/
export.log  zwd.dmp

```

## 数据库导入impdp

```text
step1: 删除表

[student@deep02 ~]$ sqlplus zwd/123@pdborcl
SQL*Plus: Release 12.2.0.1.0 Production on 星期日 4月 25 13:56:19 2021
Copyright (c) 1982, 2016, Oracle.  All rights reserved.
上次成功登录时间: 星期日 4月  25 2021 13:54:45 +08:00
连接到:
Oracle Database 12c Enterprise Edition Release 12.2.0.1.0 - 64bit Production
SQL> drop table order_details;
表已删除。
SQL> drop table orders;
表已删除。
SQL> exit

step2: 恢复数据
[student@deep02 ~]$ impdp zwd/123@202.115.82.8/pdborcl directory=expdir dumpfile=zwd.dmp

Import: Release 12.2.0.1.0 - Production on 星期日 4月 25 13:57:34 2021

Copyright (c) 1982, 2017, Oracle and/or its affiliates.  All rights reserved.

连接到: Oracle Database 12c Enterprise Edition Release 12.2.0.1.0 - 64bit Production
已成功加载/卸载了主表 "ZWD"."SYS_IMPORT_FULL_01"
启动 "ZWD"."SYS_IMPORT_FULL_01":  zwd/********@202.115.82.8/pdborcl directory=expdir dumpfile=zwd.dmp
处理对象类型 SCHEMA_EXPORT/PRE_SCHEMA/PROCACT_SCHEMA
处理对象类型 SCHEMA_EXPORT/SEQUENCE/SEQUENCE
ORA-31684: 对象类型 SEQUENCE:"ZWD"."SEQ_ORDER_ID" 已存在

ORA-31684: 对象类型 SEQUENCE:"ZWD"."SEQ_ORDER_DETAILS_ID" 已存在

处理对象类型 SCHEMA_EXPORT/TABLE/TABLE
ORA-39151: 表 "ZWD"."DEPARTMENTS" 已存在。由于跳过了 table_exists_action, 将跳过所有相关元数据和数据。

ORA-39151: 表 "ZWD"."PRODUCTS" 已存在。由于跳过了 table_exists_action, 将跳过所有相关元数据和数据。

ORA-39151: 表 "ZWD"."EMPLOYEES" 已存在。由于跳过了 table_exists_action, 将跳过所有相关元数据和数据。

ORA-39151: 表 "ZWD"."ORDER_ID_TEMP" 已存在。由于跳过了 table_exists_action, 将跳过所有相关元数据和数据。

处理对象类型 SCHEMA_EXPORT/TABLE/TABLE_DATA
. . 导入了 "ZWD"."ORDERS":"PARTITION_BEFORE_2016"      268.4 KB    5000 行
. . 导入了 "ZWD"."ORDERS":"PARTITION_BEFORE_2017"      268.5 KB    5000 行
. . 导入了 "ZWD"."ORDERS":"PARTITION_BEFORE_2018"          0 KB       0 行
. . 导入了 "ZWD"."ORDER_DETAILS":"PARTITION_BEFORE_2016"  425.7 KB   15000 行
. . 导入了 "ZWD"."ORDER_DETAILS":"PARTITION_BEFORE_2017"  426.1 KB   15000 行
. . 导入了 "ZWD"."ORDER_DETAILS":"PARTITION_BEFORE_2018"      0 KB       0 行
处理对象类型 SCHEMA_EXPORT/TABLE/COMMENT
处理对象类型 SCHEMA_EXPORT/VIEW/VIEW
ORA-31684: 对象类型 VIEW:"ZWD"."VIEW_ORDER_DETAILS" 已存在

处理对象类型 SCHEMA_EXPORT/TABLE/INDEX/INDEX
处理对象类型 SCHEMA_EXPORT/TABLE/CONSTRAINT/CONSTRAINT
处理对象类型 SCHEMA_EXPORT/TABLE/INDEX/STATISTICS/INDEX_STATISTICS
处理对象类型 SCHEMA_EXPORT/TABLE/CONSTRAINT/REF_CONSTRAINT
处理对象类型 SCHEMA_EXPORT/TABLE/TRIGGER
处理对象类型 SCHEMA_EXPORT/TABLE/STATISTICS/TABLE_STATISTICS
处理对象类型 SCHEMA_EXPORT/STATISTICS/MARKER
作业 "ZWD"."SYS_IMPORT_FULL_01" 已经完成, 但是有 7 个错误 (于 星期日 4月 25 13:57:40 2021 elapsed 0 00:00:06 完成)

step3:查看恢复结果

[student@deep02 ~]$ sqlplus zwd/123@pdborcl

SQL*Plus: Release 12.2.0.1.0 Production on 星期日 4月 25 13:58:26 2021

Copyright (c) 1982, 2016, Oracle.  All rights reserved.

上次成功登录时间: 星期日 4月  25 2021 13:57:34 +08:00

连接到:
Oracle Database 12c Enterprise Edition Release 12.2.0.1.0 - 64bit Production

SQL> select count(*) from orders;

  COUNT(*)
----------
     10000

SQL> select count(*) from order_details;

  COUNT(*)
----------
     30000

SQL>

```

## 备份与恢复

- 查看全库所有需要备份的相关文件

```sql
$sqlplus sys/123@202.115.82.8/ly as sysdba
SELECT NAME FROM v$datafile
UNION ALL
SELECT MEMBER AS NAME FROM v$logfile
UNION ALL
SELECT NAME FROM v$controlfile;
```

- 查看一个pdb数据库的数据文件,以ly为例

```sql
$sqlplus system/123@202.115.82.8/ly
SELECT NAME FROM v$datafile
UNION ALL
SELECT MEMBER AS NAME FROM v$logfile
UNION ALL
SELECT NAME FROM v$controlfile;
```

## 全库1级增量备份（每天作一次）

run{
configure retention policy to redundancy 1;
configure controlfile autobackup on;
configure controlfile autobackup format for device type disk to '/home/student/rman_backup/%F';
configure default device type to disk;
crosscheck backup;
crosscheck archivelog all;
allocate channel c1 device type disk;
allocate channel c2 device type disk;
allocate channel c3 device type disk;
backup incremental level 1 database format '/home/student/rman_backup/level1_%d_%T_%U.bak';
report obsolete;
delete noprompt obsolete;
delete noprompt expired backup;
delete noprompt expired archivelog all;
release channel c1;
release channel c2;
release channel c3;
}

## 全库完全恢复

- oracle登录linux,不是student用户
- 需要全库停机，需要oracle用户
- sys登录到orcl，查看全库的数据文件
$ sqlplus / as sysdba
SQL> select file_name from dba_data_files;

- 全库停机
$rman target /
RMAN> shutdown immediate;  或者 shutdown abort;
RMAN> exit

- 数据文件改名，模拟文件损失
$mv /home/student/pdb_ly/pdbtest_users02_1.dbf  /home/student/pdb_ly/pdbtest_users02_1.dbf2

- 全库恢复
$rman target /
RMAN> startup mount;
RMAN> restore database;
RMAN> recover pluggable;
RMAN> alter database open;

## pdb完全恢复,从ly为例

```text
- student登录linux
- 不需要全库停机，只需要待恢复pdb停机，不需要oracle用户
- 前提是已经作了全库备份或者ly的单独备份
- 假设ly数据库中有hr用户，hr用户中有表mytable

- system登录到ly，查看ly的数据文件
$ sqlplus system/123@202.115.82.8/ly
SQL> select file_name from dba_data_files;
/home/student/pdb/ ly /system01.dbf
/home/student/pdb/ ly /sysaux01.dbf
/home/student/pdb/ ly /undotbs01.dbf
/home/student/pdb/ ly /users01.dbf
/home/student/pdb/pdbtest_users02_1.dbf
/home/student/pdb/pdbtest_users02_2.dbf
/home/student/pdb_ly/pdbtest_users02_1.dbf
/home/student/pdb_ly/pdbtest_users02_2.dbf
SQL> select * from hr.mytable;
        ID NAME
---------- --------------------------------------------------
         3 zhang
         4 wang
         5 abc
SQL> select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') as currentdate from dual;
CURRENTDATE
-------------------
2021-04-27 08:02:24
SQL> update hr.mytable set id=id+1;
SQL> commit;
SQL> select * from hr.mytable;
        ID NAME
---------- --------------------------------------------------
         4 zhang
         5 wang
         6 abc
SQL> select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') as currentdate from dual;
CURRENTDATE
-------------------
2021-04-27 08:03:01

SQL> exit;

- 关闭ly数据库

$rman target sys/123@202.115.82.8/orcl:dedicated
RMAN> alter pluggable database ly close;
RMAN> exit;

- 数据文件改名，模拟文件损失
$mv -f /home/student/pdb_ly/pdbtest_users02_1.dbf  /home/student/pdb_ly/pdbtest_users02_1.dbf2

- 选项1：pdb开始完全恢复
$rman target sys/123@202.115.82.8/orcl:dedicated
RMAN> restore pluggable database ly;
RMAN> recover pluggable database ly;
RMAN> alter pluggable database ly open;
RMAN> exit;

- 完全恢复成功后，hr用户登录ly，
$ sqlplus hr/123@202.115.82.8/ly
SQL> select * from mytable;
        ID NAME
---------- --------------------------------------------------
         4 zhang
         5 wang
         6 abc

可见，完全恢复成功，数据是最新的（即2021-04-27 08:03:01），无损失。



## 选项2：pdb不完全恢复,恢复到update语句之前的状态，即恢复到2021-04-27 08:02:24时刻的数据
$rman target sys/123@202.115.82.8/orcl:dedicated
RMAN> restore pluggable database ly;
RMAN> recover pluggable database ly until time "to_date('2021-04-27 08:02:24','yyyy-mm-dd hh24:mi:ss')" AUXILIARY DESTINATION '/home/student/zwd';
正在开始介质的恢复
线程 1 序列 1624 的归档日志已作为文件 /home/oracle/app/oracle/product/12.2.0/dbhome_1/dbs/arch1_1624_1064951903.dbf 存在于磁盘上
线程 1 序列 1625 的归档日志已作为文件 /home/oracle/app/oracle/product/12.2.0/dbhome_1/dbs/arch1_1625_1064951903.dbf 存在于磁盘上
RMAN> alter pluggable database ly open resetlogs;
已处理语句
RMAN>exit

- 不完全恢复成功后，hr用户登录ly，
$ sqlplus hr/123@202.115.82.8/ly
SQL> select * from mytable;
        ID NAME
---------- --------------------------------------------------
         3 zhang
         4 wang
         5 abc

可见，不完全恢复成功，数据回到了修改前的状态（即：2021-04-27 08:02:24）。

```
