<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>商品信息页面</title>
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
                        <div class="titlebt">商品信息</div>
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
			<div id="addSubjectForm" align="center"><!--商品信息表单-->
                <s:form name="productEditAction" method="post" action="productEditAction" namespace="/"
                        enctype="multipart/form-data"
                        theme="simple">
				<table border="0" cellspacing="10" cellpadding="0">
				  <tr>
					<td><input type="hidden" name="productsEntity.productId"
                               value="<s:property value="#session.poduct2.productId"/> " size="20"></td>
				  </tr>
				  <tr>
					<td>商品名</td>
					<td><input type="text" name="productsEntity.productName"
                               value="<s:property value="#session.poduct2.productName"/>" size="20"></td>
				  </tr>
				   <tr>
					<td>商品类型</td>
					 <td>
                       <select name="productsEntity.productType">
							<option selected>${poduct2.productType}</option>
							<option>耗材</option>
							<option>电脑</option>
							<option>手机</option>
						 </select>
                       </td>
				  </tr>
				   <tr>
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
</html>
