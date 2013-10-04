<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html lang="nl">
<head>
<title>${gebruiker.emailAdres}</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<c:import url="/WEB-INF/JSP/menu.jsp" />
	<h1>${gebruiker.emailAdres} wijzigen</h1>
<%-- 	<spring:url value="/{id}/wijzigen" var="wijzigURL"> --%>
	<spring:url value="/gebruiker/wijzigen" var="wijzigURL">
		<spring:param name="id" value="${gebruiker.id}" />
	</spring:url>
	<form:form action="${wijzigURL}" method="put" commandName="gebruiker">
		<jsp:include page="gebruikerformfields.jsp" />
		<input type="submit" value="wijzigen" />
	</form:form>
</body>
</html>