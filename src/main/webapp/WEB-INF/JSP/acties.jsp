<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="actie" items="acties">
    <c:url var="actieURL" value="/actie">
        <c:param name="actieId" value="${actie.id}"/>
    </c:url>
    <form action="${actieURL}" method="post">
        <input type="submit" value="${actie}"/>
    </form>
</c:forEach>