<%--
  Created by IntelliJ IDEA.
  User: 刘凯丽
  Date: 2020/10/30
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<html>
<head>
    <title>修改菜单</title>
</head>
<body>
<form action="updateType" method="post">
    <c:if test="${pType.typeId!=0}">
        父类型：
        <select name="pTypeId" style="width: 100px">
            <option value="${pType.typeId}">${pType.name}</option>
            <c:forEach var="type" items="${types}">
                <c:if test="${pType.typeId!=type.typeId}">
                    <option value=${type.typeId}>${type.name}</option>
                </c:if>
            </c:forEach>
        </select> <br/>
    </c:if>
    <c:if test="${pType.typeId==0}">
        <input type="hidden" name="pTypeId" value="${pType.typeId}">
    </c:if>
    当前类型:<input type="text" name="typeName" value="${currentType.name}"/><br/>
    <input type="hidden" name="typeId" value="${currentType.typeId}">
    <input type="submit" value="提交修改">
</form>
</body>
</html>
