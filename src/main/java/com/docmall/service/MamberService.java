package com.docmall.service;

import com.docmall.domain.MamberVO;

public interface MamberService {

	String idCheck(String mbsp_id);
	
	void join(MamberVO vo);
	
	MamberVO login(String mbsp_id);
}
