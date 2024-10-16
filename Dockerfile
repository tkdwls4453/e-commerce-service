# Gradle 빌드를 위한 이미지
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app
COPY . .
RUN ./gradlew build -x test --stacktrace

FROM eclipse-temurin:21-jdk
COPY --from=builder /app/build/libs/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
