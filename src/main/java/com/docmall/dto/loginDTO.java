package com.docmall.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class loginDTO {

	private String mbsp_id;			// ID. 프라이멀키
	private String mbsp_password;	// 비밀번호. 암호화 처리.

	
}
