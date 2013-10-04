<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:if test="${not empty gebruiker.karakter}">
    <c:forEach var="karakter" items="${gebruiker.karakter}">
        <c:if test="${not empty karakter.naam}">
            <ul class="zonderbolletjes">
                <li>
                    <c:url var="karakterURL" value="/lokatie">
                        <c:param name="karakterId" value="${karakter.id}"/>
                    </c:url>
                    <a href="${karakterURL}">${karakter.naam}</a>
                </li>

                <li>
                    <c:url var="deleteURL" value="/karakter">
                        <c:param name="karakterId" value="${karakter.id}"/>
                    </c:url>
                    <form:form action="${deleteURL}" method="delete">
                        <input type="submit" value="[X]"/>
                    </form:form>
                </li>
            </ul>
        </c:if>
    </c:forEach>
</c:if>