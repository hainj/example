<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="cs">
<head>
    <title>Kontakty</title>
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

    <div class="row"><h1 class="col-md-4">Kontakty</h1></div>
    <div class="row">
        <div class="col-sm-3">
            <div><strong>Technická 8</strong></div>
            <div>Plzeň</div>
            <div>Česká republika</div>
            <div>306 14</div>
            <div>+420 721 016 873</div>
            <div>hainj@students.zcu.cz</div>
        </div>
        <div class="col-sm-3">
            <strong>Otevírací doba</strong>
            <div>Po: 7:00-19:00</div>
            <div>Út: 7:00-19:00</div>
            <div>St: 8:00-17:00</div>
            <div>Čt: 7:00-19:00</div>
            <div>Pá: 7:00-17:00</div>
            <div>So: 9:00-12:00</div>
            <div>Ne: 9:00-12:00</div>
        </div>
        <div class="col-sm-6">


            <div class="map" id = "map"></div>
            <script>
                function initMap() {
                    var uluru = {lat: 49.726865, lng: 13.35224};
                    var map = new google.maps.Map(document.getElementById('map'), {
                        zoom: 15,
                        center: uluru
                    });
                    var marker = new google.maps.Marker({
                        position: uluru,
                        map: map
                    });
                }
            </script>
            <script async defer
                    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC7KLagiUBTUQq4e-mbgEqrvkbb0WG3gjE&callback=initMap">
            </script>
        </div>
    </div>
</div>
</body>
</html>
