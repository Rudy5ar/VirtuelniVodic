<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Muzej</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
<h2>Novo umetnicko delo</h2>
<c:if test="${not empty umetnickoDelo}" />
	<p><strong>Naziv:</strong>${umetnickoDelo.naziv }</p>
	<p><strong>Umetnik:</strong>${umetnickoDelo.umetnik.ime }</p>
	<p><strong>Epohe:</strong>
	<ul>
	<c:forEach items="${epohe }" var="e">
		<li>${e.naziv}</li>
	</c:forEach>
	</ul>
	</p>
	<p><strong>Opsti opis:</strong>${umetnickoDelo.opis }</p>
	<p><strong>Kratak opis:</strong>${umetnickoDelo.opstost1 }</p>
	<p><strong>Detaljniji opis:</strong>${umetnickoDelo.opstost2 }</p>
	<p><strong>Veoma detaljan opis:</strong>${umetnickoDelo.opstost3 }</p>
</div>


</body>
</html>