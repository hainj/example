<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="cs">
<head>
    <title>Vytvořit Šablonu</title>
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
    <c:if test="${not empty requestScope.error}">
        <div class="alert alert-danger">
            <c:out value="${requestScope.error}"/>
        </div>
    </c:if>
    <h1>Platební příkaz</h1>


    <form action="template" class="form-vertical" method="post">
        <div class="form-group col-sm-6 col-md-4">
            <label for="tempn">Název šablony*: </label>
            <c:choose>
                <c:when test="${not empty requestScope.template.name}">
                    <input type="text" class="form-control" name="tempName" id="tempn"
                           value="<c:out value="${requestScope.template.name}"/>" required>
                </c:when>
                <c:otherwise>
                    <input type="text" class="form-control" name="tempName" id="tempn" required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="accnum">Číslo účtu*: </label>
            <c:choose>
                <c:when test="${not empty requestScope.template.accNumber}">
                    <input type="text" class="form-control" name="accNumber"
                           value="<c:out value="${requestScope.template.accNumber}"/>" id="accnum" required>
                </c:when>
                <c:otherwise>
                    <input type="text" class="form-control" name="accNumber"
                           id="accnum">
                </c:otherwise>
            </c:choose></div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="bankc">Kód banky*: </label>
            <select name="bankCode" class="form-control" id="bankc" required>
                <c:choose>
                    <c:when test="${not empty requestScope.template.bankCode}">
                        <option value="0100" <c:if
                                test="${requestScope.template.bankCode eq '0100'}">selected</c:if>>0100 - Komerční banka
                        </option>
                        <option value="0300" <c:if
                                test="${requestScope.template.bankCode eq '0300'}">selected</c:if>>0300 - Poštovní
                            spořitelna
                        </option>
                        <option value="0800" <c:if
                                test="${requestScope.template.bankCode eq '0800'}">selected</c:if>>0800 - Česká
                            spořitelna
                        </option>
                        <option value="0000" <c:if
                                test="${requestScope.template.bankCode eq '0000'}">selected</c:if>>0000 - Moje banka
                        </option>
                    </c:when>
                    <c:otherwise>
                        <option value="0100">0100 - Komerční banka</option>
                        <option value="0300">0300 - Poštovní spořitelna</option>
                        <option value="0800">0800 - Česká spořitelna</option>
                        <option value="0000" selected>0000 - Moje banka</option>
                    </c:otherwise>
                </c:choose>
            </select>
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="pay">Částka*: </label>
            <c:choose>
                <c:when test="${ not empty requestScope.template.amount}">
                    <input type="text" class="form-control" name="amount"
                           value="<c:out value="${requestScope.template.amount}"/>" id="pay" required>
                </c:when>
                <c:otherwise>
                    <input type="text" class="form-control" name="amount" id="pay" required>
                </c:otherwise>

            </c:choose>
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="const">Konstantní symbol: </label>
            <c:choose>
                <c:when test="${ not empty requestScope.template.constSymbol}">
                    <input type="text" class="form-control" name="constSym" id="const"
                           value="<c:out value="${requestScope.template.constSymbol}"/>">
                </c:when>
                <c:otherwise>
                    <input type="text" class="form-control" name="constSym" id="const">
                </c:otherwise>

            </c:choose>
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="var">Variabilní symbol: </label>
            <c:choose>
                <c:when test="${ not empty requestScope.template.varSymbol}">
                    <input type="text" class="form-control" name="varSym" id="var"
                           value="<c:out value="${requestScope.template.varSymbol}"/>">
                </c:when>
                <c:otherwise>
                    <input type="text" class="form-control" name="varSym" id="var">
                </c:otherwise>

            </c:choose>
        </div>
        <div class="form-group col-sm-6 col-md-4">
            <label for="symb">Specifický symbol: </label>
            <c:choose>
                <c:when test="${ not empty requestScope.template.constSymbol}">
                    <input type="text" class="form-control" name="specSym" id="symb"
                           value="<c:out value="${requestScope.template.specSymbol}"/>">
                </c:when>
                <c:otherwise>
                    <input type="text" class="form-control" name="specSym" id="symb">
                </c:otherwise>

            </c:choose>
        </div>

        <div class="form-group col-sm-6 col-md-4">
            <label for="mess">Zpráva: </label>
            <c:choose>
                <c:when test="${ not empty requestScope.template.message}">
            <textarea class="form-control" name="message" id="mess"><c:out
                    value="${requestScope.template.message}"/></textarea>
                </c:when>
                <c:otherwise>
                    <textarea class="form-control" name="message" id="mess"></textarea>
                </c:otherwise>

            </c:choose>
        </div>


        <c:choose>
            <c:when test="${not empty requestScope.template.id}">
                <input type="hidden" name="id" value="<c:out value="${requestScope.template.id}"/>">
                <div class="form-group col-md-offset-8 col-sm-6 col-md-4">
                    <button class="pull-right btn btn-success" name="send" value="update" type="submit">Odeslat šablonu
                    </button>
                </div>
            </c:when>
            <c:otherwise>
                <div class="form-group col-md-offset-8 col-sm-6 col-md-4">
                    <button class="pull-right btn btn-success" name="send" value="create" type="submit">Vytvořit šablonu
                    </button>
                </div>
            </c:otherwise>
        </c:choose>


    </form>
</div>
</body>
</html>