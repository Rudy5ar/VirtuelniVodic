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
    <link rel="stylesheet" href="css/style.css">
    <title>Muzej</title>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
  </head>
  <body>
    <jsp:include page="header.jsp" />

    <div class="container">
      <sf:form modelAttribute="user"  action="register"  method="post" class="login-form">
        <h2>Registracija</h2>
        <sf:input path="korisnickoIme" placeholder="Pun ime*" />
        <sf:input path="email" placeholder="Email*" />
        <sf:password path="sifra" placeholder="Lozinka*" />
        <sf:password path="sifraProvera" placeholder="Provera lozinka*" />
        <input class="btn-route" type="submit" value="Registracija" />
      </sf:form>
      <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
    </div>
  </body>
</html>
