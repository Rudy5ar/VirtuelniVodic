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
    <jsp:include page="header.jsp" />

	<sec:authorize access="!isAuthenticated()">
    <div class="container">
      <sf:form modelAttribute="user"  action="login"  method="post" class="login-form">
        <h2>Ulogovanje</h2>
        <sf:input path="email" placeholder="Email" />
        <sf:password path="sifra" placeholder="Lozinka" />
        <input class="btn-route" type="submit" value="Prijava" />
      </sf:form>

        <h1>Nemate nalog?</h1>
        <a href="register" class="btn-route">Registracija</a>
      </sec:authorize>
      <sec:authorize access="isAuthenticated()">
      	<h1>Već ste ulogovani!</h1>
      </sec:authorize>
      <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
    </div>
  </body>
</html>
