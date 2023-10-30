package com.docmall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.MemberVO;
import com.docmall.dto.loginDTO;
import com.docmall.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member/**")
@Log4j
@RequiredArgsConstructor // final 필드만 매개변수가 있는 생성자를 만들어 주고, 스프링에 의하여 생성자 주입을 받게된다.
public class MemberController {

	// 생성자를 통하여 주입받는 필드에 인터페이스를 사용하는 이유 : 유지보수
	private final MemberService memberService;
	
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
	public ResponseEntity<String> idCheck(String mbsp_id) throws Exception {
		log.info("아이디 : " + mbsp_id);
		
		ResponseEntity<String> entity = null;
		
		// 서비스 메소드 호출구문 작업
		String idUse = "";
		if(memberService.idCheck(mbsp_id) != null) {
			idUse = "no"; // 아이디가 존재하여 사용이 불가능하다.
		}else {
			idUse = "yes"; // 아이디가 존재하지 않아, 사용이 가능하다.
		}
		
		entity = new ResponseEntity<String>(idUse, HttpStatus.OK);
		
		return entity;
	}
	
	// 회원 정보 저장 -> 다른 주소로 이동(redirect)
	@PostMapping("/join")
	public String join(MemberVO vo, RedirectAttributes rttr) throws Exception {
		// RedirectAttributes 리디렉션을 수행할 때 한 컨트롤러 메서드에서 다른 컨트롤러 메서드로 Attributes 를 전달하는데 이용되는 스프링 프레임워크의 인터페이스이다.
		
		
		log.info("회원정보" + vo);
		
		vo.setMbsp_password(passwordEncoder.encode(vo.getMbsp_password()));
		// passwordEncoder.encode() 입력받은 String 타입의 비밀번호를 60byte의 암호화를 시켜준다.
		
		log.info("암호화 비밀번호 : " + vo.getMbsp_password());
		
		// db작업
		memberService.join(vo);
		
		return "redirect:/member/login";
	}
	
	// 로그인 폼 페이지
	@GetMapping("/login")
	public void login() {
		
	}
	
	// 1) 로그인 인증 성공 -> 메인페이지(/) 주소 이동
	// 2) 로그인 인증 실패 -> 로그인 폼 주소로 돌아감.
	// 파라미터 String mbsp_id, String mbsp_password 를 바로 사용도 가능.
	@PostMapping("/login")
	public String login(loginDTO dto, RedirectAttributes rttr, HttpSession session) throws Exception {
		
		log.info("로그인 : " + dto);
		
		MemberVO db_vo = memberService.login(dto.getMbsp_id());
		
		String url = "";
		String msg = "";
		
		// 아이디가 존재하면 true, 존재하지 않으면 false
		if(db_vo != null) {
			// 사용자가 입력한 비밀번호(평문 텍스트)와 DB테이블의 암호화된 비밀번호 일치여부 검사. 
			if(passwordEncoder.matches(dto.getMbsp_password(), db_vo.getMbsp_password())) {
				// 로그인 성공결과로 서버측의 메모리를 사용하는 세션형태의 작업.
				session.setAttribute("loginStatus", db_vo);
				
				// 로그인 시간 업데이트
				memberService.loginTimeUpdate(dto.getMbsp_id());
	            
	            // 로그인 시간 업데이트 후, db_vo를 다시 가져와서 최신 정보를 얻을 수 있음
	            db_vo = memberService.login(dto.getMbsp_id());
	            
	            // 최근 로그인 시간을 세션에 저장
	            session.setAttribute("lastLoginTime", db_vo.getMbsp_lastlogin());

				
				url = "/"; // 메인 페이지 주소
				
			}else {
				url = "/member/login"; // 로그인 폼 주소
				msg = "비밀번호가 일치하지 않습니다.";
				rttr.addFlashAttribute("msg", msg); // 로그인 폼 jsp파일에서 사용할 목적
			}
		}else {
			// 아이디가 일치하지 않을 때.
			url = "/member/login"; // 로그인 폼 주소
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
	
	
	// 회원수정 페이지로 이동전 인증 확인 폼
	@GetMapping("/confirmPw")
	public void confirmPw(HttpSession session) {
	
		log.info("회원 수정 전 confirm확인");


	}

	
	// 회원수정 페이지로 이동전 비밀번호 확인 비밀번호 확인.
	@PostMapping("/confirmPw")
	public String confirmPw(loginDTO dto, RedirectAttributes rttr, HttpSession session) throws Exception {
		
		log.info("회원 수정 전 인증 재확인 : " + dto);
		
		MemberVO db_vo = memberService.login(dto.getMbsp_id());
		
		String url = "";
		String msg = "";
		
		// 아이디가 존재하면 true, 존재하지 않으면 false
		if(db_vo != null) {
			
			// 사용자가 입력한 비밀번호(평문 텍스트)와 DB테이블의 암호화된 비밀번호 일치여부 검사. 
			if(passwordEncoder.matches(dto.getMbsp_password(), db_vo.getMbsp_password())) {
				url = "/member/modify"; // 회원 수정폼 주소
			}else {
				url = "/member/confirmPw"; // 비밀번호 확인(confirm) 주소
				msg = "비밀번호가 일치하지 않습니다.";
				rttr.addFlashAttribute("msg", msg); // 로그인 폼 jsp파일에서 사용할 목적
			}
		}else {
			// 아이디가 일치하지 않을 때.
			url = "/member/login"; // 로그인 폼 주소
			msg = "아이디가 일치하지 않습니다.";
			rttr.addFlashAttribute("msg", msg); // 로그인 폼 jsp파일에서 사용할 목적
		}
		
		return "redirect:" + url;
	}
	
	
	// 회원 수정 폼 : 인증 사용자의 회원 정보를 뷰(View)에 출력.
	@GetMapping("modify")
	public void modify(HttpSession session, Model medel) throws Exception {
		
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();
		
		
		MemberVO db_vo = memberService.login(mbsp_id);
		medel.addAttribute("memberVO", db_vo);
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		log.info("정보수정 : " + vo);
		
		// 로그인 시 인증목적으로 세션작업을 한 정보에서 아이디를 받아온다.
		MemberVO db_vo = (MemberVO) session.getAttribute("loginStatus");
		
		String mbsp_id = db_vo.getMbsp_id();
		
		vo.setMbsp_id(mbsp_id);
		
		memberService.modify(vo);
		
		// header.jsp 에서 전자우편이 수정된 내용으로 반영이 안되기 때문.
		db_vo.setMbsp_email(vo.getMbsp_email()); // 수정한 데이터를 바로 세션에 업로드.
		session.setAttribute("loginStatus", db_vo);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/";
	}
	
	// 마이 페이지
	@GetMapping("/mypage")
	public void mypage(HttpSession session, Model medel) throws Exception {
		
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();
		
		
		MemberVO db_vo = memberService.login(mbsp_id);
		medel.addAttribute("memberVO", db_vo);
	}
	
	// 회원탈퇴 폼
	@GetMapping("/delConfirmPw")
	public void delConfirmPw() {
		
	}
	
	// 회원탈퇴
	@PostMapping("/delete")
	public String delete (loginDTO dto, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		log.info("로그인 : " + dto);
		
		MemberVO db_vo = memberService.login(dto.getMbsp_id());
		
		String url = "";
		String msg = "";
		
		// 아이디가 존재하면 true, 존재하지 않으면 false
		if(db_vo != null) {
			// 사용자가 입력한 비밀번호(평문 텍스트)와 DB테이블의 암호화된 비밀번호 일치여부 검사. 
			if(passwordEncoder.matches(dto.getMbsp_password(), db_vo.getMbsp_password())) {
				url = "/"; // 메인 페이지 주소
				session.invalidate(); // 세션 소멸
				
				// 회원탈퇴 작업.
				memberService.delete(dto.getMbsp_id());
				
			}else {
				url = "/member/delConfirmPw"; // 회원탈퇴 폼 주소
				msg = "비밀번호가 일치하지 않습니다.";
				rttr.addFlashAttribute("msg", msg); // 회원탈퇴 폼 jsp파일에서 사용할 목적
				
				log.info("a");
			}
		}else {
			// 아이디가 일치하지 않을 때.
			url = "/member/delConfirmPw"; // 회원탈퇴 폼 주소
			msg = "아이디가 일치하지 않습니다.";
			rttr.addFlashAttribute("msg", msg); // 회원탈퇴 폼 jsp파일에서 사용할 목적
			
			log.info("b");
		}
		
		return "redirect:" + url;
	}
	
}
