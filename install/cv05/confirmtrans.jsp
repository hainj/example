<%--
  Created by IntelliJ IDEA.
  User: jakub
  Date: 27.01.2017
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Potvrdit transakci</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link rel="stylesheet" href="style.css">

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <%@include file="menulogged.jsp" %>
        </c:when>
        <c:otherwise>
            <%@include file="menu.jsp" %>
        </c:otherwise>
    </c:choose>
    <form method="post"<c:choose>
        <c:when test="${requestScope.conf == 'login' }">
            action="confirml"
        </c:when>
        <c:when test="${requestScope.conf == 'trans' }">
            action="confirm">
        </c:when>
    </c:choose>
    <label for="conf" class="form-group col-sm-6 col-md-4">Potrvzení(email)</label>
    <input type="text" class="form-control" id="conf" name="confirm"/>

    <button type="submit" value="done" name="button">Send</button>

    </form>


</div>
</body>
</html>
