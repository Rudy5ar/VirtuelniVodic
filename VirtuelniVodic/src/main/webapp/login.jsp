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

	<sec:authorize access="!isAuthenticated()">
    <div class="container">
      <sf:form modelAttribute="user"  action="login"  method="post" class="login-form">
        <h2>Ulogovanje</h2>
        <sf:input path="email" placeholder="Email" />
        <sf:password path="sifra" placeholder="Lozinka" />
        <input class="blue-btn" type="submit" value="Prijava" />
      </sf:form>

        <h1>Nemate nalog?</h1>
        <a href="register" class="blue-btn">Registracija</a>
      </sec:authorize>
      <sec:authorize access="isAuthenticated()">
      	<h1>Već ste ulogovani!</h1>
      </sec:authorize>
    </div>
  </body>
</html>
