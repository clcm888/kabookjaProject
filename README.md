# kabookjaProject (온라인서점사이트 프로젝트)<br>
## 개발환경
- JDK1.8
- TOMCAT 8.5
- eclipse photon버전
- OracleDB
- 인코딩(MS949)
<br>

## OracleDriverManager 설정파일
- kabookjaDBConn.java
<br>

## Oracle테이블생성
```SQL
CREATE TABLE Members				--회원DB
(
memberID varchar2(20) NOT NULL PRIMARY KEY,   	--아이디
memberPW VARCHAR2(20),                          --비밀번호
nickname VARCHAR2(20),                          --닉네임
memberName VARCHAR2(20),                        --이름
memberPhoneNumber VARCHAR2(13),			--전화번호
memberAddress VARCHAR2(100),			--주소
memberBirth DATE,                               --생년월일
memberGrade VARCHAR2(10),                          --회원등급
memberMileage NUMBER(8),                           --마일리지
ebookMembership DATE                         	--ebook멤버쉽구독여부
);

create TABLE ServiceCenter						--고객센터DB
(
helpNum NUMBER(38) UNIQUE,
helpID VARCHAR2(60) NOT NULL, 				--게시글 식별용 넘버링
memberID varchar2(20) NOT NULL, 	--작성자 아이디
helpTitle VARCHAR2(50) NOT NULL,                 			--게시글 제목
helpWritedate DATE NOT NULL,        					--고객센터 질문 등록일
helpContent VARCHAR2(1000) not null, 					--글 내용
helpSortation NUMBER(1)                                 --본문인지댓글인지 구분 0or1
);

CREATE TABLE BOOKS				--책DB
(
bookID VARCHAR2(20) NOT NULL PRIMARY KEY, 	--책 식별번호 1
bookName VARCHAR2(50) NOT NULL,                 --책 이름 2
bookWriter VARCHAR2(50) NOT NULL,		--저자 3
bookPublisher VARCHAR2(50) NOT NULL,		--출판사 4 
bookStock NUMBER(20) NOT NULL,                  --책 현재 수량 5
bookPrice NUMBER(20) NOT NULL,                  --책 금액 6
bookRegion VARCHAR2(10) NOT NULL,               --국외 또는 국내인지 7
bookCategory VARCHAR2(20) NOT NULL,             	--책 종류 8
bookDate date NOT NULL,                         --책 발간일 9
bookimg VARCHAR2(30) NOT NULL			--책 이미지
);

CREATE TABLE SalesPerformance                   --판매실적
(
bookID CONSTRAINT SalesPerformance_bookid REFERENCES BOOKs(bookID) NOT NULL UNIQUE,	--책이름
sales1 number(8),
sales2 number(8),
sales3 number(8),
sales4 number(8),
sales5 number(8),
sales6 number(8),
sales7 number(8),
sales8 number(8),
sales9 number(8),
sales10 number(8),
sales11 number(8),
sales12 number(8)
);

CREATE TABLE CART                  --장바구니DB
(               
cartID varchar2(14) not null,
memberID varchar2(20) NOT NULL,               --장바구니주인 아이디
bookID CONSTRAINT cart_bookid REFERENCES BOOKs(bookID) NOT NULL, --책 식별번호  
cartStock number(20) NOT NULL                     --카트에 담긴 책 수량
);

CREATE TABLE Ebooks							--EbookDB
(
bookID CONSTRAINT Ebooks_bookid REFERENCES BOOKs(bookID) NOT NULL,   	--책이름
ebookPrice NUMBER(15),                                             	--ebook단권가격
ebookContent VARCHAR2(4000)                                       	--ebook내용
);

CREATE TABLE reviews							--리뷰DB
(
memberID CONSTRAINT reviews_memberID REFERENCES Members(memberID) NOT NULL,                                     	--리뷰작성자아이디
bookID VARCHAR2(20) NOT NULL,  	--책이름
reviewDate Date,                                             		--작성일
reviewContent VARCHAR2(1000),                                      	--내용
reviewStarPoint number(1)                                           	--별점
);
CREATE TABLE paymentReport                     
(
memberID varchar2(20) NOT NULL,    --누가 구매한건지
bookIDList varchar2(1000),    --book에 대한 정보를 모두 가져오기 쉽도록
bookNameList varchar2(1000),
bookimgList varchar2(1000),
bookStockList varchar2(100),                            --도서별 수량리스트
bookPriceList varchar2(100),                            --도서별 가격리스트
ShoppingDestination VARCHAR(30) not null,                   --실제 배송지
buyDate Date not null                                 --구매일
);
create table Ebookbuylist
(
memberID varchar2(20),
bookID CONSTRAINT Ebookbuylist_bookID REFERENCES BOOKS(bookID)
);
--회원테스트용
insert into members values('test111','1234','그냥회원','회원1','010-1234-5678','경기도 @@시 @@군 @@ @@','1993-08-08','회원',0,'2000-01-01');
insert into members values('test222','1234','어쩌다회원','회원2','010-5678-1234','경기도 안성시 @@면 @@ @@','1997-01-30','회원',1000,'2021-03-05');
insert into members values('admin111','1234','운영전문가','관리1','010-8888-8888','경기도 @@시 @@구 @@ @@','1993-08-08','관리자',0,'2000-01-01');
--book,ebook,review테스트용
insert into books values('OV19980708NO20210107','마리아에게','Jonatan','좋은글집',100,13000,'해외','소설','1998-07-08','OV19980708NO20210107.jpg'); 
insert into SalesPerformance values('OV19980708NO20210107',3,0,0,0,0,0,0,0,0,0,0,7); 
insert into ebooks values('OV19980708NO20210107',1000,'########내용 내용 내용 내용 내용########'); 
insert into reviews values('test111','OV19980708NO20210107','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV19980708NO20210107','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV19980708NO20210107','2020-07-19','감동 그 자체...',4.5);
insert into books values('OV20071103NO20210107','태양을 볼 수 없는 자들','Mae Martin','글그림타운',80,15000,'해외','소설','2007-11-03','OV20071103NO20210107.jpg'); 
insert into SalesPerformance values('OV20071103NO20210107',17,0,0,0,0,0,0,89,78,120,99,55);
insert into ebooks values('OV20071103NO20210107',1000,'########내용 내용 내용 내용 내용########');
insert into reviews values('test111','OV20071103NO20210107','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20071103NO20210107','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20071103NO20210107','2020-07-19','감동 그 자체...',4.5);
insert into books values('OV20131130NO20210110','다시 그곳에 간다면','Mae Martin','글그림타운',50,15000,'해외','소설','2013-11-30','OV20131130NO20210110.jpg'); 
insert into SalesPerformance values('OV20131130NO20210110',30,0,0,0,0,0,0,300,76,100,80,66);
insert into ebooks values('OV20131130NO20210110',1000,'########내용 내용 내용 내용 내용########'); 
insert into reviews values('test111','OV20131130NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20131130NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20131130NO20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('OV20050307NO20210110','get out','Paul Walker','해바라기책방',80,17000,'해외','소설','2005-03-07','OV20050307NO20210110.jpg'); 
insert into SalesPerformance values('OV20050307NO20210110',28,0,0,0,0,0,0,0,0,0,21,10);
insert into reviews values('test111','OV20050307NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20050307NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20050307NO20210110','2020-07-19','감동 그 자체...',4.5);

insert into books values('OV20201108NO20210110','내가 죽는날','Mae Martin','글그림타운',20,15000,'해외','자기개발','2020-11-08','OV20201108NO20210110.jpg'); 
insert into SalesPerformance values('OV20201108NO20210110',98,0,0,0,0,0,150,270,100,120,170,110);
insert into ebooks values('OV20201108NO20210110',1000,'########내용 내용 내용 내용 내용########'); 
insert into reviews values('test111','OV20201108NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20201108NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20201108NO20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('OV20080327NO20210110','왓 러브 이즈','Arthur Krams','해바라기책방',200,12000,'해외','자기개발','2008-03-27','OV20080327NO20210110.jpg'); 
insert into SalesPerformance values('OV20080327NO20210110',44,0,0,0,0,0,97,78,70,130,60,140);
insert into ebooks values('OV20080327NO20210110',1000,'########내용 내용 내용 내용 내용########'); 
insert into reviews values('test111','OV20080327NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20080327NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20080327NO20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('OV20040415NO20210110','마술같은 하루','Henrik Fisker','별따는공장',300,14000,'해외','자기개발','2004-04-15','OV20040415NO20210110.jpg'); 
insert into SalesPerformance values('OV20040415NO20210110',27,0,0,0,0,0,0,0,0,66,50,47);
insert into ebooks values('OV20040415NO20210110',1000,'########내용 내용 내용 내용 내용########'); 
insert into reviews values('test111','OV20040415NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20040415NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20040415NO20210110','2020-07-19','감동 그 자체...',4.5);

insert into books values('OV20030517NO20210110','far away','kevin Bruyne','별따는공장',200,13000,'해외','에세이','2003-05-17','OV20030517NO2021011.jpg'); 
insert into SalesPerformance values('OV20030517NO20210110',50,0,0,0,0,0,0,0,0,11,19,32);
insert into ebooks values('OV20030517NO20210110',1000,'########내용 내용 내용 내용 내용########');
insert into reviews values('test111','OV20030517NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20030517NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20030517NO20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('OV20171115NO20210110','어쩔 수 없던 것','Mae Martin','글그림타운',300,15000,'해외','에세이','2017-11-15','OV20171115NO20210110.jpg'); 
insert into SalesPerformance values('OV20171115NO20210110',79,0,0,0,0,0,0,0,30,80,60,50);
insert into reviews values('test111','OV20171115NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20171115NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20171115NO20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('OV20210115NO20210110','현실을 살아가는 이들에게','Cole Mecray','현명한책',80,13000,'해외','에세이','2021-01-15','OV20210115NO20210110.jpg'); 
insert into SalesPerformance values('OV20210115NO20210110',450,0,0,0,0,0,0,0,0,0,0,0);
insert into reviews values('test111','OV20210115NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20210115NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20210115NO20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('OV20200217ES20210110','너도 할수있어','Lin Oeding','해바라기책방',100,20000,'해외','에세이','2020-02-17','OV20200217ES20210110.jpg'); 
insert into SalesPerformance values('OV20200217ES20210110',20,0,0,0,0,0,0,0,0,27,18,30);
insert into reviews values('test111','OV20200217ES20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20200217ES20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20200217ES20210110','2020-07-19','감동 그 자체...',4.5);

insert into books values('OV20100916SF20210110','멈춰진 시간','Dominique Loreau','현명한책',180,17000,'해외','SF','2010-09-16','OV20100916SF20210110.jpg'); 
insert into SalesPerformance values('OV20100916SF20210110',180,0,0,0,0,0,0,0,0,0,130,100);
insert into reviews values('test111','OV20100916SF20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20100916SF20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20100916SF20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('OV20100611SF20210110','우주전쟁','Steven Gerrard','현명한책',150,18000,'해외','SF','2010-06-11','OV20100611SF20210110.jpg'); 
insert into SalesPerformance values('OV20100611SF20210110',50,0,0,0,0,0,0,0,92,114,127,130);
insert into reviews values('test111','OV20100611SF20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20100611SF20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20100611SF20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('OV20060717SF20210110','call back','Alex Curran','더 비전',180,20000,'해외','SF','2006-07-17','OV20060717SF20210110.jpg'); 
insert into SalesPerformance values('OV20060717SF20210110',48,0,0,18,33,66,21,31,39,47,68,88);
insert into reviews values('test111','OV20060717SF20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20060717SF20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20060717SF20210110','2020-07-19','감동 그 자체...',4.5);

insert into books values('OV20151030SF20210110','어나더 월드','Dominique Loreau','더 비전',180,18000,'해외','전문서적','2015-10-30','OV20151030SF20210110.jpg'); 
insert into SalesPerformance values('OV20151030SF20210110',120,0,0,0,0,0,0,67,131,190,66,89);
insert into reviews values('test111','OV20151030SF20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20151030SF20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20151030SF20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('OV20180310SF20210110','the room','Dominique Loreau','더 비전',130,18000,'해외','전문서적','2018-03-10','OV20180310SF20210110.jpg'); 
insert into SalesPerformance values('OV20180310SF20210110',70,0,0,0,0,0,0,30,100,61,30,50);
insert into reviews values('test111','OV20180310SF20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20180310SF20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20180310SF20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('OV20110807SF20210110','시그널','Tony Blinken','좋은글집',200,20000,'해외','전문서적','2011-08-07','OV20110807SF20210110.jpg'); 
insert into SalesPerformance values('OV20110807SF20210110',30,0,0,0,0,0,55,150,100,37,54,70);
insert into reviews values('test111','OV20110807SF20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','OV20110807SF20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','OV20110807SF20210110','2020-07-19','감동 그 자체...',4.5);

insert into books values('KO20180930NO20210110','시간관리자의 내일','명현','별따는공장',300,18000,'국내','소설','2018-09-30','KO20180930NO20210110.jpg'); 
insert into SalesPerformance values('KO20180930NO20210110',170,0,0,0,0,0,33,58,27,96,80,100);
insert into reviews values('test111','KO20180930NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','KO20180930NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','KO20180930NO20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('KO20160810NO20210110','풀과 바람이야기','조제','좋은글집',250,16000,'국내','소설','2016-08-10','KO20160810NO20210110.jpg'); 
insert into SalesPerformance values('KO20160810NO20210110',50,0,0,0,0,0,0,0,0,37,60,80);
insert into reviews values('test111','KO20160810NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','KO20160810NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','KO20160810NO20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('KO20200717NO20210110','3년의 밤','서지우','글그림타운',200,17000,'국내','소설','2020-07-17','KO20200717NO20210110.jpg'); 
insert into SalesPerformance values('KO20200717NO20210110',270,0,0,0,0,0,141,177,140,210,180,290);
insert into reviews values('test111','KO20200717NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','KO20200717NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','KO20200717NO20210110','2020-07-19','감동 그 자체...',4.5);

insert into books values('KO20071123NO20210110','사과나무집 아이들','주현종','해바라기책방',180,14000,'국내','에세이','2007-11-23','KO20071123NO20210110.jpg'); 
insert into SalesPerformance values('KO20071123NO20210110',38,0,0,0,0,16,88,17,11,22,31,67);
insert into ebooks values('KO20071123NO20210110',1000,'########내용 내용 내용 내용 내용########');
insert into reviews values('test111','KO20071123NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','KO20071123NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','KO20071123NO20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('KO20030710NO20210110','마술금지','유진','해바라기책방',210,15000,'국내','에세이','2003-07-10','KO20030710NO20210110.png'); 
insert into SalesPerformance values('KO20030710NO20210110',110,0,0,0,0,0,0,22,31,50,22,70);
insert into ebooks values('KO20071123NO20210110',1000,'########내용 내용 내용 내용 내용########');
insert into reviews values('test111','KO20030710NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','KO20030710NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','KO20030710NO20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('KO20130819NO20210110','편한마음으로','주현종','별따는공장',210,15000,'국내','에세이','2013-08-19','KO20130819NO20210110.jpg'); 
insert into SalesPerformance values('KO20130819NO20210110',61,0,0,6,10,22,44,38,31,27,11,37);
insert into reviews values('test111','KO20130819NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','KO20130819NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','KO20130819NO20210110','2020-07-19','감동 그 자체...',4.5);

insert into books values('KO20020312NO20210110','백야','심현욱','현명한책',80,13000,'국내','자기개발','2002-03-12','KO20020312NO20210110.jpg'); 
insert into SalesPerformance values('KO20020312NO20210110',57,3,17,11,9,6,4,22,60,17,21,88);
insert into reviews values('test111','KO20020312NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','KO20020312NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','KO20020312NO20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('KO20200719NO20210110','붉은꽃언덕','조제','좋은글집',170,16000,'국내','자기개발','2020-07-19','KO20200719NO20210110.jpg'); 
insert into SalesPerformance values('KO20200719NO20210110',130,0,9,22,37,63,57,117,96,88,111,101);
insert into ebooks values('KO20200719NO20210110',1000,'########내용 내용 내용 내용 내용########');
insert into reviews values('test111','KO20200719NO20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','KO20200719NO20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','KO20200719NO20210110','2020-07-19','감동 그 자체...',4.5);

insert into books values('KO20180930PR20210110','인정사정볼것없다','조현욱','별따는공장',300,18000,'국내','전문서적','2018-09-30','KO20180930PR20210110.jpg'); 
insert into SalesPerformance values('KO20180930PR20210110',170,0,0,0,0,0,33,58,27,96,80,100);
insert into reviews values('test111','KO20180930PR20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','KO20180930PR20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','KO20180930PR20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('KO20160810PR20210110','소방공무원','조현욱','좋은글집',250,16000,'국내','전문서적','2016-08-10','KO20160810PR20210110.png'); 
insert into SalesPerformance values('KO20160810PR20210110',50,0,0,0,0,0,0,0,0,37,60,80);
insert into reviews values('test111','KO20160810PR20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','KO20160810PR20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','KO20160810PR20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('KO20020312PR20210110','고려','강혁','현명한책',80,13000,'국내','전문서적','2002-03-12','KO20020312PR20210110.jpg'); 
insert into SalesPerformance values('KO20020312PR20210110',57,3,17,11,9,6,4,22,60,17,21,88);
insert into reviews values('test111','KO20020312PR20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','KO20020312PR20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','KO20020312PR20210110','2020-07-19','감동 그 자체...',4.5);

insert into books values('KO20151030SF20210110','우주적관점','김민','더 비전',180,18000,'국내','SF','2015-10-30','KO20151030SF20210110.png'); 
insert into SalesPerformance values('KO20151030SF20210110',120,0,0,0,0,0,0,67,131,190,66,89);
insert into reviews values('test111','KO20151030SF20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','KO20151030SF20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','KO20151030SF20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('KO20180310SF20210110','미래를가지는자','오우제','더 비전',130,18000,'국내','SF','2018-03-10','KO20180310SF20210110.jpg'); 
insert into SalesPerformance values('KO20180310SF20210110',70,0,0,0,0,0,0,30,100,61,30,50);
insert into reviews values('test111','KO20180310SF20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','KO20180310SF20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','KO20180310SF20210110','2020-07-19','감동 그 자체...',4.5);
insert into books values('KO20110807SF20210110','격','주현욱','좋은글집',200,20000,'국내','SF','2011-08-07','KO20110807SF20210110.jpg'); 
insert into SalesPerformance values('KO20110807SF20210110',30,0,0,0,0,0,55,150,100,37,54,70);
insert into reviews values('test111','KO20110807SF20210110','2020-07-19','내용이 환상적이에요',5);
insert into reviews values('test222','KO20110807SF20210110','2020-07-19','좋아요',4.5);
insert into reviews values('admin111','KO20110807SF20210110','2020-07-19','감동 그 자체...',4.5);



--고객센터테스트용
insert into servicecenter values((select nvl(MAX(helpnum)+1,1) from ServiceCenter),'20210109214857test111','test111','책이 손상되서왔어요','20210109','내용',0);
insert into servicecenter values((select nvl(MAX(helpnum)+1,1) from ServiceCenter),'20210109214859test222','test222','표지색이 이상하네여','20210109','내용',0);
insert into servicecenter values((select nvl(MAX(helpnum)+1,1) from ServiceCenter),'20210110214859test111','test111','너무 감동적인책 감사합니다','20210109','내용',0);
insert into servicecenter values((select nvl(MAX(helpnum)+1,1) from ServiceCenter),'20210111214857test222','test222','배송이 늦어요','20210111','내용',0);
insert into servicecenter values(null,'20210109214857test111','관리자','RE : 책이 손상되서왔어요','20210111','내용',1);
insert into servicecenter values(null,'20210109214859test222','관리자','RE : 표지색이 이상하네여','20210109','내용',1);
insert into servicecenter values(null,'20210110214859test111','관리자','RE : 너무 감동적인책 감사합니다','20210111','내용',1);
insert into servicecenter values(null,'20210111214857test222','관리자','RE : 배송이 늦어요','20210111','내용',1);

--cart테스트용
insert into cart values('20210128123541','test111','OV19980708NO20210107',1);
insert into cart values(TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS'),'test111','KO20180930NO20210110',1);

--paymentReport테스트용
insert into paymentReport values('test111','OV19980708NO20210107,OV20100916SF20210110','마리아에게,멈춰진시간','OV19980708NO20210107.jpg,OV20100916SF20210110.jpg','1,2','13000,17000','경기도','20210111');
insert into paymentReport values('test111','OV19980708NO20210107,OV20100916SF20210110,KO20160810NO20210110','마리아에게,멈춰진시간,풀과 바람이야기','OV19980708NO20210107.jpg,OV20100916SF20210110.jpg,KO20160810NO20210110.jpg','1,2,1','13000,17000,16000','경기도',SYSDATE);
commit;
```
