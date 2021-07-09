# kitri94 - 미니프로젝트

지현, 재영, 경하, 연우, 동주 : 각각의 브런치에 작성한 코드 업로드
test: 작성코드 통합하여 테스트

main : 정상동작한 것들만 넘기기!

Issue 탭에는 진행중에 발생한 문제들 기록!

## pcuser/UserDAO - PCUSER 테이블
> select
- getAllUser() : 전체 유저 정보 받아오기(관리자용) 
  - 리턴값 : 유저목록( ArrayList<UserVO> )  /  null
- checkID(String id) : id 중복체크, 
  - 리턴값 : 1 / 0
- getAuth(String id, String pwd) : 아이디비번확인
  - 리턴값 : 1 / 0
> insert
- insertUser(String id, String pwd, String name) : 회원가입
  - 리턴값 : 1 / 0
>update
- chargeTime(String id, int time) : 시간충전
  - 리턴값 : 1 / 0
