<%-- 
    Document   : homePage
    Created on : Mar 19, 2021, 9:17:27 PM
    Author     : minhv
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link href="CSS/homePage.css" rel="stylesheet" type="text/css"/>
        <title>Home Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null}">
            <h3>You must login, please <a href="MainController?btnAction=Logout">Login</a> with account</h3>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER != null}">
            <c:if test="${requestScope.ARTICLE_ERROR ==null}">
                <c:set var="requestScope.ARTICLE_ERROR.titleError" value=""/>     
                <c:set var="requestScope.ARTICLE_ERROR.descriptionError" value=""/>     
                <c:set var="requestScope.ARTICLE_ERROR.imgError" value=""/>     
                
            </c:if>
            <a href="MainController?btnAction=Logout" class="logout">Logout</a>
            <h1>Welcome ${sessionScope.LOGIN_USER.fullName}</h1><br>   
            

            <form action="MainController" method="POST" class="search">
                Content <input type="text"name="txtSearchArticle" value="${param.txtSearchArticle==null ?"":param.txtSearchArticle}"/>
                <input type="submit" name="btnAction" value="Search"/>
            </form><br>   



            <div class="col-md-6">
                <c:if test="${sessionScope.LOGIN_USER.roleID == '2'}">
                    <div>
                        <h4>Post new article</h4>
                        <form action="MainController" method="POST" >
                            Title <input type="text" name="txtPostTitle" required="" value="${param.txtPostTitle==null ?"":param.txtPostTitle}"/>${requestScope.ARTICLE_ERROR.titleError}</br>
                            Description <textarea name="txtPostDescription" maxlength="1000" cols="100" rows="3" required="" value="${param.txtPostDescription==null ?"":param.txtPostDescription}"></textarea>
                            ${requestScope.ARTICLE_ERROR.descriptionError}</br>
                            Img<input type="file" name="fileIMG" id="file" class="img" accept="image/*" required="" onchange="previewImage()">${requestScope.ARTICLE_ERROR.imgError}</br>
                            <input type="submit" name="btnAction" value="Post"/>
                            <input type="reset" value="reset">
                        </form>
                    </div><br><br>
                </c:if>
                <h2>News</h2>
                <c:if test="${sessionScope.LIST_ARTICLE!=null}">
                    <c:forEach var="articles" items="${sessionScope.LIST_ARTICLE}">                       
                        <section>
                            <form action="MainController" method="POST">
                                <c:url var="details" value="MainController">
                                    <c:param name="btnAction" value="ArticleDetail"></c:param>
                                    <c:param name="txtArticleID" value="${articles.articleID}"></c:param>
                                </c:url>
                                <h2><a href="${details}">${articles.title}</a></h2>
                                <img src="${articles.img}" width="100%" height="100%"/>
                                <p>${articles.description}</p>
                                <p>Like:${articles.upvote}  Dislike: ${articles.downvote}</p> 
                                <p>Posted by ${articles.email} in ${articles.createDate}</p>
                            </form><br><br>
                        </section>
                    </c:forEach>

                    <c:if test="${requestScope.PAGE!=null && requestScope.PAGE > 1}">
                        <nav>
                            <ul class="pagination justify-content-center">                       
                                <c:forEach var="count" begin="1" end="${requestScope.PAGE}">
                                    <li class="page-item">
                                        <a class="page-link" href="MainController?btnAction=Search&pagenum=${count}&txtSearchArticle=${param.txtSearchArticle}">${count}</a>
                                    </li>
                                </c:forEach>                        
                            </ul>
                        </nav>
                    </c:if>

                </c:if>
                <c:if test="${requestScope.MESSAGE!=null}">
                    <h3>${requestScope.MESSAGE}</h3>
                </c:if>
            </div>

            



        </c:if>
            <script>
                function previewImage(){
                    var file=document.getElementById("file").files;
                    if(file.length >0){
                        var fileReader=new FileReader();
                        fileReader.onload =function (event){
                            document.getElementById("preview").setAttribute("src",event.target.result);
                        };
                        fileReader.readAsDataURL(file[0]);
                    }
                }
            
            </script>      

    </body>
</html>
