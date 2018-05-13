<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="cs">
<head>
    <title>Seznam uživatelů</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
    <jsp:include page="menulogged.jsp"/>
    <c:if test="${not empty requestScope.error}">
        <div class="alert alert-danger">
            <c:out value="${requestScope.error}"/>
        </div>
    </c:if>
    <c:if test="${not empty requestScope.success}">
        <div class="alert alert-success">
            <c:out value="${requestScope.success}"/>
        </div>
    </c:if>
    <table class="table table-responsive table-striped table-hover table-bordered">
        <thead>
        <tr>
            <th>Jméno</th>
            <th class="visible-md visible-lg">Rodné číslo</th>
            <th>Email</th>
            <c:if test="${sessionScope.rights.modifyUser == '1'}">
                <th class="visible-md visible-lg">Edit</th>
            </c:if>
            <c:if test="${sessionScope.rights.deleteUser == '1'}">
                <th>Delete</th>
            </c:if>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td>
                    <c:out value="${user.name}"/>
                </td>
                <td class="visible-md visible-lg">
                    <c:out value="${user.nid}"/>
                </td>
                <td>
                    <c:out value="${user.email}"/>
                </td>
                <c:if test="${sessionScope.rights.modifyUser == '1'}">
                    <td class="visible-md visible-lg">
                        <form method="post" action="edit">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                            <button type="submit">Edit</button>
                        </form>
                    </td>
                </c:if>
                <c:if test="${sessionScope.rights.deleteUser == '1'}">
                    <td>
                        <form method="post" action="delete">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <ul class="pagination">

            <c:forEach begin="0" end="${requestScope.total}" var="i">
                <c:choose>
                    <c:when test="${requestScope.current eq i}">
                        <li class="active"><a href="users?cpage=<c:out value="${i}"/>"><c:out value="${i}"/></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="users?cpage=<c:out value="${i}"/>"><c:out value="${i}"/></a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

    </ul>

</div>
</body>
</html>