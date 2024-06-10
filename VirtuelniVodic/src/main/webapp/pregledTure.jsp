<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pregled predmeta</title>
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

select {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    background-color: #f4f4f4;
    font-size: 16px;
    color: #333;
    appearance: none;
    -webkit-appearance: none;
    -moz-appearance: none; 
    border: none;
    /* needed for Firefox: */
    overflow:hidden;
 
}

select:focus {
    outline: none;
    border-color: #007BFF;
    box-shadow: 0 0 5px rgba(0, 123, 255, .5);
}

button {
    display: inline-block;
    padding: 10px 20px;
    margin-top: 20px;
    font-size: 16px;
    font-weight: bold;
    color: #fff;
    background-color: #007BFF;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
}

button:hover {
    background-color: #0056b3;
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
        <a href="http://localhost:8080/Muzej/kreiranjeTure.jsp">Kreirajte novu turu</a>
        <a href="http://localhost:8080/Muzej/tura/prikaziJavne">Javne ture</a>
        <a href="http://localhost:8080/Muzej/tura/prikaziPrivatne">Privatne ture</a>
    </nav>

    <div class="container">
        <h2>Pregled ture</h2>
        <p>Pregled ture ${tura.naziv}, odnosno predmeta koji se nalaze u njoj</p>
        
         <form action="${pageContext.request.contextPath}/umetnickoDelo/updateOpisi" method="post">
            <c:forEach var="predmet" items="${delaUTuri}">
                <table>
                    <thead>
                        <tr>
                            <th>Naziv predmeta</th>
                            <th>Opis predmeta</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${predmet.naziv}</td>
                            <td>
                                <select name="opis_${predmet.idUmetnickoDelo}">
                                    <option value="${predmet.opstost1}" <c:if test="${predmet.opis == predmet.opstost1}">selected</c:if>>${predmet.opstost1}</option>
                                    <option value="${predmet.opstost2}" <c:if test="${predmet.opis == predmet.opstost2}">selected</c:if>>${predmet.opstost2}</option>
                                    <option value="${predmet.opstost3}" <c:if test="${predmet.opis == predmet.opstost3}">selected</c:if>>${predmet.opstost3}</option>
                                </select>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </c:forEach>
            <input type="hidden" name="idTure" value="${tura.idTura}">
            <input type="submit" value="Sacuvaj promene">
            
        </form>

        <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
    </div>
</body>
</html>
