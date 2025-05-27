# Stage 1: build com Maven + JDK 17
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/embalagem-service-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
