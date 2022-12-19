package com.cinamo.roll.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cinamo.roll.mybatis.GuestBookMapper;

@Component //알아서 메모리에 올림. GuestbookDao 타입으로 올라감. (네임 아니고)
		   //repository 어노테이션은 하드디스크.
@Transactional 
public class GuestbookDao {
	GPageVo gVo; // 컨트롤러가 값을 가져가게 하기 위해 만듦
	
	@Autowired
	GuestBookMapper mapper;
	
	@Autowired
	PlatformTransactionManager manager;
	TransactionStatus status;
	
	public int getTotSize(GPageVo gVo) {
		int totSize=20;
		totSize = mapper.totSize(gVo);
		return totSize;		
	}

	public List<GuestbookVo> select(GPageVo gVo){//GPageVo gVo 페이징처리에 필요
		List<GuestbookVo> list = null;
		int totSize = getTotSize(gVo);
		gVo.setTotSize(totSize); //이걸 만들어야 startNo등등이 재 설정
		this.gVo = gVo;
		list = mapper.list(gVo);
		
		return list;
		
	}
	
	
	public List<GuestbookVo> select10(){
		List<GuestbookVo> list = null;
		list = mapper.select10();
		
		return list;
		
	}
	
	
	public boolean insert(GuestbookVo vo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.insert(vo);
		
		if(cnt>0) {
			b=true;
			manager.commit(status); //커밋과 롤백이 다름.. 암기..
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		return b;
	}
	
	public boolean delete(GuestbookVo vo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.delete(vo);
		if(cnt>0) {
			b=true;
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		
		return b;
	}
	
	public boolean update(GuestbookVo vo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.update(vo);
		if(cnt>0) {
			b=true;
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		
		return b;
	}
	
	

	
	
	
	public GPageVo getgVo() {
		return gVo;
	}

	public void setgVo(GPageVo gVo) {
		this.gVo = gVo;
	}
}
