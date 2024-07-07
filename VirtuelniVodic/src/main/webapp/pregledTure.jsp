<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<title>Pregled predmeta</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h2>Pregled ture ${tura.naziv }</h2>
        <p>Mozete odabrati koliko detaljan opis svakog predmeta zelite da vidite.</p>
        
         <form action="${pageContext.request.contextPath}/umetnickoDelo/updateOpisi" method="post">
                <table>
                    <thead>
                        <tr>
                            <th>Naziv predmeta</th>
                            <th>Opis predmeta</th>
                        </tr>
                    </thead>
                    <tbody>
            <c:forEach var="predmet" items="${delaUTuri}">
                        <tr>
                            <td>${predmet.naziv}</td>
                            <td>
                                <select name="opis_${predmet.idUmetnickoDelo}">
                                    <option value="${predmet.opstost1}" <c:if test="${predmet.opis == predmet.opstost1}">selected</c:if>>${predmet.opstost1}</option>
                                    <option value="${predmet.opstost2}" <c:if test="${predmet.opis == predmet.opstost2}">selected</c:if>>${predmet.opstost2}</option>
                                    <option value="${predmet.opstost3}" <c:if test="${predmet.opis == predmet.opstost3}">selected</c:if>>${predmet.opstost3}</option>
                                </select>
                            </td>
                        </tr>
            </c:forEach>
                    </tbody>
                </table>
            <input type="hidden" name="idTure" value="${tura.idTura}">
        </form>
        
        <br>
        <c:if test="${tip == 'privatna'}">
        	<a href="${pageContext.request.contextPath}/tura/proslediTuru/${tura.idTura}">Uredi turu</a>
        </c:if>

        <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
        <a href="/Muzej/route?turaId=8">Nadji rutu</a>
    </div>
</body>
</html>
