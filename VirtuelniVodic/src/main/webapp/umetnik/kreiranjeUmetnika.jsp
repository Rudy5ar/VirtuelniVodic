<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Muzej</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<h2>Kreiranje umetnika</h2>
		
		<form action="kreirajUmetnika" method="post">
			<table>
				<tr>
					<td>Puno ime</td>
					<td><input type="text" name="ime"></td>
				</tr>
				<tr>
					<td>Datum rodjenja</td>
                	<td><input type="date" name="godinaRodjenja"></td>
				</tr>
				<tr>
					<td>Datum smrti</td>
                	<td><input type="date" name="godinaSmrti"></td>
				</tr>
				<tr>
					<td>Epoha</td>
					<td>
						<select id="epoha" name="epoha">
							<c:forEach items="${epohe }" var="e">
								<option value="${e.idEpoha}">${e.naziv}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			<input type="submit" value="Sacuvaj">
		</form>
	</div>
</body>
</html>