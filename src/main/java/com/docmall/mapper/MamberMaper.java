package com.docmall.mapper;

import com.docmall.domain.MamberVO;

public interface MamberMaper {

	String idCheck(String mbsp_id);
	
	void join(MamberVO vo);
	
	MamberVO login(String mbsp_id);
}
