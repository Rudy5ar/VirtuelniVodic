<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Umetnicka dela</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<jsp:include page="../header.jsp" />
<h1>Lista umetnickih dela</h1>
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
</body>
</html>
