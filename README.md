## 영화 리뷰 토이 프로젝트

------

### 목표
- DDD Layered Architecture를 기반으로 한 애플리케이션 구조 설계
- Spring Boot와 JPA를 활용한 백엔드 개발
- MongoDB를 데이터베이스로 사용하는 환경 구성 및 사용법 익히기
- 클린 코드 원칙을 적용하여 유지 보수성과 가독성이 높은 코드 작성

### 프로젝트 설계
#### 디렉토리 구조
```markdown
com.moviereview
├── application
│   ├── dto
├── domain
│   ├── movie
│   │   ├── model
│   │   ├── repository
│   │   ├── service
│   ├── review
│   │   ├── model
│   │   ├── repository
│   │   ├── service
│   └── user
│       ├── model
│       ├── repository
│       ├── service
├── infrastructure
│   ├── repository
│   └── configuration
└── presentation
│   └── controller
└──common
    └── exception
```
- 레이어 설명
  - Presentation Layer: 사용자 인터페이스, API 엔드포인트 등을 포함합니다.
  - Application Layer: 비즈니스 로직을 포함하지 않고, 애플리케이션의 흐름을 관리합니다.
  - Domain Layer: 순수한 도메인 모델과 비즈니스 로직을 포함합니다.
  - Infrastructure Layer: 데이터베이스, 메시징 시스템 등 외부 시스템과의 통신을 처리합니다.
