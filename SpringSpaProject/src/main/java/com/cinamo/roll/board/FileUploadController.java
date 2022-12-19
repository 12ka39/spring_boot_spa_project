package com.cinamo.roll.board;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
	static String path= "C:\\Users\\7E\\eclipse-workspace\\SpringSpaProject\\src\\main\\resources\\static\\upload";
	
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/board_insertR")
	public synchronized String insertR(@RequestParam("attFile") List<MultipartFile> mul, 
			
			//@RequestParam 테이터형 변수명 : 폼의 값이 해당 변수명에 자동 대입됨
			//@RequestParam String mid
			
			
				@ModelAttribute BoardVo vo) {
		//@ModelAttribute 클래스명 객체명 -- Model 타입의 object에 폼값이 자동 설정됨.
		//@RequestParam~~ 이 코드가 실행되는 순간업로드가 된다.
		//https://cafe.naver.com/itdocument/book5115575/8774
		
		//@RequestParam은 select_insert.jsp 에서 file의 name이 attFile이고
		//이걸 가져오는데 
		//List<MultipartFile> 타입의 attFile을 매개변수로 받는 @RequestParam
		
		try {
			System.out.println("id: " + vo.getId());
			System.out.println("subject :" + vo.getSubject());
			
			List<AttVo> attList = new ArrayList<AttVo>();
			
			// 본문을 저장
			boolean flag = service.insertR(vo);
			if(!flag) return "저장 중 오류 발생~~~~~";
			
			
			//---
			
			attList = fileupload(mul);
				//밑에 함수 따로 정의되어 있음.
			
			service.insertAttList(attList);
			
			
		}catch(Exception ex) {
			
		}
		
		return "redirect:/board/board_select";
	}
	
	
	@RequestMapping("/board/board_updateR")
	public String updateR(@RequestParam("attFile") List<MultipartFile> mul,
			@ModelAttribute BoardVo bVo, @ModelAttribute PageVo pVo,
			@RequestParam(name="delFile", required = false) String[] delFile) { 
		//데이터를 넘겨받아서
		String msg = "";
		
		try {
			List<AttVo> attList = fileupload(mul);
			bVo.setAttList(attList);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		boolean flag= service.updateR(bVo, delFile); // 데이터를 넘겨줌
		if( !flag ) msg = "수정 중 오류 발생";
		
		return msg;
	}
	
	
	//file upload 공통부분(insertR, updateR, replR 에서 공통적으로 사용)
	//그래서 아예 메서드를 하나 만들었다.
	
	public List<AttVo> fileupload(List<MultipartFile> mul) throws Exception{
		List<AttVo> attList = new ArrayList<AttVo>();
		
		//파일 업로드 과정
		for(MultipartFile m: mul) {
			if(m.isEmpty()) continue;
			
			System.out.println(m.getOriginalFilename());
				//오리지널 파일명
			UUID uuid = UUID.randomUUID();
				//중복되지 않는 숫자 생성. 랜덤 숫자 
			String oriFile = m.getOriginalFilename();
			String sysFile = "";
			
			File temp = new File(path + oriFile);
			m.transferTo(temp);
			sysFile = (uuid.getLeastSignificantBits()*-1) + "-" + oriFile;
			File f = new File(path + sysFile);
			temp.renameTo(f);
			
			AttVo attVo = new AttVo();
			attVo.setOriFile(oriFile);
			attVo.setSysFile(sysFile);
			
			attList.add(attVo);
			System.out.println(uuid.getLeastSignificantBits());
		}
		
		
		
		return attList;
	}
	
	
	
	@RequestMapping("/board/board_replR")
	public synchronized String replR(@RequestParam("attFile") List<MultipartFile> mul,
			@ModelAttribute BoardVo bVo, @ModelAttribute PageVo pVo) { 
		//데이터를 넘겨받아서
		
		try {
			List<AttVo> attList = new ArrayList<AttVo>();
			attList = fileupload(mul);
			bVo.setAttList(attList);
			
			//본문을 저장
			boolean flag = service.replR(bVo);
			if(!flag) return "저장 중 오류 발생";
		
		}catch(Exception ex) {
			
		}
	
		return "redirect:/board/board_select";
		
		
	}
}
