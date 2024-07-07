<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prikazi Promenjenu Turu</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="../header.jsp" />

    <div class="container">
        <h2>Promenjena tura ${tura.naziv}</h2>
        <c:if test="${not empty tura}">
            <p><strong>Naziv:</strong> ${tura.naziv}</p>
            <p><strong>Opis:</strong> ${tura.opis}</p>
            <p><strong>ID Korisnika:</strong> ${tura.korisnik.idKorisnik}</p>

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