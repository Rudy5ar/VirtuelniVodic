<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kreiranje ture</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h2>Kreiranje ture</h2>
       
		<form action="tura/kreirajTuru" method="post">
        <table>
        	<tr>
        		<td>Naziv</td>
        		<td><input type="text" name="naziv" value="${predmet.naziv}"></td>
        	</tr>
        	<tr>
        		<td>Opis</td>
        		<td><input type="text" name="opis" value="${predmet.opis}"></td>
        	</tr>
        	
        	<!--
        	
        	Ovde ce biti na neki nacin da se dodaju djela u turu, mozda iz nekog padajuceg menija
        	ili nesto tako vidjecemo.. nema za to ni bek
        	-->
        </table>
        <input type="submit" value="Sacuvaj">
        </form>
       
        <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
    </div>
</body>
</html>
