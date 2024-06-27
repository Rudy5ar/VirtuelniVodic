<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Muzej</title>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
    <header>
      <h1><a class="header" href="http://localhost:8080/Muzej/home.jsp">Virtuelni vodic</a></h1>
  </header>
  <nav>
    <a href="http://localhost:8080/Muzej/home.jsp">Pocetna</a>
    <a href="http://localhost:8080/Muzej/register">Registracija</a>
      <a href="http://localhost:8080/Muzej/kreiranjeTure.jsp">Kreirajte novu turu</a>
      <a href="tura/prikaziJavne">Javne ture</a>
      <a href="tura/prikaziPrivatne">Privatne ture</a>
      <a href="http://localhost:8080/Muzej/urediPredmet.jsp">Uredi predmet</a>
  </nav>
    <div class="container">
      	<sec:authorize access="!hasAuthority('ADMIN')">
      		<h1>Nemate pravo da koristite ovu stranu!</h1>
		</sec:authorize>
		<sec:authorize access="hasAuthority('ADMIN')">
    <table>
      <h1>Admin strana</h1>
      <tr>
        <th>Ime i prezime</th>
        <th>Email</th>
        <th>Ulog</th>
        <th></th>
      </tr>
      <c:forEach var="korisnik" items="${korisnici}">
      <tr>
        <td>${korisnik.korisnickoIme}</td>
        <td>${korisnik.email}</td>
        <td>${korisnik.uloga}</td>
        <td>
          <c:if test="${korisnik.uloga.equals('KORISNIK')}"><a href="/Muzej/uredjivac?id=${korisnik.idKorisnik}">Dodaj dozvola</a></c:if>
          <c:if test="${!korisnik.uloga.equals('KORISNIK')}"><a href="/Muzej/neuredjivac?id=${korisnik.idKorisnik}">Ukloni dozvola</a></c:if>
        </td>
      </tr>	
      
    	</c:forEach>
    </table>

        
		</sec:authorize>
    </div>
  </body>
</html>
