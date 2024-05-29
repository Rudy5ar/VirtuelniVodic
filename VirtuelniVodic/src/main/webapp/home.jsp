<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Pocetna stranica</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header>
        <h1><a class="header" href="http://localhost:8080/Muzej/home.jsp">Virtuelni vodic</a></h1>
    </header>
    <nav>
    	<a href="http://localhost:8080/Muzej/home.jsp">Pocetna</a>
        <a href="http://localhost:8080/Muzej/kreiranjeTure.jsp">Kreirajte novu turu</a>
        <a href="tura/prikaziJavne">Javne ture</a>
        <a href="tura/prikaziPrivatne">Privatne ture</a>
        <a href="http://localhost:8080/Muzej/urediPredmet.jsp">Uredi predmet</a>
    </nav>
    <div class="container">
        <h2>Dobrodosli na Virtuelni vodic!</h2>
        <p>Ovo je pocetna stranica. Molimo Vas odaberite jednu od opcija iz menija iznad.</p>
    </div>
</body>
</html>
