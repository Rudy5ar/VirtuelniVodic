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
	Ovde ce biti dugmad za ostale stranice, mozda lista svih tura iz baze
	<br> Lista postojecih tura:
	<table>
		<tr>
			<td>Naziv ture</td>
			<td>Opis ture</td>
		</tr>
		<tr>
			<c:forEach items="${ svaDela}" var="v">
				<td><a href="http://localhost:8080/Muzej/pregledPredmeta.jsp/${v.idUmetnickoDelo}">${v.naziv}</a></td>
				<td>${v.opis}</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>