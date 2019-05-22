<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
    <title>订单列表界面</title>
	  <script type="text/javascript">
		  function fun1() {
			  alert("对不起，有对应详单，不能随意删除");
          }
	  </script>
	<style type="text/css">
	<!--
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-color: #EEF2FB;
	}
	#manageSubject table  td{
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
        <td height="31"><div class="titlebt">订单列表</div></td>
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
			<div id="manageSubject" align="center">
			<table width="95%">
				  <tr align="center">
					<td >订单编号</td>
					<td >客户名</td>
					<td >客户电话</td>
					<td >下单日期</td>
					<td >开单员工编号</td>
					<td >折扣</td>
				  </tr>
				  	 <tr align="center">
							<td><s:property value="#session.orderOne.orderId"/></td>
							<td><s:property value="#session.orderOne.customerName"/></td>
							<td><s:property value="#session.orderOne.customerTel"/></td>
							<td><s:date name="#session.orderOne.orderDate" format="yyyy-MM-dd"/></td>
							<td><s:property value="#session.orderOne.employeeId"/></td>
							<td><s:property value="#session.orderOne.discount"/></td>
                         <td><a href="orderDetailShow.action?orderId=<s:property value="#session.orderOne.orderId"/>">详情</a></td>
							<td><a href="orderOnes.action?orderId=<s:property value="#session.orderOne.orderId"/>">编辑</a></td>
						 	<s:if test="#session.orderOne.hasDetail == 1">
                                <td><a href="javascript:void(0)" onclick="fun1();">删除</a></td>
                            </s:if>
							<s:else>
                                <td><a href="deleteOne.action?orderId=<s:property value="#session.orderOne.orderId"/>" onclick= 'return confirm( "确定要删除吗? ") '>删除</a></td>
                            </s:else>
					  </tr>
			</table>
			</div>
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
