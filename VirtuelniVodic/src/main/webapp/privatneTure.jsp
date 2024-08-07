<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Privatne ture</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="container">
        <h2>Privatne Ture</h2>
        <c:if test="${!empty listaPrivatnih}">
            <table>
                <thead>
                    <tr>
                        <th>Naziv ture</th>
                        <th>Opis ture</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                        <c:forEach var="tura" items="${listaPrivatnih}">
                            <tr>
                                <td><a href="http://localhost:8080/Muzej/umetnickoDelo/delaUTuri?idTure=${tura.idTura }">${tura.naziv}</a></td>
                                <td>${tura.opis}</td>
                                <td><a href="http://localhost:8080/Muzej/tura/kopiranje?idTura=${tura.idTura}">Prilagođi kao privatnu turu</a></td>
                            </tr>
                        </c:forEach>

                </tbody>
            </table>
        </c:if>
            <c:if test="${empty listaPrivatnih}">
                <p>Nema privatnih tura</p>
            </c:if>
        <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
    </div>
</body>
</html>