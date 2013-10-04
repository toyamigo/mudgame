<%-- 
    Document   : karaktergeneratie
    Created on : Oct 1, 2013, 11:51:10 AM
    Author     : Tim.VandenLangenberg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html lang="nl">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JavaMUD - Nieuw karakter</title>
        <link rel="stylesheet" href="${contextPath}/styles/default.css"/>
    </head>
    <body>
        <c:import url="/WEB-INF/JSP/menu.jsp" />
        <h1>Nieuw karakter</h1>
        <c:url value="/karakter" var="url"/>
        <form:form action="${url}" method="POST" commandName="karakter">
            <jsp:include page="karakterformfields.jsp"/>
            <input type="submit" value="Klaar"/>
        </form:form>
    </body>
</html>
