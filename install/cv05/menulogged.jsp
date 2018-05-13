<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#collapsedmenu" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index">Kiv/Pia</a>
        </div>
        <div class="collapse navbar-collapse" id="collapsedmenu">
            <ul class="nav navbar-nav">
                <li<c:if test="${requestScope.page == 'index'}"> class="active" </c:if>><a href="index">Home <span class="sr-only">(current)</span></a></li>

                <c:if test="${sessionScope.rights.transaction == 1}">
                    <li<c:if test="${requestScope.page == 'transaction'}"> class="active" </c:if>><a href="transaction">Platební příkaz <span class="sr-only"></span></a></li>
                    <li <c:if test="${requestScope.page == 'history'}"> class="active" </c:if>><a href="history?cpage=0">Pohyby na účtu <span class="sr-only"></span></a></li>
                    <li <c:if test="${requestScope.page == 'templ'}"> class="active" </c:if>><a href="temp?cpage=0">Seznam šablon<span class="sr-only"></span></a></li>
                </c:if>

                <c:if test="${sessionScope.rights.deleteUser == 1 || sessionScope.rights.modifyUser == 1}">
                    <li<c:if test="${requestScope.page == 'userlist'}"> class="active" </c:if>><a href="users?cpage=0">Seznam uživatelů <span class="sr-only"></span></a></li>
                </c:if>
                <c:if test="${sessionScope.rights.deleteUser == 1 || sessionScope.rights.modifyUser == 1}">
                    <li<c:if test="${requestScope.page == 'registration'}"> class="active" </c:if>><a href="registration">Registrace<span class="sr-only"></span></a></li>
                </c:if>
                <li <c:if test="${requestScope.page == 'contacts'}"> class="active" </c:if>><a href="contacts">Kontakt</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li  <c:if test="${requestScope.page == 'profile'}"> class="active" </c:if>>
                    <a href="profile"><c:out value="${sessionScope.user.name}"/></a>
                </li>
                <li><a href="logout">Odhlásit se</a></li>
            </ul>
        </div>
    </div>
</nav>