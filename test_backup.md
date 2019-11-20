<!-- markdownlint-disable MD033-->
<!-- 禁止MD033类型的警告 https://www.npmjs.com/package/markdownlint -->

## 全备份和全恢复实验<!-- markdownlint-disable MD033-->
<!-- 禁止MD033类型的警告 https://www.npmjs.com/package/markdownlint -->

本实现使用老师的虚拟机，通过rman_level0.sh和rman_level1.sh脚本对数据库进行全备份和全恢复，在数据库出现异常时候，不损失任何数据！

## 1. 开始全备份
```
[oracle@oracle-pc ~]$ cat rman_level0.sh
[oracle@oracle-pc ~]$ ./rman_level0.sh
```
### 每天定时开始增量备份（可选）
```
[oracle@oracle-pc ~]$ cat rman_level1.sh
[oracle@oracle-pc ~]$ ./rman_level1.sh
```

### 查看备份文件
- *.log是日志文件
- dblv0*.bak是数据库的备份文件
- arclv0*.bak是归档日期的备份文件
- c-1392946895-20191120-01是控制文件和参数的备份
```
[oracle@oracle-pc ~]$ cd rman_backup
[oracle@oracle-pc rman_backup]$ ls
arclv0_ORCL_20191120_dauhb2fm_1_1.bak
c-1392946895-20191120-01
dblv0_ORCL_20191120_d7uhb2ap_1_1.bak
dblv0_ORCL_20191120_d8uhb2c6_1_1.bak
dblv0_ORCL_20191120_d9uhb2ei_1_1.bak
lv0_20191120-083949_L0.log
```

### 查看备份文件的内容

