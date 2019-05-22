<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <title>添加商品信息页面</title>
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
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/teacher/images/jquery.datetimepicker.css"/>
  </head>
<body> 
 <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="<%=request.getContextPath()%>/teacher/images/mail_leftbg.gif"><img src="<%=request.getContextPath()%>/teacher/images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="<%=request.getContextPath()%>/teacher/images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">录入商品信息</div></td>
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
			<div id="addSubjectForm" align="center"><!--录入员工信息表单-->
				<s:fielderror cssStyle="color: red" theme="simple"/>
                <s:actionmessage cssStyle="color:red" />
		        <s:actionerror cssStyle="color:green;" />
				<s:form name="productAddAction" method="post" action="productAddAction" namespace="/"
                        enctype="multipart/form-data"
                        theme="simple">
				    <table border="0" cellspacing="10" cellpadding="0">
                        <tr>
                            <td colspan="2" style="color: red"><s:actionerror/></td>
                        </tr>
                        <tr>
                            <td>商品编号:</td>
                            <td>
                                <s:textfield label="productsEntity.productId" id="productsEntity.productId"
                                             name="productsEntity.productId" size="20"></s:textfield>
                            </td>
                        </tr>
                        <tr>
                            <td>商品名</td>
                            <td>
                                <s:textfield label="productsEntity.productName" id="productsEntity.productName"
                                             name="productsEntity.productName" size="20"></s:textfield>
                            </td>
                        </tr>
                        <tr>
                            <td>商品类型</td>
                            <td>
                                <select name="productsEntity.productType" >
                                <option selected>耗材</option>
                                <option>电脑</option>
                                <option>手机</option>
                                </select>
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
  <script src="<%=request.getContextPath()%>/teacher/images/jquery.js"></script>
  <script src="<%=request.getContextPath()%>/teacher/images/jquery.datetimepicker.full.js"></script>
  <script>/*
   window.onerror = function(errorMsg) {
   $('#console').html($('#console').html()+'<br>'+errorMsg)
   }*/



  $('#datetimepicker').datetimepicker({
      dayOfWeekStart : 1,
      lang:'en',
      disabledDates:['1986/01/08','1986/01/09','1986/01/10'],
      startDate:	'1986/01/05'
  });
  $('#datetimepicker').datetimepicker({value:'2015/04/15 05:03',step:10});




  </script>
</html>
