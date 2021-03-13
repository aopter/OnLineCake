<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h3>该资源受保护，请输入您的账号与密码</h3>
<form action="j_security_check" method="post">
    用户名：<input type="text" name="j_username" style="border:0.5px solid #378888"/> <br/>
    密 码：<input type="password" name="j_password" style="border:0.5px solid #378888"/> <br/>
    <input type="submit" name="登录" style="border:0.5px solid #378888;background:#378888"/>
</form>
</body>
</html>