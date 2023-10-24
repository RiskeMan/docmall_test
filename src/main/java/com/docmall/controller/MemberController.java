package com.docmall.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.service.MamberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/mamber/*")
@Log4j
@RequiredArgsConstructor
public class MemberController {

	private MamberService mamberService;
	
	@GetMapping("/join")
	public void join() {
		
		log.info("called... join");
	}
	
	// 비동기 방식. ajax문법으로 호출
	@GetMapping("/idCheck")
	public ResponseEntity<String> idCheck(String mbsp_id) {
		log.info("아이디 : " + mbsp_id);
		
		ResponseEntity<String> entity = null;
		
		// 서비스 메소드 호출구문 작업
		mamberService.idCheck(mbsp_id);
		
		return entity;
	}
	
}
