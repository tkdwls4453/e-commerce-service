# 스냅슈 (SnapShoe)

**인기 한정판 신발을 빠르게 잡을 수 있는 이커머스 플랫폼. 한정판 스니커즈를 놓치고 싶지 않다면, 스냅슈에서 선착순으로 원하는 신발을 스냅하세요!**

### 프로젝트 소개

<aside>

한정된 수량의 신발을 판매를 위한 E-commerce 서비스를 개발하는 프로젝트입니다.

- 프론트 개발은 배제하고 트래픽 처리에 집중하기 위한 Rest API 서버 개발만 진행했습니다.
- 확장성과 유지보수성을 높이기 위해 MSA 와 클린아키텍처를 도입하였습니다.
- 실제 결제 프로세스는 제외하고, 주문 시 재고 차감 및 결제 시나리오 처리에 집중했습니다.
- 핵심 기능에 집중하기 위해 팬매자의 재고 관리 및 상품 등록 기능은 제외했습니다.
</aside>

### 주요 기능

- **이메일 인증 기능**
    - 이메일 인증을 통과한 유저만 정상적으로 서비스를 이용할 수 있습니다.
- **장바구니 기능**
    - 여러 아이템을 모아 한 번에 주문할 수 있습니다.
- **상품 조회 기능**
    - ~~상품을 검색할 수 있습니다.~~
    - 상품의 상세 정보 (사이즈, 색) 를 확인할 수 있습니다.
    - 커서 기반 페이징 처리
- **주문 및 결제 기능**
    - 주문 및 결제 후 알맞게 재고를 감소 시킵니다. (동시성 이슈 고려)
    - 결제 취소 및 실패인 경우 재고를 복구 시킵니다.
- **주문 단계 자동 처리 기능**
    - 주문 상태가 24시간 후에 자동으로 다음 단계로 넘어갑니다.
    - 준비중 → 배송중 → 배송완료
---

### 유저 플로우
 ![Blank diagram](https://github.com/user-attachments/assets/22bb4a65-e98a-421f-929c-50301307aa4d)
 

### ERD (Entity-Relationship Diagram)
![e-commerce](https://github.com/user-attachments/assets/4c440bf9-d1b9-4cf9-a23d-6582151e770b)



### 아키텍처
![ecommerce drawio](https://github.com/user-attachments/assets/bb6e97ff-ecb3-4072-84ec-ba3dac7bfcdd)

- ```API Gateway Server```: 라우팅, JWT 검증
- ```User Service```: 로그인, 장바구니, 이메일 인증 처리
- ```Product Service```: 상품/아이템 조회, 아이템 재고 차감/복구
- ```Order Service```: 주문 조회, 주문 생성 및 상태 관리
- ```Payment Service```: 결제 정보 저장

---
### 기술 스택
- `Spring Boot3` `Java21` `Gradle`
- `Jpa` `Redis` `RabbitMQ` `MySQL`
- `Spring Cloud Gateway` `Eureka` `Feign Client`
- `Postman` `Docker` 

---

### 설치 및 실행 방법
1. 프로젝트를 다운받습니다.
<img width="393" alt="Screenshot 2024-11-14 at 10 06 08 PM" src="https://github.com/user-attachments/assets/da5abb13-0f02-4980-b076-6fbfe277d853">

  
2. 터미널에서 명령어 실행하여 인프라와 서비스들을 실행시킵니다.
   ```
   docker compose up -d
   ```
   
3. [API 문서] 기반으로 api 를 테스트합니다.
   <포스트맨 API 문서 링크 걸 예정>

---

### 버전별 이슈 및 트러블 슈팅
- **v1: 서비스 기획 및 기본 기능 개발**
    - [주문 및 결제 프로세스 설계](https://velog.io/@tkdwls4453/%EC%9D%B4%EC%BB%A4%EB%A8%B8%EC%8A%A4-%EC%84%9C%EB%B9%84%EC%8A%A4%EC%97%90%EC%84%9C-%EC%A3%BC%EB%AC%B8-%EB%B0%8F-%EC%9E%AC%EA%B3%A0-%EA%B4%80%EB%A6%AC-%EC%84%A4%EA%B3%84-%EC%9D%98%EC%82%AC%EA%B2%B0%EC%A0%95)
    - 재고 동시성 이슈
    - 주문 단계 자동 처리 방식 고민

- **v2: 성능 측정 및 개선**
    - 작성 예정
---

### 버전 히스토리

* [x] v1: 서비스 기획 및 기본 기능 개발 (2024.11.12)
* [ ] v2: 성능 측정 및 개선 (진행중)
