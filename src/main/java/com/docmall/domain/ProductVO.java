package com.docmall.domain;

import lombok.Data;

@Data
public class ProductVO {

	/*
CREATE TABLE PRODUCT_TBL(
        PRO_NUM             NUMBER  CONSTRAINT  PK_PRO_NUM         PRIMARY KEY,
        CG_CODE             NUMBER                  NULL,
        PRO_NAME            VARCHAR2(50)            NOT NULL,       -- 상품명
        PRO_PRICE           NUMBER                  NOT NULL,
        PRO_DISCOUNT        NUMBER                  NOT NULL,       -- 할인율
        
        PRO_PUBLISHER       VARCHAR2(50)            NOT NULL,
		PRO_CONTENT         VARCHAR2(4000)          NOT NULL,       -- 내용이 4000BYTE 초과여부판단? CLOB
        PRO_UP_FOLDER       VARCHAR(50)             NOT NULL,       -- 메인 이미지의 폴더
        PRO_IMG             VARCHAR(50)             NOT NULL,       -- 메인 이미지 날짜폴더경로가 포함하여 파일이름저장
        PRO_AMOUNT          NUMBER                  NOT NULL,
        
        PRO_BUY             CHAR(1)                 NOT NULL,
        PRO_DATE            DATE DEFAULT SYSDATE    NOT NULL,       -- 상품 등록날자
        PRO_UPDATEDATE      DATE DEFAULT SYSDATE    NOT NULL,       -- 상품 수정날자
        FOREIGN KEY(CG_CODE) REFERENCES CATEGORY_TBL(CG_CODE)
)
	 * 
	 * pro_num, cg_code, pro_name, pro_price, pro_discount, pro_publisher, pro_content, pro_up_folder, 
	 * pro_img, pro_amount, pro_buy, pro_date, pro_updatedate
	 */
	
	private Integer pro_num;		// 물품 번호
	private Integer cg_code;		// 상위 카테고리 코드
	private String pro_name;		// 상품명
	private int pro_price;			// 상품 가격
	private int pro_discount;		// 할인율
	
	private String pro_publisher;	// 브랜드
	private String pro_content;		// 상품 설명
	private String pro_up_folder;	// 상품의 이미지 폴더
	private String pro_img;			// 상품의 이미지
	private int pro_amount;			// 수량
	
	private String pro_buy;			// 판매여부
	private Integer pro_date;		// 상품 등록날자
	private Integer pro_updatedate;	// 상품 수정날자
}
