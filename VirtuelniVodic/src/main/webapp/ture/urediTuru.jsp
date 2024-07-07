<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <h2>Promena informacija o turi</h2>
       
        <form action="${pageContext.request.contextPath}/tura/promeniTuru/${idTura}" method="post">
            <table>
                <tr>
                    <td>Naziv</td>
                    <td><input type="text" name="naziv" value="${tura.naziv}"></td>
                </tr>
                <tr>
                    <td>Opis</td>
                    <td><input type="text" name="opis" value="${tura.opis }"></td>
                </tr>
                <tr>
                    <td>Umetnicki predmeti</td>
                    <td>
                    <select class="veciSelect" id="umetnickaDela" name="umetnickaDela" multiple>
    				<c:forEach items="${umetnickaDela}" var="delo">
        				<c:set var="selected" value="false"/>
        				<c:forEach items="${tura.umetnickodelos}" var="selectedDelo">
           					<c:if test="${selectedDelo.idUmetnickoDelo == delo.idUmetnickoDelo}">
                				<c:set var="selected" value="true"/>
            				</c:if>
        				</c:forEach>
        				<option value="${delo.idUmetnickoDelo}" ${selected == 'true' ? 'selected' : ''}>${delo.naziv}</option>
    				</c:forEach>
					</select>
                	</td>
                </tr>
                <tr>
                    <td>Tip</td>
                    <td>
                        <input type="radio" id="privatna" name="tip" value="privatna" checked="checked">
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