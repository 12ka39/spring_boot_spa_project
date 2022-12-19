package com.cinamo.roll.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GuestBookController {
	
	@Autowired //GuestbookDao 타입으로 가져옴.
	GuestbookDao dao;
	
	@RequestMapping("/guestbook/guestbook_select") //이게 들어왔을 때
	public ModelAndView select(GPageVo gVo) {
		ModelAndView mv = new ModelAndView();
		// service or dao
		System.out.print(dao);
		System.out.println("now page: " + gVo.getNowPage());
		System.out.println("tot page: " + gVo.getTotPage());
		
		//검색어에 따른 totSize를 가져와 page를 재계산
		int totSize = dao.getTotSize(gVo);
		System.out.println("totSize : " + totSize);
		
		// 검색어에 따른 List 가져옴
		List<GuestbookVo> list = dao.select(gVo);
		
		// List를 mv에 추가
		gVo = dao.getgVo(); //새로 갱신된 페이지 정보
		mv.addObject("gVo", gVo);
		mv.addObject("list", list);
		mv.setViewName("guestbook/guestbook_select");
		return mv;

	}
	
	
	@RequestMapping("/guestbook/guestbook_insert")
	public String insert(GuestbookVo vo, HttpServletResponse resp) {
		//확장성을 고려하여 여기 컨트롤러에서 곧바로 매퍼를 부르지 않고
		//Dao를 부른다. 
		String msg = "";
		boolean b = dao.insert(vo);
		if(!b) {
			msg = "추가 중 오류 발생";
		}
		return msg;
	}
	
	
	@RequestMapping("/guestbook/guestbook_delete")
	public String delete(GuestbookVo vo, HttpServletResponse resp) {
		//확장성을 고려하여 여기 컨트롤러에서 곧바로 매퍼를 부르지 않고
		//Dao를 부른다. 
		String msg = "";
		boolean b = dao.delete(vo);
		if(!b) {
			msg = "삭제 중 오류 발생";
		}
		return msg;
	}
	
	
	@RequestMapping("/guestbook/guestbook_update")
	public String update(GuestbookVo vo, HttpServletResponse resp) {
		//확장성을 고려하여 여기 컨트롤러에서 곧바로 매퍼를 부르지 않고
		//Dao를 부른다. 
		String msg = "";
		boolean b = dao.update(vo);
		if(!b) {
			msg = "수정 중 오류 발생";
		}
		return msg;
	}
	
	
	@RequestMapping("/guestbook/guestbook10") 	
	public ModelAndView select10() {
		ModelAndView mv = new ModelAndView();
		// service or dao
		
		// 검색어에 따른 List 가져옴
		List<GuestbookVo> list = dao.select10();
		
		// List를 mv에 추가
		mv.addObject("list", list);
		mv.setViewName("guestbook/guestbook_select10");
		return mv;

	}
}