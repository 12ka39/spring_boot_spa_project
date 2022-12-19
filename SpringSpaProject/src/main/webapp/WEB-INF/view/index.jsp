<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href='css/index.css'>
<link rel='icon' href='images/favicon.jpg'>
<script src="http://code.jquery.com/jquery-3.6.1.min.js"></script>
<script defer src='js/index.js'></script>
<meta charset="UTF-8">
<title>Spring Spa Project</title>
</head>
<body>
hi... spring boot
<main>
	<header>
		<div class='main_title'>
		</div>
		<nav>
			<a href="/">HOME</a>
			<a href="#" class='btnGuestBook'>방명록</a>
			<a href='#' class='btnBoard'>게시판</a>
			
				<!-- Board Controller 매핑신호 -->
			
			<!-- <a href='abc'> 라고 하고 
			BoardController.java에서 리퀘스트 맵핑 abc해도 접속됨 -->
		</nav>
	</header>	
<div id='section'>
	<div class='guestbook'>
		최신 방명록
	</div>
	<div class='board'>
		최신 게시물
	</div>
</div>
</main>
<footer>

</footer>
</body>
</html>