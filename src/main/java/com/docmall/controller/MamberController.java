package com.docmall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.MamberVO;
import com.docmall.dto.loginDTO;
import com.docmall.service.MamberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/mamber/**")
@Log4j
@RequiredArgsConstructor // final 필드만 매개변수가 있는 생성자를 만들어 주고, 스프링에 의하여 생성자 주입을 받게된다.
public class MamberController {

	// 생성자를 통하여 주입받는 필드에 인터페이스를 사용하는 이유 : 유지보수
	private final MamberService mamberService;
	
	private final PasswordEncoder passwordEncoder;
	
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
	
	// 회원 정보 저장 -> 다른 주소로 이동(redirect)
	@PostMapping("/join")
	public String join(MamberVO vo, RedirectAttributes rttr) {
		// RedirectAttributes 리디렉션을 수행할 때 한 컨트롤러 메서드에서 다른 컨트롤러 메서드로 Attributes 를 전달하는데 이용되는 스프링 프레임워크의 인터페이스이다.
		
		
		log.info("회원정보" + vo);
		
		vo.setMbsp_password(passwordEncoder.encode(vo.getMbsp_password()));
		// passwordEncoder.encode() 입력받은 String 타입의 비밀번호를 60byte의 암호화를 시켜준다.
		
		log.info("암호화 비밀번호 : " + vo.getMbsp_password());
		
		// db작업
		mamberService.join(vo);
		
		return "redirect:/mamber/login";
	}
	
	// 로그인 폼 페이지
	@GetMapping("/login")
	public void login() {
		
	}
	
	// 1) 로그인 인증 성공 -> 메인페이지(/) 주소 이동
	// 2) 로그인 인증 실패 -> 로그인 폼 주소로 돌아감.
	// 파라미터 String mbsp_id, String mbsp_password 를 바로 사용도 가능.
	@PostMapping("/login")
	public String login(loginDTO dto, RedirectAttributes rttr, HttpSession session) {
		
		log.info("로그인 : " + dto);
		
		MamberVO db_vo = mamberService.login(dto.getMbsp_id());
		
		String url = "";
		String msg = "";
		
		// 아이디가 존재하면 true, 존재하지 않으면 false
		if(db_vo != null) {
			// 사용자가 입력한 비밀번호(평문 텍스트)와 DB테이블의 암호화된 비밀번호 일치여부 검사. 
			if(passwordEncoder.matches(dto.getMbsp_password(), db_vo.getMbsp_password())) {
				// 로그인 성공결과로 서버측의 메모리를 사용하는 세션형태의 작업.
				session.setAttribute("loginStatus", db_vo);
				url = "/"; // 메인 페이지 주소
			}else {
				url = "/mamber/login"; // 로그인 폼 주소
				msg = "비밀번호가 일치하지 않습니다.";
				rttr.addFlashAttribute("msg", msg); // 로그인 폼 jsp파일에서 사용할 목적
			}
		}else {
			// 아이디가 일치하지 않을 때.
			url = "/mamber/login"; // 로그인 폼 주소
			msg = "아이디가 일치하지 않습니다.";
			rttr.addFlashAttribute("msg", msg); // 로그인 폼 jsp파일에서 사용할 목적
		}
		
		return "redirect:" + url;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
}