```
[oracle@oracle-pc ~]$ rman target /

RMAN> list backup;
List of Backup Sets
===================
BS Key  Type LV Size       Device Type Elapsed Time Completion Time
------- ---- -- ---------- ----------- ------------ ---------------
275     Incr 0  215.21M    DISK        00:00:36     20-11月-19    
        BP Key: 275   Status: AVAILABLE  Compressed: YES  Tag: TAG20191120T083953
        Piece Name: /home/oracle/rman_backup/dblv0_ORCL_20191120_d7uhb2ap_1_1.bak
  List of Datafiles in backup set 275
  Container ID: 3, PDB Name: PDBORCL
  File LV Type Ckp SCN    Ckp Time   Name
  ---- -- ---- ---------- ---------- ----
  8    0  Incr 48162290   20-11月-19 /home/oracle/app/oracle/oradata/orcl/pdborcl/system01.dbf
  9    0  Incr 48162290   20-11月-19 /home/oracle/app/oracle/oradata/orcl/pdborcl/sysaux01.dbf
  10   0  Incr 48162290   20-11月-19 /home/oracle/app/oracle/oradata/orcl/pdborcl/SAMPLE_SCHEMA_users01.dbf
  11   0  Incr 48162290   20-11月-19 /home/oracle/app/oracle/oradata/orcl/pdborcl/example01.dbf
  12   0  Incr 48162290   20-11月-19 /home/oracle/app/oracle/oradata/orcl/pdborcl/pdbtest_users02_1.dbf
  13   0  Incr 48162290   20-11月-19 /home/oracle/app/oracle/oradata/orcl/pdborcl/pdbtest_users02_2.dbf
  16   0  Incr 48162290   20-11月-19 /home/oracle/app/oracle/oradata/orcl/pdborcl/pdbtest_users02_3.dbf
  17   0  Incr 48162290   20-11月-19 /home/oracle/app/oracle/oradata/orcl/pdborcl/pdbtest_users02_4.dbf
  77   0  Incr 48162290   20-11月-19 /home/oracle/app/oracle/oradata/orcl/pdborcl/pdbtest_users03_1.dbf
  78   0  Incr 48162290   20-11月-19 /home/oracle/app/oracle/oradata/orcl/pdborcl/pdbtest_users03_2.dbf

BS Key  Type LV Size       Device Type Elapsed Time Completion Time
------- ---- -- ---------- ----------- ------------ ---------------
276     Incr 0  368.46M    DISK        00:01:12     20-11月-19    
        BP Key: 276   Status: AVAILABLE  Compressed: YES  Tag: TAG20191120T083953
        Piece Name: /home/oracle/rman_backup/dblv0_ORCL_20191120_d8uhb2c6_1_1.bak
  List of Datafiles in backup set 276
  File LV Type Ckp SCN    Ckp Time   Name
  ---- -- ---- ---------- ---------- ----
  1    0  Incr 48162395   20-11月-19 /home/oracle/app/oracle/oradata/orcl/system01.dbf
  3    0  Incr 48162395   20-11月-19 /home/oracle/app/oracle/oradata/orcl/sysaux01.dbf
  4    0  Incr 48162395   20-11月-19 /home/oracle/app/oracle/oradata/orcl/undotbs01.dbf
  6    0  Incr 48162395   20-11月-19 /home/oracle/app/oracle/oradata/orcl/users01.dbf

BS Key  Type LV Size       Device Type Elapsed Time Completion Time
------- ---- -- ---------- ----------- ------------ ---------------
277     Incr 0  157.08M    DISK        00:00:26     20-11月-19    
        BP Key: 277   Status: AVAILABLE  Compressed: YES  Tag: TAG20191120T083953
        Piece Name: /home/oracle/rman_backup/dblv0_ORCL_20191120_d9uhb2ei_1_1.bak
  List of Datafiles in backup set 277
  Container ID: 2, PDB Name: PDB$SEED
  File LV Type Ckp SCN    Ckp Time   Name
  ---- -- ---- ---------- ---------- ----
  5    0  Incr 1819408    01-12月-14 /home/oracle/app/oracle/oradata/orcl/pdbseed/system01.dbf
  7    0  Incr 1819408    01-12月-14 /home/oracle/app/oracle/oradata/orcl/pdbseed/sysaux01.dbf

BS Key  Size       Device Type Elapsed Time Completion Time
------- ---------- ----------- ------------ ---------------
278     103.50K    DISK        00:00:00     20-11月-19    
        BP Key: 278   Status: AVAILABLE  Compressed: YES  Tag: TAG20191120T084230
        Piece Name: /home/oracle/rman_backup/arclv0_ORCL_20191120_dauhb2fm_1_1.bak

  List of Archived Logs in backup set 278
  Thrd Seq     Low SCN    Low Time   Next SCN   Next Time
  ---- ------- ---------- ---------- ---------- ---------
  1    1679    48162279   20-11月-19 48162608   20-11月-19

BS Key  Type LV Size       Device Type Elapsed Time Completion Time
------- ---- -- ---------- ----------- ------------ ---------------
279     Full    17.77M     DISK        00:00:01     20-11月-19    
        BP Key: 279   Status: AVAILABLE  Compressed: NO  Tag: TAG20191120T084231
        Piece Name: /home/oracle/rman_backup/c-1392946895-20191120-01
  SPFILE Included: Modification time: 20-11月-19
  SPFILE db_unique_name: ORCL
  Control File Included: Ckp SCN: 48162622     Ckp time: 20-11月-19

```
### 由上面的"list backup;" 输出可以看出，备份集中的文件内容是：
- /home/oracle/rman_backup/dblv0_ORCL_20191120_d7uhb2ap_1_1.bak 是插接数据库PDBORCL的备份集
- /home/oracle/rman_backup/dblv0_ORCL_20191120_d8uhb2c6_1_1.bak 是ORCL的备份集
- /home/oracle/rman_backup/dblv0_ORCL_20191120_d9uhb2ei_1_1.bak是PDB$SEED的备份集
- /home/oracle/rman_backup/arclv0_ORCL_20191120_dauhb2fm_1_1.bak是归档文件的备份集
- /home/oracle/rman_backup/c-1392946895-20191120-01是参数文件(SPFILE)和控制文件(Control File)的备份集


## 2. 备份后修改数据
```sql
[oracle@oracle-pc ~]$ sqlplus study/123@pdborcl
SQL> create table t1 (id number,name varchar2(50));
Table created.
SQL> insert into t1 values(1,'zhang');
1 row created.
SQL> commit;
Commit complete.
SQL> select * from t1;

        ID NAME
---------- --------------------------------------------------
         1 zhang
SQL> exit
```

## 3. 删除数据库文件，模拟数据库文件损坏
```
[oracle@oracle-pc ~]$ rm /home/oracle/app/oracle/oradata/orcl/pdborcl/SAMPLE_SCHEMA_users01.dbf
```

