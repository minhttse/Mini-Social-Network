<%-- 
    Document   : login
    Created on : Oct 17, 2020, 3:55:38 PM
    Author     : minhv
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="CSS/login.css">   
    </head>
    <body>       
        <div class="main-div">
            <h1>Login </h1>
            <form action="MainController" method="POST" class="main-layout">
                Email:  <input type="email" name="txtEmail" class="ID"/></br>
                Password: <input type="password" name="txtPassword" class="password"/></br>
                <input type="submit" name="btnAction" value="Login" class="login"/>
                <input type="reset" value="Reset" class="reset"/>
            </form>
            <c:if test="${sessionScope.LOGIN_INVALID !=null}">
                <h5>${sessionScope.LOGIN_INVALID}</h5>
            </c:if>
            <a href="MainController?btnAction=Create Page" class="create">Register</a> <br><br>
            
            <c:if test="${sessionScope.VERIFY_NUMBER_AGAIN !=null}">
                <h4>We sent verify code to your email, please check and input code for verify account</h4>
                <form action="MainController" method="POST" class="verify-layout">
                    Verify <input type="number" name="txtVerifyNumber" class="confirm">
                    <input type="submit" name="btnAction" value="Verify User Again" class="verify">                        
                </form>

                <c:if test="${requestScope.VERIFY_ERROR_AGAIN !=null}">
                    ${requestScope.VERIFY_ERROR_AGAIN}
                </c:if>
            </c:if>
        </div>
       


    </body>
</html>
