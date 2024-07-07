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
		<h2>Kreiran umetnik ${umetnik.ime }</h2>
		<c:if test="${not empty umetnik}">
			<p><strong>Ime:</strong> ${umetnik.ime}</p>
            <p><strong>Godina rodjenja:</strong> 
        		<fmt:formatDate value="${umetnik.godinaRodjenja}" pattern="yyyy" />
    		</p>
    		<p><strong>Godina smrti:</strong> 
        		<fmt:formatDate value="${umetnik.godinaSmrti}" pattern="yyyy" />
    		</p>
    		<p><strong>Epoha:</strong> ${umetnik.epoha.naziv}</p>
		</c:if>
	</div>
</body>
</html>