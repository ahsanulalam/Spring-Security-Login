<%--
  Created by IntelliJ IDEA.
  User: Ahsan
  Date: 3/29/2017
  Time: 1:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<div class="generic-container">
    Welcome to our site.

</div>
<div style="float: left;">
    <p>
        &nbsp;|&nbsp;
        <a href="<c:url value='/home'/>">Home</a>
        &nbsp;|&nbsp;
        <a href="<c:url value='/member/home'/>">Member Area</a>
        &nbsp;|&nbsp;
        <a href="<c:url value='/admin/home'/>">Admin Area</a>
    </p>
</div>
</body>
</html>
