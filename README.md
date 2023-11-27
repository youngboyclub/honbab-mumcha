
<br>
<br>

![header](https://capsule-render.vercel.app/api?type=waving&color=0:8a8a8a,100:0c2340&height=180&section=header&text=혼밥멈춰!!%20&fontSize=90&)

<br>

# Sesac 🌱 Final Project
> Meet Together Eat Together!

## 멤버
_구성원과 프로젝트 구조에 대한 자세한 정보는 [Wiki][wiki]를 참고하세요._
![멤버소개](https://github.com/youngboyclub/honbab-mumcha/assets/75743050/1d98c049-9e19-4318-a241-2936d2bfe5ef)


## Version Guide
### npm version
![Static Badge](https://img.shields.io/badge/npm-10.1.0-orange?style=for-the-badge)
### JDK version
![Static Badge](https://img.shields.io/badge/JAVA-11-brown?style=for-the-badge)
### Spring Boot version
![Static Badge](https://img.shields.io/badge/SpringBoot--Gradle-2.7.17-green?style=for-the-badge)


<!--![](../header.png)-->


<!--목차-->

<br>

## 👨‍👧‍👧 프로젝트 소개

<p align="justify">
혼밥을 방지하고자 만든 밥먹기 미팅 앱입니다.
</p>

<p align="center">

</p>

<br>






## 🔨 기술 스택

| JAVA | Spring boot | React | TailWind 
| :--------: | :--------: | :--------: | :--------: | 
|   <img src="https://images.velog.io/images/cham/post/6097eb91-2d98-4fab-afaa-e3dbfa52456e/java.png" width="150">    |  <img src="https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbanyVC%2FbtqKopot9Qy%2Fd4ENMFhbysKoNy4nt2UKz1%2Fimg.png" width="80">    |  <img src="https://images.velog.io/images/namezin/post/a4780e7b-4d05-4b46-9ece-e745c49b6d58/react.png" width="50"> |  <img src="https://cdn.hashnode.com/res/hashnode/image/upload/v1625321458213/-layBQDv1.jpeg" width="50">

<br>

## 🌫️ 환경 설정
[개발환경 Wiki](https://github.com/youngboyclub/honbab-mumcha/wiki/%EA%B0%9C%EB%B0%9C-%ED%99%98%EA%B2%BD)

### Back
이름 | 버전 | 기능
-- | -- | --
cloud aws | 2.2.6 | 데이터베이스 클라우드 시스템
jpa |   | jpa orm
thymeleaf |   | 뷰단 테스트용
spring web |   | tomcat
websocket |   | 채팅기능 구현
mongodb | 5.0.21 | 채팅기능 구현
validation |   | 백엔드 벨리데이션
mail |   | 회원가입 메일 인증용
redis |   | jwt 토큰 저장
lombok |   | 롬복 어노테이션
h2 |   | 데이터베이스 테스트용
mysql connector j | 5.0.21 | mysql 연결

### Front
이름 | 버전 | 기능
-- | -- | --
ant-design/icons | 5.2.6 | 컴포넌트를 사용하여 아이콘 쉽게 사용
craco/craco |  7.1.0 |  테일윈드 확장
stomp/stompjs | 7.0.0   |  채팅 기능 구현 
axios | 1.6.0 | 비동기 통신  
date-fns | 2.30.0 | 날짜 및 시간 다루기 
http-proxy-middleware | 2.0.6 |  백과 프론트 간 프록시 설정 
react-kakao-maps-sdk |  1.1.24 | 리액트에서 카카오 맵 api를 사용하게 해줌 
swiper | 11.0.3 | 배너 슬라이드를 위한 라이브러리 



### server

**node.js : 21.1.0**

**Tool**

- **figma**
- **notion**
- **obsidian**
- **discord**
- **github wiki**

**형상관리 및 협업**

- **git**
- **github**
- **git flow(forking workflow 사용)**

<br>

## 📑 DB 명세
### USER

key | 컬럼설명 | 컬럼명(영어) | 타입 | NULL | 비고
-- | -- | -- | -- | -- | --
PK | 사용자번호 | USER_ID | BIGINT | NOT NULL |  
  | 생성일 | REG_DATE | DATETIME | NOT NULL |  
  | 아이디 | USER_EMAIL | VARCHAR(255) | NOT NULL |  
  | 비밀번호 | USER_PWD | VARCHAR(255) | NOT NULL |  
  | 이름 | USER_NAME | VARCHAR(255) | NOT NULL |  
  | 생년월일 | BIRTHDAY | DATETIME | NOT NULL |  
  | 휴대폰번호 | PHONE_NUMBER | VARCHAR(255) | NOT NULL |  
  | 거주지역(주소) | ADRESS | VARCHAR(255) | NULL |  
  | 성별 | GENDER | VARCHAR(255) | NOT NULL |  
  | MBTI | MBTI | VARCHAR(32) | NULL |  

[... 더보기](https://github.com/youngboyclub/honbab-mumcha/wiki/DB-%ED%85%8C%EC%9D%B4%EB%B8%94-%EB%AA%85%EC%84%B8%EC%84%9C)

## 📑 API 명세
### Board-Controller

기능 설명 | URL | HTTP Method
-- | -- | --
모든 모집글 조회 | /api/board | GET
모집글 작성 | /api/board/new | POST
모집글 삭제 | /api/board/delete/{id} | DELETE
모집글 수정 | /api/board/edit/{id} | PUT
모집글 상세 페이지 입장 | /api/board/boardDetails/{id} | GET
음식 카테고리 검색 | /api/board/food/{foodCategory} | GET
장소 카테고리 검색 | /api/board/place/{placeCategory} | GET
키워드 검색 | /api/board/findby/{keyword} | GET

[... 더보기](https://github.com/youngboyclub/honbab-mumcha/wiki/API-%EB%AA%85%EC%84%B8%EC%84%9C)

## 🔥 화면
https://github.com/youngboyclub/honbab-mumcha/assets/112153004/b86bea7c-bfc5-4b0f-98a8-c4c107f6f59a

### 회원가입
![회원가입](https://github.com/youngboyclub/honbab-mumcha/assets/114975279/243de224-fb48-4635-a1a1-429212ae0c9e)

### 로그인

https://github.com/youngboyclub/honbab-mumcha/assets/112153004/934ca491-0fc8-4d5d-8690-74b6c1fa23af


### 게시판
![게시판둘러보기](https://github.com/youngboyclub/honbab-mumcha/assets/114975279/df3ce1fe-78ba-4286-b164-d31ee962fe66)

### 게시판 구동
![보드페이지 구동방식](https://github.com/youngboyclub/honbab-mumcha/assets/114975279/2bccfb65-64be-4fca-b9bd-390770570173)

### 게시판 상세페이지
![디테일페이지](https://github.com/youngboyclub/honbab-mumcha/assets/114975279/76d84132-5136-4357-93b7-7634629f5cf1)

### 글쓰기
![글쓰기](https://github.com/youngboyclub/honbab-mumcha/assets/114975279/1ddb3847-de3e-45d3-84f0-c40ac0b0c3c5)

### 찜
![찜](https://github.com/youngboyclub/honbab-mumcha/assets/114975279/44bb422c-48e0-4f85-85a2-059f30e8ed66)

### 검색
![검색](https://github.com/youngboyclub/honbab-mumcha/assets/114975279/112df11b-e6f5-4ed1-ac35-dbfd96532627)

### 마이페이지
![마이페이지](https://github.com/youngboyclub/honbab-mumcha/assets/114975279/8664feaf-b749-48e7-b93b-d93f02b61abc)

<br>

## 🚀 정보
- 청년취업사관학교 새싹 영등포 SW캠퍼스<br>
  - 백엔드 구축을 위한 클라우드 기술 활용 개발자 과정(JAVA 기반)

- Copyright ⓒ 이로운, 김완, 민동찬, 류수환, 김태호 (새싹 백엔드 스쿨 영보이 클럽팀)

- contact : dev.rowoon@gmail.com
<!--이름 – [@트위터 주소](https://twitter.com/dbader_org) – 이메일주소@example.com

XYZ 라이센스를 준수하며 ``LICENSE``에서 자세한 정보를 확인할 수 있습니다.

[https://github.com/yourname/github-link](https://github.com/dbader/)-->


<!-- Markdown link & img dfn's -->
[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://img.shields.io/npm/v/datadog-metrics/10.1.0
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
[wiki]: https://github.com/youngboyclub/honbab-mumcha/wiki

