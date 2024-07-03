<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Uredjivanje predmeta</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<header>
		<h1>
			<a class="header" href="http://localhost:8080/Muzej/home.jsp">Virtuelni
				vodic</a>
		</h1>
	</header>
	<nav>
		<a href="http://localhost:8080/Muzej/home.jsp">Pocetna</a> <a
			href="http://localhost:8080/Muzej/login">Ulogovanje</a> <a
			href="http://localhost:8080/Muzej/kreiranjeTure.jsp">Kreirajte
			novu turu</a> <a href="tura/prikaziJavne">Javne ture</a> <a
			href="tura/prikaziPrivatne">Privatne ture</a> <a
			href="http://localhost:8080/Muzej/urediPredmet.jsp">Uredi predmet</a>
	</nav>
	<div class="container">
		<form action="urediDelo">
			<table>
				<tr>
					<td>Naziv</td>
					<td><input type="text" name="naziv" value="${predmet.naziv}"></td>
				</tr>
				<tr>
					<td>Opis</td>
					<td><input type="text" name="opis" value="${predmet.opis}"></td>
				</tr>
				<tr>
					<td>Datum nastanka</td>
					<td><input type="date" name="datum" value="${predmet.datum}"></td>
				</tr>
				<tr>
					<td>Geografska sirina</td>
					<td><input type="number" name="geografskaSirina"
						value="${predmet.geografskaSirina}"></td>
				</tr>
				<tr>
					<td>Geografska duzina</td>
					<td><input type="number" name="geografskaDuzina"
						value="${predmet.geografskaDuzina}"></td>
				</tr>
				<tr>
					<td>Najnizi nivo opstosti</td>
					<td><input type="text" name="opstost1"
						value="${predmet.opstost1}"></td>
				</tr>
				<tr>
					<td>Srednji nivo opstosti</td>
					<td><input type="text" name="opstost2"
						value="${predmet.opstost2}"></td>
				</tr>
				<tr>
					<td>Najvisi nivo opstosti</td>
					<td><input type="text" name="opstost3"
						value="${predmet.opstost3}"></td>
				</tr>
				<tr>
					<td>Epoha</td>
					<td><select name="epoha">
							<c:forEach items="${epohe }" var="e">
								<option value="${e.idEpoha }">${e.naziv }</option>
							</c:forEach>
					</select></td>
				</tr>
				<!--
        	
        	Ovo treba skontati kako da izabere postojeceg umjetnika ili doda novog
        	<tr>
        		<td>Umetnik</td>
        		<td><input type="text" name="opstost3" value="${predmet.opstost3}"></td>
        	</tr>
        	-->
			</table>
		</form>
	</div>
</body>
</html>