### 删除数据库文件后修改数据
删除数据文件后，仍然可以增加一条数据。这是因为增加的数据并没有写入数据文件，而是写到了日志文件中。如果增加的数据较多的时候，就会出问题了。
```shell
[oracle@oracle-pc ~]$ sqlplus study/123@pdborcl
SQL> insert into t1 values(2,'wang');
1 row created.
SQL> commit;
Commit complete.
SQL> select * from t1;
        ID NAME
---------- --------------------------------------------------
         1 zhang
         2 wang
SQL> 

SQL> declare
  2  n number;
  3  begin
  4    for n in 1..10000 loop
  5      insert into t1 values(n,'name'||n);
  6    end loop;
  7  end;
  8  /
declare
*
ERROR at line 1:
ORA-01116: 打开数据库文件 10 时出错 ORA-01110:
数据文件 10: '/home/oracle/app/oracle/oradata/orcl/pdborcl/SAMPLE_SCHEMA_users01.dbf'
ORA-27041: 无法打开文件
Linux-x86_64 Error: 2: No such file or directory
Additional information: 3
ORA-06512: 在 line 5

SQL> select * from t1;
        ID NAME
---------- --------------------------------------------------
         1 zhang
         2 wang
SQL> exit
```

## 4. 数据库完全恢复
### 4.1 重启损坏的数据库到mount状态
通过shutdown immediate无法正常关闭数据库，只能通过shutdown abort强制关闭。然后将数据库启动到mount状态。
```
[oracle@oracle-pc ~]$ sqlplus / as sysdba
SQL> shutdown immediate
ORA-01116: 打开数据库文件 10 时出错
ORA-01110: 数据文件 10: '/home/oracle/app/oracle/oradata/orcl/pdborcl/SAMPLE_SCHEMA_users01.dbf'
ORA-27041: 无法打开文件
Linux-x86_64 Error: 2: No such file or directory
Additional information: 3
SQL> shutdown abort
ORACLE instance shut down.
SQL> startup mount
ORACLE instance started.

Total System Global Area 1577058304 bytes
Fixed Size                  2924832 bytes
Variable Size             738201312 bytes
Database Buffers          654311424 bytes
Redo Buffers               13848576 bytes
In-Memory Area            167772160 bytes
Database mounted.
SQL> exit
```
### 4.2 开始恢复数据库
```sql
[oracle@oracle-pc ~]$ rman target /
RMAN> restore database ;
Starting restore at 20-11月-19
using target database control file instead of recovery catalog
allocated channel: ORA_DISK_1
channel ORA_DISK_1: SID=243 device type=DISK

skipping datafile 5; already restored to file /home/oracle/app/oracle/oradata/orcl/pdbseed/system01.dbf
skipping datafile 7; already restored to file /home/oracle/app/oracle/oradata/orcl/pdbseed/sysaux01.dbf
channel ORA_DISK_1: starting datafile backup set restore
channel ORA_DISK_1: specifying datafile(s) to restore from backup set
channel ORA_DISK_1: restoring datafile 00008 to /home/oracle/app/oracle/oradata/orcl/pdborcl/system01.dbf
channel ORA_DISK_1: restoring datafile 00009 to /home/oracle/app/oracle/oradata/orcl/pdborcl/sysaux01.dbf
channel ORA_DISK_1: restoring datafile 00010 to /home/oracle/app/oracle/oradata/orcl/pdborcl/SAMPLE_SCHEMA_users01.dbf
channel ORA_DISK_1: restoring datafile 00011 to /home/oracle/app/oracle/oradata/orcl/pdborcl/example01.dbf
channel ORA_DISK_1: restoring datafile 00012 to /home/oracle/app/oracle/oradata/orcl/pdborcl/pdbtest_users02_1.dbf
channel ORA_DISK_1: restoring datafile 00013 to /home/oracle/app/oracle/oradata/orcl/pdborcl/pdbtest_users02_2.dbf
channel ORA_DISK_1: restoring datafile 00016 to /home/oracle/app/oracle/oradata/orcl/pdborcl/pdbtest_users02_3.dbf
channel ORA_DISK_1: restoring datafile 00017 to /home/oracle/app/oracle/oradata/orcl/pdborcl/pdbtest_users02_4.dbf
channel ORA_DISK_1: restoring datafile 00077 to /home/oracle/app/oracle/oradata/orcl/pdborcl/pdbtest_users03_1.dbf
channel ORA_DISK_1: restoring datafile 00078 to /home/oracle/app/oracle/oradata/orcl/pdborcl/pdbtest_users03_2.dbf
channel ORA_DISK_1: reading from backup piece /home/oracle/rman_backup/dblv0_ORCL_20191120_d7uhb2ap_1_1.bak
channel ORA_DISK_1: piece handle=/home/oracle/rman_backup/dblv0_ORCL_20191120_d7uhb2ap_1_1.bak tag=TAG20191120T083953
channel ORA_DISK_1: restored backup piece 1
channel ORA_DISK_1: restore complete, elapsed time: 00:01:41
channel ORA_DISK_1: starting datafile backup set restore
channel ORA_DISK_1: specifying datafile(s) to restore from backup set
channel ORA_DISK_1: restoring datafile 00001 to /home/oracle/app/oracle/oradata/orcl/system01.dbf
channel ORA_DISK_1: restoring datafile 00003 to /home/oracle/app/oracle/oradata/orcl/sysaux01.dbf
channel ORA_DISK_1: restoring datafile 00004 to /home/oracle/app/oracle/oradata/orcl/undotbs01.dbf
channel ORA_DISK_1: restoring datafile 00006 to /home/oracle/app/oracle/oradata/orcl/users01.dbf
channel ORA_DISK_1: reading from backup piece /home/oracle/rman_backup/dblv0_ORCL_20191120_d8uhb2c6_1_1.bak
channel ORA_DISK_1: piece handle=/home/oracle/rman_backup/dblv0_ORCL_20191120_d8uhb2c6_1_1.bak tag=TAG20191120T083953
channel ORA_DISK_1: restored backup piece 1
channel ORA_DISK_1: restore complete, elapsed time: 00:01:55
Finished restore at 20-11月-19

RMAN> recover database;
RMAN> alter database open;
Statement processed
RMAN> exit
```
## 5 查询数据是否恢复
```
[oracle@oracle-pc ~]$ sqlplus study/123@pdborcl
SQL> select * from t1;
        ID NAME
---------- --------------------------------------------------
         1 zhang
         2 wang
SQL> 
```
由以上查询结果可见，数据100%恢复了!

