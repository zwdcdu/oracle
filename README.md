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

--关闭pdborcl
ALTER PLUGGABLE DATABASE pdborcl CLOSE;
--或者先切换到pdborcl：
alter session set container=pdborcl
shutdown immediate

--切换数据库，回到CDB
alter session set container=cdb$root

--只读方式打开pdborcl
ALTER PLUGGABLE DATABASE pdborcl OPEN READ ONLY;

--创建数据库clonedb
CREATE PLUGGABLE DATABASE clonedb FROM pdborcl file_name_convert=('/home/oracle/app/oracle/oradata/orcl/pdborcl'，'/home/student/pdb/clonedb');

--创建成功后，重新打开pdborcl为读写状态
ALTER PLUGGABLE DATABASE pdborcl CLOSE;
ALTER PLUGGABLE DATABASE pdborcl OPEN;
--打开新数据库
ALTER PLUGGABLE DATABASE clonedb OPEN;
--查看新数据库
show pdbs;

--创建成功后,退出sys用户，以hr登录到新数据库,测试一下
$ sqlplus hr/123@202.115.82.8/clonedb 

--重新sys登录,删除新增的数据库
DROP PLUGGABLE DATABASE clonedb INCLUDING DATAFILES;
```
