/**
 * index.js
 */
 
 
 $('.btnBoard').on('click' , function(){
 	$('#section').load('/board/board_select');	
 });
 //@@@ 경로명 아니고 매핑!!!!
 //이게 전달될 곳은 GuestBookController
 
 
$('.btnGuestBook').on('click' , function(){
 	$('#section').load('/guestbook/guestbook_select');	
 });

/* 방명록 최근 10개 ------ */
$('#section>.guestbook').load("/guestbook/guestbook10");



/* 게시물 최근 10개 ------ */ 
$('#section>.board').load("/board/board10");
				//이 컨트롤러를 부르겠다


