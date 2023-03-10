package com.cinamo.roll.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cinamo.roll.board.AttVo;
import com.cinamo.roll.board.BoardVo;
import com.cinamo.roll.board.PageVo;

//인터페이스 특징.
//1.Mapper로서의 특징
//2.저장성

@Repository
@Mapper
public interface BoardMapper {
	public int totList(PageVo vo);
	public List<BoardVo> select(PageVo vo);
	public List<BoardVo> board10();
	
	public void hitUp(int sno);
	public BoardVo view(int sno);
	public List<AttVo> attList(int sno);
	
	public int replCheck (BoardVo bVo);
	public int delete(BoardVo bVo);
	public List<String> delFileList(int sno);
	public int attDeleteAll(int sno);
	
	public int insertR(BoardVo vo);
	public int insertAttList(List<AttVo> attList);
	
	public int update(BoardVo bVo);
	public int attUpdate(BoardVo bVo);
	public int attDelete(String[] defiles);
	
	
	public void seqUp(BoardVo bVo);
	public int replR(BoardVo bVo);
	
}
//추상메서드