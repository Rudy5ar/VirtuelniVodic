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
  </head>
  <body>
    <div class="nav-bar">
      <a href="login" class="nav-btn-right">Home</a>
      <a href="register" class="nav-btn-right">Register</a>
      <sec:authorize access="isAuthenticated()">
      	<a href="/Muzej/logout" class="nav-btn-right">Log out</a>
      </sec:authorize>
    </div>

	<sec:authorize access="!isAuthenticated()">
    <div class="horizontal-container">
      <sf:form modelAttribute="user"  action="login"  method="post" class="login-form">
        <h2>Login</h2>
        <sf:input path="email" placeholder="Email" />
        <sf:password path="sifra" placeholder="Password" />
        <input class="blue-btn" type="submit" value="Continue" />
      </sf:form>

      <div class="vertical-container">
        <h1>Don't have an account?</h1>
        <a href="register" class="blue-btn">Register</a>
      </div>
      </sec:authorize>
      <sec:authorize access="isAuthenticated()">
      	<h1>Već ste ulogovani!</h1>
      </sec:authorize>
    </div>
  </body>
</html>
