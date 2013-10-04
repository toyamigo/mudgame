<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty fouten}">
    <c:forEach var="fout" items="${fouten}">
        <div class="fout">
            ${fout}
        </div>
    </c:forEach>
</c:if>