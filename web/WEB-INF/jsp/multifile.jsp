<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021-05-28
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/file/multiFile" method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="username"/><br/>
    文件上传1：<input type="file" name="file"/><br/>
    文件上传2：<input type="file" name="file"/><br/>
    <input type="submit" value="OKOK"/>
</form>
</body>
</html>
