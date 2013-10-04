<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<div class="menu">
		<nav>
			<ul class="menu">
				<c:url value="/" var="rootURL" />
				<li><a href="${rootURL}">Hoofdmenu</a></li>
			</ul>
		</nav>
		<img
			src="${pageContext.servletContext.contextPath}/images/mudlogo.jpg"
			alt="logo" />
	</div>
</header>
