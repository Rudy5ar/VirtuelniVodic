<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalji Umetničkog Dela</title>
</head>
<body>
    <h1>Detalji Umetničkog Dela</h1>
        <p><strong>Naziv:</strong> ${umetnickoDelo.naziv}</p>
        <p><strong>Opis:</strong> ${umetnickoDelo.opis}</p>
        <p><strong>Datum:</strong> ${umetnickoDelo.datum}</p>
        <p><strong>Geografska Dužina:</strong> ${umetnickoDelo.geografskaDuzina}</p>
        <p><strong>Geografska Širina:</strong> ${umetnickoDelo.geografskaSirina}</p>
        <p><strong>Umetnik:</strong> ${umetnickoDelo.umetnik.ime}</p>
        <p><strong>Opstost1:</strong> ${umetnickoDelo.opstost1}</p>
        <p><strong>Opstost2:</strong> ${umetnickoDelo.opstost2}</p>
        <p><strong>Opstost3:</strong> ${umetnickoDelo.opstost3}</p>
    <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
</body>
</html>