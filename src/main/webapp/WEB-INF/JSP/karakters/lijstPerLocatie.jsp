<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html lang="nl">
<head>
<title>Karakters Per Locatie</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
function fAjax() {
	$('div').empty();
	$('div').css({
		opacity : 1.0
	});
	$.ajax({
		url : "http://localhost:8080/JavaMUD/karakter/lijstPerLocatie",
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			$('div').append($('<ul>'));
			$.each(data, function(index, element) {

				$('ul').append($('<li>' + element.naam + '</li>'));
			});
			myAnimation($('div'));
		}
	});
}
function myAnimation(div) {
	div.animate({
		opacity : 0.0
	}, 10000, "linear", function() {
		fAjax();
	}, 2000);
}
$(function() {
	fAjax();

});
</script>
</head>
<body>
	<div></div>
</body>
</html>