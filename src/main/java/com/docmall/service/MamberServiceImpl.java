package com.docmall.service;

import org.springframework.stereotype.Service;

import com.docmall.domain.MamberVO;
import com.docmall.mapper.MamberMaper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 구현 클래스에 파이널로 된 필드에 생성자를 만들어주는 역할
public class MamberServiceImpl implements MamberService {

	// 자동주입
	private final MamberMaper mamberMapper;
	// @RequiredArgsConstructor 로 인해 memberMapper를 매개변수로 생성자 메소드 생성됨.

//	private MamberServiceImpl (MamberMaper memberMapper) {
//		this.memberMapper = memberMapper;
//	}
	
	@Override
	public String idCheck(String mbsp_id) {

		return mamberMapper.idCheck(mbsp_id);
	}

	@Override
	public void join(MamberVO vo) {
		
		mamberMapper.join(vo);
	}

	@Override
	public MamberVO login(String mbsp_id) {
		
		return mamberMapper.login(mbsp_id);
	}
	
	
}
