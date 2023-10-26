package com.docmall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docmall.dto.EmailDTO;
import com.docmall.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController // 이 컨트롤러 클래스를 ajax용도로만 사용 할 때에 어노테이션을 적용.
@RequestMapping("/email/*") // 현제는 jsp사용안할 예정
@RequiredArgsConstructor
public class EmailController {
	
	private final EmailService emailService;

	// 메일 인증코드 요청주소
	@GetMapping("/authCode")
	public ResponseEntity<String> authCode(EmailDTO dto, HttpSession session) {
		
		log.info("전자우편 정보 : " + dto);
		
		ResponseEntity<String> entity = null;
		
		// 인증코드 랜덤생성
		String authCode = "";
		for(int i=0; i<6; i++) {
			authCode += String.valueOf((int)(Math.random() * 10));
		}
		
		log.info("인증코드 : " + authCode);
		
		// 사용자에게 메일로 발급해준 인증코드를 입력시 비교할 목적으로 세선형태로 미리 저장해 둔다.
		session.setAttribute("authCode", authCode);
		
		try {
			emailService.sendMail(dto, authCode); // 메일(인증 코드) 보내기
			entity = new ResponseEntity<String>("success", HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
		
		return entity;
	}
	
	// 인증코드 확인 = 서센형태로 저장한 정보를 이용
	@GetMapping("/confirmAuthcode")
	public ResponseEntity<String> confirmAuthcode(String authCode, HttpSession session) {
		
		ResponseEntity<String> entity = null;
		
//		String sauthCode = "";
		if(session.getAttribute("authCode") != null) {
			// 인증 일치 여부
			if(authCode.equals(session.getAttribute("authCode"))) {
				entity = new ResponseEntity<String>("success", HttpStatus.OK);
			}else {
				entity = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		}else {
			// 세션이 소멸되었을 때
			entity = new ResponseEntity<String>("request", HttpStatus.OK);
		}
		
		// 톰캣은 기본 설정으로 세션 정보가 30분만 지속되고, 그 이후엔 null값이 되므로,
		// 서버 세션의 authCode 값이 null인지 확인 작업을 해야 한다.
		
		return entity;
	}
	
}
