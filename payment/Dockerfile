# Gradle 빌드를 위한 이미지
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# 프로젝트의 전체 파일을 복사
COPY . .

# Gradle Wrapper에 실행 권한을 부여합니다.
RUN chmod +x ./gradlew

# Gradle Wrapper를 사용하여 빌드합니다.
RUN ./gradlew :payment:bootJar --no-daemon

# 런타임 이미지를 위한 설정
FROM eclipse-temurin:21-jdk
WORKDIR /app

# build 단계에서 생성된 JAR 파일을 복사합니다.
COPY --from=builder /app/payment/build/libs/payment-*.jar app.jar

# 애플리케이션을 실행합니다.
ENTRYPOINT ["java", "-jar", "app.jar"]
