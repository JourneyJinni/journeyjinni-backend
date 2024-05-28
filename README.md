# 여행지니
> 여행을 지니다 + 여행을 추천하다의 두가지 뜻을 포함한 단어로 사진 기반 여행 정보 추천 시스템입니다.


## 기술 스택 

### 백엔드 기술 스택
<img src="https://img.shields.io/badge/Spring-9ACD32?style=flat&logo=Spring&logoColor=white"/> <img src="https://img.shields.io/badge/SpringBoot-9ACD32?style=flat&logo=SpringBoot&logoColor=white"/>
![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=oracle&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white)
![OpenCV](https://img.shields.io/badge/OpenCV-5C3EE8?style=flat-square&logo=OpenCV&logoColor=white)
![Geolocation](https://img.shields.io/badge/Geolocation%20API-00A86B?style=flat-square&logo=google-maps&logoColor=white)

## 팀원

|  [김규형](https://github.com/unggu0704)| [서동인](https://github.com/ssDoi)       |
|----------------------------------------|---------------------------------|
| <img src="https://github.com/JourneyJinni/.github/assets/130115689/2707f0d7-3239-4755-8d34-fa91939a9723" width="120"> | <img src="https://github.com/JourneyJinni/.github/assets/130115689/98d32549-eb84-4055-b9c3-3e1d734659fe" width="120"> |

## 기능

- 고객들을 위한 회원관리(로그인, 회원가입, 정보 변경)를 제공
- 게시판을 통해 회원들끼리 커뮤니티 기능 제공
- 관광지 조회 기능 제공
  - 카테고리/검색어 기반
  - GPS 기반 검색 위치 기반
  - 이미지 기반
    - metadata 기반
    - OpenCV 기반
- 나의 여행 지도 제공
- 여행 사진 게시판 제공

## 프로젝트 구조
![제목 없는 다이어그램 drawio](https://github.com/JourneyJinni/journeyjinni-backend/assets/130115689/b46a6d46-f45b-4a91-ac57-033d2e905137)



## Use case 다이어그램

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/JourneyJinni/journeyjinni-backend/blob/master/resources/DIagram/usecase.drawio.png?raw=true" alt="Use Case Diagram">
  </tr>
</table>

## ERD 다이어 그램
![image](https://github.com/JourneyJinni/journeyjinni-backend/assets/130115689/911d22fc-aafe-4249-9c35-ffdac82f26aa)




## 메인화면
<img src="https://github.com/JourneyJinni/journeyjinni-frontend/blob/master/resources/readmeImg/main.gif?raw=true" alt="login">

## 회원관리 및 게시판

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/JourneyJinni/journeyjinni-frontend/blob/master/resources/readmeImg/login.gif?raw=true" alt="signup">
    </td>
    <td align="center">
      <img src="https://github.com/JourneyJinni/journeyjinni-frontend/blob/master/resources/readmeImg/board.gif?raw=true" alt="login">
    </td>
  </tr>
</table>

- 로그인과 회원가입은 slide를 통해 가능합니다.
- 게시판은 회원만 작성 가능합니다.

## 관광지 조회(검색어 기반)

<img src="https://github.com/JourneyJinni/journeyjinni-frontend/blob/master/resources/readmeImg/search1.gif?raw=true" alt="login">

- 카테고리와 검색어를 기반으로 관광지를 조회합니다.
- 리스트 형태로 출력되며 modal을 이용해 상세조회가 가능합니다.
- `KakaoMap`을 통해 정확한 위치를 확인할 수 있습니다.

## 관광지 조회(내 위치기반)

<img src="https://github.com/JourneyJinni/journeyjinni-frontend/blob/master/resources/readmeImg/search123.gif?raw=true" alt="login">

- `GeoLocation`을 사용하여 GPS 정보를 읽습니다.
- Hoovers Sign 공식에 기반하여 가까운 관광지 리스트를 보여줍니다.

## 관광지 조회(사진 기반)

<img src="https://github.com/JourneyJinni/journeyjinni-frontend/blob/master/resources/readmeImg/search12.gif?raw=true" alt="login">

- `exifr`을 통해 사진의 metedata(경도, 위도)를 획득합니다.
- Hoovers Sign 공식에 기반하여 가까운 관광지 리스트를 보여줍니다.
- `OpenCV`의 유사 이미지 검색 모델을 통해 DB에서 가장 유사한 이미지와 그 위치를 제공합니다.

## 나의 여행 지도 

<img src="https://github.com/JourneyJinni/journeyjinni-frontend/blob/master/resources/readmeImg/trip.gif?raw=true" alt="login">

- 여행과 그에 속한 사진을 기록하고 저장할 수 있습니다.
- 해당 사진은 **나의 여행 지도**에 표시되며 사진을 클릭하여 상세 정보를 기록 할 수 있습니다.

## 여행지도 게시판

<img src="https://github.com/JourneyJinni/journeyjinni-frontend/blob/master/resources/readmeImg/tripcourse.gif?raw=true" alt="login">

- 유저가 만든 사진 기록은 공유가 가능합니다.
- 갤러리 형태로 기록에 대한 사진을 제공합니다.

