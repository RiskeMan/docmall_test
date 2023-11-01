package com.docmall.domain;

import lombok.Data;

// Cg_Code, Cg_Name, Cg_Parent_Code
@Data
public class CategoryVO {

	private Integer Cg_Code;		// 모든 카테고리 번호.
	private Integer Cg_Name;		// 상위 카테고리 번호.
	private String Cg_Parent_Code;	// 카테고리명
	
}
