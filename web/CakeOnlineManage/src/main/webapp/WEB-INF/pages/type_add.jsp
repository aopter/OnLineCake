<%--
  Created by IntelliJ IDEA.
  User: 刘凯丽
  Date: 2020/10/29
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<html>
<head>
    <title>增加类型</title>
</head>
<body>
<a href="${ctx}">返回首页</a><br/>
<form action="addType" method="post">
    父类型：
    <select name="cakeType">
        <option value="0"></option>
        <c:forEach var="type" items="${types}">
            <option value=${type.typeId}>${type.name}</option>
        </c:forEach>
    </select>
    <br/>
    类型名称：<input type="text" name="typeName"/>
    <br/><br/>
    <input type="submit" value="提交">
</form>

</select> <br/>
</body>
</html>
