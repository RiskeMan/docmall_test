package com.docmall.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class MemberVO {

/*
 * 주로 사용되는 기능
 * - 회원가입, 회원 수정 폼, 회원 수정 ....
 * - db회원 테이블에서 정보를 읽어올 때
 */
	
/*
CREATE TABLE MBSP_TBL(

        MBSP_ID             VARCHAR2(15),                           -- ID. 프라이멀키

        MBSP_NAME           VARCHAR2(30)            NOT NULL,       -- 이름

        MBSP_EMAIL          VARCHAR2(50)            NOT NULL,       -- 이메일

        MBSP_PASSWORD       CHAR(60)               NOT NULL,        -- 비밀번호 암호화 처리.

        MBSP_ZIPCODE        CHAR(5)                 NOT NULL,       -- 우편번호

        MBSP_ADDR           VARCHAR2(100)            NOT NULL,      -- 주소

        MBSP_DEADDR         VARCHAR2(100)            NOT NULL,      -- 상세주소

        MBSP_PHONE          VARCHAR2(15)            NOT NULL,       -- 전화번호(폰 번호)

        MBSP_POINT          NUMBER DEFAULT 0        NOT NULL,       -- 포인트 점수

        MBSP_LASTLOGIN      DATE DEFAULT SYSDATE    NOT NULL,       -- 최종 로그인 날짜

        MBSP_DATESUB        DATE DEFAULT SYSDATE    NOT NULL,       -- 회원 가입 일자

        MBSP_UPDATEDATE     DATE DEFAULT SYSDATE    NOT NULL        -- 정보 갱신(수정) 날짜

);
                                                                    -- PK_MBSP_ID 프라이멀키 명.
 */
	
	// 맴버필드 작업
	
	private String mbsp_id;			// ID. 프라이멀키
	private String mbsp_name;		// 이름
	private String mbsp_email;		// 이메일
	private String mbsp_password;	// 비밀번호. 암호화 처리.
	
	private String mbsp_zipcode;	// 우편번호
	private String mbsp_addr;		// 주소
	private String mbsp_deaddr;		// 상세주소
	private String mbsp_phone;		// 전화번호(폰 번호)
	
	private int mbsp_point;			// 포인트 점수
	
	private Date mbsp_lastlogin;	// 최종 로그인 날짜
	private Date mbsp_datesub;		// 회원 가입 일자
	private Date mbsp_updatedate;	// 정보 갱신(수정) 날짜
	
}
