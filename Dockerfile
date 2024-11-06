# 빌드 단계
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# 프로젝트 파일 복사 및 종속성 다운로드
COPY pom.xml .
RUN mvn dependency:go-offline

# 애플리케이션 소스 복사 및 빌드
COPY src ./src
RUN mvn clean package -DskipTests

# 실행 단계
FROM openjdk:17-jdk-slim
WORKDIR /app

# 빌드 단계에서 생성된 JAR 파일 복사
COPY --from=build /app/target/EnjoyTrip_SpringBoot-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
