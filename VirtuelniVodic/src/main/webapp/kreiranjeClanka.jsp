<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
<title>Kreiranje Clanka</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<form action="${pageContext.request.contextPath}/clanak/kreirajClanak" method="post">
			<table>
				<tr>
					<td>Naziv:</td>
					<td><input type="text" name="naziv"></td>
				</tr>
				<tr>
					<td>Tekst:</td>
					<td><input type="text" name="tekst"></td>
				</tr>
			</table>
			<input type="submit" value="Sacuvaj">
		</form>
		
		<c:if test="${not empty requestScope.uspeo}">
            <p>${requestScope.uspeo}</p>
        </c:if>
        <c:if test="${not empty requestScope.uspeo}">
            <p>${requestScope.uspeo}</p>
        </c:if>
		<a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
	</div>
</body>
</html>
