<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页面</title>
</head>
<body>
发生错误：<br/>
<s:if test="hasActionErrors()">
    <div class="error">
        <s:actionerror />
    </div>
    <s:else>
        <div class="error">
            其他错误
        </div>
    </s:else>
</s:if>
</body>
</html>