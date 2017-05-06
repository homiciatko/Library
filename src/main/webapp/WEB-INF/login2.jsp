<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta id="dynamicViewport" name="viewport" content="initial-scale=1">
    <title>Library App</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/main.css"/>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery/jquery-2.2.4.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/bootstrap/bootstrap.js"></script>
</head>
<body>
<div class="page-wrapper">
    <header>

    </header>
    <div class="clearfix"></div>
    <div class="page-content">
        <c:url value="/login" var="loginURL" />

        <div class="container">

            <c:if test="${param.logout != null}">
                <div class="alert alert-success fade in">
                    <a class="close" data-dismiss="alert" href="#">&times;</a>
                    <p>You've logged out</p>
                </div>
            </c:if>
            <c:if test="${param.register != null}">
                <div class="alert alert-info fade in">
                    <a class="close" data-dismiss="alert" href="#">&times;</a>
                    <p>Register successful. You can log in</p>
                </div>
            </c:if>
            <c:if test="${param.error != null}">
                <div class="alert alert-danger fade in">
                    <a class="close" data-dismiss="alert" href="#">&times;</a>
                    <p>Username or password is incorrect</p>
                </div>
            </c:if>

            <div class="card card-container">
                <h4> Login to your account</h4>
                <form action="${loginURL}" method="post" class="form-signin">
                    <input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                    <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                    <div id="remember" class="checkbox">
                        <label>
                            <input type="checkbox" value="remember-me"> Remember me
                        </label>
                    </div>
                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
                </form>
                <div class="margin-bottom-10">
                    Don't have account? <a href="" class="register">Create account</a>
                </div>
            </div>
        </div>
    </div>
</div>

<footer>

</footer>

</body>
</html>
