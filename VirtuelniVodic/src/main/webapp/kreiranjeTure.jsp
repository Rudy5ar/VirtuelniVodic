<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kreiranje ture</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
}

header {
    background-color: #333;
    color: #fff;
    padding: 20px;
    text-align: left;
}

nav {
    background-color: #444;
    overflow: hidden;
}

nav a {
    float: left;
    display: block;
    color: #fff;
    text-align: center;
    padding: 14px 20px;
    text-decoration: none;
}

nav a:hover {
    background-color: #ddd;
    color: #000;
}

.container {
    padding: 20px;
}

h2 {
    text-align: left;
    margin-bottom: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

table, th, td {
    border: 1px solid #ddd;
}

th, td {
    padding: 12px;
    text-align: left;
}

th {
    background-color: #333;
    color: #fff;
}

tr:nth-child(even) {
    background-color: #f2f2f2;
}

tr:hover {
    background-color: #ddd;
}

.back-link {
    display: block;
    text-align: center;
    margin-top: 20px;
    text-decoration: none;
    color: #444;
}

.back-link:hover {
    color: #000;
}
.header {
            color: inherit;
            text-decoration: none;
            position: relative; 
        }
        .header:hover::after {
            position: absolute;
            left: 0;
            bottom: 100%;
            background-color: #333;
            color: #fff;
            padding: 5px;
            border-radius: 5px;
            white-space: nowrap;
            transform: translateY(-5px);
            opacity: 0;
            transition: opacity 0.3s;
            opacity: 1;
            z-index: 1;
        }
</style>
</head>
<body>
    <header>
		<h1><a class="header" href="http://localhost:8080/Muzej/home.jsp">Virtuelni vodic</a></h1>
    </header>
    <nav>
        <a href="http://localhost:8080/Muzej/home.jsp">Pocetna</a> 
        <a href="http://localhost:8080/Muzej/login">Ulogovanje</a>
        <a href="http://localhost:8080/Muzej/kreiranjeTure.jsp">Kreirajte novu turu</a>
        <a href="http://localhost:8080/Muzej/tura/prikaziJavne">Javne ture</a>
        <a href="http://localhost:8080/Muzej/tura/prikaziPrivatne">Privatne ture</a>
    </nav>

    <div class="container">
        <h2>Kreiranje ture</h2>
       
		<form action="kreirajTuru">
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
