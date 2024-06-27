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
    <link rel="stylesheet" href="css/style.css">
    <title>Muzej</title>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
  </head>
  <body>
    <jsp:include page="header.jsp" />

    <div class="container">
      <h1 class="error">${error}</h1>
    </div>
  </body>
</html>
