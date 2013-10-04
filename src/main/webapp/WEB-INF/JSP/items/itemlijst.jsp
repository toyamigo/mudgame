<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="item" items="${items}">
    <div class="item">
        ${item.id}
    </div>
</c:forEach>