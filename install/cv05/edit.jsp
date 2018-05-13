<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="cs">
<head>
    <title>Uprava uživatele</title>
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
    <h1>Úprava profilu</h1>
    <form class="form-vertical" action="update" method="post">
        <input type="hidden" class="form-control" name="id"  required value="<c:out value="${requestScope.editUser.id}"/>">
        <div class="form-group col-sm-6 col-md-4">
            <label for="name">Jméno*: </label>
            <input type="text" class="form-control" name="userName" id="name" required value="<c:out value="${requestScope.editUser.name}"/>">
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="date">Datum narozeni*: </label>
            <input type="date" class="form-control" name="birthdate" id="date" required value="<c:out value="${requestScope.editUser.birthDate}"/>">
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="nin">Rodné číslo*: </label>
            <input type="text" pattern="[0-9]{6}[/]{1}[0-9]{4}" class="form-control" name="idNum" id="nin" required value="<c:out value="${requestScope.editUser.nid}"/>">
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="mail">Email*: </label>
            <input type="email" class="form-control" name="email" id="mail" required value="<c:out value="${requestScope.editUser.email}"/>">
        </div>


        <div class="form-group col-sm-6 col-md-4">
            <label for="phone">Telefon: </label>
            <input type="tel" class="form-control" name="phoneNumber" id="phone" value="<c:out value="${requestScope.editUser.phone}"/>">
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="adr">Adresa: </label>
            <input type="text" class="form-control" name="address" id="adr" value="<c:out value="${requestScope.editUser.address}"/>">
        </div>

        <div class="form-group col-sm-offset-6 col-md-offset-8 col-sm-6 col-md-4">
            <button class="pull-right btn btn-success">Send</button>
        </div>

    </form>
</div>
</body>
</html>
