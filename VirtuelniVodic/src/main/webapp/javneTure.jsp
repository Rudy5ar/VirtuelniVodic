<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Javne ture</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="http://localhost:8080/Muzej/css/style.css">
</head>
<body>
    <header>
        <h1><a class="header" href="http://localhost:8080/Muzej/home.jsp">Virtuelni vodic</a></h1>
    </header>
    <nav>
        <a href="http://localhost:8080/Muzej/home.jsp">Pocetna</a>
        <a href="http://localhost:8080/Muzej/login">Ulogovanje</a> 
        <a href="http://localhost:8080/Muzej/kreiranjeTure.jsp">Kreirajte novu turu</a>
        <a href="http://localhost:8080/Muzej/tura/prikaziJavne">Javne ture</a>
        <a href="http://localhost:8080/Muzej/tura/prikaziPrivatne">Privatne ture</a>
    </nav>

    <div class="container">
        <h2>Javne Ture</h2>
        <c:forEach var="tura" items="${listaJavnih}">
            <table>
                <thead>
                    <tr>
                        <th>Naziv ture</th>
                        <th>Opis ture</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><a href="http://localhost:8080/Muzej/umetnickoDelo/delaUTuri?idTure=${tura.idTura }">${tura.naziv}</a></td>
                        <td>${tura.opis}</td>
                        <td><a href="http://localhost:8080/Muzej/tura/pdf?idTura=${tura.idTura }">Izveštaj o turu</a></td>
                    </tr>
                </tbody>
            </table>
        </c:forEach>
        <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
    </div>
</body>
</html>