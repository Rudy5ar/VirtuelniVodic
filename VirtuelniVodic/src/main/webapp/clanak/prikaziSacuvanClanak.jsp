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
    <jsp:include page="../header.jsp"/>
    <div class="container">
        <h2>Novi clanak je uspesno sacuvan</h2>
        <h3>Naziv: ${clanak.naziv}</h3>
        <p>Tekst: ${clanak.tekst}</p>
        <p>Datum Kreiranja: ${clanak.datumKreiranja}</p>
        <p>Autor: ${clanak.korisnik.korisnickoIme}</p>
    </div>
</body>
</html>
