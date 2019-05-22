<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <title>登录页面V2</title>
    <style type="text/css">
	<!--
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-color: #1D3647;
	}
	.STYLE1 {color: #E6EBF1}
	-->
	</style>
	<link href="teacher/images/skin.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
	  <table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
	      <tr>
	        <td width="1%" height="21">&nbsp;</td>
	        <td height="42">&nbsp;</td>
	        <td width="17%">&nbsp;</td>
	      </tr>
	    </table></td>
	  </tr>
	  <tr>
	    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
	      <tr>
	        <td width="49%" align="right"><table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
	            <tr>
	              <td height="138" valign="top"><table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
	                <tr>
	                  <td height="149">&nbsp;</td>
	                </tr>
	                <tr>
	                  <td height="80" align="right" valign="top">&nbsp;</td>
	                </tr>
	                <tr>
	                  <td height="198" align="right" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
	                    <tr>
	                      <td width="35%">&nbsp;</td>
	                      <td width="65%" height="25" class="left_txt"><p align="right">1- 订单管理</p></td>
	                    </tr>
	                    <tr>
	                      <td>&nbsp;</td>
	                      <td height="25" class="left_txt"><p align="right">2- 产品管理</p></td>
	                    </tr>
	                    <tr>
	                      <td>&nbsp;</td>
	                      <td height="25" class="left_txt"><p align="right">3- 部门管理</p></td>
	                    </tr>
						  <tr>
							  <td>&nbsp;</td>
							  <td height="25" class="left_txt"><p align="right">4- 员工管理</p></td>
						  </tr>
	                  </table></td>
	                </tr>
	              </table></td>
	            </tr>
	            
	        </table></td>
	        <td width="1%" >&nbsp;</td>
	        <td width="50%" valign="bottom"><table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
	            <tr>
	              <td width="4%">&nbsp;</td>
	              <td width="96%" height="38"><span class="login_txt_bt">管理人员登录(用户名和密码都是admin)</span></td>
	            </tr>
	            <tr>
	              <td>&nbsp;</td>
	              <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
	                  <tr>
	                    <td height="164" colspan="2" align="middle">
	                    	<form name="myform" action="loginAction.action" method="post">
	                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
	                          <tr>
	                          	<td colspan="2" style="color: red"><s:actionerror/></td><!--显示错误信息 -->
	                          </tr>
	                          <tr>
	                            <td width="14%" height="38" class="top_hui_text"><span class="login_txt">用户名：&nbsp;&nbsp; </span></td>
	                            <td height="38" colspan="2" class="top_hui_text"><input name="username" class="editbox4" value="admin" size="20">                            </td>
	                          </tr>
	                          <tr>
	                            <td width="14%" height="35" class="top_hui_text"><span class="login_txt"> 密 码： &nbsp;&nbsp; </span></td>
	                            <td height="35" colspan="2" class="top_hui_text"><input class="editbox4" type="password" size="20" name="password" value="admin">
	                              <img src="teacher/images/luck.gif" width="19" height="18"> </td>
	                          </tr>
	                          <tr>
	                            <td height="35" >&nbsp;</td>
	                            <td width="19%" height="35" ><input type="submit" class="button" id="Submit" value="登 录"> </td>
	                            <td width="67%" class="top_hui_text"><input type="button" class="button" id="cs" value="取 消"></td>
	                          </tr>
	                        </table>
	                        <br>
	                    </form></td>
	                  </tr>
	                  <tr>
	                    <td width="433" height="164" align="right" valign="bottom"><img src="teacher/images/login-wel.gif" width="242" height="138"></td>
	                    <td width="57" align="right" valign="bottom">&nbsp;</td>
	                  </tr>
	              </table></td>
	            </tr>
	          </table>
	          </td>
	      </tr>
	    </table></td>
	  </tr>
	  <tr>
	    <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
	      <tr>
	        <td align="center"><span class="login-buttom-txt STYLE1">小型订单管理系统 Copyright &copy; 2017</span></td>
	      </tr>
	    </table></td>
	  </tr>
	</table>
  </body>
</html>
