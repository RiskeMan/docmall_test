package com.docmall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class EmailDTO {

	private String senderName; // 발신자 이름
	private String sendermail; // 발신자 메일주소
	private String receiverMail; // 수신자 메일주소. 즉 회원메일 주소
	private String subject; // 제목	
	private String message; // 내용
	
	public EmailDTO() {
		this.senderName = "DocMall";
		this.sendermail = "DocMall";
		this.subject = "DocMall 회원가입 메일인증 코드입니다.";
		this.message = "메일인증 코드를 확인하시고, 호원가입시 인증코드 입력란에 입력 바랍니다.";
	}
}
