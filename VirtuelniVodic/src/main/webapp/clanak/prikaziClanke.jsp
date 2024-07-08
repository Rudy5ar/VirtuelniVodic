<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Javne ture</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container">
    <h2>Procitajte razne zanimljive clanke</h2>
    <table>
        <thead>
        <tr>
            <th>Naslov clanka</th>
            <th>Datum</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="clanak" items="${sviClanci}">
            <tr>
                <td>
                    <a href="http://localhost:8080/Muzej/clanak/detaljiClanka?idClanka=${clanak.idClanak}">${clanak.naziv}</a>
                </td>
                <td>${clanak.datumKreiranja}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
</div>
</body>
</html>