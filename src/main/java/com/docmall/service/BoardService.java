package com.docmall.service;

import java.util.List;

import com.docmall.domain.BoardVO;
import com.docmall.domain.Criteria;



// 인터페이스는 @Service 애노테이션을 사용하지 않음.

public interface BoardService {

	public void register(BoardVO board);
	
	public BoardVO get(Long bno);
	
	public void modify(BoardVO board);
	
//	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public int getTotalCount(Criteria cri);
	
	public void delete(Long bno);
	
	
}
