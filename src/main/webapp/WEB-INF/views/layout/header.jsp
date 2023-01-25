<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<sec:authorize access = "isAuthenticated()">
    <sec:authentication property="principal" var = "principal"/>
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="/">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <c:choose>
        <c:when test="${empty principal }">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/auth/loginForm">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/auth/joinForm">Register</a>
                </li>
            </ul>
        </c:when>
        <c:otherwise>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/boad/boadform">Posting</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/form">Infomation</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
            </ul>
        </c:otherwise>
     </c:choose>
    </div>
</nav>
<br>