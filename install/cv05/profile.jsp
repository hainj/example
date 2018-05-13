<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="cs">
<head>
    <title>Profil</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">

    <%@include file="menulogged.jsp" %>
    <div class="col-md-6">
        <h3><c:out value="${sessionScope.user.name}"/></h3>
        <table class="table table-stripped">
            <tr>
                <td class="col-md-2">Email</td>
                <td class="col-md-2"><c:out value="${sessionScope.user.email}"/></td>

            <tr>
                <td class="col-md-2">Role</td>
                <td class="col-md-2"><c:out value="${sessionScope.user.idRights.role}"/></td>
            </tr>
            <c:if test="${not empty sessionScope.user.phone}">
                <tr>
                    <td class="col-md-2">Telefon</td>
                    <td class="col-md-2"><c:out value="${sessionScope.user.phone}"/></td>
                </tr>
            </c:if>
            <c:if test="${not empty sessionScope.user.address}">
                <tr>
                    <td class="col-md-2">Adresa</td>
                    <td class="col-md-2"><c:out value="${sessionScope.user.address}"/></td>
                </tr>
            </c:if>
            <tr>
                <td class="col-md-2">Datum narození</td>
                <td class="col-md-2"><c:out value="${sessionScope.user.birthDate}"/></td>
            </tr>
            <tr>
                <td class="col-md-2">Pohlaví</td>
                <td class="col-md-2"><c:out value="${sessionScope.user.gender}"/></td>
            </tr>
            <c:if test="${not empty sessionScope.account}">
                <tr>
                    <td class="col-md-2">Číslo účtu</td>
                    <td class="col-md-2"><c:out value="${sessionScope.account.accNumber}"/></td>
                </tr>
                <tr>
                    <td class="col-md-2">Číslo karty</td>
                    <td class="col-md-2"><c:out value="${sessionScope.account.cardNumber}"/></td>
                </tr>
                <tr>
                    <td class="col-md-2">Stav účtu</td>
                    <td class="col-md-2"><c:out value="${sessionScope.account.balance}"/> Kč</td>
                </tr>
            </c:if>
            <tr>
                <td colspan="2" class="col-md-4">
                    <form method="post" action="edit">
                        <input type="hidden" name="id" value="<c:out value="${sessionScope.user.id}"/>">
                        <button type="submit">Edit</button>
                    </form>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
