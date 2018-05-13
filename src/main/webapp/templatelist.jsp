<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="cs">
<head>
    <title>Seznam šablon</title>
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
    <c:choose>
        <c:when test="${not empty requestScope.templates}">
            <table class="table table-responsive table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>Název</th>
                    <th >Cílový účet</th>
                    <th>Částka</th>
                    <th>Vytvořit příkaz</th>
                    <th  class="visible-md visible-lg">Upravit</th>
                    <th class="visible-md visible-lg">Smazat</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${requestScope.templates}" var="temp">
                    <tr>
                        <td>
                            <c:out value="${temp.name}"/>
                        </td>
                        <td>
                            <c:out value="${temp.accNumber}"/>
                        </td>
                        <td>
                            <c:out value="${temp.amount}"/>
                        </td>
                        <td>
                            <form method="post" action="transaction">
                                <input type="hidden" name="id" value="<c:out value="${temp.id}"/>"/>
                                <button class="alert alert-success" type="submit">Vytvořit příkaz</button>
                            </form>
                        </td>
                        <td  class="visible-md visible-lg">
                            <form method="post" action="edittemp">
                                <input type="hidden" name="id" value="<c:out value="${temp.id}"/>"/>
                                <button class="alert alert-warning" type="submit">Upravit</button>
                            </form>
                        </td >

                        <td  class="visible-md visible-lg">
                            <form method="post" action="deletetemp">
                                <input type="hidden" name="id" value="<c:out value="${temp.id}"/>"/>
                                <button class="alert alert-error" type="submit">Smazat</button>
                            </form>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul class="pagination">
                <c:forEach begin="0" end="${requestScope.total}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.current eq i}">
                            <li class="active"><a href="temp?cpage=<c:out value="${i}"/>"><c:out value="${i}"/></a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="temp?cpage=<c:out value="${i}"/>"><c:out value="${i}"/></a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </ul>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info">Žádné dostupné šablony</div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>