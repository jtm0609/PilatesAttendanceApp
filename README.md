## 서비스 내용
필라테스 센터의 회원 출석과 마일리지 적립을 간편하게 도와주는 Android 애플리케이션입니다.

## 개발 목적
- Clean Architecture 및 Jetpack Compose 기반 아키텍처 설계 경험 강화
- MVI 패턴을 통한 상태 관리 구조 학습
- 실무에 가까운 구조로 유지보수와 확장성을 고려한 개발 연습

## 주요 기능
- **회원 출석 체크**
  - 출석 버튼 클릭으로 간편한 출결 등록

- **마일리지 적립 시스템**
  - 출석 시 마일리지 자동 적립

- **회원 등록 및 관리**
  - 관리자가 신규 회원을 등록하고 정보를 수정/삭제할 수 있음

- **출석 현황 및 회원 목록 조회**
  - 회원별 출석 내역 및 마일리지 현황 확인

## 기술 스택
- Kotlin
- Clean Architecture
- MVI
- Compose
- RxJava
- Hilt
- LiveData
- Room
- Navigation

## 시스템 아키텍처
### MVI 패턴
본 프로젝트는 **MVI(Model–View–Intent)** 패턴을 적용하여 단방향 데이터 흐름을 기반으로 한 **예측 가능한 상태 관리**를 구현했습니다.   
MVI는 다음과 같은 흐름으로 동작합니다.
1. **View**: UI 요소에서 사용자 이벤트(Intent)를 발생시킵니다.
2. **Intent**: 사용자의 액션을 표현합니다.
3. **Model(ViewModel)**: 비즈니스 로직을 처리하고, 결과 데이터를 State로 만듭니다.
4. **State**: UI가 구독하는 상태 값입니다. View는 상태에 따라 자동으로 갱신됩니다

![image](https://github.com/user-attachments/assets/da59f1d3-37eb-4a77-b78b-0076cf1650ce)

### Clean Architecture
본 프로젝트는 **Clean Architecture 원칙**을 기반으로, 관심사 분리(Separation of Concerns)를 철저히 지켜 설계되었습니다.
아키텍처는 크게 아래와 같은 네 가지 모듈로 구성됩니다:
- **Presentation Module**
  - UI를 담당 (Jetpack Compose 사용)
  - 사용자 이벤트 처리 및 상태 렌더링
  - ViewModel과 상호작용
- **Domain Module**
  - 순수한 비즈니스 로직
  - UseCase를 통해 앱의 핵심 규칙 정의
- **Data Module**
  - Repository 구현체
  - Room 기반 로컬 데이터베이스와의 연결 및 데이터 처리 담당
- **App Module**
  - 앱의 진입점 역할 수행 (MainActivity, Application)
  - DI 설정 (Hilt를 통한 의존성 주입 설정 및 초기화)
  - 모듈 간 연결 및 네비게이션 흐름 관리

![image](https://github.com/user-attachments/assets/3b411cfb-b185-48a9-8092-3680c9c63cba) 

## 스크린 샷
<p align="center">
  <img src="https://github.com/user-attachments/assets/cca4337b-d77c-45a2-87fc-3ab52142ce51" width="49%" />
  <img src="https://github.com/user-attachments/assets/4c4a8752-3413-4dbb-8297-6ab721b647d0" width="49%" /> 
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/e2f6fde8-4f9e-432a-bf36-5058315bbdb5" width="49%" />
  <img src="https://github.com/user-attachments/assets/a94ee53a-23a4-44d3-9762-a98c69574a7b" width="49%" /> 
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/9b71a8d3-753d-4161-9d75-5ee213afb42c" width="49%" />
  <img src="https://github.com/user-attachments/assets/23796b8e-27e8-41cc-a4d2-c98d108d47f7" width="49%" /> 
</p>

## 기타
본 프로젝트는 1인 사이드 프로젝트로, UI/UX 설계부터 구조 설계, 기능 구현까지 전 과정을 직접 수행하였습니다.  
기술적인 고민과 학습하면서 배웠던 내용들은 기술 블로그에 정리해두었습니다.  
👉 [MVI 패턴이란?](https://jtm0609.tistory.com/295)   
👉 [Compose에 대한 이해](https://jtm0609.tistory.com/category/Android/Compose)   
👉 [Clean Architecture 개념](https://jtm0609.tistory.com/6)   
👉 [Clean Architecture vs Google의 앱 아키텍처](https://jtm0609.tistory.com/325)   
