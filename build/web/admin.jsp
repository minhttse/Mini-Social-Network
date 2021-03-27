<%-- 
    Document   : admin
    Created on : Mar 19, 2021, 9:17:38 PM
    Author     : minhv
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID=='2'}">
            <h3>You must login, please <a href="MainController?btnAction=Logout">Login</a> with admin account</h3>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER != null && sessionScope.LOGIN_USER.roleID=='1'}">
            <h1>Welcome admin : ${sessionScope.LOGIN_USER.fullName}</h1>
        </c:if>
    </body>
</html>
