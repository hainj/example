<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="cs">
<head>
    <title>Pohyby na účtu</title>
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

    <c:choose>
        <c:when test="${not empty requestScope.transactions}">
            <table class="table table-responsive table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>Číslo účtu</th>
                    <th class="visible-md visible-lg">Datum splatnosti</th>
                    <th>Částka</th>

                    <th>Rozbalit</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${requestScope.transactions}" var="transaction">
                    <tr data-toggle="collapse" data-target="#<c:out value="${transaction.id}"/>"
                        class="clickable success">
                        <td class="col-md-3"><c:out value="${transaction.accNumber}"/>/<c:out
                                value="${transaction.bankCode}"/></td>
                        <td class="col-md-3 visible-md visible-lg"><c:out value="${transaction.date}"/></td>
                        <td class="col-md-3"><c:out value="${transaction.amount}"/> Kč</td>

                        <td><span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></td>
                    </tr>
                    <tr id="<c:out value="${transaction.id}"/>" class="collapse">
                        <td colspan="4">
                            <div>
                                <div class="col-sm-6 col-md-4">
                                    <label>Číslo účtu:</label>
                                    <c:out value="${transaction.accNumber}"/>
                                </div>
                                <div class="col-sm-6 col-md-4">
                                    <label>Variabilni symbol:</label>
                                    <c:out value="${transaction.varSymbol}"/>
                                </div>
                                <div class="col-sm-6 col-md-4">
                                    <label>Konstantni symbol:</label>
                                    <c:out value="${transaction.constSymbol}"/>
                                </div>
                                <div>
                                    <div class="col-sm-6 col-md-4">
                                        <label>Částka:</label>
                                        <c:out value="${transaction.amount}"/>
                                    </div>
                                    <div class="col-sm-6 col-md-4">
                                        <label>Datum splatnosti:</label>
                                        <c:out value="${transaction.date}"/>
                                    </div>

                                    <div class="clearfix visible-md-block visible-lg-block"></div>
                                    <div class="col-sm-12 col-md-12">
                                        <label>Zpráva:</label>
                                        <c:out value="${transaction.message}"/>
                                    </div>
                                </div>
                            </div>
                        </td>

                    </tr>

                </c:forEach>
                </tbody>
            </table>
            <ul class="pagination">

                <c:forEach begin="0" end="${requestScope.total}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.current eq i}">
                            <li class="active"><a href="history?cpage=<c:out value="${i}"/>"><c:out value="${i}"/></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="history?cpage=<c:out value="${i}"/>"><c:out value="${i}"/></a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </ul>
            <form method="get" action="pdf">
                <button type="submit">Pdf výpis</button>
            </form>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info">Žádné transakce</div>
        </c:otherwise>
    </c:choose>

</div>
</body>
</html>