<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <title>员工信息页面</title>
	<style type="text/css">
	<!--
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-color: #EEF2FB;
	}
	#addSubjectForm table  td{
		font-size:12px;
	}
	-->
	</style>
	<link href="<%=request.getContextPath()%>/teacher/images/skin.css" rel="stylesheet" type="text/css">
  </head>
<body> 
 <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="<%=request.getContextPath()%>/teacher/images/mail_leftbg.gif"><img src="<%=request.getContextPath()%>/teacher/images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="<%=request.getContextPath()%>/teacher/images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">员工信息</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="<%=request.getContextPath()%>/teacher/images/mail_rightbg.gif"><img src="<%=request.getContextPath()%>/teacher/images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td valign="middle" background="<%=request.getContextPath()%>/teacher/images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="53%" valign="top">&nbsp;</td>
        </tr>
      <tr>
        <td valign="middle"><span class="left_txt">
			<div id="addSubjectForm" align="center"><!--员工信息表单-->
				<table border="0" cellspacing="10" cellpadding="0">
				  <tr>
					<td>员工编号:</td>
					<td><input type="text" value="<s:property value="#session.employee1.employeeId"/> " size="20" disabled="disabled" ></td>
				  </tr>
				  <tr>
					<td>员工名字</td>
					<td><input type="text" value="<s:property value="#session.employee1.name"/>" size="20" disabled="disabled"></td>
				  </tr>
				   <tr>
					<td>员工邮箱</td>
					<td><input type="text" value="<s:property value="#session.employee1.email"/>" size="20" disabled="disabled"></td>
				  </tr>
				   <tr>
					<td>员工电话</td>
					<td><input type="text" value="<s:property value="#session.employee1.phoneNumber"/>" size="20" disabled="disabled"></td>
				  </tr>
				   <tr>
					<td>员工任职日期(yyyy-MM-dd)</td>
					<td><input type="text" value="<s:date name="#session.employee1.hireDate" format="yyyy-MM-dd"/>" size="20" disabled="disabled"></td>
				  </tr>
				   <tr>
					<td>员工工资</td>
					<td><input type="text" value="<s:property value="#session.employee1.salary"/>" size="20" disabled="disabled"></td>
				  </tr>
					 <tr>
					<td>员工上司ID</td>
						 <td><input type="text" value="<s:property value="#session.employee1.managerId"/>" size="20" disabled="disabled"></td>
				  </tr>
					 <tr>
					<td>员工部门ID</td>
					<td><input type="text" value="<s:property value="#session.employee1.departmentId"/>" size="20" disabled="disabled"></td>
				  </tr>
					<s:if test="#session.employee1.photo != null">
						<tr>
					<td>员工照片</td>
						<td>
							<img src="<s:url action="employeeAction4?">
							<s:param name="employeeId" value="#session.employee1.employeeId"/></s:url>"
								 alt="员工.jpg" width="200px" height="200px">
						</td>
				  </tr>
					</s:if>
			</table>
		</td>
        </tr>

    </table></td>
    <td background="<%=request.getContextPath()%>/teacher/images/mail_rightbg.gif">&nbsp;</td>
  </tr>
  <tr>
    <td valign="bottom" background="<%=request.getContextPath()%>/teacher/images/mail_leftbg.gif"><img src="<%=request.getContextPath()%>/teacher/images/buttom_left2.gif" width="17" height="17" /></td>
    <td background="<%=request.getContextPath()%>/teacher/images/buttom_bgs.gif"><img src="<%=request.getContextPath()%>/teacher/images/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="<%=request.getContextPath()%>/teacher/images/mail_rightbg.gif"><img src="<%=request.getContextPath()%>/teacher/images/buttom_right2.gif" width="16" height="17" /></td>
  </tr>
</table>
</body>
</html>
