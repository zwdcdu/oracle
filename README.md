# Oracle 数据库应用

## 内容说明

- PPT目录：各章节的PPT
- script目录：各章节的SQL语句及源码
- doc目录：参考文档
- book.pdf： 本书的PDF文档
- tools/gitgfb_ttar.rar -windows运行linux命令工具
- student@202.115.82.8:~/downloads/sqldeveloper.zip :sqldeveloper工具

## 实验服务器地址

```flow js
地址：202.115.82.8
数据库：pdborcl
system密码:123，所有密码都为123

成绩网址：http://202.115.82.8:1522


## SSH登录
```shell
$ ssh student@202.115.82.8
student@202.115.82.8's password:
[student@deep02 ~]$cat readme.txt
```

密码是123
在Windows上登录需要ssh客户端，可以下载安装 : 
https://github.com/zwdcdu/oracle/raw/master/gitgfb_ttrar.rar

```sql
[student@deep02 ~]$ sqlplus system/123@202.115.82.8/pdborcl
sqlplus 你的用户名/123@pdborcl
SQL> select * from hr.employees;
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

## GitHub上不能显示图片的解决方法

- 修改：C:\Windows\System32\drivers\etc\hosts，在文件后面增加：

```text
# GitHub Start 
192.30.253.112    github.com 
192.30.253.119    gist.github.com
151.101.184.133    assets-cdn.github.com
151.101.184.133    raw.githubusercontent.com
151.101.184.133    gist.githubusercontent.com
151.101.184.133    cloud.githubusercontent.com
151.101.184.133    camo.githubusercontent.com
151.101.184.133    avatars0.githubusercontent.com
151.101.184.133    avatars1.githubusercontent.com
151.101.184.133    avatars2.githubusercontent.com
151.101.184.133    avatars3.githubusercontent.com
151.101.184.133    avatars4.githubusercontent.com
151.101.184.133    avatars5.githubusercontent.com
151.101.184.133    avatars6.githubusercontent.com
151.101.184.133    avatars7.githubusercontent.com
151.101.184.133    avatars8.githubusercontent.com
# GitHub End
```

## 添加student用户

```shell
useradd student -G  dba
usermod -a -G oinstall student
usermod -a -G dba student
```