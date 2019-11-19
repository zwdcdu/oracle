# Oracle 数据库应用


## 内容说明

- PPT目录：各章节的PPT
- script目录：各章节的SQL语句及源码
- doc目录：参考文档
- book.pdf： 本书的PDF文档

## 实验服务器地址

```flow js
地址：202.115.82.8
数据库：pdborcl
system密码:123，所有密码都为123

成绩网址：http://202.115.82.8:1522

````
![](./img/timg.gif)c 
# 5个平时实验成绩中有三个实验超过90分的同学（共16位）
## 其中全优的同学有4位（夏伟钞，袁佳琪，晏德兵，陈泯全）：
```
201710414107	李聪灵	软件(本)17-1	miss-pwj	90|7.2	85|6.8	85|6.8	90|7.2	90|7.2
201710414113	牟婷	软件(本)17-1	Blue-blue123	85|6.8	80|6.4	90|7.2	90|7.2	90|7.2
201710414114	蒲雪	软件(本)17-1	puxueqd	        55|4.4	80|6.4	90|7.2	90|7.2	90|7.2
201710414121	夏伟钞	软件(本)17-1	perfectismman	90|7.2	90|7.2	100|8	95|7.6	100|8
201710215204	高志洋	软件(本)17-2	yangyam	        90|7.2	90|7.2	90|7.2	90|7.2	85|6.8
201710414215	施实伟	软件(本)17-2	ssw383318348	75|6	85|6.8	90|7.2	90|7.2	90|7.2
201710414225	袁佳琪	软件(本)17-2	enthusiasmYuan	90|7.2	90|7.2	90|7.2	90|7.2	90|7.2
201710414321	向柏璇	软件(本)17-3	cccccolorful	95|7.6	90|7.2	90|7.2	90|7.2	85|6.8
201710112218	田皓友	软件(本)17-4	947346705	75|6	90|7.2	90|7.2	90|7.2	90|7.2
201710112425	晏德兵	软件(本)17-4	YUnIv	        90|7.2	90|7.2	100|8	95|7.6	100|8
201710414401	陈泯全	软件(本)17-4	Frapschen	90|7.2	90|7.2	90|7.2	90|7.2	90|7.2
201710414410	卢爽	软件(本)17-4	R0bBer-ls	80|6.4	80|6.4	90|7.2	90|7.2	90|7.2
201710414418	涂翔宇	软件(本)17-4	KIDDOZABER	85|6.8	90|7.2	90|7.2	90|7.2	90|7.2
201710414419	王绍华	软件(本)17-4	wang-zzz	85|6.8	90|7.2	90|7.2	90|7.2	90|7.2
201710414427	张露平	软件(本)17-4	ZhanglupingCDU	85|6.8	90|7.2	90|7.2	90|7.2	85|6.8
201710424105	邓聪	软件(本)17-4	smartdengc 错误	90|7.2	90|7.2	90|7.2	85|6.8	85|6.8
```
## SSH登录
```shell
$ ssh student@202.115.82.8
student@202.115.82.8's password:
[student@deep02 ~]$cat readme.txt

```
密码是123321qweewq
在Windows上登录需要ssh客户端，可以下载安装 : 
https://github.com/zwdcdu/oracle/raw/master/gitgfb_ttrar.rar

```sql
sqlplus system/123@pdborcl
sqlplus 你的用户名/123@pdborcl
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
```
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