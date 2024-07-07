<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		<h2>Kreirana epoha ${epoha.naziv }</h2>
		<c:if test="${not empty epoha}">
			<p><strong>Naziv:</strong> ${epoha.naziv}</p>
            <p><strong>Opis:</strong> ${epoha.opis}</p>
            <p><strong>Početak epohe:</strong> 
        		<fmt:formatDate value="${epoha.vremenskiPeriodOd}" pattern="yyyy" />
    		</p>
    		<p><strong>Kraj epohe:</strong> 
        		<fmt:formatDate value="${epoha.vremenskiPeriodDo}" pattern="yyyy" />
    		</p>
		</c:if>
	</div>
</body>
</html>