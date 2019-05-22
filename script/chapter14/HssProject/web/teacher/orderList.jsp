<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
            background-color: #EEF2FB;
        }

        #manageSubject table td {
            font-size: 12px;
        }
    </style>
    <link href="<%=request.getContextPath()%>/teacher/images/skin.css" rel="stylesheet" type="text/css">
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
                        <div class="titlebt">订单列表</div>
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
			<div id="manageSubject" align="center">
			<table width="95%">
				  <tr align="center">
					<td>订单编号</td>
					<td>客户名</td>
					<td>客户电话</td>
					<td>下单日期</td>
					<td>开单员工编号</td>
					<td>折扣</td>
					<td>金额总和</td>
				  </tr>
				  <s:iterator value="#session.orders" var="list">
				  	 <tr align="center">
							<td><s:property value="#list.orderId"/></td>
							<td><s:property value="#list.customerName"/></td>
							<td><s:property value="#list.customerTel"/></td>
							<td><s:date name="#list.orderDate" format="yyyy-MM-dd"/></td>
							<td><s:property value="#list.employeeId"/></td>
							<td><s:property value="#list.discount"/></td>
							<td><s:property value="#list.tradeReceivable"/></td>
							<td><a href="orderDetailShow.action?orderId=<s:property value="#list.orderId"/>">详情</a></td>
							<td><a href="orderOnes.action?orderId=<s:property value="#list.orderId"/>">编辑</a></td>
						 	<s:if test="#list.hasDetail == 1">
                                <td><a href="javascript:void(0)" onclick="fun1();">删除</a></td>
                            </s:if>
							<s:else>
                                <td><a href="deleteOne.action?orderId=<s:property value="#list.orderId"/> "
                                       onclick='return confirm( "确定要删除吗? ")'>删除</a></td>
                            </s:else>
					  </tr>
                  </s:iterator>
				<tr></tr>
					<tr>
						<s:form name="orderOne" method="post" action="orderOne" namespace="/"
                                enctype="multipart/form-data"
                                theme="simple">
                            <td colspan="2" style="color: red"><s:actionerror/></td>
                            <td colspan="6" align="center" id="ss">
						<input type="text" name="orderId" size="15" id="orderId">
						<input type="submit" value="查询" onclick="fun2(this);">
					        </td>
                        </s:form>
					</tr>
				  <tr>
				  	<td colspan="6" align="center">
				  		共${pager.totalSize}条纪录，当前第${pager.pageNow}/${pager.totalPage}页，每页${pager.pageSize}条纪录
				  	<s:if test="#session.pager.hasPre">
                        <a href="orderShow.action?pageNow=1">首页</a> |
                        <a href="orderShow.action?pageNow=${pager.pageNow - 1}">上一页</a> |
                    </s:if>
               		<s:else>
                        首页 | 上一页 |
                    </s:else>
               		<s:if test="#session.pager.hasNext">
                        <a href="orderShow.action?pageNow=${pager.pageNow + 1}">下一页</a> |
                        <a href="orderShow.action?pageNow=${pager.totalPage}">尾页</a>
                    </s:if>
               		<s:else>
                        下一页 | 尾页
                    </s:else>
				  	</td>
				  </tr>
			</table>	
			</div>
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
<script type="text/javascript">
    function fun2() {
        var str = document.getElementById("orderId").value;
        if (str == "") {
            alert("请输入查询商品Id");
            return false;
        }
        return true;
    }
</script>
</html>
