<%-- 
    Document   : article
    Created on : Mar 20, 2021, 8:41:01 PM
    Author     : minhv
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link href="CSS/article.css" rel="stylesheet" type="text/css"/>
        <title>Article Detail Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null}">
            <h3>You must login, please <a href="MainController?btnAction=Logout">Login</a> with account</h3>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER != null}">
            <c:if test="${requestScope.COMMENT_ERROR ==null}">
                <c:set var="requestScope.COMMENT_ERROR.descriptionError" value=""/>               
            </c:if>
            
            <c:set var="articledetail" value="${sessionScope.ARTICLE}">
            </c:set>
            <a href="MainController?btnAction=Logout" class="logout">Logout</a>
            <a href="MainController?btnAction=Search" class="back">Back Home Page</a>
            
            <h1>Welcome ${sessionScope.LOGIN_USER.fullName}</h1><br><br><br><br>        

            <form action="MainController" method="POST">
                
                <div class="row">
                    <div class="col-md-6">
                        <div class="showbutton">
                            <h2>${articledetail.title}</h2>
                            <img src="${articledetail.img}" alt="${articledetail.title}" class="img-thumbnail" width="100%" height="100%"/>
                            <p>${articledetail.description}</p>
                            <h5>Like ${articledetail.upvote}    Dislike ${articledetail.downvote}</h5>
                            <p>
                                Posted by ${articledetail.email} in ${articledetail.createDate}
                                <c:if test="${sessionScope.LOGIN_USER.email == articledetail.email && sessionScope.LOGIN_USER.roleID == '2'}">
                                    <input type="hidden" name="txtArticleID" value="${articledetail.articleID}"/>
                                    <input type="submit" class="button" name="btnAction" value="Delete Article" onclick="return confirm('Are you sure you want to delete this article?');"/>
                                </c:if>
                            </p>
                        </div>
                        <c:if test="${sessionScope.LOGIN_USER.roleID == '2'}">     
                            <div>
                                <textarea name="txtComment" maxlength="1000" cols="100,05" rows="3"></textarea>
                                ${requestScope.COMMENT_ERROR.descriptionError}
                                <input type="hidden" name="txtArticleID" value="${articledetail.articleID}"/>
                                <input type="submit" name="btnAction" value="Comment"/>
                            </div><br>
                        </c:if>   
                        <div>
                            <h3>Comment</h3>
                            <c:if test="${sessionScope.COMMENT !=null}">
                                <c:forEach var="comment" items="${sessionScope.COMMENT}">
                                    <div class="showbutton">
                                        <h4>${comment.email}</h4> at ${comment.commentDate}
                                        <h5>${comment.description}</h5>
                                            <c:if test="${comment.email == sessionScope.LOGIN_USER.email}">
                                                <input type="hidden" name="txtCommentID" value="${comment.id}"/>
                                                <input type="submit" class="button" name="btnAction" value="Delete Comment" onclick="return confirm('Are you sure you want to delete this comment?');"/>
                                            </c:if>                                       
                                    </div><br><br><br>

                                </c:forEach>
                            </c:if>
                            <c:if test="${sessionScope.COMMENT ==null}">
                                No comment in this article                                   
                            </c:if>
                        </div>
                    </div>
                   
                </div>
                
            </form>
        </c:if>
    </body>
</html>
