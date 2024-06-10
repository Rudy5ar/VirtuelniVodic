<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Security test</title>
</head>
<body>
	<div class="nav-bar">
      <a href="login" class="nav-btn-right">Home</a>
      <a href="register" class="nav-btn-right">Register</a>
      <sec:authorize access="isAuthenticated()">
      	<a href="/Muzej/logout" class="nav-btn-right">Log out</a>
      </sec:authorize>
    </div>
	<div sec:authorize="hasAuthority('KORISNIK')">
        Dobro došli ${korisnickoIme}!
    </div>
</body>
</html>