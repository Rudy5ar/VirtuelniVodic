<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<title>Prikazi Sacuvanu Turu</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="../header.jsp" />

    <div class="container">
        <h2>Kreirana Tura</h2>
        <c:if test="${not empty tura}">
            <p><strong>Naziv:</strong> ${tura.naziv}</p>
            <p><strong>Opis:</strong> ${tura.opis}</p>
            <p><strong>ID Korisnika:</strong> ${tura.korisnik.idKorisnik}</p>
            <p><strong>Status:</strong> ${uspelo}</p>

            <h3>Umetnicka Dela</h3>
            <c:if test="${not empty umetnickaDela}">
                <ul>
                    <c:forEach items="${umetnickaDela}" var="delo">
                        <li>${delo.naziv}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </c:if>
        <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
    </div>
</body>
</html>
