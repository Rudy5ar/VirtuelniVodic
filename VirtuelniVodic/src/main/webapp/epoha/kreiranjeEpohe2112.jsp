<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Muzej</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container">
	<h2>Kreiranje epohe</h2>
	
	<form action="kreirajEpohu" method="post">
		<table>
			<tr>
				<td>Naziv</td>
				<td><input type="text" name="naziv"></td>
			</tr>
			<tr>
				<td>Opis</td>
                <td><input type="text" name="opis"></td>
			</tr>
			<tr>
				<td>Pocetak epohe</td>
				<td><input type="date" name="vremenskiPeriodOd"></td>
			</tr>
			<tr>
				<td>Kraj epohe</td>
				<td><input type="date" name="vremenskiPeriodDo"></td>
			</tr>
		</table>
		<input type="submit" value="Sacuvaj">
	</form>
</div>
</body>
</html>