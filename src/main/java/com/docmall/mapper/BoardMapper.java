package com.docmall.mapper;

// 컬렉션의 3 인터페이스 : List, Set, Map
import java.util.List;

import com.docmall.domain.BoardVO;
import com.docmall.domain.Criteria;


// 인터페이스(중요)


//- root-context.xml 파일
//	<mybatis-spring:scan base-package="com.demo.mapper" /> 수동으로 추가

public interface BoardMapper {

	// 추상 메소드
	
	// 글쓰기 저장
	// 매서드명과 id="register"는 일치 되어야 한다. <insert id="register"></insert>
	public void register(BoardVO board);
	
	// 게시물 읽기. 1개 BoardVO
	public BoardVO get(Long bno);
	
	// 글수정 폼
	
	// 조회수 증가
	public void readCount(Long bno);
	
	// 글수정하기
	public void modify(BoardVO board);
	
	// 목록. 여러개를 읽기 List<BoardVO>
//	public List<BoardVO> getList();
	
	// 페이징 목록 List<BoardVO> 리턴값 pageNum, amount, type, keyword 사용.
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	// 전체 데이터 갯수(검색 포함) type, keyword 사용.
	public int getTotalCount(Criteria cri);
	
	// 글 삭제
	public void delete(Long bno);
	
}
