package com.docmall.service;

import org.springframework.stereotype.Service;

import com.docmall.domain.MemberVO;
import com.docmall.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 구현 클래스에 파이널로 된 필드에 생성자를 만들어주는 역할
public class MemberServiceImpl implements MemberService {

	// 자동주입
	private final MemberMapper memberMapper;
	// @RequiredArgsConstructor 로 인해 memberMapper를 매개변수로 생성자 메소드 생성됨.

//	private MamberServiceImpl (MamberMaper memberMapper) {
//		this.memberMapper = memberMapper;
//	}
	
	@Override
	public String idCheck(String mbsp_id) {

		return memberMapper.idCheck(mbsp_id);
	}

	@Override
	public void join(MemberVO vo) {
		
		memberMapper.join(vo);
	}

	@Override
	public MemberVO login(String mbsp_id) {
		
		return memberMapper.login(mbsp_id);
	}

	@Override
	public void modify(MemberVO vo) {
		
		memberMapper.modify(vo);
	}

	@Override
	public void loginTimeUpdate(String mbsp_id) {
		
		memberMapper.loginTimeUpdate(mbsp_id);
	}

	@Override
	public void delete(String mbsp_id) {

		memberMapper.delete(mbsp_id);
	}
	
	
}
