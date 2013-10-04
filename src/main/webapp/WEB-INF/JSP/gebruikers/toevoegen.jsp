<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="nl">
<head>
<title>Gebruiker toevoegen</title>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/styles/default.css" />
</head>
<body>
	<h1>Gebruiker toevoegen</h1>
	<c:url value="/gebruiker" var="url" />
	<form:form action="${url}" method="post" commandName="gebruiker">
		<jsp:include page="gebruikerformfields.jsp" />
		<input type="submit" value="Toevoegen" />
	</form:form>
</body>
</html>