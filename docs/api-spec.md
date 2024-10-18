# E-commerce API Specification V1

* 응답 형태는 추후 변경 가능성이 높습니다.
* 로그인 기능 완성전에는 유저ID 를 Path Parameter 로 받습니다. 추후 스펙이 변경됩니다.
## User API

### 회원가입

- **POST /api/users**
   - **Request Body**
      - `email` (string, required): 사용자 이메일
      - `password` (string, required): 비밀번호 (8자리 이상, 대문자, 소문자, 숫자, 특수문자 포함)
      - `name` (string, required): 사용자 이름
      - `phone` (string, required): 전화번호
      - `address` (string, required): 주소
   - **Response**
      - `status` (string): 회원가입 요청 성공 여부

### 이메일 인증 요청

- **POST /api/users/{userId}/verification**
   - **Path Parameters**
      - `userId` (string, required): 사용자 ID
   - **Request Body**
      - `email` (string, required): 사용자 이메일
   - **Response**
      - `status` (string): 인증 이메일 발송 성공 여부

### 이메일 인증 확인

- **PUT /api/users/{userId}/verification**
   - **Path Parameters**
      - `userId` (string, required): 사용자 ID
   - **Request Body**
      - `verificationCode` (string, required): 이메일 인증 코드
   - **Response**
      - `status` (string): 인증 성공 여부

### 로그인 (추후 개발 예정)

- **POST /api/users/login**
   - **Request Body**
      - `email` (string, required): 사용자 이메일
      - `password` (string, required): 비밀번호
   - **Response**
      - `token` (string): 인증 토큰

### 로그아웃 (추후 개발 예정)

- **POST /api/users/{userId}/logout**
   - **Path Parameters**
      - `userId` (string, required): 사용자 ID
   - **Response**
      - `status` (string): 로그아웃 성공 여부

## Product API

### 상품 정보 조회

- **GET /api/products**
   - **Query Parameters**
      - `cursor` (int, optional): 페이징을 위한 커서 값 (기본값: 0)
      - `size` (int, optional): 가져올 상품 개수 (기본값: 10)
   - **Response**
      - `products` (array): 상품 정보 리스트
         - `name` (string): 상품 이름
         - `price` (int): 최저가
         - `description` (string): 상품 설명

### 상품 상세 정보 조회

- **GET /api/products/{productId}**
   - **Path Parameters**
      - `productId` (string, required): 상품 ID
   - **Response**
      - `product` (object): 상품 정보
         - `name` (string): 상품 이름
         - `description` (string): 상품 설명
         - `items` (array): 아이템 리스트
            - `price` (int): 가격
            - `color` (string, optional): 색상 (NULL이면 반환하지 않음)
            - `size` (string, optional): 사이즈 (NULL이면 반환하지 않음)
            - `stock` (int, optional): 재고 (10개 이하일 경우만 반환)

### 장바구니에 아이템 추가

- **POST /api/users/{userId}/cart/items**
   - **Path Parameters**
      - `userId` (string, required): 사용자 ID
   - **Request Body**
      - `itemId` (string, required): 아이템 ID
   - **Response**
      - `status` (string): 장바구니 추가 성공 여부

### 장바구니 아이템 조회

- **GET /api/users/{userId}/cart/items**
   - **Path Parameters**
      - `userId` (string, required): 사용자 ID
   - **Response**
      - `items` (array): 장바구니 아이템 리스트
         - `itemId` (string): 아이템 ID
         - `name` (string): 아이템 이름
         - `price` (int): 아이템 가격

## Order API

### 장바구니 주문

- **POST /api/users/{userId}/orders/cart**
   - **Path Parameters**
      - `userId` (string, required): 사용자 ID
   - **Request Body**
      - `items` (array, required): 아이템-수량 리스트
         - `itemId` (string, required): 아이템 ID
         - `quantity` (int, required): 수량
   - **Response**
      - `orderId` (string): 생성된 주문 ID

### 주문 내역 조회

- **GET /api/users/{userId}/orders**
   - **Path Parameters**
      - `userId` (string, required): 사용자 ID
   - **Query Parameters**
      - `cursor` (int, optional): 페이징을 위한 커서 값 (기본값: 0)
      - `size` (int, optional): 가져올 주문 개수 (기본값: 10)
   - **Response**
      - `orders` (array): 주문 내역 리스트
         - `orderId` (string): 주문 ID
         - `status` (string): 주문 상태
         - `createdAt` (string): 주문 생성 일시

### 주문 취소

- **POST /api/orders/{orderId}/cancel**
   - **Path Parameters**
      - `orderId` (string, required): 주문 ID
   - **Response**
      - `status` (string): 주문 취소 성공 여부

### 주문 반품

- **POST /api/orders/{orderId}/return**
   - **Path Parameters**
      - `orderId` (string, required): 주문 ID
   - **Response**
      - `status` (string): 주문 반품 성공 여부

