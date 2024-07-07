<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header>
    <h1><a class="header" href="http://localhost:8080/Muzej/home.jsp">Virtuelni vodic</a></h1>
</header>
<nav>
    <a href="http://localhost:8080/Muzej/home.jsp">Pocetna</a>
    <sec:authorize access="!isAuthenticated()">
        <a href="http://localhost:8080/Muzej/login">Ulogovanje</a>
        <a href="http://localhost:8080/Muzej/register">Registracija</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()"><a href="${pageContext.request.contextPath}/tura/getUmetnickaDela">Kreirajte novu turu</a></sec:authorize>
    <a href="${pageContext.request.contextPath}/tura/prikaziJavne">Javne ture</a>
    <sec:authorize access="isAuthenticated()">
        <a href="${pageContext.request.contextPath}/tura/prikaziPrivatne">Privatne ture</a>
    </sec:authorize>
    <a href="http://localhost:8080/Muzej/umetnickoDelo/svaDela">Umetnicki predmeti</a>
    <sec:authorize access="hasAuthority('ADMIN') or hasAuthority('UREDJIVAC')"><a href="${pageContext.request.contextPath}/umetnickoDelo/getPodaciZaFormuKreiranje">Kreiraj umetnicki predmet</a></sec:authorize>
    <sec:authorize access="hasAuthority('ADMIN') or hasAuthority('UREDJIVAC')">
    	<a href="${pageContext.request.contextPath}/epoha/kreiranjeEpohe.jsp">Kreiraj epohu</a>
 	</sec:authorize>
 	<sec:authorize access="hasAuthority('ADMIN') or hasAuthority('UREDJIVAC')">
    	<a href="${pageContext.request.contextPath}/umetnik/kreirajUmetnika">Kreiraj umetnika</a>
 	</sec:authorize>
 	<sec:authorize access="hasAuthority('ADMIN') or hasAuthority('UREDJIVAC')">
    	<a href="${pageContext.request.contextPath}/kreiranjeClanka.jsp">Kreiraj clanak</a>
 	</sec:authorize>
    <sec:authorize access="hasAuthority('ADMIN')"><a href="${pageContext.request.contextPath}/admin">Admin strana</a></sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a href="http://localhost:8080/Muzej/logout">Odjava</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <span><img src="http://localhost:8080/Muzej/images/avatar.png" width="18px" height="18px"></span>
        <span>${sessionScope.username}</span>
    </sec:authorize>
</nav>