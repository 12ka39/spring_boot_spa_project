package com.cinamo.roll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cinamo.roll.board.BoardService;
import com.cinamo.roll.board.BoardVo;
import com.cinamo.roll.board.PageVo;

@RestController
public class BoardController {
	
	/*
	 * //DB연결 JdbcTemplate jdbcTemp; //mybatis를 사용하게 되면 불필요
	 */
	@Autowired 
	BoardService service;
	
	/*
	 * @Autowired //자동으로 생성자를 통해 주입해라. public BoardController(JdbcTemplate temp) {
	 * //Spring DI 구동 this.jdbcTemp = temp; }
	 */
	
	@RequestMapping("/board/board_select")
	public ModelAndView select(PageVo pVo) {
		ModelAndView mv = new ModelAndView();
		//service를 new~로 안 해준 건
		//필드에서 BoardService service를 Autowired로 선언해서.
		List<BoardVo> list = service.select(pVo); 
			//여기서는 매개변수가 문자열이다.
		pVo = service.getpVo();
		
		//이 밑에는 위에 service.select("1") 한참 돌고나서
		//나중에 결과값 가져와서 다시 돌아올 때 실행된다.
		
		mv.addObject("list", list); //모델, 데이터를 담는다.
		mv.addObject("pVo", pVo);
		mv.setViewName("board/board_select");  //뷰
			// WEB-INF/view/board/board_select.jsp
		return mv;
		
	}
	
	@RequestMapping("/board/board10")
	public ModelAndView board10(){
		ModelAndView mv = new ModelAndView();
		List<BoardVo> list = service.board10();
		
		mv.addObject("list", list);
		mv.setViewName("/board/board_board10");
		
		return mv;
	}
	
	@RequestMapping("/board/board_view")
	public ModelAndView view(BoardVo bVo, PageVo pVo) {//매개변수 2개 쓰면, 
		ModelAndView mv = new ModelAndView();
		
		//조회수 증가
		bVo = service.view(bVo.getSno(), "up");
		
		//doc 안에 있는 \n 기호를 <br>로 변경
		String temp = bVo.getDoc();
		bVo.setDoc(temp.replace("\n", "<br>"));
		mv.addObject("bVo", bVo);
		mv.addObject("pVo", pVo);
	
		mv.setViewName("/board/board_view");
		return mv;
	}
	
	@RequestMapping("/board/board_delete")
	public ModelAndView delete(BoardVo bVo, PageVo pVo) {
		String msg = "";
		ModelAndView mv = new ModelAndView();
		
		//조회수 증가
		boolean b=service.delete(bVo);
		if( !b ) {
			msg = "삭제 중 오류 발생";
		}
		mv = select(pVo);
		mv.addObject("msg", msg);
		mv.addObject("pVo", pVo);
		mv.setViewName("/board/board_select");
		return mv;
	}
	
	
	@RequestMapping("/board/board_insert")
	public ModelAndView insert(PageVo pVo) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("pVo", pVo); //뷰로 보낼 데이터 값
		mv.setViewName("/board/board_insert"); // 뷰의 이름
		
		return mv; //ModelAndView 객체를 반환
	}
	
	@RequestMapping("/board/board_update")
	public ModelAndView update(PageVo pVo) {
		ModelAndView mv = new ModelAndView();
		BoardVo bVo = service.view(pVo.getSno(), "");
		
		mv.addObject("bVo", bVo);
		mv.addObject("pVo", pVo); //뷰로 보낼 데이터 값
		mv.setViewName("/board/board_update"); // 뷰의 이름
		
		return mv; //ModelAndView 객체를 반환
	}
	
	@RequestMapping("/board/board_repl")
	public ModelAndView repl(PageVo pVo, BoardVo bVo) {
		ModelAndView mv = new ModelAndView();
		

		mv.addObject("bVo", bVo);
		mv.addObject("pVo", pVo); //뷰로 보낼 데이터 값
		mv.setViewName("/board/board_repl"); // 뷰의 이름
		
		return mv; //ModelAndView 객체를 반환
	}
	
	/*
	@RequestMapping("/board/board_select")
	public ModelAndView select(PageVo pVo) {
		ModelAndView mv = new ModelAndView();
	}
	*/
	
	
	/*
	@RequestMapping("/board/test")
		//이거 실행하고 싶으면 
		//"/board/board_select" 대신 넣고 
		//위에 있는 걸 다른 걸로 바꿈
	public ModelAndView select() {
		ModelAndView mv = new ModelAndView(); 
		List<String> subjects = null; 
		
		try {
			Connection conn = jdbcTemp.getDataSource().getConnection();
			
			//테스트로.. 게시판 제목만 불러들여서 문자열로 반환
			String sql = "select subject from board";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			subjects = new ArrayList<String>();
			while(rs.next()) {
				subjects.add(rs.getString("subject"));
			}
			rs.close();
			ps.close();
			conn.close();
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		mv.addObject("subjects", subjects); //모델
		mv.setViewName("board/board_select");  //뷰
			// WEB-INF/view/board/board_select.jsp
		return mv;
	}
	
	*/
}
