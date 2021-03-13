<%--
  Created by IntelliJ IDEA.
  User: 刘凯丽
  Date: 2020/10/29
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${ctx }">返回首页</a>
<h1>更新蛋糕</h1>
<form action="updateCake" method="post">
    <input type="hidden" name="cakeId" value="${cake.id}"/><br/>
    蛋糕名称: <input type="text" name="cakeName" value="${cake.name}"/><br/>
    蛋糕价格(元) : <input type="text" name="cakePrice" value="${cake.price}"/><br/>
    蛋糕尺寸(寸) : <input type="text" name="cakeSize" value="${cake.size}"/><br/>
    蛋糕类型:
    <select name="cakeType">
        <option value="${cake.type.typeId}">${cake.type.name}</option>
        <c:forEach var="type" items="${types}">
            <c:if test="${cake.type.typeId!=type.typeId}">
                <option value=${type.typeId}>${type.name}</option>
            </c:if>
        </c:forEach>
    </select> <br/>
    蛋糕描述: <textarea name="cakeDescription" style="height: 70px;width: 500px">${cake.description}</textarea><br/>
    <input type="submit" value="提交修改"><br/>
</form>

</body>
</html>
