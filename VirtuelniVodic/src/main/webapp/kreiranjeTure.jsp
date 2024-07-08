<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<title>Kreiranje ture</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h2>Kreiranje ture</h2>
       
        <sf:form modelAttribute="tura" action="kreirajTuru" method="post">
            <table>
                <tr>
                    <td>Naziv</td>
                    <td><sf:input type="text" path="naziv"/></td>
                </tr>
                <tr>
                    <td>Opis</td>
                    <td><sf:input type="text" path="opis"/></td>
                </tr>
                <tr>
                    <td>Umetnicki predmeti</td>
                    <td>
                        <sf:select class="veciSelect" path="umetnickodelos" multiple="true">
                            <sf:options items="${umetnickaDela}" itemValue="idUmetnickoDelo" itemLabel="naziv"/>
                        </sf:select>
                    </td>
                </tr>
                <tr>
                    <td>Tip</td>
                    <td>
                        <sf:radiobutton id="privatna" path="tip" value="privatna"/>
                        <label for="privatna">Privatna</label><br>
                        <sf:radiobutton id="javna" path="tip" value="javna"/>
                        <label for="javna">Javna</label><br>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Sacuvaj"/>
        </sf:form>
       
        <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
    </div>
</body>
</html>
