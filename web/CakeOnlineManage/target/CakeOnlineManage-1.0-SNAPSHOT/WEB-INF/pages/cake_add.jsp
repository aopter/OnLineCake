<%--
  Created by IntelliJ IDEA.
  User: 刘凯丽
  Date: 2020/10/29
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<html>
<head>
    <title>添加蛋糕</title>
</head>
<body>
<%
    if(null==request.getAttribute("types")){
        response.sendRedirect("toAddCake");
        return;
    }
%>
<a href="${ctx }">返回首页</a>
<h1>添加新品蛋糕</h1>
<form action="addCake" method="post">
    蛋糕名称: <input type="text" name="cakeName"/><br />
    蛋糕价格(元) : <input type="text" name="cakePrice"  /><br />
    蛋糕尺寸(寸) : <input type="text" name="cakeSize" /><br />
    蛋糕类型:
    <select name="cakeType">
        <c:forEach var="type" items="${types}">
            <option value=${type.typeId}>${type.name}</option>
        </c:forEach>
    </select> <br />
    蛋糕描述: <textarea name="cakeDescription" style="height: 70px;width: 500px"></textarea><br />
    <input type="submit" value="添加蛋糕"><br />
</form>

</body>
</html>
