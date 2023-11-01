package com.docmall.domain;

import lombok.Getter;
import lombok.ToString;

// 페이징 기능 목적 클래스. [prev] 1 2 3 4 5 6 7 8 9 10 [next]

@Getter
@ToString
public class PageDTO {

	private int startPage; // 각 블럭에서 출력할 시작페이지 번호
	private int endPage; // 각 블럭에서 출력할 종료페이지 번호
	
	private boolean prev, next; // 이전, 다음 표시여부
	
	private int total; // 테이블의 데이터 총 갯수.
	
	private Criteria cri;
	// 1) 페이징 목적 : pageNum, amount
	// 2) 검색 목적 : type, keyword
	
	public PageDTO(Criteria cri, int total) {
		
		/* 
		 * 1) 나머지가 존재하면 페이지 하나가 더 필요하게 된다.
		 * total : 13개
		 * amount : 5개
		 * 필요한 페이지의 수 : 3
		 * 
		 * 2) 나머지가 존재하지 않으면 추가 페이지는 불필요 해 진다.
		 * total : 13개
		 * amount : 5개
		 * 필요한 페이지의 수 : 2
		 */
		
		this.cri = cri;
		// 1) 검색조건이 없을 경우 
		this.total = total;
		
		int pageSize = 10; // 블럭에 보여줄 번호 갯수. 1 2 3 ... 9 10
		int endPageInfo = pageSize - 1; //9

		this.endPage = (int) (Math.ceil(cri.getPageNum() / (double) pageSize)) * pageSize;
		//								사용자가 선택한 페이지 번호/
		// this.endPage = (int) (Math.ceil(10 / 10.0)) * 10;
		
		this.startPage = this.endPage - endPageInfo;
		// this.startPage = 10 - 9;
		
		int readEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));
		// int readEnd = (int) (Math.ceil((13 * 1.0) / 5)) = (int) Math.ceil(2.6) = 3;
		
		if(readEnd <= this.endPage) {
			this.endPage = readEnd;
		}
		
		this.prev = this.startPage > 1;
		
		this.next = this.endPage < readEnd;
		
		// pageNum=1, amount=10, type=null, keyword=null
		// pageSize, startPage, endPage, prev, next
	}
}
