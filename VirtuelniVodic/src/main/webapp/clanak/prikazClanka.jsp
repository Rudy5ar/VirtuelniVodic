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
<div class="container" style="max-width:500px">
    <table>
        <thead>
        <tr>
            <th>${clanak.naziv}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                ${clanak.datumKreiranja}</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    ${clanak.tekst}
                </td>

            </tr>
        </tbody>
    </table>
    <a class="back-link" href="http://localhost:8080/Muzej/clanak/prikaziClanke">Back to Prikaz clanaka</a>
    <a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
</div>
</body>
</html>