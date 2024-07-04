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
    <a href="http://localhost:8080/Muzej/kreiranjeTure.jsp">Kreirajte novu turu</a>
    <a href="${pageContext.request.contextPath}/tura/prikaziJavne">Javne ture</a>
    <a href="${pageContext.request.contextPath}/tura/prikaziPrivatne">Privatne ture</a>
    <a href="http://localhost:8080/Muzej/umetnickoDelo/svaDela">Umetnicki predmeti</a>
    <sec:authorize access="hasAuthority('ADMIN')"><a href="admin">Admin strana</a></sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a href="http://localhost:8080/Muzej/logout">Odjava</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <span><img src="images/avatar.png" width="18px" height="18px"></span>
        <span>${sessionScope.username}</span>
    </sec:authorize>
</nav>