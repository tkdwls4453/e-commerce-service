# Gradle 빌드를 위한 이미지
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app


COPY . .

# Gradle Wrapper에 실행 권한 부여
RUN chmod +x ./gradlew

RUN ./gradlew :config-server:bootJar --no-daemon

# 런타임 이미지를 위한 설정
FROM eclipse-temurin:21-jdk
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/config-server/build/libs/config-server-*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