## 6 删除备份集(可选)

[oracle@oracle-pc ~]$ rman target /
```
RMAN> delete backup;
using target database control file instead of recovery catalog
allocated channel: ORA_DISK_1
channel ORA_DISK_1: SID=366 device type=DISK

List of Backup Pieces
BP Key  BS Key  Pc# Cp# Status      Device Type Piece Name
------- ------- --- --- ----------- ----------- ----------
275     275     1   1   AVAILABLE   DISK        /home/oracle/rman_backup/dblv0_ORCL_20191120_d7uhb2ap_1_1.bak
276     276     1   1   AVAILABLE   DISK        /home/oracle/rman_backup/dblv0_ORCL_20191120_d8uhb2c6_1_1.bak
277     277     1   1   AVAILABLE   DISK        /home/oracle/rman_backup/dblv0_ORCL_20191120_d9uhb2ei_1_1.bak
278     278     1   1   AVAILABLE   DISK        /home/oracle/rman_backup/arclv0_ORCL_20191120_dauhb2fm_1_1.bak
279     279     1   1   AVAILABLE   DISK        /home/oracle/rman_backup/c-1392946895-20191120-01

Do you really want to delete the above objects (enter YES or NO)? yes
deleted backup piece
backup piece handle=/home/oracle/rman_backup/dblv0_ORCL_20191120_d7uhb2ap_1_1.bak RECID=275 STAMP=1024821593
deleted backup piece
backup piece handle=/home/oracle/rman_backup/dblv0_ORCL_20191120_d8uhb2c6_1_1.bak RECID=276 STAMP=1024821638
deleted backup piece
backup piece handle=/home/oracle/rman_backup/dblv0_ORCL_20191120_d9uhb2ei_1_1.bak RECID=277 STAMP=1024821715
deleted backup piece
backup piece handle=/home/oracle/rman_backup/arclv0_ORCL_20191120_dauhb2fm_1_1.bak RECID=278 STAMP=1024821750
deleted backup piece
backup piece handle=/home/oracle/rman_backup/c-1392946895-20191120-01 RECID=279 STAMP=1024821752
Deleted 5 objects
RMAN> exit
[oracle@oracle-pc ~]$ cd rman_backup
[oracle@oracle-pc rman_backup]$ ls
lv0_20191120-083949_L0.log
```
可见，删除备份集之后，备份文件也删除了。只留下了日志文件。
