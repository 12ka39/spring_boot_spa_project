/**
 * 게시판용
 */
 //()() 즉시 실행 함수 (코드 맨끝까지)
 (board = function(){
    var frm = $('.frm')[0];
    var param;
    var url='board/board_main.jsp?';
    
    $('.btnSelect').on('click', function(){
        param = $(frm).serialize();
        $.post("/board/board_select", param, function(data){
            $('#section').html(data);
        })
    })
    
    $('.btnSearch').on('click', function(){
        frm = $('.frm_search')[0];
        frm.nowPage.value = 1;
        param = $(frm).serialize();
        $.post("/board/board_select", param, function(data){
            $('#section').html(data);
        })
       
    })
    
    
    $('.btnInsert').on('click', function(){
        param = $('.frm_search').serialize();
        $.post("/board/board_insert", param, function(data){
            $('#section').html(data);
        })
    })
    
    
    $('.btnInsertR').on('click', function(){
        var frm = $('.frm')[0]; //폼데이터를 객체화
        var param = new FormData(frm); 
	        //new FormData인 이유는 
	        //board_insert.jsp 폼타입이
	        //enctype='multipart/form-data 이라서
       
        $.ajax({
            type : 'POST',
            url : '/board/board_select',
            //url : '/board/board_insertR',
            contentType : false,
            processData : false,
            data : param,
            dataType : 'html', //수신되는 타입
            success : function(data){
                $('#section').html(data);
            }
        })
    })
    
    
    $('.btnUpdate').on('click', function(){
        param = $(frm).serialize();
        $.post("/board/board_update", param, function(data){
            $('#section').html(data);
        })
    })
    
    
    $('.btnUpdateR').on('click', function(){
        var frm = $('.frm')[0];
        var param = new FormData(frm);
       
        $.ajax({
            type : 'POST',
            url : '/board/board_select',
            //url : '/board/board_updateR',
            contentType : false,
            processData : false,
            data : param,
            dataType : 'html',
            success : function(data){
                $('#section').html(data);
            }
        })
    })
    
    
    $('.btnRepl').on('click', function(){
        param = $(frm).serialize();
        $.post("/board/board_repl", param, function(data){
            $('#section').html(data);
        })
    })
    
    
    $('.btnReplR').on('click', function(){
        var frm = $('.frm')[0];
        var param = new FormData(frm);
       
        $.ajax({
            type : 'POST',
            //url : '/board/board_select',
            url : '/board/board_replR',
            contentType : false,
            processData : false,
            data : param,
            dataType : 'html',
            success : function(data){
                $('#section').html(data);
            }
        })
    })
    
    $('.btnDeleteR').on('click', function(){
        let yn = confirm('자료를 삭제하시겠습니까 ?');
        if( !yn ) return;
       
        param = $('.frm').serialize();
        $.post("/board/board_delete", param, function(data){
            $('#section').html(data);
        })
    })
    
    board.move = function(nowPage){
        frm = $('.frm_search')[0];
        frm.nowPage.value = nowPage;
        param = $(frm).serialize();
        $.post("/board/board_select", param, function(data){
            $('#section').html(data);
        })
    }
    
    board.view = function(sno){
        frm = $('.frm_search')[0];
        frm.sno.value = sno;
        param = $(frm).serialize();
        $.post("/board/board_view", param, function(data){
            $('#section').html(data);
        })
    }   
    
})() 
