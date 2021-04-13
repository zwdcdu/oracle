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
