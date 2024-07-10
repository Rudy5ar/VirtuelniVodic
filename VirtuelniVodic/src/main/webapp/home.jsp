<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pocetna stranica</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <jsp:include page="header.jsp" />
    <div class="container">
        <h2>Dobrodosli na Virtuelni vodic!</h2>
        <p>Ovo je pocetna stranica. Molimo Vas odaberite jednu od opcija iz menija iznad.</p>
    </div>
</body>
</html>
