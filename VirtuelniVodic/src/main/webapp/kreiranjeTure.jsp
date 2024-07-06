<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kreiranje ture</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h2>Kreiranje ture</h2>
       
        <form action="kreirajTuru" method="post">
            <table>
                <tr>
                    <td>Naziv</td>
                    <td><input type="text" name="naziv" value=""></td>
                </tr>
                <tr>
                    <td>Opis</td>
                    <td><input type="text" name="opis" value=""></td>
                </tr>
                <tr>
                    <td>Umetnicki predmeti</td>
                    <td>
                        <select id="umetnickaDela" name="umetnickaDela" multiple>
                            <c:forEach items="${umetnickaDela}" var="delo">
                                <option value="${delo.idUmetnickoDelo}">${delo.naziv}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Tip</td>
                    <td>
                        <input type="radio" id="privatna" name="tip" value="privatna">
                        <label for="privatna">Privatna</label><br>
                        <input type="radio" id="javna" name="tip" value="javna">
                        <label for="javna">Javna</label><br>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Sacuvaj">
        </form>
       
        <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
    </div>
</body>
</html>
