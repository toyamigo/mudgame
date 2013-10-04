<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html lang="nl">
<head>
<title>${gebruiker.emailAdres}</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
<style>
form,form input {
	display: inline;
}

form {
	margin-right: 5px;
}
</style>
</head>
<body>
<c:import url="/WEB-INF/JSP/menu.jsp" />
	<c:choose>
		<c:when test="${not empty gebruiker}">
			<h1>${gebruiker.voornaam}</h1>
			<dl>
				<dt>EmailAdres</dt>
				<dd>${gebruiker.emailAdres}</dd>
				<dt>Voornaam</dt>
				<dd>${gebruiker.voornaam}</dd>
				<dt>FamilieNaam</dt>
				<dd>${gebruiker.naam}</dd>
				<dt>paswoord</dt>
				<dd>${gebruiker.paswoord}</dd>
			</dl>
			<!-- DELETE -->
			<spring:url value="/gebruiker/{id}/verwijderen" var="verwijderURL">
				<spring:param name="id" value="${gebruiker.id}" />
			</spring:url>
			<form:form action="${verwijderURL}" method="delete">
				<input type="submit" value="Verwijderen" />
			</form:form>
			<!-- UPDATE -->
			<spring:url value="/gebruiker/{id}/wijzigen" var="wijzigURL">
				<spring:param name="id" value="${gebruiker.id}" />
			</spring:url>
			<form action="${wijzigURL}" method="get">
				<input type="submit" value="Wijzigen" />
			</form>
		</c:when>
		<c:otherwise>
			<div>Gebruiker niet gevonden</div>
		</c:otherwise>
	</c:choose>

	<c:if test="${not empty param.fout}">
		<div class="fout">${param.fout}</div>
	</c:if>
</body>
</html>