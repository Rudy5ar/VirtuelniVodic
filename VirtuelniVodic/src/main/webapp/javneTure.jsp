<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Javne ture</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <h2>Javne Ture</h2>
    <table>
        <thead>
        <tr>
            <th>Naziv ture</th>
            <th>Opis ture</th>
            <th></th>
            <sec:authorize access="isAuthenticated()">
                <th></th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tura" items="${listaJavnih}">
            <tr>
                <td>
                    <a href="http://localhost:8080/Muzej/umetnickoDelo/delaUTuri?idTure=${tura.idTura }">${tura.naziv}</a>
                </td>
                <td>${tura.opis}</td>
                <td><a href="http://localhost:8080/Muzej/tura/pdf?idTura=${tura.idTura }">Izveštaj o turi</a></td>
                <sec:authorize access="isAuthenticated()">
                <td><a href="http://localhost:8080/Muzej/tura/kopiranje?idTura=${tura.idTura}">Prilagođi kao privatnu turu</a></td>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
</div>
</body>
</html>