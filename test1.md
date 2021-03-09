# 实验1：SQL语句的执行计划分析与优化指导

## 实验目的

  分析SQL执行计划，执行SQL语句的优化指导。理解分析SQL语句的执行计划的重要作用。
  
## 实验内容

- 对Oracle12c中的HR人力资源管理系统中的表进行查询与分析。
- 首先运行和分析教材中的样例：本训练任务目的是查询两个部门('IT'和'Sales')的部门总人数和平均工资，以下两个查询的结果是一样的。但效率不相同。
- 设计自己的查询语句，并作相应的分析，查询语句不能太简单。

## 教材中的查询语句

查询1：

```SQL
set autotrace on

SELECT d.department_name,count(e.job_id)as "部门总人数",
avg(e.salary)as "平均工资"
from hr.departments d,hr.employees e
where d.department_id = e.department_id
and d.department_name in ('IT','Sales')
GROUP BY d.department_name;
```

- 查询2

```SQL
set autotrace on

SELECT d.department_name,count(e.job_id)as "部门总人数",
avg(e.salary)as "平均工资"
FROM hr.departments d,hr.employees e
WHERE d.department_id = e.department_id
GROUP BY d.department_name
HAVING d.department_name in ('IT','Sales');
```

执行上面两个比较复杂的返回相同查询结果数据集的SQL语句，通过分析SQL语句各自的执行计划，判断哪个SQL语句是最优的。最后将你认为最优的SQL语句通过sqldeveloper的优化指导工具进行优化指导，看看该工具有没有给出优化建议

## 实验参考地址

- Oracle地址：202.115.82.8 用户名：system ， 密码123， 数据库名称：pdborcl，端口号：1521
- 用户hr默认没有统计权限，运行上述命令时要报错：

```text  
无法收集统计信息, 请确保用户具有正确的访问权限。
统计信息功能要求向用户授予 v_$sesstat, v_$statname 和 v_$session 的选择权限。
```

怎样解决？

## 实验注意事项，完成时间： 2021-3-17日前上交

- 请按时完成实验，过时扣分。
- 查询语句及分析文档`必须提交`到：你的oracle项目中的test1目录中。
- 上交后，通过这个地址应该可以打开你的源码：https://github.com/你的用户名/oracle/tree/master/test1
- 实验分析及结果文档说明书用Markdown格式编写。

## 具体做法流程

- 登录你的GitHub账号，创建一个公有资料库oracle，创建一个test1目录，在test1目录中，至少创建一个文件：README.md，里面有你的作业文字，截图文件。
- 复制地址：https://github.com/youruser/oracle.git
- 在本机运行克隆命令：
- $git clone https://github.com/youruser/oracle.git
- 自动在本地磁盘上生成oracle目录
- 启动vscode，打开oracle目录
- 创建一个test1目录，在test1目录中，至少创建一个文件：README.md，里面有你的作业文字，截图文件。
- 作业完成后，推送到github网站（git add ,git commit,git push）
- 推送时，如果出现错误OpenSSL SSL_connect: SSL_ERROR_SYSCALL in connection to github.com，应添加Windows环境变量：GIT_SSL_NO_VERIFY=true。如果出现Time Out超时，等待一会儿，再次推送。
- 作业目录样例
  
```text
oracle
   test1
      README.md
      pict1.png
      pict2.png
   test2
      README.md
      pict1.png
      pict2.png
  ...
  test5  
```

## 评分标准

- 实验独立完成，有详细的分析文档。（总分20分）
- 分析教材中的文档（总分40分）
- 分析自定义查询的文档（总分40分）
