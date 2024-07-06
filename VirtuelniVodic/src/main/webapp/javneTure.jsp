<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Javne ture</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h2>Javne Ture</h2>
            <table>
                <thead>
                    <tr>
                        <th>Naziv ture</th>
                        <th>Opis ture</th>
                    </tr>
                </thead>
                <tbody>
        			<c:forEach var="tura" items="${listaJavnih}">
                    <tr>
                        <td><a href="http://localhost:8080/Muzej/umetnickoDelo/delaUTuri?idTure=${tura.idTura }">${tura.naziv}</a></td>
                        <td>${tura.opis}</td>
                    </tr>
        			</c:forEach>
                </tbody>
            </table>
        <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
    </div>
</body>
</html>