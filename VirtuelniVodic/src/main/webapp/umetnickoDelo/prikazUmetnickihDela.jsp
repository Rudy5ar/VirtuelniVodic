<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Umetnicka dela</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container">
    <h2>Lista umetnickih predmeta</h2>
    <table>

        <tr>
            <th>Naziv</th>
            <th>Opis</th>
            <th>Datum</th>
            <th>Geografska duzina</th>
            <th>Geografska sirina</th>
            <th>Akcije</th>
        </tr>
        <c:forEach var="umetnickoDelo" items="${svaDela}">
            <tr>
                <td>${umetnickoDelo.naziv}</td>
                <td>${umetnickoDelo.opis}</td>
                <td>${umetnickoDelo.datum}</td>
                <td>${umetnickoDelo.geografskaDuzina}</td>
                <td>${umetnickoDelo.geografskaSirina}</td>
                <td><a href="${pageContext.request.contextPath}/umetnickoDelo/edit/${umetnickoDelo.idUmetnickoDelo}">Izmeni</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
