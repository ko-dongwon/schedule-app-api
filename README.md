# 일정 관리 앱 API 
## 프로젝트 개요
이 프로젝트는 일정을 등록·조회·수정·삭제할 수 있는 RESTful API 애플리케이션으로,  
사용자의 편의성을 높이기 위해 검색·페이지 단위 탐색, 안정적인 요청 처리, 언제든 열람 가능한 API 문서 제공 기능을 갖추고 있습니다.

## 주요 기능
- 일정 등록: 원하는 내용을 입력해 새 일정을 추가  
- 일정 목록 확인: 날짜나 작성자 기준으로 일정을 검색
- 일정 상세 확인: 특정 ID의 일정을 자세히 열람
- 일정 변경·삭제: 작성된 일정을 수정하거나 지울 수 있음
- 페이징 지원: 많은 일정도 페이지 단위로 편리하게 탐색

## ERD
![img.png](img.png)
## API 명세서
더 자세한 명세는 [API 문서](https://ko-dongwon.github.io/schedule-app-api/)를 참고해주세요.  

| 구분 | 기능            | Method | URI                             |
| ---- | --------------- | ------ | ------------------------------- |
| 일정 | 일정 조회       | GET    | /schedules/{scheduleId}         |
| 일정 | 일정 전체 조회  | GET    | /schedules                      |
| 일정 | 일정 생성       | POST   | /schedules                      |
| 일정 | 일정 수정       | PATCH  | /schedules/{scheduleId}         |
| 일정 | 일정 삭제       | DELETE | /schedules/{scheduleId}         |

## 기술 스택
<div>   
        <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=openjdk&logoColor=white">
        <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=flat&logo=spring&logoColor=white">
        <img src="https://img.shields.io/badge/mysql-4479A1.svg?style=flat&logo=mysql&logoColor=ffdd54">
        <img src="https://img.shields.io/badge/JDBC-gray?style=flat&logo=&logoColor=ffdd54"> 
</div>