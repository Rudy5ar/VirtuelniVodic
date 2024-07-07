<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
<title>Kreiranje Clanka</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<header>
		<h1>
			<a class="header" href="http://localhost:8080/Muzej/home.jsp">Virtuelni vodic</a>
		</h1>
	</header>
	<nav>
		<a href="http://localhost:8080/Muzej/home.jsp">Pocetna</a> 
		<a href="http://localhost:8080/Muzej/login">Ulogovanje</a> 
		<a href="http://localhost:8080/Muzej/kreiranjeTure.jsp">Kreirajte novu turu</a> 
		<a href="tura/prikaziJavne">Javne ture</a> 
		<a href="tura/prikaziPrivatne">Privatne ture</a> 
		<a href="http://localhost:8080/Muzej/urediPredmet.jsp">Uredi predmet</a>
	</nav>
	<div class="container">
		<form action="${pageContext.request.contextPath}/clanak/kreirajClanak" method="post">
			<table>
				<tr>
					<td>Naziv:</td>
					<td><input type="text" name="naziv"></td>
				</tr>
				<tr>
					<td>Tekst:</td>
					<td><input type="text" name="tekst"></td>
				</tr>
			</table>
			<input type="submit" value="Sacuvaj">
		</form>
		
		<c:if test="${not empty requestScope.uspeo}">
            <p>${requestScope.uspeo}</p>
        </c:if>
        <c:if test="${not empty requestScope.uspeo}">
            <p>${requestScope.uspeo}</p>
        </c:if>
	</div>
</body>
</html>
