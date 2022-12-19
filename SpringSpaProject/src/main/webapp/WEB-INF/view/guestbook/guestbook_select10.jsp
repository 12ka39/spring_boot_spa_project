<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='js/guestbook.js'></script>
</head>
<body>
<div>
	<c:forEach var='vo' items = '${list }'>
		<div>
			[${vo.id }] <span>${vo.doc }</span>
		</div>
	</c:forEach>
</div>
</body>
</html>
