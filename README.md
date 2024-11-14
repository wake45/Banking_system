# 설명
## 프로젝트명 : 8Percent Coding Project
## 사용기술 : Java, JavaScript, SQlite, SpringBoot, JPA
## 기간 : 2024년 10/26 ~ 11/15 

# 주요 설계 고려 사항 

## 이체(출금API + 입금API)
### 1. 이체 화면에 진입시 보유계좌 목록을 조회
### 2. 이체 실행시 첫번째 입금계좌 수취조회(입금자 ID를 가져오기 위해 로직 분리)
### 3. 이체 실행시 두번째 출금계좌 소유자 본인 확인(출금API)
### 4. 이체 실행시 세번째 출금계좌 비밀번호 확인(출금API)
### 5. 이체 실행시 네번째 계좌잔액 확인(출금API)
### 6. 2-5번 사항이 확인이 되면 출금계좌 출금 실행
### 7. 출금 실행 완료 되면 입금계좌 입금 실행
### 8. 이체가 완료되면 거래내역DB에 출금, 입금 내역 적재
### 9. 이체 완료

## 거래내역조회(거래내역API)
### 1. 거래내역조회 화면에 진입시 보유계좌 목록을 조회
### 2. 거래내역조회 시 조회계좌 소유자 본인 확인
### 3. 거래내역조회 실행
#### 3-1. 대량의 데이터 조회시 고려사항
#### 3-2. OrderBy절에 사용되는 컬럼(ex: TransactionDate) & Where절에 자주사용되는 컬럼(ex: AccountNumber) 를 인덱싱 설정
#### 3-3. DB 쿼리문 내에서 페이징 처리를 수행 (ex: MySQL 내 LIMIT, Oracle 내 ROWNUM 등)
### 4. Pageable 기능을 사용해 5개씩 조회가 가능하도록 설정
### 5. 현재페이지 기준으로 앞뒤로 2페이지, 총 5개 페이지블럭이 보여지도록 알고리즘 설계
### 6. 거래내역조회 및 페이징처리 완료

# API Endpoint에 대한 설명

## EtcController - 주요 기능을 제외한 기능 테스트를 위한 나머지 controller
### 1. LoginView(/) : 로그인 화면 호출 & 초기 데이터 세팅(데이터 세팅 후 주석처리)
### 2. MainView(/main) : 로그인시 선택한 ID값 전달(사용자명 조회 및 세션 처리 생략) 후 거래내역조회 & 이체 메뉴선택 화면으로 이동
#### - 전달데이터 : ID(사용자 ID)

### TransFerController - 이체(출금,입금) 기능을 위한 controller
### 1. TransferView(/transferMenu) : 이체 화면 진입 전 보유계좌를 조회 후 이체 화면으로 이동
#### - 전달데이터 : ID(사용자 ID)
### 2. Transfer(/transfer) : 실제 이체 실행(주요 고려사항대로 개발) 및 거래내역 적재(예외발생시 전체 트랜잭션 롤백) 후 이체 결과 화면으로 이동
#### - 전달데이터 : TransferForm객체(출금계좌번호, 출금계좌비밀번호, 이체금액, 입금계좌번호, 적요, ID)

### TransactionsHistoryInquiryController - 거래내역 조회를 위한 controller
### 1. TransactionsView(/transactionsViewMenu) : 거래내역 조회 화면 진입 전 보유계좌를 조회 후 거래내역 조회 화면으로 이동
#### - 전달데이터 : ID(사용자 ID)
### 2. TransactionsHistoryInquiry(/transactionsHistoryInquiry) : 실제 거래내역 조회(주요 고려사항대로 개발) 및 페이징 처리 후 거래내역 조회 결과화면으로 이동
#### - 전달데이터 : TransactionsHistoryInquiryForm객체(조회계좌번호, 조회시작일자, 조회종료일자, 입출금 조회구분, 정렬 방식, ID) , page(현재 페이지)


![API설명서](https://github.com/user-attachments/assets/9e743777-c04d-4631-b928-663814aadc94)


# History
## 10월 26일 
### Git Repository 생성
### SpringBoot Project 생성
### ERD 생성
### DB구축(SQlite)

## 10월 27일
### 설계서 작성(ppt)
### DB수정(적요 항목 추가)

## 10월 28일
### 설계서 수정(ERD)
### DB수정(계좌 비밀번호 항목 추가)

## 11월 4일
### 화면 그리기

## 11월 10일
### DB연결 및 기초 데이터 INSERT

## 11월 12일
### domain & repository 생성

## 11월 13일
### repository 수정 & service 생성
### repository, service, view 수정 & controller 생성 & 의존성 주입

## 11월 14일
### view, controller, service, repository 수정
### 기능 완성(- 페이징 처리 필요)
### 페이징 기능 완성
### 에러 페이지 추가 (에러 메세지 커스터 마이징은 예정)
