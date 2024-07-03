<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Pocetna stranica</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h1><a class="header" href="http://localhost:8080/Muzej/home.jsp">Virtuelni vodic</a></h1>
    </header>
    <nav>
    	<a href="http://localhost:8080/Muzej/home.jsp">Pocetna</a>
        <a href="http://localhost:8080/Muzej/login">Ulogovanje</a>
        <a href="http://localhost:8080/Muzej/kreiranjeTure.jsp">Kreirajte novu turu</a>
        <a href="tura/prikaziJavne">Javne ture</a>
        <a href="tura/prikaziPrivatne">Privatne ture</a>
        <a href="http://localhost:8080/Muzej/kreiranjeClanka.jsp">Kreiranje clanka</a>
        <a href="http://localhost:8080/Muzej/urediPredmet.jsp">Uredi predmet</a>
    </nav>
    <div class="container">
		<h1>Clanak nije uspesno sacuvan.</h1>
    </div>
</body>
</html>
