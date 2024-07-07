<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
<title>Kreiranje umetnickog predmeta</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">
	<h2>Dodaj novi umetnicki predmet</h2>
	<form action="kreirajUmetnickoDelo" method="post">
		<table>
			<tr>
				<td>Naziv</td>
				<td><input type="text" id="naziv" name="naziv"></td>
			</tr>
			<tr>
				<td>Opis</td>
				<td><input type="text" id="opis" name="opis"></td>
			</tr>
			<tr>
				<td>Datum</td>
				<td><input type="date" id="datum" name="datum"></td>
			</tr>
			<tr>
				<td>Geografska duzina</td>
				<td><input type="text" id="geografskaDuzina" name="geografskaDuzina"></td>
			</tr>
			<tr>
				<td>Geografska sirina</td>
				<td><input type="text" id="geografskaSirina" name="geografskaSirina"></td>
			</tr>	
			<tr>
				<td>Umetnik</td>
				<td>
					<select id="umetnikId" name="umetnikId">
        				<c:forEach items="${sviUmetnici}" var="umetnik">
            				<option value="${umetnik.idUmetnik}">${umetnik.ime}</option>
        				</c:forEach>
    				</select>
				</td>
			</tr>
			<tr>
				<td>Epoha</td>
				<td>
					<select class="veciSelect" id="epohe" name="epohe" multiple>
        				<c:forEach items="${sveEpohe}" var="epoha">
            				<option value="${epoha.idEpoha}">${epoha.naziv}</option>
        				</c:forEach>
    				</select>
				</td>
			</tr>
			<tr>
				<td>Kratak opis</td>
				<td><input type="text" id="opstost1" name="opstost1"></td>
			</tr>
			<tr>
				<td>Detaljniji opis</td>
				<td><input type="text" id="opstost2" name="opstost2"></td>
			</tr>
			<tr>
				<td>Veoma detaljan opis</td>
				<td><input type="text" id="opstost3" name="opstost3"></td>
			</tr>
		</table>
		<input type="submit" value="Sacuvaj"/>
	</form>
		<a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
	</div>
</body>
</html>