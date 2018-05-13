<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="cs">
<head>
    <title>Registrace</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.min.js"></script>
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
    <h1>Registrace</h1>
    <form class="form-vertical" action="registration" id="registration-form" method="post" name="regForm">
        <div class="form-group col-sm-6 col-md-4 control-group">
            <label for="name">Jméno*: </label>
            <input type="text" class="form-control" minlength="2" maxlength="300" name="userName" pattern="[A-Z]{1}[a-zA-Z ]+" id="name" required>
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="date">Datum narozeni*: </label>
            <input type="date" class="form-control" name="birthdate" max="<c:out value="${requestScope.date}"/>"id="date" required>
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="nin">Rodné číslo*: </label>
            <input type="text" class="form-control" name="idNum" id="nin" placeholder="YYMMDD/####" pattern="[0-9]{6}[/]{1}[0-9]{4}" required>
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="mail">Email*: </label>
            <input type="text" pattern="^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" class="form-control" name="email" id="mail" required>
        </div>

        <div class="form-group col-sm-6 col-md-4">
            <label for="acn">Číslo účtu*: </label>
            <input type="text" minlength="10" maxlength="10" pattern="[0-9]{10}" class="form-control"  name="accNumber" id="acn" required>
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="card">Číslo karty*: </label>
            <input type="text" minlength="16" maxlength="16" pattern="[0-9]{16}" class="form-control"  name="cardNumber" id="card" required>
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="phone">Telefon: </label>
            <input type="text" pattern="[1-9][" class="form-control" name="phoneNumber" id="phone" >
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="adr">Adresa: </label>
            <input type="text" class="form-control" minlength="3" name="address" id="adr" >
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label>Pohlaví*: </label><br>
            <div class="form-control no-border">
                <input type="radio" name="gender" value="male" checked required> Male
                <input type="radio" name="gender" value="female" required> Female
                <input type="radio" name="gender" value="other" required> Other
            </div>
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="tur">Napiste cislo <c:out value="${requestScope.number}"/>*: </label>
            <input type="number" class="form-control" name="turTest" id="tur" required>
        </div>
        <div class = "form-group col-sm-6 col-md-4">
            <label for="mess">Zpráva: </label>
            <textarea class="form-control" name="message" id="mess"></textarea>
        </div>
        <div class="form-group col-sm-offset-6 col-md-offset-8 col-sm-6 col-md-4">
            <button class="pull-right btn btn-success">Send</button>
        </div>

    </form>

</div>
</body>
</html>
