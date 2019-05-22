<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>管理页面</title>
<script language=JavaScript1.2>
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
</script>
<meta http-equiv=Content-Type content=text/html;charset=gb2312>
<meta http-equiv="refresh" content="60">
<script language=JavaScript1.2>
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
</script>
<base target="main">
<link href="<%=request.getContextPath()%>/teacher/images/skin.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
<div style="position: absolute;margin-left: 500px;margin-top: 13px;" >
  <font color="#f0f8ff">基于Oracle 12c的小型商品销售系统</font>
</div>
<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">

  <tr>
    <td width="61%" height="64" align="center" style="margin-top: -15px">&nbsp;</td>
    <td width="39%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="74%" height="38" class="admin_txt">&nbsp;</td>
        <td width="22%"><a href="#" target="_self" onClick="logout();"></a></td>
        <td width="4%">&nbsp;</td>
      </tr>
      <tr>
        <td height="19" colspan="3">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
