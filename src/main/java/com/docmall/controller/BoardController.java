package com.docmall.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.BoardVO;
import com.docmall.domain.Criteria;
import com.docmall.domain.PageDTO;
import com.docmall.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

// 게시판 기능에 사용할 URL매핑주소를 구성요소로 하는 컨트롤러 기능의 클래스.
// servlet-context.xml파일의 <context:component-scan base-package="com.demo.controller" /> 자동 추가됨.
// BoardController에서는 BoardServiceImpl매소드를 호출한다.

@Log4j 
// @Log4j를 사용할 때는 pum.xml파일에서 <geoupId>Log4j</geoupId>위치에서
// <scope>runtime</scope>를 주석처리 해야 사용할 수 있다.(크러싱)
// log 객체를 지원해줌.
@Controller // servlet-context.xml파일에 views폴더 밑에 board생성.
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

//	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private final BoardService boardService;
	
//	public public BoardController(BoardService boardService) {
//		this.boardService = boardService;
//	}
//	↑ 이 코드를 롬복이 알아서 생성해 준다. @RequiredArgsConstructor

	// 매핑주소 /board/register
	// 글쓰기 폼
	@GetMapping("/register")
	public void register() {
		log.info("called register...");
	}
	
	// http://localhost:9090/board/register
	// 저장버튼 클릭 후 글쓰기 저장. jsp 파일이 필요가 없다.
	@PostMapping("/register")
	public String register(BoardVO board) {
		
		log.info("게시판 입력데이터 : " + board); // board.toString()
		// DB저장
		/*
		 * 1) BoardMapper인터페이스와 BoardMapper.xml작업
		 * 1) BoardService인터페이스와 BoardServiceImpl 클래스
		 */
		
		boardService.register(board);
		
		return "redirect:/board/list";
	}
	
	// 매핑주소 /board/list
	// 목록
	// Model model : jsp파일에 데이터(대부분 DB)를 출력하고자 할 때 사용하는 파라미터
//	@GetMapping("/list")
//	public void list(Model model) {
//		//DB작업
//		// 서비스 메소드 호출
//		List<BoardVO> list = boardService.getList();
//		model.addAttribute("list", list); // 작업 후 jsp작업.
//	}
	
	//스프링이 Criteria클래스의 기본생성자를 호출하여, 객체를 생성해준다.
	// Model model = new Model();		이 두 객체를 자동으로 생성해 준다.
	// Criteria cri = new Criteria()	자바에서 기본으로 지원해주지 않은 생성자도 클래스로 만들어져 있고, 연결한다면 자동 생성 받을 수 있다.
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		
		log.info("list : " + cri);
		// list : Criteria(pageNum=1, amount=10, type=null, keyword=null)
		
		//DB작업
		// 서비스 메소드 호출
		
		// 1) 목록 데이터 - List<BoardVO>
		List<BoardVO> list = boardService.getListWithPaging(cri);
		model.addAttribute("list", list);
		
		// 2) 페이징 기능 및 검색기능. - PageDTO
		int total = boardService.getTotalCount(cri); // 테이블의 데이터 전체 갯수.
		
		log.info("data 총 갯수 : " + total);
		
		PageDTO pageDTO = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageDTO);
		
	}
	
	// 매핑주소 /board/get?bno=게시물번호
	// 게시물 읽기 : 리스트에서 제목을 클릭했을 때, 게시물 번호에 데이터를 출력
	// 수정폼 기능 추가됨.
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		// 목록에서 선택할 게시물 번호
		log.info("게시물 번호 : " + bno);
		
		// 페이징과 검색정보
		log.info("페이징과 검색정보 : " + cri);
		
		BoardVO board = boardService.get(bno);
		model.addAttribute("board", board);
	}
	
	// 매핑주소 /board/modify
	// 수정하기
	// RedirectAttributes : 페이지(주소) 이동시 파라미터 정보를 제공하는 목적으로 사용.
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		log.info("수정 데이터 : " + board);
		log.info("Criteria : " + cri);
		// DB에 들어가는 작업 후 다른 주소로 들어가고 싶다면 String 타입으로 입력해위치 주소를 변경시켜 준다.
		
		boardService.modify(board);
		
		// 검색과 페이징 정보를 이동주소(/board/list) 파라미터로 사용하기 위한 작업.
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
		// list(Criteria cri, Model model) 호출되는 메소드의 정보.
		// 결과 : /board/list?pageNum=값&amount=값&type=값&keyword=값
	}
	
	// 매핑주소 /board/delete
	// 삭제하기
	@GetMapping("/delete")
	public String delete(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
		log.info("삭제할 번호 : " + bno);
		
		// DB작업
		boardService.delete(bno);
		
		// 검색과 페이징 정보를 이동주소(/board/list) 파라미터로 사용하기 위한 작업.
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list" + cri.getListLink();
		// Criteria 클래스의 getListLink()를 만들어 반복해 사용 할 수 있게 하는 형식.
	}
	
}
