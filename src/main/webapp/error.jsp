<%--
  Created by IntelliJ IDEA.
  User: 10297
  Date: 2018/12/22
  Time: 1:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
    <title>ErrorPage</title>
</head>
<body>
Error Happened.
<br/>
Error Message:<s:property value="message"/>&nbsp<a href="javascript:history.go(-1)">返回</a>
</body>
</html>
