<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="styles/index.css" />
    <link rel="stylesheet" href="styles/error.css" />
    <title>Glassnet</title>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
  </head>
  <body>
    <div class="nav-bar">
       <a href="login" class="nav-btn-right">Home</a>
      <a href="register" class="nav-btn-right">Register</a>
      <sec:authorize access="isAuthenticated()">
      	<a href="logout" class="nav-btn-right">Log out</a>
      </sec:authorize>
    </div>

    <div class="horizontal-container">
      <div class="vertical-container">
        <h1 class="error">${error}</h1>
      </div>
    </div>
  </body>
</html>
