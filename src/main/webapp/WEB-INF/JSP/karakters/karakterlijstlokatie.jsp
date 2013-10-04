<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="karakter" items="${karakters}">
    <div class="karakter">
        ${karakter.naam}
    </div>
</c:forEach>
