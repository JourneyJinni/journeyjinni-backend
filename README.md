# SSAFY11_Algorithm_관통_프로젝트

## 광주_5반_1팀 (서동인, 김규형)

## INFO

- 고객들을 위한 회원관리(로그인, 회원가입, 정보 변경)를 제공
- 여행 계획을 세울 때 각 지역별 유명한 여행지의 정보를 타입(관광지,숙박, 음식점, 축제정보 등..)에 맞게 제공받을 수 있는 서비스로 구현
- 자신의 현재 위치에 기반하여 가장 가까운 위치의 여행지를 제공
- 원하는 여행 정보가 있을 경우 모달로 상세 정보를 제공

## 기술 스택
![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white)
![JSP/Servlet](https://img.shields.io/badge/JSP%2FServlet-007396?style=flat-square&logo=Java&logoColor=white)
![Apache](https://img.shields.io/badge/Apache-D22128?style=flat-square&logo=Apache&logoColor=white)
![HTML](https://img.shields.io/badge/HTML-239120?style=flat-square&logo=HTML5&logoColor=white)
![CSS](https://img.shields.io/badge/CSS-1572B6?style=flat-square&logo=CSS3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=black)




## 요구사항

- [x]  로그인 관리
- [x]  회원 관리
- [x]  지역별 관광지 정보 수집
- [x]  관광지, 숙박, 음식점 조회
- [x]  문화시설, 공연, 여행코스 쇼힝 조회
- [x]  내 위치 기반 여행 정보 제공 
- [x]  관광지 세부정보 제공



## 관광지 조회
![Haversine](https://img.shields.io/badge/Haversine-000000?style=flat-square&logo=JavaScript&logoColor=F7DF1E)
![Merge Sort](https://img.shields.io/badge/Merge_Sort-000000?style=flat-square)

![signup](https://github.com/unggu0704/algorithm-study/assets/130115689/745c5648-939c-4e67-a9af-27ab882cc3a8)


- 관광지 조회는 모두 **attraction-controller** 가 제어합니다.

- `geolocation`를 사용하여 사용자의 위치를 서버에 저장합니다.
- `GET` 요청에 대해서 `action` 태그를 통해 처리할 서비스를 결정합니다.
- 각 서비스는 비즈니스 로직을 처리하고 DAO를 통해 JDBC를 사용해 데이터베이스와 통신합니다.
- DAO에서는 데이터베이스에 저장 되어 있는 데이터를 다양한 조건으로 read 합니다.
    - 사용자가 원하는 정보만을 Read
    - 사용자가 자세히 보기를 원하는 관광지 정보 Read
- 선택한 관광지에 대해서 카카오 맵 api를 활용해 위치 정보를 제공합니다.
- 결과는 서버에 저장되어 있는 사용자 위치를 기반으로 **Haversine** 알고리즘을 사용해 거리를 계산합니다.
- **merge sort**를 사용하여 가까운 순서부터 제공합니다. 

## 메인 화면

![index](https://github.com/unggu0704/nhnacademy-study/assets/130115689/8c687e8c-bbd3-4604-a68f-61013637a953)

- 메인화면은 다른 서비스로 가는 네비게이션 기능을 제공합니다.
- 데이터베이스에 저장되어 있는 관광지 4곳을 랜덤으로 보여줍니다.
- `더보기` 버튼을 통해 modal로 관광지에 대한 자세한 정보를 제공합니다.



## 로그인 및 회원 관리

![login](https://github.com/unggu0704/algorithm-study/assets/130115689/e3be4e51-90d8-47a4-82fc-653c1c004af5)


- 유저관련 CRUD는 모두 **memberController** 가 제어합니다.
- 로그인 성공 시 유저의 id, 이름은 session에 저장됩니다.
- 유저정보 조회 시 수정 버튼을 누르기 전까지는 변경할 수 없습니다.
- 수정 완료 시 로그아웃 처리됩니다.


![signup](https://github.com/unggu0704/algorithm-study/assets/130115689/7bd6a5b0-7ca4-4149-8a28-eba5cf1d4414)

- 회원가입 시 입력한 ID가 DB에 존재하는 지 ajax를 사용하여 검사합니다.
- 검사 결과 아이디 사용여부를 물은 후 완료 또는 취소될 때까지 ID를 수정할 수 없습니다.
- 회원탈퇴 시 해당 유저를 DB에서 삭제합니다.


## 디렉터리 구조
```
│  │  .gitignore
│  │  .project
│  │  EnjoyTrip_Algorithm_최종.zip
│  │  알고리즘 적용기획서.docx
│  │  pom.xml
│  │  README.md
│  ├─src
│  │  └─main
│  │      ├─java
│  │      │  └─com
│  │      │      └─ssafy
│  │      │          ├─mvc
│  │      │          │  ├─controller
│  │      │          │  │      AttractionContoller.class
│  │      │          │  │      MemberController.class
│  │      │          │  │
│  │      │          │  └─model
│  │      │          │      │  AttractionDistance.class
│  │      │          │      │  AttractionDto.class
│  │      │          │      │  MemberDto.class
│  │      │          │      │  NowLocation.class
│  │      │          │      │
│  │      │          │      ├─dao
│  │      │          │      │      AttractionDao.class
│  │      │          │      │      AttractionDaoImpl.class
│  │      │          │      │      MemberDao.class
│  │      │          │      │      MemberDaoImpl.class
│  │      │          │      │
│  │      │          │      └─service
│  │      │          │              AttractionService.class
│  │      │          │              AttractionServiceImpl.class
│  │      │          │              MemberService.class
│  │      │          │              MemberServiceImpl.class
│  │      │          │
│  │      │          └─util
│  │      │              │  DBUtil.class
│  │      │              │
│  │      │              └─Distance
│  │      │                     DistanceSort.class
│  │      │                     DistanceUtil.class
│  │      ├─resource
│  │      │      .gitkeep
│  │      │      memberCreate.sql
│  │      │
│  │      └─webapp
│  │          │  index.jsp
│  │          │
│  │          ├─assets
│  │          │  ├─css
│  │          │  │      main.css
│  │          │  │
│  │          │  ├─img
│  │          │  │      a.png
│  │          │  │      favicon.ico
│  │          │  │      main_beach.jpg
│  │          │  │      my_position.png
│  │          │  │      noimg.jpg
│  │          │  │      noimg.png
│  │          │  │      ssafy_logo.png
│  │          │  │
│  │          │  ├─js
│  │          │  │      Geolocation.js
│  │          │  │      kakaomap.js
│  │          │  │      main.js
│  │          │  │      map.js
│  │          │  │      tour.js
│  │          │  │
│  │          │  └─map
│  │          │          map.html
│  │          │
│  │          ├─common
│  │          │      error.jsp
│  │          │      footer.jsp
│  │          │      head.jsp
│  │          │      modal.jsp
│  │          │      nav.jsp
│  │          │
│  │          ├─META-INF
│  │          │      MANIFEST.MF
│  │          │
│  │          └─view
│  │              ├─member
│  │              │      login.jsp
│  │              │      memberInfo.jsp
│  │              │
│  │              └─tour
│  │                      attraction.jsp
```
