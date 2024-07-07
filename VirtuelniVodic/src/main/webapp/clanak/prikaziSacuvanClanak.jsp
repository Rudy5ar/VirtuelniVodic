<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
        <h1>Novi clanak je uspesno sacuvan</h1>
        <p>Naziv: ${clanak.naziv}</p>
        <p>Tekst: ${clanak.tekst}</p>
        <p>Datum Kreiranja: ${clanak.datumKreiranja}</p>
        <p>Autor: ${clanak.korisnik.idKorisnik}</p> <!-- Assuming korisnik has an id field -->
    </div>
</body>
</html>
