<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="nl">
<head>
<title>Gebruiker verwijderd</title>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/styles/default.css" />
</head>
<body>
	<div>De gebruiker ${param.emailAdres} (${param.id}) is verwijderd.</div>
</body>
</html>