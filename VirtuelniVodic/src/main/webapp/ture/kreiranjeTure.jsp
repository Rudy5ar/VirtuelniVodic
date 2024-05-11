<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/Muzej/tura/kreirajTuru" method="post">
		<table>
			<tr>
				<td>Naziv</td>
				<td>Opis</td>
			</tr>
			<tr>
				<td><input type="text" name="naziv"></td>
				<td><input type="text" name="opis"></td>
			</tr>
		</table>
		<input type="submit" value="Kreiraj Turu">
	</form>
</body>
</html>