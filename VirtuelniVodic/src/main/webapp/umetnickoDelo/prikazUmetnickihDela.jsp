<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Umetnicka dela</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container">
    <h2>Lista umetnickih predmeta</h2>
    <form action="${pageContext.request.contextPath}/umetnickoDelo/searchUmetnickodelo" method="get">
        <select id="epohaId" name="epohaId">
            <option value="" disabled selected>Izaberite epohu</option>
            <c:forEach items="${epohe}" var="epoha">
                <option value="${epoha.idEpoha}">${epoha.naziv}</option>
            </c:forEach>
        </select>
        <select id="umetnikId" name="umetnikId">
            <option value="" disabled selected>Izaberite umetnika</option>
            <c:forEach items="${umetnici}" var="u">
                <option value="${u.idUmetnik}">${u.ime}</option>
            </c:forEach>
        </select>
        <input type="date" name="godinaNastanka">
        <input type="submit" value="Pretrazi po filterima">
    </form>
    <table>
        <tr>
            <th>Naziv</th>
            <th>Opis</th>
            <th>Datum</th>
            <th>Geografska duzina</th>
            <th>Geografska sirina</th>
            <sec:authorize access="hasAuthority('ADMIN') or hasAuthority('UREDJIVAC')">
                <th>Akcije</th>
            </sec:authorize>
        </tr>
        <c:forEach var="umetnickoDelo" items="${svaDela}">
            <tr>
                <td>${umetnickoDelo.naziv}</td>
                <td>${umetnickoDelo.opis}</td>
                <td>${umetnickoDelo.datum}</td>
                <td>${umetnickoDelo.geografskaDuzina}</td>
                <td>${umetnickoDelo.geografskaSirina}</td>
                <sec:authorize access="hasAuthority('ADMIN') or hasAuthority('UREDJIVAC')">
                    <td>
                        <a href="${pageContext.request.contextPath}/umetnickoDelo/edit/${umetnickoDelo.idUmetnickoDelo}">Izmeni</a>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </table>
    <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
</div>
</body>
</html>
