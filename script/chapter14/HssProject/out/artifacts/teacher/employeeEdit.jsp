<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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

        #addSubjectForm table td {
            font-size: 12px;
        }

        -->
    </style>
    <link href="<%=request.getContextPath()%>/teacher/images/skin.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/teacher/images/jquery.datetimepicker.css"/>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td width="17" valign="top" background="<%=request.getContextPath()%>/teacher/images/mail_leftbg.gif"><img
                src="<%=request.getContextPath()%>/teacher/images/left-top-right.gif" width="17" height="29"/></td>
        <td valign="top" background="<%=request.getContextPath()%>/teacher/images/content-bg.gif">
            <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
                <tr>
                    <td height="31">
                        <div class="titlebt">员工信息</div>
                    </td>
                </tr>
            </table>
        </td>
        <td width="16" valign="top" background="<%=request.getContextPath()%>/teacher/images/mail_rightbg.gif"><img
                src="<%=request.getContextPath()%>/teacher/images/nav-right-bg.gif" width="16" height="29"/></td>
    </tr>
    <tr>
        <td valign="middle" background="<%=request.getContextPath()%>/teacher/images/mail_leftbg.gif">&nbsp;</td>
        <td valign="top" bgcolor="#F7F8F9">
            <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="53%" valign="top">&nbsp;</td>
                </tr>
                <tr>
                    <td valign="middle"><span class="left_txt">
			<div id="addSubjectForm" align="center"><!--员工信息表单-->
			<s:fielderror cssStyle="color: red" theme="simple"/>
			<s:form name="employeeAction6" method="post" action="employeeAction6" namespace="/"
                    enctype="multipart/form-data"
                    theme="simple">
				<table border="0" cellspacing="10" cellpadding="0">
				  <tr>
					<td><input type="hidden" name="employeesEntitys.employeeId"
                               value="<s:property value="#session.employee2.employeeId"/> " size="20"></td>
				  </tr>
				  <tr>
					<td>员工名字</td>
					<td><input type="text" name="employeesEntitys.name"
                               value="<s:property value="#session.employee2.name"/>" size="20"></td>
				  </tr>
				   <tr>
					<td>员工邮箱</td>
					<td><input type="text" name="employeesEntitys.email"
                               value="<s:property value="#session.employee2.email"/>" size="20"></td>
				  </tr>
				   <tr>
					<td>员工电话</td>
					<td><input type="text" name="employeesEntitys.phoneNumber"
                               value="<s:property value="#session.employee2.phoneNumber"/>" size="20"></td>
				  </tr>
				   <tr>
					<td>员工任职日期</td>
					<td><input type="text" name="hireDate" id="datetimepicker1"
                               value="<s:date name="#session.employee2.hireDate" format="yyyy-MM-dd"/>" size="20"></td>
				  </tr>
				   <tr>
					<td>员工工资</td>
					<td><input type="text" name="employeesEntitys.salary"
                               value="<s:property value="#session.employee2.salary"/>" size="20"></td>
				  </tr>
                    <tr>
					<td>员工上司ID</td>
						 <td>
						 <select name="employeesEntitys.managerId">
							 <s:iterator value="#session.ListEmployeesID" id="list">
								 <s:if test="#session.employee2.managerId==#list">
									 <option selected><s:property value="#session.employee2.managerId"/> </option>
								 </s:if>
								 <s:else>
									 <option><s:property value="#list"/></option>
								 </s:else>
                             </s:iterator>
						 </select>
						 </td>
				    </tr>
					 <tr>
					<td>员工部门ID</td>
					<td><select name="employeesEntitys.departmentId">
							 <s:iterator value="#session.departments" id="list">
								 <s:if test="#session.employee2.departmentId==#list.departmentId">
									 <option selected><s:property value="#session.employee2.departmentId"/> </option>
								 </s:if>
								 <s:else>
									 <option><s:property value="#list.departmentId"/></option>
								 </s:else>
                             </s:iterator>
						 </select>
						</td>
				  </tr>
					<tr>
					<td>员工照片</td>
						<td>
							<s:file name="photo"/>
						</td>
				  	</tr>
					<tr>
				  	<td colspan="2"><div align="center">
				  	  <input type="submit" value="录入">
				  	  <input type="reset" value="重置">
			  	  </div>
				</td>
				  </tr>
			</table>
				</s:form>
                    </td>
                </tr>
            </table>
        </td>
        <td background="<%=request.getContextPath()%>/teacher/images/mail_rightbg.gif">&nbsp;</td>
    </tr>
    <tr>
        <td valign="bottom" background="<%=request.getContextPath()%>/teacher/images/mail_leftbg.gif"><img
                src="<%=request.getContextPath()%>/teacher/images/buttom_left2.gif" width="17" height="17"/></td>
        <td background="<%=request.getContextPath()%>/teacher/images/buttom_bgs.gif"><img
                src="<%=request.getContextPath()%>/teacher/images/buttom_bgs.gif" width="17" height="17"></td>
        <td valign="bottom" background="<%=request.getContextPath()%>/teacher/images/mail_rightbg.gif"><img
                src="<%=request.getContextPath()%>/teacher/images/buttom_right2.gif" width="16" height="17"/></td>
    </tr>
</table>
</body>
<script src="<%=request.getContextPath()%>/teacher/images/jquery.js"></script>
<script src="<%=request.getContextPath()%>/teacher/images/jquery.datetimepicker.full.js"></script>
<script>
    $('#datetimepicker1').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
        disabledDates: ['1986/01/08', '1986/01/09', '1986/01/10'],
        startDate: '1986/01/05'
    });
    $('#datetimepicker1').datetimepicker({value: '2015/04/15 05:03', step: 10});
</script>
</html>
