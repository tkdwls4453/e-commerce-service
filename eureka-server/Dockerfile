# Gradle 빌드를 위한 이미지
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# eureka-server 관련 파일 복사
COPY . .

# Gradle Wrapper에 실행 권한 부여
RUN chmod +x ./gradlew

# Gradle 캐시를 초기화하고 Eureka Server 모듈 빌드
RUN ./gradlew :eureka-server:bootJar --no-daemon

# 런타임 이미지를 위한 설정
FROM eclipse-temurin:21-jdk
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/eureka-server/build/libs/eureka-server-*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
