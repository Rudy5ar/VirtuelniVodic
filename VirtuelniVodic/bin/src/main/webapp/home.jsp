<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pocetna stranica</title>
</head>
<body>
<a href="/Muzej/ture/kreiranjeTure.jsp">Kreirajte novu turu</a><br>
	Ovde ce biti dugmad za ostale stranice, mozda lista svih
	tura iz baze <br>
	<c:forEach items="${ svaDela}" var="v">
		${v.naziv} ${v.opis}<br>
	</c:forEach>
</body>
</html>