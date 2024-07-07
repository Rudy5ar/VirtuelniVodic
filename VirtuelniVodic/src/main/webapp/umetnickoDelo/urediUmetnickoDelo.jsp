<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Izmena umetnickog predmeta</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>
<jsp:include page="../header.jsp" />
<h2>Izmeni umetnicki predmet</h2>
<form action="http://localhost:8080/Muzej/umetnickoDelo/edit/${umetnickoDelo.idUmetnickoDelo}" method="post">
    <input type="hidden" name="idUmetnickoDelo" value="${umetnickoDelo.idUmetnickoDelo}"/>
<table>
	<tr>
		<td><label for="naziv">Naziv:</label></td>
		<td><input type="text" id="naziv" name="naziv" value="${umetnickoDelo.naziv}"/></td>
	</tr>
	<tr>
		<td><label for="opis">Opis:</label></td>
		<td><input type="text" id="opis" name="opis" value="${umetnickoDelo.opis}"/></td>
	</tr>
	<tr>
		<td><label for="datum">Datum:</label></td>
		<td><input type="date" id="datum" name="datum" value="${umetnickoDelo.datum}" /></td>
	</tr>
	<tr>
		<td><label for="geografskaDuzina">Geografska duzina:</label></td>
		<td><input type="text" id="geografskaDuzina" name="geografskaDuzina" value="${umetnickoDelo.geografskaDuzina}"/></td>
	</tr>
	<tr>
		<td><label for="geografskaSirina">Geografska sirina:</label></td>
		<td><input type="text" id="geografskaSirina" name="geografskaSirina" value="${umetnickoDelo.geografskaSirina}"/></td>
	</tr>
	<tr>
		<td><label for="umetnikId">Umetnik:</label><br></td>
		<td>
			<select id="umetnikId" name="umetnikId">
        	<c:forEach items="${allUmetnici}" var="umetnik">
            	<option value="${umetnik.idUmetnik}" ${umetnickoDelo.umetnik.idUmetnik == umetnik.idUmetnik ? 'selected' : ''}>${umetnik.ime}</option>
        	</c:forEach>
    		</select>
		</td>
	</tr>
	<tr>
		<td><label for="epoheIds">Epohe:</label></td>
		<td>
			<select class="veciSelect" id="epoheIds" name="epoheIds" multiple>
        	<c:forEach items="${allEpohe}" var="epoha">
            	<option value="${epoha.idEpoha}" ${umetnickoDelo.epohas.stream().anyMatch(epohaItem -> epohaItem.idEpoha == epoha.idEpoha).orElse(false) ? 'selected' : ''}>${epoha.naziv}</option>
        	</c:forEach>
    		</select>
		</td>
	</tr>
    </table>
    <input type="submit" value="Sacuvaj"/>
</form>
</body>
</html>
