package com.docmall.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.service.MamberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/mamber/**")
@Log4j
@RequiredArgsConstructor
public class MemberController {

	private final MamberService mamberService;
	
	@GetMapping("/join")
	public void join() {
		
		log.info("called... join");
	}
	
	// 비동기 방식. ajax문법으로 호출
	// ID 중복체크 기능
	// ResponseEntity는 httpentity를 상속받는, 결과 데니터와 HTTP상태코드를 직접 제어할 수 있는 클래스
	// 3가지 구성 요소 - HttpStatus, HttpHeaders, HttpBody
	// ajax기능과 함께 사용
	@GetMapping("/idCheck")
	public ResponseEntity<String> idCheck(String mbsp_id) {
		log.info("아이디 : " + mbsp_id);
		
		ResponseEntity<String> entity = null;
		
		// 서비스 메소드 호출구문 작업
		String idUse = "";
		if(mamberService.idCheck(mbsp_id) != null) {
			idUse = "no"; // 아이디가 존재하여 사용이 불가능하다.
		}else {
			idUse = "yes"; // 아이디가 존재하지 않아, 사용이 가능하다.
		}
		
		entity = new ResponseEntity<String>(idUse, HttpStatus.OK);
		
		return entity;
	}
	
}
