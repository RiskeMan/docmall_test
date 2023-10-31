package com.docmall.service;

import org.springframework.stereotype.Service;

import com.docmall.domain.AdminVO;
import com.docmall.mapper.AdminMapper;

import lombok.RequiredArgsConstructor;

@Service // bean이름 등록 : adminServiceImpl
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final AdminMapper adminMapper;

	@Override
	public AdminVO admin_ok(String admin_id) {
		
		return adminMapper.admin_ok(admin_id);
	}

	@Override
	public void adminTimeUpdate(String admin_id) {
		
		adminMapper.adminTimeUpdate(admin_id);
	}
	
	
}
