<%-- 
    Document   : lokatie
    Created on : Sep 30, 2013, 3:29:15 PM
    Author     : Tim.VandenLangenberg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html lang="nl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JavaMUD</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
    <a href="<c:url value='lokatie/hoofdmenu'/>">Hoofdmenu</a>
    <h1>Lokatie ${lokatie.id}</h1>
    <div id="lokatieBescrijving">${lokatie.beschrijving}</div>
    <c:if test="${not empty lokatie.items}">
        <c:set var="items" value="${lokatie.items}"/>
        <jsp:include page="/WEB-INF/JSP/items/itemlijst.jsp"/>
    </c:if>
    <c:if test="${not empty lokatie.karakters}">
        <c:set var="karakters" value="lokatie.karakters"/>
        <jsp:include page="/WEB-INF/JSP/karakters/karakterlijstlokatie.jsp"/>
    </c:if>
</body>
</html>
