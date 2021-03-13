<%@ page import="java.util.List" %>
<%@ page import="com.lkl.entity.Type" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: 刘凯丽
  Date: 2020/10/29
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<html>
<head>
    <title>菜单列表</title>
</head>
<body>
<a href="${ctx}">返回首页</a><br/>
<c:forEach var="type" items="${container}">
    <table border="1" style="margin-bottom: 50px">
        <tr>
            <td>类型</td>
            <td>名字</td>
            <td>操作</td>
            <td>操作</td>

        </tr>
        <tr>
            <td>父类型</td>
            <td>${type.key.name}</td>
            <td><a href="deleteType?typeId=${type.key.typeId}">删除</a></td>
            <td><a href="toUpdateType?typeId=${type.key.typeId}">修改</a></td>
        </tr>
        <c:forEach var="suntype" items="${container.get(type.key)}">
            <tr>
                <td>子类型</td>
                <td>${suntype.name}</td>
                <td><a href="deleteType?typeId=${suntype.typeId}">删除</a></td>
                <td><a href="toUpdateType?typeId=${suntype.typeId}">修改</a></td>
            </tr>
        </c:forEach>
    </table>
</c:forEach>
</body>
</html>
