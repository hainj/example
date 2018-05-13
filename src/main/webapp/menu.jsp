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
                <li <c:if test="${requestScope.page == 'index'}"> class="active" </c:if>><a href="index">Home <span class="sr-only">(current)</span></a></li>
                <li <c:if test="${requestScope.page == 'contacts'}"> class="active" </c:if>><a href="contacts">Kontakt</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#prihl" data-toggle="modal" data-target=".bs-modal-sm">Přihlásit</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="modal fade bs-modal-sm" id="myModal" tabindex="-1" role="dialog" >
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <br>
            <div class="bs-example bs-example-tabs">
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#prihl" data-toggle="tab">Přihlásit</a></li>
                </ul>
            </div>
            <div class="modal-body">
                <div id="myTabContent" class="tab-content">

                    <div class="tab-pane fade active in" id="prihl">
                        <form class="form-horizontal" method="POST" action="login">
                            <fieldset>
                                <!-- Přihlašovací formulář -->
                                <!-- Email -->
                                <div class="control-group">
                                    <label class="control-label" for="name">Uživatelské jméno:</label>
                                    <div class="controls">
                                        <input id="name" name="userName" type="text" class="form-control" required>
                                    </div>
                                </div>

                                <!-- Heslo -->
                                <div class="control-group">
                                    <label class="control-label" for="pass">Heslo:</label>
                                    <div class="controls">
                                        <input required id="pass" name="password" type="password" class="form-control" >
                                    </div>
                                </div>
                                <!-- Přihlašovací tlačítko -->
                                <div class="control-group">
                                    <label class="control-label" for="signin"></label>
                                    <div class="controls">
                                        <button id="signin" name="signin" class="btn btn-success">Přihlásit</button>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <!-- Tlačítko zavřít modal -->
                <button type="button" class="btn btn-default" data-dismiss="modal">Zavřít</button>

            </div>
        </div>
    </div>
</div>
