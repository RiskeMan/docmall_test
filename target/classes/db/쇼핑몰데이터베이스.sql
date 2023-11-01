/*
DROP TABLE CATEGORY_TBL;
CREATE TABLE CATEGORY_TBL(
        CG_CODE            NUMBER    PRIMARY KEY,    -- 카테고리 코드
        CG_PARENT_CODE         NUMBER    NULL,           -- 상위카테고리 코드
        CG_NAME            VARCHAR2(50)    NOT NULL,
        FOREIGN KEY(CG_PARENT_CODE) REFERENCES CATEGORY_TBL(CG_CODE)
);

SELECT * FROM CATEGORY_TBL;

-- / -> /

-- 1차 카테고리 : TOP(1) PANTS(2) SHIRTS(3) OUTER(4) SHOES(5) BAG(6) ACC(7)
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME) 
    VALUES (1,NULL,'TOP');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (2,NULL,'PANTS');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (3,NULL,'SHIRTS');    
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (4,NULL,'OUTER');        
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (5,NULL,'SHOES');    
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (6,NULL,'BAG');    
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (7,NULL,'ACC');    

-- 1차카테고리 TOP : 1
-- 2차 카테고리 : 긴팔티 니트 맨투맨/후드티 프린팅티 나시 반팔티/7부티
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (8,1,'긴팔티');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
VALUES (9,1,'니트');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
VALUES (10,1,'맨투맨&#38;후드티');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
VALUES (11,1,'프린팅티');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
VALUES (12,1,'나시');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
VALUES (13,1,'반팔티&#38;7부티');

-- 1차카테고리 PANTS : 2
-- 2차카테고리 : 밴딩팬츠 청바지 슬랙스 면바지 반바지
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (14,2,'밴딩팬츠');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (15,2,'청바지');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (16,2,'슬랙스');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (17,2,'면바지');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (18,2,'반바지');
    
-- 1차카테고리 SHIRTS : 3
-- 2차카테고리 : 헨리넥/차이나 베이직 체크/패턴 청남방 스트라이프 

INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (19,3,'헨리넥&#38;차이나');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (20,3,'베이직');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (21,3,'체크&#38;패턴');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (22,3,'청남방');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (23,3,'스트라이프'); 
    
    
-- 1차카테고리 OUTER : 4
-- 2차카테고리 : 패딩 코트 수트/블레이져 자켓 블루종/MA-1 가디건/조끼 후드/집업

INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (24,4,'패딩');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (25,4,'코트');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (26,4,'수트&#38;블레이져');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (27,4,'자켓');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (28,4,'블루종&#38;MA-1');     
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (29,4,'가디건&#38;조끼');     
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (30,4,'후드&#38;집업');  
    
-- 1차카테고리 SHOES : 5
-- 2차카테고리 : 스니커즈 로퍼/구두 키높이신발/깔창 슬리퍼/쪼리/샌들
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (31,5,'스니커즈');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (32,5,'로퍼&#38;구두');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (33,5,'키높이신발&#38;깔창');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (34,5,'슬리퍼&#38;쪼리/샌들');

-- 1차카테고리 BAG : 6
-- 2차카테고리 : 백팩 토트/숄더백 크로스백 클러치
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (35,6,'백팩'); 
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (36,6,'토트/숄더백');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (37,6,'크로스백');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (38,6,'클러치');    
-- 1차카테고리 ACC : 7
-- 2차카테고리 : 양말/넥타이 모자 머플러/장갑 아이웨어 벨트/시계 기타
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (39,7,'양말/넥타이');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (40,7,'모자');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (41,7,'머플러&#38;장갑');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (42,7,'아이웨어');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (43,7,'벨트&#38;시계');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (44,7,'기타');
*/