
/**
 * guestbook.js
 */
 
 (gb = function(){
 
 		$('.btnSearch').on('click', function(){
	 	let frm = $('.frm_gb_search')[0];
	 	frm.nowPage.value= 1;
	 	let param = $(frm).serialize();
	 	$('#section').load('/guestbook/guestbook_select', param);
	 						//컨트롤러에 있는 매핑정보
	 });
 
 
	 $('.btnPrev').on('click', function(){
	 	let frm = $('.frm_gb_search')[0];
	 	frm.nowPage.value= Number(frm.nowPage.value)-1;
	 				//Number 해주는 이유 = 안 그러면 오류날 때가 있어서.
	 	let param = $(frm).serialize();
	 	$('#section').load('/guestbook/guestbook_select', param);
	 						//컨트롤러에 있는 매핑정보
	 });
	 
	 $('.btnNext').on('click', function(){
	 	let frm = $('.frm_gb_search')[0];
	 	frm.nowPage.value= Number(frm.nowPage.value)+1;
	 	let param = $(frm).serialize();
	 	$('#section').load('/guestbook/guestbook_select', param);
	 });
	 
	 $('.btnGuestbookSave').on('click', function(){
	 	let frm = $('.frm_guestbook_insert')[0];
	 	let param = $(frm).serialize();
	 	$.post('/guestbook/guestbook_insert', param, function(){
	 		
	 		frm = $('.frm_gb_search')[0];
	 		param = $(frm).serialize();
	 		$('#section').load('/guestbook/guestbook_select', param);
	 	});
	 	
	 });
	 
	 //수정버튼
	 //gb. 지우고 jsp도 gb. 지워도 실행됨. 
	 gb.modifyView = function(frm){
		 let div = frm.querySelector('.updateZone');
		 let bz = frm.querySelector('.btnZone');
		 
		 div.style.visibility = 'visible';
		 frm.doc.readOnly=false;
		 $(bz).css('display', 'none');
	 }
	 
	 gb.modifyCancel = function(frm){
		 let div = frm.querySelector('.updateZone');
		 let bz = frm.querySelector('.btnZone');
		 
		 div.style.visibility = 'hidden';
		 frm.doc.readOnly = true;
		 $(bz).css('display', 'block');
	 }
	 
	 
	 /* modal box ----- */
	 $('#btnClose').on('click', function(){
		 $('#modal').css('display', 'none');
	 });
	 
	 var delForm; //변수 선언
	 gb.modalView = function(frm){
		 delForm = frm;
		 $('#modal').css('display', 'block');
	 };
	 
	 
	 /* 방명록 삭제 ------------ */
	 $('#btnCheck').on('click', function(){
		 
		 delForm.pwd.value = $('#pwd').val();

		 let frm = delForm;
		 let param = $(frm).serialize();

		 $.post('/guestbook/guestbook_delete', param, function(){
		 frm = $('.frm_gb_search')[0];
		 param = $(frm).serialize();
		 $('#section').load('/guestbook/guestbook_select', param);
		 
		 });
	 });	 
	 
	 
	 /* 방명록 수정 --------------- */
	 
	 gb.update = function(frm){
		 let param = $(frm).serialize();
		 
		 $.post('/guestbook/guestbook_update', param, function(msg){
			 if(msg != '') alert (msg);
			 
		 frm = $('.frm_gb_search')[0];
		 param = $(frm).serialize();
		 $('#section').load('/guestbook/guestbook_select', param);
		 	//select 가져와서 수정된 내용 보이도록
		 
		 });
	 }
	 
 })();
 